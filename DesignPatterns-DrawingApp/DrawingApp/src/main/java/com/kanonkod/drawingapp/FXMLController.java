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
import model.Shape;
import model.Subject;

public class FXMLController implements Initializable,Observer {

    @FXML
    private Canvas canvas;
    ModelFascade model;
    Drawing drawing;
    Subject subject;
    @FXML
    private AnchorPane Anchor;
    @FXML
    private BorderPane border;
    @FXML
    private VBox vbox;
    @FXML
    private MenuBar menubar;
    @FXML
    private Menu File;
    @FXML
    private HBox Hbox;
    @FXML
    private ImageView highlight;
    
    
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

    private void clear() {
        model.clearDrawing();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("init controller");
        // TODO
        model = ModelFascade.getInstance();
        this.setSubject(this.drawing);
     //   this.drawing.register(this);
       
        
    }

    @Override
    public void update(Drawing drawing) {
        System.out.println("here's a drawing " + drawing.toString());
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSubject(Subject sub) {
        System.out.println("");
        this.subject = sub;
        
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
