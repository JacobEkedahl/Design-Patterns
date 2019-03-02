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
    Observer singleObsever;
    public Drawing() {
        
    }
    
    public void addShape(Shape shape) {
        //notify observers
        shapes.add(shape);
        
    }
    
    public void clear() {
        shapes = new ArrayList<>();
    }

    @Override
    public void register(Observer obj) {
        this.singleObsever = obj;
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyObservers(Shape shp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Shape getUpdate(Observer obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

/**
 *
 * @author Jacob
 */

