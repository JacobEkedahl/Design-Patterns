/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

/**
 *
 * @author Jacob
 */
public class Polygon extends Shape {
   

  
    public Polygon(float fromX, float fromY, float toX, float toY,Color col, float strokeWidth) {
        super(fromX, fromY, toX, toY,col,strokeWidth);
    }

    @Override
    void drawShape(GraphicsContext gc) {
        Rectangle r = new Rectangle();
        r.setX(getFromX());
        r.setY(getFromY());
        r.setWidth(getToX()-getFromX());
        r.setHeight(getToY()-getToX());
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
}
