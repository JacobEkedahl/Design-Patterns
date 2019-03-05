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
    
    private LinkedList<Shape> queue;
    
  
   
    
    public void add(Shape shape){
        if(queue==null){
            queue = new LinkedList<>();
        }
        queue.add(shape);
    }
    public int getSize(){
        return queue.size();
    }
    
    void draw(GraphicsContext gc) {
        System.out.println("doing draw");
         for(Shape e : queue){
            e.draw(gc);
        }
    }
    
    @Override
    void drawFill(GraphicsContext gc) {
        for(Shape e : queue){
            e.drawFill(gc);
        }
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void drawHollow(GraphicsContext gc) {
        for(Shape e : queue){
            e.drawHollow(gc);
        }
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void changeSize(double newX, double newY) {
          for(Shape e : queue){
            e.changeSize(newX,newY);
        }
     //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        if(queue==null){
            return null;
        }
        return "ShapeComposite{" + "queue=" + queue.toString() + '}';
    }
    
    
    
}
