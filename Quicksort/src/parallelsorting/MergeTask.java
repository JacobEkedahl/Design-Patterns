/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelsorting;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

/**
 *
 * @author Jacob
 */
public class MergeTask extends RecursiveAction {

    private static final int THRESHOLD = 128;

    private final float[] array;
    private final int start, stop;

    public MergeTask(float[] array, int start, int stop) {
        this.array = array;
        this.start = start;
        this.stop = stop;
    }

    public void sort() {
        this.compute();
    }

    @Override
    protected void compute() {
        if (start < stop) {
            int size = stop - start;
            if (size < Task1.THRESHOLD) {
                //Arrays.parallelSort(array, start, stop);
                //insertionsort();
                Arrays.sort(array, start, stop);
            } else {
                int m = (start + stop) / 2;
                invokeAll(
                        new MergeTask(array, start, m),
                        new MergeTask(array, m + 1, stop));

                merge(m);
            }
        }
    }

    private void insertionsort() {
        for (int i = start + 1; i <= stop; i++) {
            float key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }

            array[j + 1] = key;
        }
    }

    private void merge(int m) {
        float[] a = Arrays.copyOfRange(array, start, m + 1);
        float[] b = Arrays.copyOfRange(array, m + 1, stop + 1);
        int indexa = 0, indexb = 0, indexc = start;

        while (indexa < a.length && indexb < b.length) {
            if (a[indexa] <= b[indexb]) {
                array[indexc++] = a[indexa++];
            } else {
                array[indexc++] = b[indexb++];
            }
        }
        while (indexa < a.length) {
            array[indexc++] = a[indexa++];
        }
        while (indexb < b.length) {
            array[indexc++] = b[indexb++];
        }
    }
}
