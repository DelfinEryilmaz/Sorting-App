package com.delfin;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import com.delfin.SortingAlgorithms.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
/**
 * SortingController is the controller class for the SortingApp.fxml which is the main scene
 * for the application.
 * Author: Delfin Eryılmaz
 * Date: 
 */
public class SortingController implements SortAlgorithm.VisualCallback {
    @FXML
    private Canvas canvas; 
    @FXML
    private StackPane canvasHolder; 
    @FXML
    private Button pauseButton; 
    @FXML
    private Button resetButton; 
    @FXML
    private Button generateButton; 
    @FXML
    private ComboBox sizeComboBox; 
    @FXML
    private ComboBox lowerBoundComboBox; 
    @FXML
    private ComboBox upperBoundComboBox; 
    @FXML
    private Slider speedSlider;

    private CanvasController canvasController;
    private int[] currentArr;
    private int maxValue;
    private final AtomicBoolean isSorting = new AtomicBoolean(false);
    private final AtomicBoolean isPaused = new AtomicBoolean(false);
    // Our private monitor object
    private final Object pauseLock = new Object(); 
    private final static long BASE_SPEED = 100;
    // volatile -> ensure the sorting thread sees the most recent one
    private volatile long currentSpeed;
    // Create one single thread for sorting
    private final ExecutorService sortingThread = Executors.newSingleThreadExecutor();

    // Private static sorting methods
    private static final BubbleSort bubbleSort = new BubbleSort();
    private static final InsertionSort insertionSort = new InsertionSort();
    private static final SelectionSort selectionSort = new SelectionSort();
    private static final MergeSort mergeSort = new MergeSort();
    private static final QuickSort quickSort = new QuickSort();

