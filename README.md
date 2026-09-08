# Sorting-App

A JavaFX desktop app that visualizes sorting algorithms in real time on a canvas, with pause/resume and adjustable speed.

## Features

- **Algorithms**: Bubble Sort, Insertion Sort, Selection Sort, Merge Sort (2-way in-place, 2-way out-of-place, 3-way), QuickSort (first/random/median pivot)
- **Visualization**: each compare, swap, iterate, and shift is drawn on a canvas, color-coded by operation
- **Controls**: generate a random array (configurable size and value range), adjust playback speed, pause/resume, and reset

Sorting runs on a background thread so the UI stays responsive while a sort is in progress.

## Requirements

- Java 17+
- Maven

## Running

```bash
cd sorting_app
mvn javafx:run
```

## Project layout

```
sorting_app/
  src/main/java/com/delfin/
    App.java                 # JavaFX application entry point
    Main.java                # launches App
    SortingController.java   # FXML controller: UI wiring, threading, pause/resume
    CanvasController.java    # draws/redraws array bars on the canvas
    SortingAlgorithms/       # SortAlgorithm interface + one class per algorithm
  src/main/resources/
    SortingApp.fxml          # UI layout
    style.css
```
