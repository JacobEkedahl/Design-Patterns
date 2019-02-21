/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelsorting;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import static parallelsorting.Task1.MIN_THRESHOLD;

/**
 *
 * @author Jacob
 */
public class Task1 {

    /**
     * @param args the command line arguments
     */
    static int MAX_THRESHOLD = 11000;
    static int MIN_THRESHOLD = 100;
    static int INTERVAL = 500;
    public static int THRESHOLD = 1000;

    //set the type of sorting to use inside start_task method
    static final int QUICKSORT = 0;
    static final int ARRAY_SORT = 1;
    static final int MERGESORT = 2;
    static final int ARRAY_PARALLELSORT = 3;
    static final int ALL = 4;

    public static int ITERATIONS = 10;
    public static int START_INDEX = 5;
    public static int SIZE_ARRAY = (int) 1000000;

    static long totalTime = 0;
    static int cores;
    static String output_file = "cores_sorting";

    static HashMap<Integer, Long> totalTimes = new HashMap<Integer, Long>();

    public static void main(String[] args) throws InterruptedException, IOException {
        cores = Runtime.getRuntime().availableProcessors();
        initMap();
        System.out.println("cores: " + cores);
        //findOptimalThreshold(QUICKSORT);
         record_cores();
    }

    private static void initMap() {
        for (int i = QUICKSORT; i <= ARRAY_PARALLELSORT; i++) {
            totalTimes.put(i, (long) 0);
        }
    }

    private static void start_task(float[] input_arr, int type) throws InterruptedException {
        for (int i = 0; i < ITERATIONS; i++) {

            //whether or not we use the same input for all iterations
            float[] arr;
            if (input_arr == null) {
                arr = gen_random_arr(SIZE_ARRAY);
            } else {
                arr = (float[]) input_arr.clone();
            }

            System.gc();
            switch (type) {
                case ARRAY_SORT:
                    array_sort(arr, i);
                    break;
                case QUICKSORT:
                    parallel_quicksort(arr, i, cores);
                    break;
                case MERGESORT:
                    parallel_mergesort(arr, i, cores);
                    break;
                case ARRAY_PARALLELSORT:
                    array_parallel_sort(arr, i);
                    break;
            }
            System.gc();
        }

        // float average = (float) totalTime / totalRecordings / 1000000;
        // System.out.println("average of " + totalRecordings + " runs: " + average + " ms" + ", threshold: " + THRESHOLD + " type: " + type);
        // saveResult(THRESHOLD, average, cores, totalRecordings, output_file);
        System.gc();
        totalTime = 0;
    }

    private static void record_cores() throws InterruptedException, IOException {
        prepareFile("arraysort parallelsort mergesort quicksort cores", output_file);
        int totalCores = cores;
        
        for (int core = 1; core <= totalCores; core++) {
            cores = core;
            float[] arr = gen_random_arr(SIZE_ARRAY);
            for (int i = QUICKSORT; i <= ARRAY_PARALLELSORT; i++) {
                start_task(arr, i);
            }

            save_result();
            printAvg();
            initMap();
        }
    }

    private static void save_result() {
        saveResult(getAvg(ARRAY_SORT), getAvg(ARRAY_PARALLELSORT),
                    getAvg(MERGESORT), getAvg(QUICKSORT), cores, output_file);
    }

    private static void printAvg() {
        for (int type : totalTimes.keySet()) {
            float avg = getAvg(type);
            if (avg != 0) {
                System.out.println("avg: " + avg + ", type: " + type + ", cores: " + cores + ", threshold: " + THRESHOLD);
            }
        }
    }

    private static float getAvg(int type) {
        int totalRecordings = ITERATIONS - START_INDEX;
        float currVal = totalTimes.get(type);
        float avg = currVal / totalRecordings / 1000000;
        return avg;
    }

    private static void findOptimalThreshold(int type) throws InterruptedException {
        float arr[] = gen_random_arr(SIZE_ARRAY);
        for (int j = MIN_THRESHOLD; j <= MAX_THRESHOLD; j += INTERVAL) {
            THRESHOLD = j;
            start_task(arr, type);

            printAvg();
            initMap();
        }
    }

    private static void array_parallel_sort(float arr[], int currIndex) {
        long startTime = System.nanoTime();
        Arrays.parallelSort(arr);
        long endTime = System.nanoTime();

        long elapsedTime = endTime - startTime;
        if (currIndex >= START_INDEX) {
            long currVal = totalTimes.get(ARRAY_PARALLELSORT);
            long newVal = currVal + elapsedTime;
            totalTimes.put(ARRAY_PARALLELSORT, newVal);
        }

        //  System.out.println("time: " + elapsedTime + ", is sorted: " + isSorted(arr));
    }

    private static void array_sort(float arr[], int currIndex) {
        long startTime = System.nanoTime();
        Arrays.sort(arr);
        long endTime = System.nanoTime();

        long elapsedTime = endTime - startTime;
        if (currIndex >= START_INDEX) {
            long currVal = totalTimes.get(ARRAY_SORT);
            long newVal = currVal + elapsedTime;
            totalTimes.put(ARRAY_SORT, newVal);
        }

        //  System.out.println("time: " + elapsedTime + ", is sorted: " + isSorted(arr));
    }

    private static void parallel_quicksort(float[] arr, int currIndex, int cores) {
        ForkJoinPool pool = new ForkJoinPool(cores);
        QuicksortTask rootTask = new QuicksortTask(arr, 0, arr.length - 1);

        long startTime = System.nanoTime();
        pool.invoke(rootTask);
        long endTime = System.nanoTime();

        long elapsedTime = endTime - startTime;
        if (currIndex >= START_INDEX) {
            long currVal = totalTimes.get(QUICKSORT);
            long newVal = currVal + elapsedTime;
            totalTimes.put(QUICKSORT, newVal);
        }

        pool.shutdown();
        //  System.out.println("time: " + elapsedTime + ", is sorted: " + isSorted(arr));
    }

    private static void parallel_mergesort(float[] arr, int currIndex, int cores) {
        ForkJoinPool pool = new ForkJoinPool(cores);
        MergeTask rootTask = new MergeTask(arr, 0, arr.length - 1);

        long startTime = System.nanoTime();
        pool.invoke(rootTask);
        long endTime = System.nanoTime();

        long elapsedTime = endTime - startTime;
        if (currIndex >= START_INDEX) {
            long currVal = totalTimes.get(MERGESORT);
            long newVal = currVal + elapsedTime;
            totalTimes.put(MERGESORT, newVal);
        }

        pool.shutdown();
        // System.out.println("time: " + elapsedTime + ", is sorted: " + isSorted(arr)); //To change body of generated methods, choose Tools | Templates.
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

    public static void saveResult(float timeSort, float timeParallel, float timeMerge, float timeQuick, int cores, String output) {
        PrintWriter pw;
        try {
            pw = new PrintWriter(new BufferedWriter(new FileWriter(output + ".txt", true)));

            pw.println();
            pw.print(timeSort + " " + timeParallel + " " + timeMerge + " " + timeQuick + " " + cores);
            pw.flush();
            pw.close();

        } catch (Exception ex) {
            // do nothing
        }
    }

    public static void prepareFile(String message, String output) throws IOException {
        Writer fileWriter = new FileWriter(output + ".txt");
        fileWriter.write(message);
        fileWriter.close();
    }
}