    /**
     * Initialize the SortingController which includes initializing CanvasController, 
     * attaching listener to slider, initiazlizing combo boxes etc..
     */
    @FXML
    public void initialize() {
        this.maxValue = 0;
        this.currentSpeed = BASE_SPEED;
        this.currentArr = null;
        this.canvasController = new CanvasController(canvas);

        // Bind canvas size to its parent container's size
        canvas.widthProperty().bind(((StackPane)canvas.getParent()).widthProperty().subtract(40));
        canvas.heightProperty().bind(((StackPane)canvas.getParent()).heightProperty().subtract(100));

        // Redraw whenever the size changes
        // canvas.widthProperty().addListener(evt -> redrawCurrentState());
        // canvas.heightProperty().addListener(evt -> redrawCurrentState());

        // Add a listener to combo boxes to prevent an invalid input
        // If combobox is editable it stores a secret text field
        sizeComboBox.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                // Remove any character that isn't a digit
                lowerBoundComboBox.getEditor().setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        lowerBoundComboBox.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                // Remove any character that isn't a digit
                lowerBoundComboBox.getEditor().setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        upperBoundComboBox.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                // Remove any character that isn't a digit
                lowerBoundComboBox.getEditor().setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        // Initialize combo boxes
        sizeComboBox.getItems().addAll("10", "20", "50", "100", "200");
        sizeComboBox.getSelectionModel().select("50"); // Set a default

        lowerBoundComboBox.getItems().addAll("0", "10", "50");
        lowerBoundComboBox.getSelectionModel().select("0");

        upperBoundComboBox.getItems().addAll("100", "500", "1000");
        upperBoundComboBox.getSelectionModel().select("100");

        // Add a listener to speed slider and andjust the the speed
        speedSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            currentSpeed = (long) (BASE_SPEED / newValue.doubleValue());
        });
    }

    /**
     * Generates the array according to the selected properties through array panel.
     */
    @FXML
    public void handleGenerateArr() {
        try {
            int lowerBound = Integer.parseInt(lowerBoundComboBox.getEditor().getText());
            int upperBound = Integer.parseInt(upperBoundComboBox.getEditor().getText());
            int length = Integer.parseInt(sizeComboBox.getEditor().getText());

            this.currentArr = new int[length];
            generateArr(upperBound, lowerBound);
            canvasController.drawArray(currentArr, maxValue, true);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    public void handlePauseButton() {
        // If there is no array being sorted
        if (!isSorting.get()) return; 

        // Change the status of the isPaused Atomic boolean
        boolean currentlyPaused = isPaused.get();
        isPaused.set(!currentlyPaused); 

        // Change the button text based 
        if (currentlyPaused) {
            // If sorting is resuming
            synchronized (pauseLock) {
                // Wake up the sorting thread which was waited in checkStatus()
                pauseLock.notifyAll();
            }

            pauseButton.setText("Pause");
        } else {
            // If sorting paused.
            pauseButton.setText("Resume"); 
        }
    }

    @FXML
    public void handleResetButton() {
        isSorting.set(false);
        isPaused.set(false);

        synchronized (pauseLock) {
            pauseLock.notifyAll();
        }

        // Update UI elements
        pauseButton.setText("Pause");
        canvasController.clear(); 
        currentArr = null;    
    }

    /**
     * Handles the BubbleSort running selected through menu.
     */
    @FXML
    public void handleBubbleSort() {
        if (currentArr == null) {
            displayError("Array", "You have to initialize array before sorting.");
            return;
        }

        if (isSorting.compareAndSet(false, true)) {
            isPaused.set(false);

            sortingThread.submit(() -> {
                try {
                    bubbleSort.sort(currentArr, this);
                } catch (RuntimeException e) {
                    System.out.println("Sort stopped: " + e.getMessage());
                } finally {
                    // Ensure the flag is reset so we can sort again later
                    isSorting.set(false);
                }
            });
        } else {
            displayError("Concurrency", "You cannot run two sorting algorithms at once.");
        }
    }

    @FXML
    public void handleInsertionSort() {
        if (currentArr == null) {
            displayError("Array", "You have to initialize array before sorting.");
            return;
        }

        if (isSorting.compareAndSet(false, true)) {
            isPaused.set(false);

            sortingThread.submit(() -> {
                try {
                    insertionSort.sort(currentArr, this);
                } catch (RuntimeException e) {
                    System.out.println("Sort stopped: " + e.getMessage());
                } finally {
                    // Ensure the flag is reset so we can sort again later
                    isSorting.set(false);
                }
            });
        } else {
            displayError("Concurrency", "You cannot run two sorting algorithms at once.");
        }
    }

    @FXML
    public void handleSelectionSort() {
        if (currentArr == null) {
            displayError("Array", "You have to initialize array before sorting.");
            return;
        }
        sortingThread.submit(() -> {
            selectionSort.sort(currentArr, this);
        });
    }

    @FXML
    public void handle2InPlaceMergeSort() {
        if (currentArr == null) {
            displayError("Array", "You have to initialize array before sorting.");
            return;
        }
        mergeSort.setInPlace(true);
        mergeSort.setTwoPart(true);
        sortingThread.submit(() -> {
            mergeSort.sort(currentArr, this);
        });
    }

    @FXML
    public void handle2OutOfPlaceMergeSort() {
        if (currentArr == null) {
            displayError("Array", "You have to initialize array before sorting.");
            return;
        }
        mergeSort.setInPlace(false);
        mergeSort.setTwoPart(true);
        sortingThread.submit(() -> {
            mergeSort.sort(currentArr, this);
        });
    }

    @FXML
    public void handle3MergeSort() {
        if (currentArr == null) {
            displayError("Array", "You have to initialize array before sorting.");
            return;
        }
        mergeSort.setInPlace(false);
        mergeSort.setTwoPart(false);
        sortingThread.submit(() -> {
            mergeSort.sort(currentArr, this);
        });
    }

    @FXML
    public void handleFirstQuickSort() {
        if (currentArr == null) {
            displayError("Array", "You have to initialize array before sorting.");
            return;
        }
        quickSort.setType(QuickSort.Type.FIRST);
        sortingThread.submit(() -> {
            quickSort.sort(currentArr, this);
        });
    }

    @FXML
    public void handleRandomQuickSort() {
        if (currentArr == null) {
            displayError("Array", "You have to initialize array before sorting.");
            return;
        }
        quickSort.setType(QuickSort.Type.RANDOM);
        sortingThread.submit(() -> {
            quickSort.sort(currentArr, this);
        });
    }

    @FXML
    public void handleMedianQuickSort(ActionEvent event) {
        if (currentArr == null) {
            displayError("Array", "You have to initialize array before sorting.");
            return;
        }
        quickSort.setType(QuickSort.Type.MEDIAN);
        sortingThread.submit(() -> {
            quickSort.sort(currentArr, this);
        });
    }

    /**
     * Helper method that generates an array filled with random integers in a specific range.
     * @param length
     * @param lowerBound
     * @param upperBound
     * @return the filled array
     */
    private void generateArr(int lowerBound, int upperBound) {
        int max = 0;
        for (int i = 0; i < currentArr.length; i++) {
            currentArr[i] = lowerBound + (int) (Math.random() * (upperBound - lowerBound + 1));
            if (max < currentArr[i]) max = currentArr[i];
        }
        this.maxValue = max;
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

    /**
     * Checks the status of the UI thread and continue the execution according to that.
     */
    public void checkStatus() {
        if (!isSorting.get()) {
            throw new RuntimeException("Sort stopped by user.");
        }

        // Synchronized code block -> stops the sorting thread if sorting is not happening
        // Where pause and resume signals are caught
        synchronized (pauseLock) {
            while (isPaused.get()) {
                try {
                    // The sorting thread waits here and releases lock
                    pauseLock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    // Implement VisualCallback interface according to the app for algorithms to use

    @Override
    public void onCompare(int index1, int index2) {
        checkStatus();
        // Tell the UI thread to update the screen
        // It does not create a new thread only adds a task
        javafx.application.Platform.runLater(() -> {
            canvasController.redrawBar(currentArr, index1, SortAlgorithm.OperationType.COMPARE);
            canvasController.redrawBar(currentArr, index2, SortAlgorithm.OperationType.COMPARE);
        });

        // Since this code block is read by sortingThread, it sleeps
        try {
            Thread.sleep(currentSpeed); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        javafx.application.Platform.runLater(() -> {
            canvasController.redrawBar(currentArr, index1, SortAlgorithm.OperationType.DEFAULT);
            canvasController.redrawBar(currentArr, index2, SortAlgorithm.OperationType.DEFAULT);
        });
    }

    @Override
    public void onSwap(int index1, int index2) {
        checkStatus();

        javafx.application.Platform.runLater(() -> {
            canvasController.redrawBar(currentArr, index1, SortAlgorithm.OperationType.SWAP);
            canvasController.redrawBar(currentArr, index2, SortAlgorithm.OperationType.SWAP);
        });

        // Since this code block is read by sortingThread, it sleeps
        try {
            Thread.sleep(currentSpeed); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        javafx.application.Platform.runLater(() -> {
            canvasController.redrawBar(currentArr, index1, SortAlgorithm.OperationType.DEFAULT);
            canvasController.redrawBar(currentArr, index2, SortAlgorithm.OperationType.DEFAULT);
        });
    }

    @Override
    public void onIterate(int index) {
        checkStatus();

        javafx.application.Platform.runLater(() -> {
            canvasController.redrawBar(currentArr, index, SortAlgorithm.OperationType.ITERATE);
        });

        // Since this code block is read by sortingThread, it sleeps
        try {
            Thread.sleep(currentSpeed); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        javafx.application.Platform.runLater(() -> {
            canvasController.redrawBar(currentArr, index, SortAlgorithm.OperationType.DEFAULT);
        });
    }

    @Override
    public void onMove(int from, int to) {
        checkStatus();

        javafx.application.Platform.runLater(() -> {
            canvasController.drawEmptyBar(from);
            canvasController.redrawBar(currentArr, to, SortAlgorithm.OperationType.SWAP);
        });

        // Since this code block is read by sortingThread, it sleeps
        try {
            Thread.sleep(currentSpeed); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        javafx.application.Platform.runLater(() -> {
            canvasController.redrawBar(currentArr, to, SortAlgorithm.OperationType.DEFAULT);
        });
    }

    @Override
    public void onComplete() {
        checkStatus();

        javafx.application.Platform.runLater(() -> {
            canvasController.drawArray(currentArr, maxValue, false);
        });

        // Since this code block is read by sortingThread, it sleeps
        try {
            Thread.sleep(currentSpeed); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}