/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kanonkod.drawingapp.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import model.Drawing;
import model.Shape;
import model.interfaces.RedoCommand;
import propertychangers.PropertyChange;

/**
 *
 * @author fno
 */
public class RedoChange implements RedoCommand {
    private List<Shape> shapes;
    private Drawing drawing;
    private HashMap<Shape,PropertyChange> hmap;
    public RedoChange(List<Shape> shapes, Drawing drawing, HashMap<Shape, PropertyChange> hmap) {
        this.shapes = shapes;
        this.drawing = drawing;
        this.hmap = hmap;
    }
    
    //private PropertyChange property;
    
    /*public RedoChange(List<Shape> shapes, Drawing drawing, PropertyChange property) {
        this.shapes = shapes;
        this.drawing = drawing;
        this.property = property;
    }*/
    

    @Override
    public void redo() {
        
        HashMap<Shape,PropertyChange> newMap = new HashMap<>();
        for(Shape s : shapes){
            newMap.put(s, sample().getInstance(s));
        }
        
        this.drawing.updateUndoStack(new UndoChange(new ArrayList<Shape>(shapes),this.drawing,newMap));
        Iterator<Shape> iter = shapes.iterator();
        while(iter.hasNext()){
            Shape tmp = iter.next();
            PropertyChange p = hmap.get(tmp);
            p.doChange(tmp);
        }
         this.drawing.notifyAllObservers();
        /*PropertyChange p = null;
        //use property to change something   
        p = property.getInstance(shapes.get(0));
//        this.drawing.updateUndoStack(new UndoChange(new ArrayList<Shape>(shapes),this.drawing,p));
        for(Shape s: shapes){
            property.doChange(s);       
        }
       */
    }
    public PropertyChange sample(){
       return hmap.get(this.shapes.get(0));
    }
}
