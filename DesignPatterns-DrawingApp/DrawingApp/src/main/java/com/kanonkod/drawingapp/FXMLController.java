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
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.color;
import model.Shape;
import model.Subject;

public class FXMLController implements Initializable, Observer {

    @FXML
    private Canvas canvas;

    @FXML
    private void drawCircle() {
        System.out.println("Drawing circle");
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
    }
    
    @FXML
    private void saveFrom(MouseDragEvent event) {
        
    }
    
    @FXML
    private void saveTo(MouseDragEvent event) {
        
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
        
    }

    @Override
    public void update(Shape shp) {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSubject(Subject sub) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

