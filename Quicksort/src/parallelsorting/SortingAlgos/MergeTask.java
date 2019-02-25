/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelsorting.SortingAlgos;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
/**
 *
 * @author Jacob
 */
public class MergeTask extends RecursiveAction implements SortingStrategy {

    private int threshold = 2100;
    private static final int MAXITER = 15;
    private static final int STARTITER = 5;

    private final float[] arr;
    private final int low, high;

    public MergeTask(float[] array, int start, int stop) {
        this.arr = array;
        this.low = start;
        this.high = stop;
    }

    public MergeTask(float[] array, int start, int stop, int threshold) {
        this.arr = array;
        this.low = start;
        this.high = stop;
        this.threshold = threshold;
    }

    @Override
    public void messure(int cores) {
        System.out.println("Mergesort");
        System.out.println("time cores");
        for (int i = 1; i <= MAXITER; i++) {
            System.gc();
            ForkJoinPool pool = new ForkJoinPool(cores);
            MergeTask rootTask = new MergeTask((float[]) arr.clone(), 0, arr.length - 1);

            long startTime = System.nanoTime();
            pool.invoke(rootTask);
            long endTime = System.nanoTime();

            long elapsedTime = endTime - startTime;
            if (i >= STARTITER) {
                System.out.println(elapsedTime + " " + cores);
            }

            pool.shutdown();
            System.gc();
        }
    }

    @Override
    public int findOptimalThreshold() {
        int currMin = Integer.MAX_VALUE;
        long minVal = Long.MAX_VALUE;
        long currTotal = 0;
        int totalRuns = MAXITER - STARTITER;
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("MergeSort threshold");
        System.out.println("average,threshold");

        for (int i = 100; i <= 100000; i += 1000) {
            for (int j = 1; j <= MAXITER; j++) {
                System.gc();
                ForkJoinPool pool = new ForkJoinPool(cores);
                MergeTask rootTask = new MergeTask((float[]) arr.clone(), 0, arr.length - 1, i);

                long startTime = System.nanoTime();
                pool.invoke(rootTask);
                long endTime = System.nanoTime();

                long elapsedTime = endTime - startTime;
                if (j >= STARTITER) {
                    currTotal += elapsedTime;
                }

                pool.shutdown();
                System.gc();
            }
            
            long avg = currTotal / totalRuns;
            
            System.out.println(avg +"," + i);
       //     System.out.println("avg + ", min: " + minVal + ", i:" + i + ", currmin: " + currMin);
            if (avg < minVal) {
                minVal = avg;
                currMin = i;
            }
            currTotal = 0;
        }

        return currMin;
    }

    @Override
    public void sort() {
        this.compute();
    }

    @Override
    protected void compute() {

        if (low < high) {
            int size = high - low;
            if (size < threshold) {
                //Arrays.parallelSort(array, start, stop);
                //insertionsort();
                Arrays.sort(arr, low, high);
            } else {
                int m = (low + high) / 2;
                invokeAll(new MergeTask(arr, low, m),
                        new MergeTask(arr, m + 1, high));

                merge(m);
            }
        }
    }

    private void insertionsort() {
        for (int i = low + 1; i <= high; i++) {
            float key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }

            arr[j + 1] = key;
        }
    }

    private void merge(int m) {
        float[] a = Arrays.copyOfRange(arr, low, m + 1);
        float[] b = Arrays.copyOfRange(arr, m + 1, high + 1);
        int indexa = 0, indexb = 0, indexc = low;

        while (indexa < a.length && indexb < b.length) {
            if (a[indexa] <= b[indexb]) {
                arr[indexc++] = a[indexa++];
            } else {
                arr[indexc++] = b[indexb++];
            }
        }
        while (indexa < a.length) {
            arr[indexc++] = a[indexa++];
        }
        while (indexb < b.length) {
            arr[indexc++] = b[indexb++];
        }
    }

    @Override
    public boolean isSorted() {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] < arr[i]) {
                return false;
            }
        }

        return true;
    }
}
