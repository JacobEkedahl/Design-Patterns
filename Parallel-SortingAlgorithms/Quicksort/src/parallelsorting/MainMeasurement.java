/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelsorting;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Random;
import parallelsorting.SortingAlgos.*;

/**
 *
 * @author Jacob
 */
public class MainMeasurement {

    public static final int SIZE_ARRAY = (int) 1000000;
    private static final int MAXITER = 15;
    private static final int STARTITER = 5;

    static String output_file = "final_result";

    public static void main(String[] args) throws InterruptedException, IOException {
        record();
//        int res = findThresholdMerge();
//        int resQuick = findThresholdQuick();
//        System.out.println("merge: " + res + ", quick: " + resQuick);
    }

    private static int findThresholdMerge() {
        Sorter sorter = new Sorter();
        float[] arr = gen_random_arr(SIZE_ARRAY);
        return sorter.findOptimalThreshold(new MergeTask(arr, 0, arr.length - 1));
    }

    private static int findThresholdQuick() {
        Sorter sorter = new Sorter();
        float[] arr = gen_random_arr(SIZE_ARRAY);
        return sorter.findOptimalThreshold(new QuicksortTask(arr, 0, arr.length - 1));
    }

    private static void record() throws IOException {
        int cores = Runtime.getRuntime().availableProcessors();
        String msg = "arraysort parallelsort mergesort quicksort cores";
        prepareFile(msg, output_file);
        Sorter sorter = new Sorter();

        for (int i = 1; i <= cores; i++) {
            for (int j = 1; j <= MAXITER; j++) {
                float[] arr = gen_random_arr(SIZE_ARRAY);
                long standParallel = sorter.messurePerformance(new StandardParallelSort(arr), i);
                long merge = sorter.messurePerformance(new MergeTask(arr, 0, arr.length - 1), i);
                long quick = sorter.messurePerformance(new QuicksortTask(arr, 0, arr.length - 1), i);
                long stand = sorter.messurePerformance(new StandardSort(arr), i);

                if (j > STARTITER) {
                    saveResult(stand, standParallel, merge, quick, i, output_file);
                }
                
            }
            System.out.println("core " + i + " completed..");
        }
    }

    static Random random = new Random();

    private static float[] gen_random_arr(int size) {
        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextFloat() * 1000;
        }

        return arr;
    }

    public static void saveResult(long timeSort, long timeParallel, long timeMerge, long timeQuick, int cores, String output) {
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
