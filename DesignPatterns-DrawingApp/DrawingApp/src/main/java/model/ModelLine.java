/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import javafx.scene.shape.*;

/**
 *
 * @author Jacob
 */
public class ModelLine extends Shape {
    public ModelLine(float fromX, float fromY, float toX, float toY) {
        super(fromX, fromY, toX, toY);
    }

    @Override
    public void drawShape() {
        Line l = new javafx.scene.shape.Line();
        
        l.setStartX(getFromX());
        l.setStartY(getFromY());
        l.setEndX(getToX());
        l.setEndY(getToY());
        
       
    }
}
