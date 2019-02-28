/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelsorting.SortingAlgos;

import java.util.Arrays;

/**
 *
 * @author jaceke
 */
public class StandardParallelSort implements SortingStrategy {

    float[] arr;
    private static final int MAXITER = 15;
    private static final int STARTITER = 5;

    public StandardParallelSort(float[] arr) {
        this.arr = arr;
    }

    @Override
    public void sort() {
        Arrays.parallelSort(arr);
    }

    @Override
    public long messure(int cores) {
        float[] copyArr = (float[]) arr.clone();
        System.gc();
        long startTime = System.nanoTime();
        Arrays.parallelSort(copyArr);
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        return elapsedTime;
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

    @Override
    public int findOptimalThreshold() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
