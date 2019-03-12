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
import java.util.Map;
import javafx.scene.paint.Color;
import model.Drawing;
import model.Shape;
import propertychangers.PropertyChange;
import model.interfaces.UndoCommand;

/**
 *
 * @author fno
 */
public class UndoChange implements UndoCommand{
    private List<Shape> shapes;
    private Drawing drawing;
    private PropertyChange  changer;
    private HashMap<Shape,PropertyChange> hmap;
    //purple  //black
   /* public UndoChange(List<Shape> shapes, Drawing drawing, PropertyChange property) {
        this.shapes = shapes;
        this.drawing = drawing;
        this.changer = property;
    }*/
     public UndoChange(List<Shape> shapes, Drawing drawing, HashMap<Shape,PropertyChange> hash) {
        this.shapes = shapes;
        this.drawing = drawing;
        this.hmap = hash;
    }
    @Override
    public void undo() {
       
        
       
        //use property to change something   
      /*  p = changer.getInstance(shapes.get(0));
        this.drawing.updateRedoStack(new RedoChange(new ArrayList<Shape>(shapes),this.drawing,p));
        for(Shape s: shapes){
            changer.doChange(s);       
        }
        this.drawing.notifyAllObservers();
        
        
        
         HashMap<Shape,PropertyChange> colors = new HashMap<>();
         for(Shape s : shapes){
             colors.put(s,new ColorChange(s.getCol()));
         }
         return colors;
        */
        for (Map.Entry<Shape, PropertyChange> entry : hmap.entrySet()) {
                    
		    //System.out.println(entry.getKey() + " = " + entry.getValue());
	}

      
        HashMap<Shape,PropertyChange> newMap = new HashMap<>();
        for(Shape s : shapes){
            newMap.put(s, sample().getInstance(s));
        }
        this.drawing.updateRedoStack(new RedoChange(new ArrayList<Shape>(shapes),this.drawing,newMap));
        Iterator<Shape> iter = shapes.iterator();
        while(iter.hasNext()){
            Shape tmp = iter.next();
            PropertyChange p = hmap.get(tmp);
            p.doChange(tmp);
        }
        
        this.drawing.notifyAllObservers();
         
       // this.drawing.updateRedoStack(new RedoChange(new ArrayList<Shape>(shapes),this.drawing));
    }
    public PropertyChange sample(){
       return hmap.get(this.shapes.get(0));
    }
    
}
