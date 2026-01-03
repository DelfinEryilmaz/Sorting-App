package com.delfin.SortingAlgorithms;
/**
 * SelectionSort is a comparison based, in place algorithm.
 * It works by selecting the minimum element repeatedly. 
 * Selection sort has a time complexity of O(n^2) in all cases and requires
 * O(1) extra space.
 * 
 * Author: Delfin Eryılmaz
 * Date: 03/01/2025
 */
public class SelectionSort implements VisualizableAlgorithm{

    /**
     * Selection sort algorithm sorting an int array in an ascending order.
     * @param arr
     */
    public static void sort(int[] arr) {
        int length = arr.length;

        for (int i = 0; i < length - 1; i++) {

            // Where minimum number is positioned in the sorted array
            int minIndex = i;

            // Finding the minimum in every traverse
            for (int j = i + 1; j < length; j++) {
                if (arr[j] <= arr[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap it with the smallest element
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    /**
     * Selection sort algorithm sorting a comparable array in an ascending order.
     * @param arr
     */
    public static void sort(Comparable[] arr) {
        int length = arr.length;

        for (int i = 0; i < length - 1; i++) {

            // Where minimum number is positioned in the sorted array
            int minIndex = i;

            // Finding the minimum in every traverse
            for (int j = i + 1; j < length; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            // Swap it with the smallest element
            Comparable temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
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
