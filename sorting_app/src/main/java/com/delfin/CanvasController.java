package com.delfin;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * CanvasController is a helper class to handle canvas operations in the SortingController.
 * Auhor: Delfin Eryılmaz
 * Date:
 */
public class CanvasController {
    private final Canvas canvas;
    private final GraphicsContext gc;
    private static double GAP;
    private static double MAX_HEIGHT;
    private static double intitialStartY;

    /**
     * Constructor for the operate on canvas.
     * @param canvas
     */
    public CanvasController(Canvas canvas) {
        this.canvas = canvas;
        MAX_HEIGHT = canvas.getHeight() - 20;
        intitialStartY = canvas.getScaleY() + MAX_HEIGHT / 2;
        this.gc = canvas.getGraphicsContext2D();
    }

    /**
     * Handles the initial array drawing 
     */
    public void drawArray(int[] arr, double maxHeight) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        double spacing = 2; // Space between bars
        double barWidth = (canvas.getWidth() - 40) / arr.length - spacing;
        
        for (int i = 0; i < arr.length; i++) {
            // Calculate proportional height
            double barHeight = (arr[i] / maxHeight) * MAX_HEIGHT;
            
            // X position: 20px padding + index * (width + gap)
            double x = 20 + (i * (barWidth + spacing));
            
            // Y position: To make bars grow UP, start from bottom and subtract height
            double y = canvas.getHeight() - barHeight;

            gc.fillRect(x, y, barWidth, barHeight);
        }
    }

    /**
     * Redraws the specific bar in the array
     * @param index bar's position in the array
     */
    public void redrawBar(int index) {
        
    }
}
