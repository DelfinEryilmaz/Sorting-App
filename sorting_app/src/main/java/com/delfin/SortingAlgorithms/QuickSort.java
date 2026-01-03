package com.delfin.SortingAlgorithms;
/**
 * QuickSort class handles the operations for QuickSort in various ways.
 * Author: Delfin Eryılmaz
 * Date: 
 */
public class QuickSort implements VisualizableAlgorithm{
    /**
     * QuickSort algorithm types based on the pivot element
     */
    public static enum Type {
        FIRST,
        RANDOM,
        MEDIAN
    }

    /**
     * Sorts the array with quicksort using pivot based on the type.
     * @param arr
     */
    public static void quickSort(int[] arr, Type type) {
        sort(arr, 0, arr.length - 1, type);
    }

    /**
     * Sorts the array recursively.
     * @param arr
     * @param left
     * @param right
     * @param type
     */
    private static void sort(int[] arr, int left, int right, Type type) {
        if (left < right) {
            int splitPoint = partition(arr, left, right, type);
            sort(arr, left, splitPoint, type);
            sort(arr, splitPoint + 1, right, type);
        }
    }

    /**
     * Helper partition method based on Hoare's quick sort method.
     * @param arr
     * @param left
     * @param right
     * @param type
     * @return the split point.
     */
    private static int partition(int[] arr, int left, int right, Type type) {
        int pivotValue = selectPivot(arr, left, right, type);

        int i = left - 1;
        int j = right + 1;
        while (i < j) {
            i++; while (arr[i] < pivotValue) { i++; }
            j--; while (arr[j] > pivotValue) { j--; }
            if (i < j) { 
                swap(arr, i, j); 
            }
        }

        return j;
    }

    /**
     * Selects the pivot according to the type.
     * @param arr
     * @param left
     * @param right
     * @param type
     * @return
     */
    private static int selectPivot(int[] arr, int left, int right, Type type) {
        int pivotValue = 0;

        switch (type) {
            case FIRST:
                pivotValue = arr[left];
                break;

            case RANDOM:
                int random = left + (int)(Math.random() * (right - left + 1));
                pivotValue = arr[random];
                swap(arr, left, random);
                break;

            case MEDIAN:
                int a = arr[left];
                int b = arr[(left + right) / 2];
                int c = arr[right];

                if ((b <= a && a <= c) || (c <= a && a <= b)) {
                    // It is already in the first position
                    pivotValue = a;
                } else if ((a <= b && b <= c) || (c <= b && b <= a)){
                    swap(arr, left, (left + right) / 2);
                    pivotValue = b;
                } else {
                    swap(arr, left, right);
                    pivotValue = c;
                }
                
                break;
            default:
                break;
        }

        return pivotValue;
    }

    /**
     * Private helper method to swap elements.
     * @param arr
     * @param index1
     * @param index2
     */
    private static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
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
    
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
