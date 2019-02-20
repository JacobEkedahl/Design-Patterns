/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quicksort;

import java.util.Arrays;

/**
 *
 * @author Jacob
 */
public class Quicksort {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        float[] arr = {1.0f, 22.0f, 33.0f, 11.0f, 45.2f, 1001.034f};
        quicksort(arr, 0, arr.length-1);
        
        String res = Arrays.toString(arr);
        System.out.println("res: " + res);
    }
    
    //pick random item as pivot
    
    private static void quicksort(float[] arr, int low, int high) {
        
        if (low < high) {
            //pi is partitioning index
            int pi = partition(arr, low, high);
            
            quicksort(arr, low, pi-1);
            quicksort(arr, pi+1, high);
        }
    }
    
    /*
    This method takes last element as pivot, 
    places the pivot element at its correct  position in sorted array,
    places all smaller (smaller than pivot) to the left, and greater element
    to the right.
    */
    private static int partition(float[] arr, int low, int high) {
        //right elements
        float pivot = arr[high];
        
        int i = low -1;
        
        for (int j = low; j <= high-1; j++) {
            //if current element is smaller than or equal to pivot
            if (arr[j] <= pivot) {
                i++;
                //swap arr[i] and arr[j]
                swap(arr, i, j);
            }
        }
        
        //swap arr[i+1] and arr[high]
        swap(arr, i+1, high);
        return (i+1);
    }
    
    private static void swap(float[] arr, int i, int j) {
        float tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    
    
    
}
