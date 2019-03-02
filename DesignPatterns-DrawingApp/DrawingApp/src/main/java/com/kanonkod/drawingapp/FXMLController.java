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

    @FXML
    private void drawCircle(MouseEvent event) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        double fromX = event.getSceneX();
        double fromY = event.getSceneY();
        model.setFrom(fromX, fromY);
        double toX = fromX + 50;
        double toY = fromY + 50;
        model.setTo(toX, toY);
        model.addShape(canvas.getGraphicsContext2D());
    }
    
    @FXML
    private void saveFrom(MouseDragEvent event) {
        double fromX = event.getSceneX();
        double fromY = event.getSceneY();
        model.setFrom(fromX, fromY);
    }
    
    @FXML
    private void saveTo(MouseDragEvent event) {
        double toX = event.getSceneX();
        double toY = event.getSceneY();
        model.setTo(toX, toY);
        //model.draw("Circle", canvas.getGraphicsContext2D());
        //model.addShape();
    }

    @FXML
    private void clear() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("init controller");
        // TODO
        model = ModelFascade.getInstance();
        
    }
}
