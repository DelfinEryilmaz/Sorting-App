package com.delfin.SortingAlgorithms;
/**
 * BubbleSort is a sorting algorithm with BubbleSort method.
 * Author: Delfin Eryılmaz
 * Date: 1/1/2025
 */
public class BubbleSort implements VisualizableAlgorithm {

    /**
     * General Bubble Sort algorithm which takes an int array.
     * @param arr
     */
    public static void sort(int[] arr) {
        int length = arr.length;

        boolean swapped;
        for (int i = 0; i < length - 1; i++) {

            swapped = false;
            for (int j = 0; j < length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp; 

                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

    /**
     * General Bubble Sort algorithm which can sort every type of object impelements Comparable interface.
     * @param arr
     */
    public static void sort(Comparable[] arr) {
        int length = arr.length;

        boolean swapped;
        for (int i = 0; i < length - 1; i++) {

            swapped = false;
            for (int j = 0; j < length - i - 1; j++) {
                Comparable data1 = arr[j];
                Comparable data2 = arr[j + 1];
                if (data1.compareTo(data2) > 0) {
                    Comparable temp = data2;
                    data2 = data1;
                    data1 = temp; 

                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
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
