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
public class CircleLineDiagonal extends Shape{
    private Shape shape1 = null;
    private Shape shape2 = null;
    
    public void init(){
        //System.out.println("initCircle diagonal");
        if(shape1==null ){
             this.shape1 = ShapeFactory.getShape("aLine", super.getFromX(), super.getFromY(), super.getToX(), super.getToY(), super.getCol(), super.getStrokeWidth(), super.getFill());
         }
        if(shape2==null ){
           
             this.shape2 = ShapeFactory.getShape("anOval", super.getFromX(), super.getFromY(), super.getToX(), super.getToY(), super.getCol(), super.getStrokeWidth(), super.getFill());
         }   
    }
    
    @Override
    void drawFill(GraphicsContext gc) {
             init();
             shape1.drawFill(gc);
             shape2.drawFill(gc);
             
    }

    @Override
    void drawHollow(GraphicsContext gc) {  
            init();
            shape1.drawHollow(gc);
            shape2.drawHollow(gc);
          

    }

    @Override
    void changeSize(double newX, double newY) {
             init();
             this.setEnd(newX, newY);
             
             shape1.changeSize(newX, newY);
             shape2.changeSize(newX, newY);
             
    }
    
    
    
}
