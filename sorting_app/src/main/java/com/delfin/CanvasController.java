package com.delfin;

import com.delfin.SortingAlgorithms.SortAlgorithm;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * CanvasController is a helper class to handle canvas operations in the SortingController.
 * Auhor: Delfin Eryılmaz
 * Date:
 */
public class CanvasController {
    private final Canvas canvas;
    private final GraphicsContext gc;
    private static final double GAP = 2;
    private static final double PADDING = 20;
    private final double MAX_HEIGHT;
    private double maxValue;
    private double barWidth;
    private double intitialStartY;

    /**
     * Constructor for the operate on canvas.
     * @param canvas
     */
    public CanvasController(Canvas canvas) {
        this.canvas = canvas;
        MAX_HEIGHT = canvas.getHeight() - PADDING;
        intitialStartY = canvas.getScaleY() + MAX_HEIGHT / 2;
        this.gc = canvas.getGraphicsContext2D();
    }

    /**
     * Handles the initial array drawing 
     */
    public void drawArray(int[] arr, double maxHeight, boolean isInitial) {
        if (!isInitial) {
            gc.setFill(Paint.valueOf("GREEN"));
        }

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        barWidth = ( (canvas.getWidth() - 2 * PADDING) - ((arr.length - 1) * GAP) ) / arr.length;
        
        for (int i = 0; i < arr.length; i++) {
            // Calculate proportional height
            double barHeight = ((double)arr[i] / maxHeight) * MAX_HEIGHT;
            
            // X position: 20px padding + index * (width + gap)
            double x = (i * (barWidth + GAP));
            
            // Y position: To make bars grow UP, start from bottom and subtract height
            double y = MAX_HEIGHT - barHeight;

            gc.fillRect(x, y, barWidth, barHeight);
        }
    }

    /**
     * Redraws the specific bar in the array.
     * @param index bar's position in the array
     */
    public void redrawBar(int[] arr, int index, SortAlgorithm.OperationType operationType) {
        // Decide the color of the bar based on the operation type
        switch (operationType) {
            case COMPARE:
                gc.setFill(Paint.valueOf("BLUE"));
                break;

            case SWAP:
                gc.setFill(Paint.valueOf("RED"));
                break;
            case ITERATE:
                gc.setFill(Paint.valueOf("YELLOW"));
                break;
            default:
                break;
        }

        double barHeight = ((double) arr[index] / maxValue) * MAX_HEIGHT;
        double x = index * (barWidth + GAP);
        double y = MAX_HEIGHT - barHeight;

        // Clear old bar
        gc.clearRect(x, y, barWidth, barHeight);

        // Draw the new bar
        gc.fillRect(x, y, barWidth, barHeight);
    }
}
