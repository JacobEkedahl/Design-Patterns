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
        //adjustSize(shape);
        queue.add(shape);
    }
    public void remove(Shape shape) {
        if(queue!=null && !queue.isEmpty()){
             queue.remove(shape);
        }
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

    private void adjustSize(Shape shape) {
        if(shape.getFromX() < getFromX()){
            setFromX(shape.getFromX());
        }
        if(shape.getFromY() < getFromY()){
            setFromY(shape.getFromY());
        }
        if(shape.getToX() > getToX()){
            setToX(shape.getToX());
        }
         if(shape.getToY() > getToY()){
            setToX(shape.getToY());
        }
    }

   

       
}
