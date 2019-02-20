/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quicksort;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
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
    static int MAX_THRESHOLD = 10100;
    static int MIN_THRESHOLD = 100;
    static int INTERVAL = 1000;
    public static int THRESHOLD = 10000;
    int currentCoreIndex = 1;

    //set the type of sorting to use inside start_task method
    static final int QUICKSORT = 0;
    static final int ARRAY_SORT = 1;

    public static int ITERATIONS = 15;
    public static int START_INDEX = 5;
    public static int SIZE_ARRAY = (int) 100000;

    static long totalTime = 0;
    static int cores;

    static String output_file = "quicksort";

    public static void main(String[] args) throws InterruptedException, IOException {
        cores = 1;//Runtime.getRuntime().availableProcessors();
        output_file += "_" + cores;
        prepareFile(output_file);
        System.out.println("cores: " + cores);
        start_task(QUICKSORT);
    }

    private static void start_task(int type) throws InterruptedException {
        for (int j = MIN_THRESHOLD; j <= MAX_THRESHOLD; j += INTERVAL) {
            THRESHOLD = j;

            for (int i = 0; i < ITERATIONS; i++) {
                float[] arr = gen_random_arr(SIZE_ARRAY);
                switch (type) {
                    case ARRAY_SORT:
                        array_sort(arr, i);
                        break;
                    case QUICKSORT:
                        parallel_quicksort(arr, i, cores);
                        break;
                }
                System.gc();
            }

            int totalRecordings = ITERATIONS - START_INDEX;
            float average = (float) totalTime / totalRecordings / 1000000;
            System.out.println("average of " + totalRecordings + " runs: " + average + " ms" + ", threshold: " + THRESHOLD);
            saveResult(THRESHOLD, average, cores, totalRecordings, output_file);
            System.gc();
            totalTime = 0;
        }
    }

    private static void array_sort(float arr[], int currIndex) {
        long startTime = System.nanoTime();
        Arrays.parallelSort(arr);
        long endTime = System.nanoTime();

        long elapsedTime = endTime - startTime;
        if (currIndex >= START_INDEX) {
            totalTime += elapsedTime;
        }

        System.out.println("time: " + elapsedTime + ", is sorted: " + isSorted(arr));
    }

    private static void parallel_quicksort(float[] arr, int currIndex, int cores) {
        ForkJoinPool pool = new ForkJoinPool(cores);
        QuicksortTask rootTask = new QuicksortTask(arr, 0, arr.length - 1);

        long startTime = System.nanoTime();
        pool.invoke(rootTask);
        long endTime = System.nanoTime();

        long elapsedTime = endTime - startTime;
        if (currIndex >= START_INDEX) {
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

    public static void saveResult(int threshold, float time, int cores, int totalRecordings, String output) {
        PrintWriter pw;
        try {
            pw = new PrintWriter(new BufferedWriter(new FileWriter(output + ".txt", true)));

            pw.println();
            pw.print(SIZE_ARRAY + " " + threshold + " " + time + " " + cores + " " + totalRecordings);
            pw.flush();
            pw.close();

        } catch (Exception ex) {
            // do nothing
        }
    }

    public static void prepareFile(String output) throws IOException {
        Writer fileWriter = new FileWriter(output + ".txt");
        fileWriter.write("size threshold time cores iterations");
        fileWriter.close();
    }
}
