
package parallelsorting.SortingAlgos;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

/**
 *
 * @author Jacob
 */

 public interface SortingStrategy {
     public void sort();
     public void messure(int cores);
     public boolean isSorted();

    /**
     * 
     * @return the optimal threshold value
     * for the max amount of cores on the current machine
     */ 
     public long findOptimalThreshold(int cores, int i);
 }