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
      //  System.out.println("adding");
        queue.add(shape);
    }
    public boolean remove(Shape shape){
        if(queue!=null && !queue.isEmpty()){
            return queue.remove(shape);
        }
        return false;
    }
    public int getSize(){
        return queue.size();
    }
    
    void draw(GraphicsContext gc) {
         for(Shape e : queue){
            e.draw(gc);
        }
    }
    
    @Override
    void drawFill(GraphicsContext gc) {
        for(Shape e : queue){
            e.drawFill(gc);
        }
    }

    @Override
    void drawHollow(GraphicsContext gc) {
        for(Shape e : queue){
            e.drawHollow(gc);
        }
    }

    @Override
    void changeSize(double newX, double newY) {
          for(Shape e : queue){
            e.changeSize(newX,newY);
        }
    }

    @Override
    public String toString() {
        if(queue==null){
            return null;
        }
        return "ShapeComposite{" + "queue=" + queue.toString() + '}';
    }

    
    
    
    
}
