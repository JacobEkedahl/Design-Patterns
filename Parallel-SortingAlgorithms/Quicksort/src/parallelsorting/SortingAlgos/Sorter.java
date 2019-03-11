/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelsorting.SortingAlgos;

import java.util.concurrent.ForkJoinPool;

/**
 *
 * @author jaceke
 */
public class Sorter {

    public void Sorter() {

    }

    public void sort(SortingStrategy sortingAlgorithm) {
        sortingAlgorithm.sort();
    }

    public long messurePerformance(SortingStrategy sortingAlgorithm, int cores) {
        System.gc();
        long startTime = System.nanoTime();
        sortingAlgorithm.messure(cores);
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        return elapsedTime;
    }

    public int findOptimalThreshold(SortingStrategy sortingAlgorithm) {
        int currMin = Integer.MAX_VALUE;
        long minVal = Long.MAX_VALUE;
        long currTotal = 0;
        int totalRuns = 15 - 5;
        int cores = Runtime.getRuntime().availableProcessors();
        
        for (int i = 100; i <= 100000; i += 1000) {
            for (int j = 1; j <= 15; j++) {
                System.gc();
                long elapsedTime = sortingAlgorithm.findOptimalThreshold(cores, i);
                if (j >= 5) {
                    currTotal += elapsedTime;
                }

                System.gc();
            }

            long avg = currTotal / totalRuns;

            System.out.println(avg + "," + i);
            if (avg < minVal) {
                minVal = avg;
                currMin = i;
            }
            currTotal = 0;
        }

        return currMin;
    }
}
