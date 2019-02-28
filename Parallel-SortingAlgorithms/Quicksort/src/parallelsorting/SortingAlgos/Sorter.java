/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelsorting.SortingAlgos;

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
        return sortingAlgorithm.messure(cores);
    }
    
    public int findOptimalThreshold(SortingStrategy sortingAlgorithm) {
        return sortingAlgorithm.findOptimalThreshold();
    }
}
