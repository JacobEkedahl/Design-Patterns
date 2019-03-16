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
import java.util.stream.Collectors;
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
    public RedoChange(Drawing drawing, HashMap<Shape, PropertyChange> hmap) {
        this.drawing = drawing;
        this.hmap = hmap;
    }
    /**
     * 
     */
    @Override
    public void redo() {       
        HashMap<Shape,PropertyChange> newMap = fillHashMap();
        this.drawing.updateUndoStack(new UndoChange(this.drawing,newMap));
        doChanges();
        this.drawing.notifyAllObservers();     
    }
    /**
     * get property type
     * @return 
     */
     public PropertyChange sample(){
       List<PropertyChange> values = hmap.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
       return values.get(0);
    }
    public HashMap<Shape,PropertyChange> fillHashMap(){
        HashMap<Shape,PropertyChange> newMap = new HashMap<>();
          for (Map.Entry<Shape, PropertyChange> entry : hmap.entrySet()) {
                 newMap.put(entry.getKey(), sample().getInstance(entry.getKey()));
	}
        return newMap;  
    }
    public void doChanges(){
        List<Shape> thisList = hmap.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
        Iterator<Shape> iter = thisList.iterator();
        while(iter.hasNext()){
            Shape tmp = iter.next();
            PropertyChange p = hmap.get(tmp);
            p.doChange(tmp);
        }
    }
}
