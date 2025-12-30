package com.delfin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
/**
 * SortingController is the controller class for the SortingApp.fxml which is the main scene
 * for the application.
 * Author: Delfin Eryılmaz
 * Date: 
 */
public class SortingController {

    @FXML
    private Button mergeSortButton; 

    @FXML
    public void handleMergeSortButton(ActionEvent event) {
        System.out.println("Button was clicked!");
    }
}