/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author fno
 */
public class Composite extends Shape{
    
    private List<Shape> components = new ArrayList<Shape>();
    
    public void addShapes(List<Shape> s){
        components = s;
    }
    public List<Shape> getShapes(){
        return components;
    }
    @Override
    void drawFill(GraphicsContext gc) {
         for(Shape s: components){
             s.drawFill(gc);
         }
    }

    @Override
    void drawHollow(GraphicsContext gc) {
          for(Shape s: components){
             s.drawHollow(gc);
         }
    
    }

    @Override
    void changeSize(double newX, double newY) {
            for(Shape s: components){
             s.changeSize(newX, newY);
           }
    }
    
}
