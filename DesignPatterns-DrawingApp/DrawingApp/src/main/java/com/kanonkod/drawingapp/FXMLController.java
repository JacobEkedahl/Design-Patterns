package com.kanonkod.drawingapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import model.ModelLine;
import model.Shape;
import model.Subject;

public class FXMLController implements Initializable,Observer {
    
    private Subject subject;
    
    @FXML
    private Canvas canvas;
    
    @FXML
    private void drawCircle(ActionEvent event) {
        System.out.println("You clicked me!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void update(Shape shp) {
        if(shp instanceof ModelLine ){
            shp.drawShape();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSubject(Subject sub) {
        this.subject = sub;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
