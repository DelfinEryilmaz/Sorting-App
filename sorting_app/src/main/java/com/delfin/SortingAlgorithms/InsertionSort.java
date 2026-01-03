package com.delfin.SortingAlgorithms;
/**
 * InsertionSort class contains two sort methods, which can sort an int array and comparable array.
 * Author: Delfin Eryılmaz
 * Date: 1/1/2025
 */
public class InsertionSort implements VisualizableAlgorithm{

    /**
     * Sort the int array with using Insertion sort implementation.
     * @param arr
     */
    public static void sort(int[] arr) {
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
    public static void sort(Comparable[] arr) {
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

    @Override
    public void onCompare(int index1, int index2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onCompare'");
    }

    @Override
    public void onSwap(int index1, int index2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onSwap'");
    }

    @Override
    public void onSuccStep(int index1, int index2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onSuccStep'");
    }
}
