/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author fno
 */
public class SmileyShape extends Shape{
    private Shape shape1 = ShapeFactory.getShape("aLine", super.getFromX(), super.getFromY(), super.getToX(), super.getToY(), super.getCol(), super.getStrokeWidth(), super.getFill());
    private Shape shape2 = ShapeFactory.getShape("anOval",super.getFromX()+8, super.getFromY()-4, super.getToX()-4, super.getToY()+4,  super.getCol(), super.getStrokeWidth(), super.getFill());
    
    

    @Override
    void drawFill(GraphicsContext gc) {
        // shape1 = ShapeFactory.getShape("aLine", super.getFromX(), super.getFromY(), super.getToX(), super.getToY(), super.getCol(), super.getStrokeWidth(), super.getFill());
        // shape2 = ShapeFactory.getShape("anOval",super.getFromX()+8, super.getFromY()-4, super.getToX()-4, super.getToY()+4,  super.getCol(), super.getStrokeWidth(), super.getFill());
         shape1.drawFill(gc);
         shape2.drawFill(gc);
             
    }

    @Override
    void drawHollow(GraphicsContext gc) {  
        shape1.drawHollow(gc);
        shape2.drawHollow(gc);
       // gc.strokeLine(shape1.getFromX(), shape1.getFromY(), shape1.getToX(), shape1.getToY());
        //gc.strokeLine(shape2.getFromX(), shape2.getFromY(), shape2.getToX(), shape2.getToY());
        
    }

    @Override
    void changeSize(double newX, double newY) {
        shape1.changeSize(newX, newY);
        shape2.changeSize(newX, newY);
    }
    
}
