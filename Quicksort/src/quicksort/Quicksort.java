/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quicksort;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Jacob
 */
public class Quicksort {

    /**
     * @param args the command line arguments
     */
    public static int THRESHOLD_INSERTION = 10;
    public static int ITERATIONS = 15;
    public static int START_INDEX = 5;

    static long totalTime = 0;

    public static void main(String[] args) {

        for (int i = 0; i < ITERATIONS; i++) {
            float[] arr = gen_random_arr(10000);
            start(arr, i);
            System.gc();
        }
        
        int totalRecordings = ITERATIONS - START_INDEX;
        float average = (float) totalTime / totalRecordings / 1000000;
        System.out.println("average of " + totalRecordings + " runs: " + average + " ms");
    }

    private static void start(float[] arr, int currIndex) {
        long startTime = System.nanoTime();
        quicksort_insertion(arr, 0, arr.length - 1);
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        if (currIndex - 1 >= START_INDEX) {
            totalTime += elapsedTime;
        }
        String res = Arrays.toString(arr);
        System.out.println("(#" + currIndex + ") time elapsed: " + (float) elapsedTime / 1000000 + " ms");
    }

    private static float[] gen_random_arr(int size) {
        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextFloat() * 100;
        }

        return arr;
    }

    //invoke when arr is below threshold (eg 10)
    //implementation of binary insertionsort
    private static void insertionsort(float[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            float key = arr[i];

            //find location to insert using binary search
            int j = Math.abs(Arrays.binarySearch(arr, low, high, key));

            //shifting arr to one location right
            System.arraycopy(arr, j, arr, j + 1, i - 1);
            arr[j] = key;
        }
    }

    //pick random item as pivot
    private static void quicksort_insertion(float[] arr, int low, int high) {
        if (arr.length <= THRESHOLD_INSERTION) {
            insertionsort(arr, low, high);
        } else {
            quicksort(arr, low, high);
        }
    }

    private static void quicksort(float[] arr, int low, int high) {
        while (low < high) {
            //pi is partitioning index, random partition improves average complexity
            int pi = parition_random(arr, low, high);

            if (pi - low < high - pi) {
                quicksort(arr, low, pi - 1);
                low = pi + 1;
            } else {
                quicksort(arr, pi + 1, high);
                high = pi - 1;
            }
        }
    }

    /*
    This method takes last element as pivot, 
    places the pivot element at its correct  position in sorted array,
    places all smaller (smaller than pivot) to the left, and greater element
    to the right.
     */
    private static int partition(float[] arr, int low, int high) {
        //right elements
        float pivot = arr[high];

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

    private static int parition_random(float[] arr, int low, int high) {
        int r = random.nextInt((high - low) + 1) + low;
        swap(arr, r, low);
        return partition(arr, low, high);
    }

    private static void swap(float[] arr, int i, int j) {
        float tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
