/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quicksort;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Jacob
 */
public class Task1 {

    /**
     * @param args the command line arguments
     */
    static int MAX_THRESHOLD = 100000;
    static int MIN_THRESHOLD = 100;
    static int INTERVAL = 1000;
    public static int THRESHOLD = 1000;
    int currentCoreIndex = 1;

    public static int ITERATIONS = 25;
    public static int START_INDEX = 5;
    public static int SIZE_ARRAY = (int) 1000000;

    static long totalTime = 0;
    static int cores;
    public static void main(String[] args) throws InterruptedException {
        cores = Runtime.getRuntime().availableProcessors();
        start_task();
    }

    private static void start_task() {
       // for (int j = MIN_THRESHOLD; j < MAX_THRESHOLD; j += INTERVAL) {
         //   THRESHOLD = j;
            
            for (int i = 0; i < ITERATIONS; i++) {
                float[] arr = gen_random_arr(SIZE_ARRAY);
                parallel_quicksort(arr, i, cores);
                System.gc();
            }

            int totalRecordings = ITERATIONS - START_INDEX;
            float average = (float) totalTime / totalRecordings / 1000000;
            System.out.println("average of " + totalRecordings + " runs: " + average + " ms" + ", threshold: " + THRESHOLD);
            System.gc();
      //  }
    }

    private static void parallel_quicksort(float[] arr, int currIndex, int cores) {
        ForkJoinPool pool = new ForkJoinPool(cores);
        QuicksortTask rootTask = new QuicksortTask(arr, 0, arr.length - 1);

        long startTime = System.nanoTime();
        pool.invoke(rootTask);
        long endTime = System.nanoTime();

        long elapsedTime = endTime - startTime;
        if (currIndex - 1 >= START_INDEX) {
            totalTime += elapsedTime;
        }

        pool.shutdown();
        System.out.println("time: " + elapsedTime + ", is sorted: " + isSorted(arr));
    }

    //test method for validating the sorting of array
    private static boolean isSorted(float[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] < arr[i]) {
                return false;
            }
        }

        return true;
    }

    static Random random = new Random();

    private static float[] gen_random_arr(int size) {
        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextFloat() * 100;
        }

        return arr;
    }
}
