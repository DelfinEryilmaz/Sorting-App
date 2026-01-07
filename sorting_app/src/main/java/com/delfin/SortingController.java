package com.delfin;

import java.util.Arrays;
import com.delfin.SortingAlgorithms.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
/**
 * SortingController is the controller class for the SortingApp.fxml which is the main scene
 * for the application.
 * Author: Delfin Eryılmaz
 * Date: 
 */
public class SortingController {
    @FXML
    private Canvas canvas; // Hook from FXML
    @FXML
    private Button mergeSortButton; 
    @FXML
    private Button generateButton; 
    @FXML
    private ComboBox sizeComboBox; 
    @FXML
    private ComboBox loweBoundComboBox; 
    @FXML
    private ComboBox upperBoundComboBox; 
    private CanvasController canvasController;
    private int[] currentArr;

    @FXML
    public void initialize() {
        this.currentArr = null;
        this.canvasController = new CanvasController(canvas);
    }

    @FXML
    public void handleGenerateArr() {

    }

    @FXML
    public void handleBubbleSort() {
        if (currentArr == null) {
            displayError("Array", "You have to initialize array before sorting.");
            return;
        }
    }

    @FXML
    public void handleInsertionSort() {
        if (currentArr == null) {
            displayError("Array", "You have to initialize array before sorting.");
            return;
        }
    }

    @FXML
    public void handleSelectionSort() {
        if (currentArr == null) {
            displayError("Array", "You have to initialize array before sorting.");
            return;
        }
    }

    @FXML
    public void handle2InPlaceMergeSort() {
        if (currentArr == null) {
            displayError("Array", "You have to initialize array before sorting.");
            return;
        }
    }

    @FXML
    public void handle2OutOfPlaceMergeSort() {
        if (currentArr == null) {
            displayError("Array", "You have to initialize array before sorting.");
            return;
        }
    }

    @FXML
    public void handle3MergeSort() {
        if (currentArr == null) {
            displayError("Array", "You have to initialize array before sorting.");
            return;
        }
    }

    @FXML
    public void handleFirstQuickSort() {
        if (currentArr == null) {
            displayError("Array", "You have to initialize array before sorting.");
            return;
        }
        System.out.println("Button was clicked!");
        int[] arr = generateArr(20, 0, 10);
        System.out.println(Arrays.toString(arr));
        canvasController.drawArray(arr, 10);
        QuickSort.quickSort(arr, QuickSort.Type.FIRST);
        System.out.println(Arrays.toString(arr));
    }

    @FXML
    public void handleRandomQuickSort() {
        if (currentArr == null) {
            displayError("Array", "You have to initialize array before sorting.");
            return;
        }
        System.out.println("Button was clicked!");
        int[] arr = generateArr(20, 0, 10);
        System.out.println(Arrays.toString(arr));
        canvasController.drawArray(arr, 10);
        QuickSort.quickSort(arr, QuickSort.Type.FIRST);
        System.out.println(Arrays.toString(arr));
    }

    @FXML
    public void handleMedianQuickSort(ActionEvent event) {
        if (currentArr == null) {
            displayError("Array", "You have to initialize array before sorting.");
            return;
        }
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

    /**
     * Displays the error as an alert.
     * @param title title of the error
     * @param message message regarding to the error
     */
    private void displayError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}