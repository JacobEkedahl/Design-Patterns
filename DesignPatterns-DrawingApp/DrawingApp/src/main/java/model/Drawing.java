/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.kanonkod.drawingapp.command.RedoAdd;
import com.kanonkod.drawingapp.command.UndoAdd;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.interfaces.Observer;
import model.interfaces.UndoCommand;

/**
 *
 * @author Jacob
 */
public class Drawing {

    List<Shape> shapes = new ArrayList<>();
    List<Shape> selectedShapes = new ArrayList<>();

    //Object - Subject pattern with methods ------------------------
    List<Observer> observers = new ArrayList<Observer>();
    
    private final Object MUTEX= new Object();

    public void notifyAllObservers() {
        for (Observer observer : observers) {
           synchronized (MUTEX) {
              observer.update();  
           } 
        }
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }
    //End object - subject pattern

    public Drawing() {
         
    }

    public void addShape(Shape shape) {
        if (shape == null) {
            return;
        }
        
        //notify observers
      //  undoCommands.add(new UndoAdd(shape,this,shapes.size()-1));
       // redoCommands.add(new RedoAdd(shape,this,shapes.size()-1));  
        shapes.add(shape);
        // notifyAllObservers();
    }

    //is called from the views update method
    public void drawAll(GraphicsContext gc) {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
       
        for (Shape shape : selectedShapes) {
            //draw rect around
            Shape tmpRect = ShapeFactory.getShape("aMarker", shape.getFromX(), shape.getFromY(), shape.getToX(), shape.getToY(), Color.CORAL, 1, false);
            tmpRect.draw(gc);
        }

        for (Shape shape : shapes) {
            shape.draw(gc);
        }

    }

    public void changeSize(Shape shape, double toX, double toY) {
        int index = shapes.indexOf(shape);

        if (index < 0) {
            return;
        }

        Shape shapeToChange = shapes.get(index);
        shapeToChange.changeSize(toX, toY);
        notifyAllObservers();
    }
      

    public void repeat(Shape shape) {
        
        shapes.add(shape);
        notifyAllObservers();
    }

    public void removeSelected() {
        for (Shape shape : selectedShapes) {
            removeShape(shape);
        }
    }

    public void removeShape(Shape shape) {
        int index = shapes.indexOf(shape);
        shapes.remove(index);
        notifyAllObservers();
    }

    public void deselectAll() {
        selectedShapes.clear();
        notifyAllObservers();
    }
    
    public void changeSelectedColor(Color newCol) {
        selectedShapes.forEach((shape) -> {
            shape.setCol(newCol);
        });
        
        notifyAllObservers();
    }
    
    public void changeSelectedFill(boolean newVal) {
        selectedShapes.forEach((shape) -> {
            shape.setFill(newVal);
        });
        
        notifyAllObservers();
    }

    public void selectShapes(Shape shape) {
        //find all the object which are inside the marker area and add to selectedShapes
        selectedShapes.clear();
        for (Shape orig : shapes) {
            if ((orig.getMinX() > shape.getMaxX()) //orig is to right of marker
                    ||
                    (orig.getMaxX() < shape.getMinX())//orig is to left of marker
                    || 
                    (orig.getMinY() > shape.getMaxY())//orig is below marker
                    || 
                    (orig.getMaxY() < shape.getMinY()) //orig is above marker
                    || (orig.equals(shape))) { //this shape is the marker
                continue;
            }
            selectedShapes.add(orig);
            System.out.println("orig: " + orig);
        }
        
        notifyAllObservers();
    }
    public boolean updateComposite(Shape selectedShape) {
         for (Shape s : shapes) {
            if(s instanceof ShapeComposite){
                ShapeComposite composite = (ShapeComposite) s;
                if(s.isInsideAnotherShape(selectedShape) || s.isCoveringAnotherShape(selectedShape)){  
                    shapes.remove(selectedShape);
                    composite.add(selectedShape);
                    return true;
                }
            }
        }
        return false;
    }
    
    public void initializeComposite(ShapeComposite composite, Shape shape, Shape outer){
        shapes.remove(outer);
        shapes.remove(shape);
        composite.add(outer);
        composite.add(shape);
        shapes.add(composite);
    }
    public Shape retrieveCompositeOutline(Shape selected){
         if(shapes.size()<=1){
          return null;
        }
        for (Shape s : shapes) {
            if(!s.equals(selected)){
               //if(checkIfInsideShape(s,selected)){
               if(s.isCoveringAnotherShape(selected)){
                  if(!( s instanceof ShapeComposite)){
                    return s;
              }
            }
            else if(s.isInsideAnotherShape(selected)){
                return selected;
              }   
            }
        }
        
        return null;
    }  
    public void clear() {
        shapes = new ArrayList<>();
        notifyAllObservers();
        //notify observers
    }
    public void printAll(){
        for(Shape e: shapes){
            System.out.println("PA: " + e.toString());
        }
    }

}
