package com.kanonkod.drawingapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.color;
import model.ModelCircle;
import model.ModelFascade;

public class FXMLController implements Initializable {

    @FXML
    private Canvas canvas;
    ModelFascade model;

    //mouse dragged, change size/move selected object
    @FXML
    private void changeSize(MouseEvent event) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double toX = event.getX();
        double toY = event.getX();
        model.setEnd(toX, toY);
    }
    
    //add shape, mouse pressed
    @FXML
    private void saveFrom(MouseEvent event) {
        System.out.println("save from!");
        double fromX = event.getX();
        double fromY = event.getX();
        model.addShape(fromX, fromY);
    }
    
    //mouse released, deselect
    @FXML
    private void saveTo(MouseEvent event) {
        model.deselect();
    }

    @FXML
    private void clear() {
        model.clearDrawing();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("init controller");
        // TODO
        model = ModelFascade.getInstance();
        
    }
}
