package com.kanonkod.drawingapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.color;
import model.Drawing;
import model.ModelCircle;
import model.ModelFascade;
import model.ModelLine;
import model.Shape;
import model.interfaces.Observer;

public class FXMLController extends Observer implements Initializable  {

    @FXML
    private Canvas canvas;
    ModelFascade model;
    Drawing drawing;
    //Subject subject;    
    
    //mouse dragged, change size/move selected object
    @FXML
    private void changeSize(MouseEvent event) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double toX = event.getX();
        double toY = event.getY();
        model.setEnd(toX, toY);
    }
    
    //add shape, mouse pressed
    @FXML
    private void saveFrom(MouseEvent event) {
      //  model.clearDrawing();
        double fromX = event.getX();
        double fromY = event.getY();
        
        model.selectShape(new ModelCircle());
        model.addShape(fromX, fromY);
    }
    
    //mouse released, deselect
    @FXML
    private void saveTo(MouseEvent event) {
        model.deselect();
    }

    private void clear() {
        model.clearDrawing();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("init controller");
        // TODO
        model = ModelFascade.getInstance();
        model.getDrawing().attach(this);
    }

    @Override
    public void update() {
        //the drawing has been changed, clear and draw it
        model.draw(canvas.getGraphicsContext2D());
        
    }

    @FXML
    private void clickedItemClose(ActionEvent event) {
        System.out.println("clicked menu item");
    }

    @FXML
    private void clickedHighLight(MouseEvent event) {
        System.out.println("Clicked highlight");
    }
}
