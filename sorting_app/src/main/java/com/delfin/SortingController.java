package com.delfin;

import java.util.Arrays;
import com.delfin.SortingAlgorithms.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
/**
 * SortingController is the controller class for the SortingApp.fxml which is the main scene
 * for the application.
 * Author: Delfin Eryılmaz
 * Date: 
 */
public class SortingController implements SortAlgorithm.VisualCallback {
    @FXML
    private Canvas canvas; // Hook from FXML
    @FXML
    private StackPane canvasHolder; 
    @FXML
    private Button mergeSortButton; 
    @FXML
    private Button generateButton; 
    @FXML
    private ComboBox sizeComboBox; 
    @FXML
    private ComboBox lowerBoundComboBox; 
    @FXML
    private ComboBox upperBoundComboBox; 

    private CanvasController canvasController;
    private int[] currentArr;
    // Private static sorting methods
    private static final BubbleSort bubbleSort = new BubbleSort();
    private static final InsertionSort insertionSort = new InsertionSort();
    private static final SelectionSort selectionSort = new SelectionSort();
    private static final MergeSort mergeSort = new MergeSort();
    private static final QuickSort quickSort = new QuickSort();

    @FXML
    public void initialize() {
        this.currentArr = null;
        this.canvasController = new CanvasController(canvas);

        // Bind canvas size to its parent container's size
        canvas.widthProperty().bind(((StackPane)canvas.getParent()).widthProperty().subtract(40));
        canvas.heightProperty().bind(((StackPane)canvas.getParent()).heightProperty().subtract(100));

        // Redraw whenever the size changes
        // canvas.widthProperty().addListener(evt -> redrawCurrentState());
        // canvas.heightProperty().addListener(evt -> redrawCurrentState());

        // Adding a listener to combo boxes to prevent an invalid input
        // If combobox is editable it stores a secret text field
        lowerBoundComboBox.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                // Remove any character that isn't a digit
                lowerBoundComboBox.getEditor().setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        // Initializing combo boxes
        sizeComboBox.getItems().addAll("10", "20", "50", "100", "200");
        sizeComboBox.getSelectionModel().select("50"); // Set a default

        lowerBoundComboBox.getItems().addAll("0", "10", "50");
        lowerBoundComboBox.getSelectionModel().select("0");

        upperBoundComboBox.getItems().addAll("100", "500", "1000");
        upperBoundComboBox.getSelectionModel().select("100");

    }

    @FXML
    public void handleGenerateArr() {
        try {
            int lowerBound = Integer.parseInt(lowerBoundComboBox.getEditor().getText());
            int upperBound = Integer.parseInt(upperBoundComboBox.getEditor().getText());
            int length = Integer.parseInt(sizeComboBox.getEditor().getText());

            this.currentArr = new int[length];
            generateArr(upperBound, lowerBound);
            canvasController.drawArray(currentArr, length);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    public void handleBubbleSort() {
        if (currentArr == null) {
            displayError("Array", "You have to initialize array before sorting.");
            return;
        }
        bubbleSort.sort(currentArr, this);
    }

    @FXML
    public void handleInsertionSort() {
        if (currentArr == null) {
            displayError("Array", "You have to initialize array before sorting.");
            return;
        }
        insertionSort.sort(currentArr, this);
    }

    @FXML
    public void handleSelectionSort() {
        if (currentArr == null) {
            displayError("Array", "You have to initialize array before sorting.");
            return;
        }
        selectionSort.sort(currentArr, this);
    }

    @FXML
    public void handle2InPlaceMergeSort() {
        if (currentArr == null) {
            displayError("Array", "You have to initialize array before sorting.");
            return;
        }
        mergeSort.setInPlace(true);
        mergeSort.setTwoPart(true);
        mergeSort.sort(currentArr, this);
    }

    @FXML
    public void handle2OutOfPlaceMergeSort() {
        if (currentArr == null) {
            displayError("Array", "You have to initialize array before sorting.");
            return;
        }
        mergeSort.setInPlace(false);
        mergeSort.setTwoPart(true);
        mergeSort.sort(currentArr, this);
    }

    @FXML
    public void handle3MergeSort() {
        if (currentArr == null) {
            displayError("Array", "You have to initialize array before sorting.");
            return;
        }
        mergeSort.setInPlace(false);
        mergeSort.setTwoPart(false);
        mergeSort.sort(currentArr, this);
    }

    @FXML
    public void handleFirstQuickSort() {
        if (currentArr == null) {
            displayError("Array", "You have to initialize array before sorting.");
            return;
        }
        quickSort.setType(QuickSort.Type.FIRST);
        quickSort.sort(currentArr, this);
    }

    @FXML
    public void handleRandomQuickSort() {
        if (currentArr == null) {
            displayError("Array", "You have to initialize array before sorting.");
            return;
        }
        quickSort.setType(QuickSort.Type.RANDOM);
        quickSort.sort(currentArr, this);
    }

    @FXML
    public void handleMedianQuickSort(ActionEvent event) {
        if (currentArr == null) {
            displayError("Array", "You have to initialize array before sorting.");
            return;
        }
        quickSort.setType(QuickSort.Type.MEDIAN);
        quickSort.sort(currentArr, this);
    }

    /**
     * Generates an array filled with random integers in a specific range.
     * @param length
     * @param lowerBound
     * @param upperBound
     * @return the filled array
     */
    private void generateArr(int lowerBound, int upperBound) {
        for (int i = 0; i < currentArr.length; i++) {
            currentArr[i] = lowerBound + (int) (Math.random() * (upperBound - lowerBound + 1));
        }
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

    // Implement VisualCallback interface according to the app for algorithms to use
    @Override
    public void onCompare(int index1, int index2) {
        canvasController.redrawBar(index1);
        canvasController.redrawBar(index2);
    }

    @Override
    public void onSwap(int index1, int index2) {
        canvasController.redrawBar(index1);
        canvasController.redrawBar(index2);
    }

    @Override
    public void onSuccStep(int index1, int index2) {
        canvasController.redrawBar(index1);
        canvasController.redrawBar(index2);
    }

    @Override
    public void onComplete() {
        canvasController.drawArray(currentArr, 0);
    }
}