package com.delfin.SortingAlgorithms;
/**
 * QuickSort class handles the operations for QuickSort in various ways.
 * Author: Delfin Eryılmaz
 * Date: 
 */
public class QuickSort implements SortAlgorithm {
    /**
     * QuickSort algorithm types based on the pivot element
     */
    public static enum Type {
        FIRST,
        RANDOM,
        MEDIAN
    }

    private Type type = Type.FIRST;

    @Override
    public void sort(int[] arr, VisualCallback callback) {
        quickSort(arr, callback, type);
    }
    
    /**
     * Sorts the array with quicksort using pivot based on the type.
     * @param arr
     */
    public void quickSort(int[] arr, VisualCallback callback, Type type) {
        sort(arr, 0, arr.length - 1, type);
    }

    /**
     * Sorts the array recursively.
     * @param arr
     * @param left
     * @param right
     * @param type
     */
    private void sort(int[] arr, int left, int right, Type type) {
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
    private int partition(int[] arr, int left, int right, Type type) {
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
    private int selectPivot(int[] arr, int left, int right, Type type) {
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

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    @Override
    public void sort(Comparable[] arr, VisualCallback callback) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sort'");
    }

    // Getters and Setters

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }    
}
