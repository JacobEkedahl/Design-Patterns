/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kanonkod.drawingapp.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import model.ModelFascade;

/**
 *
 * @author Jacob
 */
public class DrawingChooserController implements Initializable {

    @FXML
    private VBox scrollContainer;

    private ModelFascade model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = ModelFascade.getInstance();
        initButtons();
    }

    private void initButtons() {
        for (String s : model.getNames()) {
            Button choice = new Button(s);
            scrollContainer.getChildren().add(choice);
            
            choice.setOnAction(actionEvent -> {
                model.clearDrawing();
                model.setName(choice.getText());
                scrollContainer.getScene().getWindow().hide();
            });
        }
    }

}
