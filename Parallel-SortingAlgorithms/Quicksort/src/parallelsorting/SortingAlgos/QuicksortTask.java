/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelsorting.SortingAlgos;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import static java.util.concurrent.ForkJoinTask.invokeAll;
import java.util.concurrent.RecursiveAction;

/**
 *
 * @author Jacob
 */
//sorting inline, do not need to return value
public class QuicksortTask extends RecursiveAction implements SortingStrategy {

    private int threshold = 21100;
    private float[] arr;
    private final int low, high;

    public QuicksortTask(float[] arr, int low, int high) {
        this.low = low;
        this.high = high;
        this.arr = arr;
    }

    public QuicksortTask(float[] arr, int low, int high, int threshold) {
        this.low = low;
        this.high = high;
        this.arr = arr;
        this.threshold = threshold;
    }

    @Override
    public long findOptimalThreshold(int cores, int i) {
        System.out.println("Quicksort threshold");
        System.out.println("average,threshold");

        ForkJoinPool pool = new ForkJoinPool(cores);
        QuicksortTask rootTask = new QuicksortTask((float[]) arr.clone(), 0, arr.length - 1, i);

        long startTime = System.nanoTime();
        pool.invoke(rootTask);
        long endTime = System.nanoTime();

        pool.shutdown();
        return endTime - startTime;
    }

    @Override
    public void messure(int cores) {
        ForkJoinPool pool = new ForkJoinPool(cores);
        QuicksortTask rootTask = new QuicksortTask((float[]) arr.clone(), 0, arr.length - 1);
        pool.invoke(rootTask);
        pool.shutdown();
    }

    @Override
    public void sort() {
        this.compute();
    }

    @Override
    protected void compute() {
        if (high - low < threshold) {
            //pi is partitioning index
            int pi = partition_plain(arr, low, high);

            invokeAll(new QuicksortTask(arr, low, pi - 1),
                    new QuicksortTask(arr, pi + 1, high));
        } else {
            Arrays.parallelSort(arr, low, high + 1);
            // quicksort(arr, low, high);
        }
    }

    private void quicksort(float[] arr, int low, int high) {
        while (low < high) {
            //pi is partitioning index, random partition improves average complexity
            int pi = partition_plain(arr, low, high);

            //do quicksort to the left first (it is the larger one)
            if (pi - low < high - pi) {
                quicksort(arr, low, pi - 1);
                low = pi + 1;

                //do quicksort to the right
            } else {
                quicksort(arr, pi + 1, high);
                high = pi - 1;
            }
        }
    }

    private int partition_median(float[] arr, int low, int high) {
        int midIndex = (high - low) / 2;
        float start = arr[low];
        float middle = arr[midIndex];
        float end = arr[high];

        float[] tmpArr = {start, middle, end};
        Arrays.sort(tmpArr);
        return partition(arr, low, high, tmpArr[1]);
    }

    /*
    This method takes last element as pivot, 
    places the pivot element at its correct  position in sorted array,
    places all smaller (smaller than pivot) to the left, and greater element
    to the right.
     */
    private int partition_plain(float[] arr, int low, int high) {
        float pivot = arr[high];
        return partition(arr, low, high, pivot);
    }

    private int partition(float[] arr, int low, int high, float pivot) {
        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {
            //if current element is smaller than or equal to pivot
            if (arr[j] <= pivot) {
                i++;
                //swap arr[i] and arr[j]
                swap(arr, i, j);
            }
        }

        //swap arr[i+1] and arr[high]
        swap(arr, i + 1, high);
        return (i + 1);
    }

    static Random random = new Random();

    private int partition_random(float[] arr, int low, int high) {
        int r = random.nextInt((high - low) + 1) + low;
        swap(arr, r, low);
        return partition_plain(arr, low, high);
    }

    private void swap(float[] arr, int i, int j) {
        float tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private void printPartion(float[] arr, int low, int high, int partition) {
        String res = "p: " + arr[partition] + "\t";
        for (int i = low; i <= high; i++) {
            res += arr[i] + ",";
        }

        System.out.println(res);
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
