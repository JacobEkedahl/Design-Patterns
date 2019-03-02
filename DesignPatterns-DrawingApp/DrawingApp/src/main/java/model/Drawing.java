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
public class Drawing implements Subject{
    List<Shape> shapes = new ArrayList<>();
    List<Observer> observers = new ArrayList<>();
    public Drawing() {
        
    }
    
    public void addShape(Shape shape) {
        //notify observers
        shapes.add(shape);
        notifyObservers(shape);
    }

    @Override
    public void register(Observer obj) {
        observers.add(obj);
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyObservers(Shape shape) {
        ArrayList<Observer> tmp = (ArrayList<Observer>) observers;
        
        for(Observer obj : tmp){
            obj.update(shape);
        }
        
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Shape getUpdate(Observer obj) {
        // I don't know yet
       return null;
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
