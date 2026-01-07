package com.delfin;

import java.util.Arrays;
import com.delfin.SortingAlgorithms.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
/**
 * SortingController is the controller class for the SortingApp.fxml which is the main scene
 * for the application.
 * Author: Delfin Eryılmaz
 * Date: 
 */
public class SortingController {
    @FXML
    private Canvas visualizationCanvas; // Hook from FXML
    @FXML
    private Button mergeSortButton; 
    private CanvasController canvasController;

    @FXML
    public void initialize() {
        this.canvasController = new CanvasController(visualizationCanvas);
    }

    @FXML
    public void handleMergeSort(ActionEvent event) {
        System.out.println("Button was clicked!");
        int[] arr = generateArr(20, 0, 10);
        System.out.println(Arrays.toString(arr));
        canvasController.drawArray(arr, 10);
        QuickSort.quickSort(arr, QuickSort.Type.FIRST);
        System.out.println(Arrays.toString(arr));
    }

    @FXML
    public void handleQuickSort(ActionEvent event) {
        System.out.println("Button was clicked!");
        int[] arr = generateArr(20, 0, 10);
        System.out.println(Arrays.toString(arr));
        canvasController.drawArray(arr, 10);
        QuickSort.quickSort(arr, QuickSort.Type.FIRST);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * Generates an array filled with random integers in a specific range.
     * @param length
     * @param lowerBound
     * @param upperBound
     * @return the filled array
     */
    private int[] generateArr(int length, int lowerBound, int upperBound) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = lowerBound + (int) (Math.random() * (upperBound - lowerBound + 1));
        }
        return arr;
    }
}