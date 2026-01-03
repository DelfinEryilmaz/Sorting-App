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
    public static void mergeSort(int[] arr, boolean isTwoPart, boolean inPlace) {
        if (isTwoPart) {
            if (inPlace) {
                sortInPlace2(0, arr.length - 1, arr);
            } else {
                int[] temp = new int[arr.length];
                sort2(0, arr.length - 1, arr, temp);
            }
        } else {
            if (inPlace) {

            } else {
                int[] temp = new int[arr.length];
                sort3(0, arr.length - 1, arr, temp);
            }
        }
    }

    /**
     * Recursive helper method for sorting with a temp array.
     * @param start
     * @param finish
     * @param arr
     * @param tempArr
     */
    private static void sort2(int start, int finish, int[] arr, int[] tempArr) {
        if (finish - start == 1) {
            if (arr[start] > arr[finish]) {
                int temp = arr[finish];
                arr[finish] = arr[start];
                arr[start] = temp;
                return;
            }
                
        } else if (finish != start) {
            int mid = (start + finish) / 2;
            sort2(start, mid, arr, tempArr);
            sort2(mid + 1, finish, arr, tempArr);
            merge2(arr, tempArr, start, mid, finish);
        }
        // If the remaining part is one element which is start == finish
        return;
    }

    /**
     * Helper merge method using a temp array.
     * @param arr
     * @param temp
     * @param start
     * @param mid
     * @param finish
     */
    private static void merge2(int[] arr, int[] temp, int start, int mid, int finish) {
        int i = start;
        int j = mid + 1;
        int k = start;

        // This loop will continue until one of the part is finish
        while (i <= mid && j <= finish) {
            if (arr[i] > arr[j]) {
                temp[k] = arr[j];
                k++; j++;
            } else {
                temp[k] = arr[i];
                k++; i++;
            }
        }

        while (i <= mid) {
            temp[k] = arr[i];
            k++; i++;
        }

        while (j <= finish) {
            temp[k] = arr[j];
            k++; j++;
        }

        // Copy the temp into original array
        for (int l = start; l <= finish; l++) {
            arr[l] = temp[l];
        }
    }

    /**
     * Recursive helper method for sorting in-place.
     * @param start
     * @param finish
     * @param arr
     */
    private static void sortInPlace2(int start, int finish, int[] arr) {
        if (start >= finish) return;
        if (finish - start == 1) {
            if (arr[start] > arr[finish]) {
                int temp = arr[finish];
                arr[finish] = arr[start];
                arr[start] = temp;
                return;
            }
        } else if (finish != start) {
            int mid = (start + finish) / 2;
            sortInPlace2(start, mid, arr);
            sortInPlace2(mid + 1, finish, arr);
            mergeInPlace2(arr, start, mid, finish);
        }
        // If the remaining part is one element which is start == finish
        return;
    }

    /**
     * Helper in-place merge method using array operations like shifting.
     * @param arr
     * @param start
     * @param mid
     * @param finish
     */
    private static void mergeInPlace2(int[] arr, int start, int mid, int finish) {
        int start2 = mid + 1;

        // If there is a direct merge
        if (arr[mid] <= arr[start2]) {
            return;
        }

        while (start <= mid && start2 <= finish) {
            // If first element is at the correct place
            if (arr[start] <= arr[start2]) {
                start++;
            } else {
                int value = arr[start2];
                int index = start2;

                // Shifting all the elements
                while (index != start) {
                    arr[index] = arr[index - 1];
                    index--;
                }
                arr[index] = value;

                // Updating all pointers
                start++; mid++; start2++;
            }
        }
    }

    /**
     * Recursive merge sort method dividing array into 3 parts.
     * @param start
     * @param finish
     * @param arr
     * @param tempArr
     */
    private static void sort3(int start, int finish, int[] arr, int[] tempArr) {
        if (start >= finish) return;

        if (finish - start == 1) {
            if (arr[start] > arr[finish]) {
                int temp = arr[finish];
                arr[finish] = arr[start];
                arr[start] = temp;
                return;
            }
                
        } else if (finish != start) {
            int length = finish - start + 1;
            int mid1 = start + (length / 3);
            int mid2 = start + 2 * (length / 3);

            sort3(start, mid1 - 1, arr, tempArr);
            sort3(mid1, mid2 - 1, arr, tempArr);
            sort3(mid2, finish, arr, tempArr);

            merge3(arr, tempArr, start, mid1, mid2, finish);
        }
    }

    /**
     * Private helper merge method for the 3 part-merge sort using a temp array.
     * @param arr
     * @param tempArr
     * @param start
     * @param mid1
     * @param mid2
     * @param finish
     */
    private static void merge3(int[] arr, int[] tempArr, int start, int mid1, int mid2, int finish) {
        int i = start;
        int j = mid1;
        int k = mid2;
        int l = start;

        // Making comparisons for merging
        while (i < mid1 && j < mid2 && k <= finish) {
            if (arr[i] <= arr[j] && arr[i] <= arr[k]) {
                tempArr[l] = arr[i];
                l++; i++;

            } else if (arr[j] <= arr[i] && arr[j] <= arr[k]) {
                tempArr[l] = arr[j];
                l++; j++;

            } else {
                tempArr[l] = arr[k];
                l++; k++;
            }
        }

        // Merging remaining pairs
        while (i < mid1 && j < mid2) {
            if (arr[i] <= arr[j]) {
                tempArr[l] = arr[i];
                l++; i++;
            } else {
                tempArr[l] = arr[j];
                l++; j++;
            }
        }

        while (i < mid1 && k <= finish) {
            if (arr[i] <= arr[k]) {
                tempArr[l] = arr[i];
                l++; i++;
            } else {
                tempArr[l] = arr[k];
                l++; k++;
            }
        }
        
        while (j < mid2 && k <= finish) {
            if (arr[j] <= arr[k]) {
                tempArr[l] = arr[j];
                l++; j++;
            } else {
                tempArr[l] = arr[k];
                l++; k++;
            }
        }

        // Merging the leftovers remaining from the last part
        while (i < mid1) {
           tempArr[l] = arr[i]; 
           l++; i++;
        }
        while (j < mid2) {
            tempArr[l] = arr[j]; 
            l++; j++;
        }
        while (k <= finish) {
            tempArr[l] = arr[k]; 
            l++; k++;
        }

        // Lastly copying the array into the original array
        for (int m = start; m < tempArr.length; m++) {
            arr[m] = tempArr[m];
        }
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
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
