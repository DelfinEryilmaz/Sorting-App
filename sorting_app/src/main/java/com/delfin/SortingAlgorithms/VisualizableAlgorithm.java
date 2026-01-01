package com.delfin.SortingAlgorithms;
/**
 * Visualizable interface determines which classes can be displayed in the 
 * JavaFX application and which operations they have to implement.
 * Author: Delfin Eryılmaz
 * Date:
 */
public interface VisualizableAlgorithm {
    void onCompare(int index1, int index2);
    void onSwap(int index1, int index2);
    void onSuccStep(int index1, int index2);
}
