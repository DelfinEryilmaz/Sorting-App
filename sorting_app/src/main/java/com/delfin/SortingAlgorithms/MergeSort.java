package com.delfin.SortingAlgorithms;
/**
 * MergeSort class contains multiple merge sort algorithms.
 * Author: Delfin Eryılmaz
 * Date: 02/01/2025
 */
public class MergeSort implements VisualizableAlgorithm{
    /**
     * Main method for the merge sort.
     * @param arr
     * @param isTwoPart
     */
    public static void mergeSort(int[] arr, boolean isTwoPart) {
        if (isTwoPart) {
            int partLength = arr.length / 2;
            sortInPlace(0, partLength, arr);
            sortInPlace(partLength + 1, arr.length - 1, arr);
        } else {

        }
    }

    public static void sort() {

    }

    public static void sortInPlace(int start, int finish, int[] arr) {
        if (finish - start == 1) {
            if (arr[start] > arr[finish]) {
                int temp = arr[finish];
                arr[finish] = arr[start];
                arr[start] = temp;
                printArray(arr);
                return;
            }

        } else if (finish != start) {
            int mid = (start + finish) / 2;
            sortInPlace(start, mid - 1, arr);
            sortInPlace(mid, finish, arr);
            mergeTwo(arr, start, mid);
        }
    }

    public static void mergeTwo(int[] arr, int start, int mid) {
        int i = start;
        int j = mid;
        while (start <= mid) {

        }
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        int[] arr = {12, 32, 32, 50, 5, 60, 54};

        MergeSort.mergeSort(arr, true);
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
