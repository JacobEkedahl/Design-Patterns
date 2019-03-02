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
public class ModelCircle extends Shape {
    public ModelCircle(float fromX, float fromY, float toX, float toY,Color col, float strokeWidth) {
        super(fromX, fromY, toX, toY,col,strokeWidth);
    }

    

    @Override
    void drawShape(GraphicsContext gc) {
        
        
        Circle circle = new Circle();
      
      float fromX = getFromX();
      float fromY = getFromY();
      
      float a = getFromX() - getToX();
      float b = getFromY() - getToY();
      
      float pyth = (float)Math.sqrt((a*a) + (b*b));
      
      circle.setCenterX(fromX);
      circle.setCenterY(fromY);
      circle.setRadius(pyth);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
