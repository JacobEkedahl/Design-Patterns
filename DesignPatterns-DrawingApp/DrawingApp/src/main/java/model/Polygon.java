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
public class Polygon extends Shape {
    public Polygon(float fromX, float fromY, float toX, float toY) {
        super(fromX, fromY, toX, toY);
    }

    @Override
    public void drawShape() {
        Rectangle r = new Rectangle();
        r.setX(getFromX());
        r.setY(getFromY());
        r.setWidth(getToX()-getFromX());
        r.setHeight(getToY()-getToX());
       // r.setArcWidth(20);
      //  r.setArcHeight(20);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
