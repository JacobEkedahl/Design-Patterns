/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.LinkedList;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author fno
 */
public class ShapeComposite extends Shape {
    
    LinkedList<Shape> queue;
    
    public ShapeComposite(){
        queue = new LinkedList<Shape>();
    }
    
    public void add(Shape shape){
        queue.add(shape);
    }
    public int getSize(){
        return queue.size();
    }
    
    
    
    @Override
    void drawFill(GraphicsContext gc) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void drawHollow(GraphicsContext gc) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void changeSize(double newX, double newY) {
     //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
