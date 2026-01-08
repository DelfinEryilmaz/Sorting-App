package com.delfin.SortingAlgorithms;
/**
 * InsertionSort class contains two sort methods, which can sort an int array and comparable array.
 * Author: Delfin Eryılmaz
 * Date: 1/1/2025
 */
public class InsertionSort implements SortAlgorithm {

    /**
     * Sort the int array with using Insertion sort implementation.
     * @param arr
     */
    @Override
    public void sort(int[] arr, VisualCallback callback) {
        int length = arr.length;

        // Outer loop
        for (int i = 1; i < length; i++) {
            int value = arr[i];
            int insertPos = i - 1;

            // Tracking backwards to insert value its correct place
            while (insertPos >= 0 && arr[insertPos] > value) {
                // Shifting the array
                arr[insertPos + 1] = arr[insertPos];
                insertPos--;
            }
            // Quitting loop means we have reach the correct index for the value.
            arr[insertPos + 1] = value;
        }
    }

    /**
     * General insertion sort method that can sort any array implementing comparable interface.
     * @param arr
     */
    @Override
    public void sort(Comparable[] arr, VisualCallback callback) {
        int length = arr.length;

        for (int i = 1; i < length; i++) {
            Comparable value = arr[i];
            int insertPos = i - 1;

            // Tracking backwards to insert value its correct place
            while ((insertPos >= 0) && (arr[insertPos].compareTo(value) > 0)) {
                // Shifting the array
                arr[insertPos + 1] = arr[insertPos];
                insertPos--;
            }
            // Quitting loop means we have reach the correct index for the value.
            arr[insertPos + 1] = value;
        }
    }
}
