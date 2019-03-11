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
    private Shape lineComponent = null;
    private Shape circleComponent = null;
    /**
     * Initialize the components
     */
    public void init(){
        if(lineComponent==null ){
             this.lineComponent = ShapeFactory.getShape("aLine", super.getFromX(), super.getFromY(), super.getToX(), super.getToY(), super.getCol(), super.getStrokeWidth(), super.getFill());
         }
        if(circleComponent==null ){
             this.circleComponent = ShapeFactory.getShape("anOval", super.getFromX(), super.getFromY(), super.getToX(), super.getToY(), super.getCol(), super.getStrokeWidth(), super.getFill());
         }   
    }
    
    
    @Override
    void drawFill(GraphicsContext gc) {
             init();
             lineComponent.draw(gc);
             circleComponent.draw(gc);             
    }

    @Override
    void drawHollow(GraphicsContext gc) {  
            init();
            lineComponent.draw(gc);
            circleComponent.draw(gc);
    }

    @Override
    void changeSize(double newX, double newY) {
             init();
             this.setEnd(newX, newY);
             lineComponent.changeSize(newX, newY);
             circleComponent.changeSize(newX, newY);        
    }
    
    
    
}
