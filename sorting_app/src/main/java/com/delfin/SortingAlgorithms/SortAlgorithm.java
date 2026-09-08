package com.delfin.SortingAlgorithms;
/**
 * Visualizable interface determines which classes can be displayed in the 
 * JavaFX application and which operations they have to implement.
 * Author: Delfin Eryılmaz
 * Date:
 */
public interface SortAlgorithm {
    enum OperationType{
        COMPARE,
        SWAP,
        ITERATE,
        DEFAULT
    }

    void sort(Comparable[] arr, VisualCallback callback);
    void sort(int[] arr, VisualCallback callback);

    /**
     * A Callback interface to provide blueprint methods for visualizing the sorting algorithm.
     */
    public interface VisualCallback {
        /**
         * Displays the comparison of the values at index1 and index2.
         * @param index1
         * @param index2
         */
        void onCompare(int index1, int index2);

        /**
         * Displays the swaps of values at index1 and index2.index1 and index2.
         * @param index1
         * @param index2
         */
        void onSwap(int index1, int index2);

        /**
         * Displays the iterated index.
         * @param index
         */
        void onIterate(int index);

        /**
         * Displays the shifting from the index to another one.
         * @param from
         * @param to
         */
        void onMove(int from, int to);

        /**
         * Displays the complete situation.
         */
        void onComplete();
    }
}
