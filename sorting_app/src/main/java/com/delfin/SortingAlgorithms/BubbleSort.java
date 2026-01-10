package com.delfin.SortingAlgorithms;
/**
 * BubbleSort is a sorting algorithm with BubbleSort method.
 * Author: Delfin Eryılmaz
 * Date: 1/1/2025
 */
public class BubbleSort implements SortAlgorithm {

    /**
     * General Bubble Sort algorithm which takes an int array.
     * @param arr
     */
    public void sort(int[] arr, VisualCallback callback) {
        int length = arr.length;

        boolean swapped;
        for (int i = 0; i < length - 1; i++) {

            swapped = false;
            for (int j = 0; j < length - i - 1; j++) {
                callback.onCompare(j, j + 1);
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    callback.onSwap(j, j + 1);
                    arr[j] = temp; 

                    swapped = true;
                }
            }

            if (!swapped) {
                callback.onComplete();
                break;
            }
        }
    }

    /**
     * General Bubble Sort algorithm which can sort every type of object impelements Comparable interface.
     * @param arr
     */
    public void sort(Comparable[] arr, VisualCallback callback) {
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
}
