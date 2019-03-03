/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.kanonkod.drawingapp.Observer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jacob
 */
public class Drawing implements Subject {
    List<Shape> shapes = new ArrayList<>();
    Observer observer;
    public Drawing() {
        
    }
    
    public void addShape(Shape shape) {
        //notify observers
        shapes.add(shape);
        System.out.println("added shape!");
    }
    
    public void changeSize(Shape shape, double toX, double toY) {
        int index = shapes.indexOf(shape);
        Shape shapeToChange = shapes.get(index);
        shapeToChange.setEnd(toX, toY);
        
        System.out.println("changing size: " + toX + ": " + toY);
        //notify observers
    }
    
    public void clear() {
        shapes = new ArrayList<>();
        //notify observers
    }

    @Override
    public void register(Observer obj) {
        observer = obj;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyObservers(Drawing drawing) {
        
        observer.update(drawing);
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Shape getUpdate(Observer obj) {
        System.out.println("here's an update");
        return null;
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
