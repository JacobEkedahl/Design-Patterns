/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergeSort;

/**
 *
 * @author fno
 */
import parallelsorting.MergeTask;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import static parallelsorting.Task1.ITERATIONS;
import static parallelsorting.Task1.SIZE_ARRAY;

public class ParallelMerge {
 
    public static void merge(float[] a ,float[] b, float[] c) {
        int indexa = 0, indexb = 0, indexc = 0;
        while (indexa < a.length && indexb < b.length) {
            if (a[indexa] <= b[indexb]) {
                c[indexc++] = a[indexa++];
            } else {
                c[indexc++] = b[ indexb++];
            }
        }
        while (indexa<a.length){
            c[indexc++]=a[indexa];
            indexa++;
        }
        while(indexb<b.length){
            c[indexc++]=b[indexb];
            indexb++;
        }
    }

    public static void mergeSort(float[] a){
        if(a.length==1){
            return;
        }
        float[]b = Arrays.copyOfRange(a,0,a.length/2);
        float[]c = Arrays.copyOfRange(a,a.length/2,a.length);
        mergeSort(b);
        mergeSort(c);
        merge(b,c,a);
        return;
    }
}

