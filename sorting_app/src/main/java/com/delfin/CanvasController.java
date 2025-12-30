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

    /**
     * Constructor for the operate on canvas.
     * @param canvas
     */
    public CanvasController(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
    }

    public void drawArray() {
        gc.fillRect(50, 50, 100 - 2, 30);
    }
}
