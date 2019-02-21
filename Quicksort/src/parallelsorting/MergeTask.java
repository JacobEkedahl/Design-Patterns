/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelsorting;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import mergeSort.ParallelMerge;

public class MergeTask extends RecursiveAction {
    
    private float[] array;
    private final int start, stop;
    private long res;

    public MergeTask(float[] array, int start, int stop) {
        this.array = array;
        this.start = start;
        this.stop = stop;
    }


    @Override
    protected void compute() {
        if(start - stop < Task1.THRESHOLD){
            ParallelMerge.mergeSort(array);
            
        }
        else{
            System.out.println("making workers");
            int mid = (start+stop)/2;
            MergeTask worker1 = new MergeTask(array,start,mid);
            MergeTask worker2 = new MergeTask(array,mid+1,stop);
            worker1.fork();
            worker2.compute();
            worker1.join();
        }




    }
}
