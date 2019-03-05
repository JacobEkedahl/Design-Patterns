/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.kanonkod.drawingapp.command.RedoAdd;
import com.kanonkod.drawingapp.command.UndoAdd;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javafx.scene.canvas.GraphicsContext;
import model.interfaces.Command;
import javafx.scene.paint.Color;
import model.interfaces.Observer;
import model.interfaces.UndoCommand;

/**
 *
 * @author Jacob
 */
public class Drawing {

    List<Shape> shapes = new ArrayList<>();
     Stack<Command> undoCommands = new Stack<>();
     Stack<Command> redoCommands = new Stack<>();
    
    List<Shape> selectedShapes = new ArrayList<>();
    //Object - Subject pattern with methods ------------------------
    List<Observer> observers = new ArrayList<Observer>();

    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
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
        undoCommands.add(new UndoAdd(shape,this,shapes.size()-1));
        redoCommands.add(new RedoAdd(shape,this,shapes.size()-1));
        shapes.add(shape);
        // notifyAllObservers();
    }

    //is called from the views update method
    public void drawAll(GraphicsContext gc) {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        for (Shape shape : shapes) {
            shape.draw(gc);
        }
        
        for (Shape shape : selectedShapes) {
            //draw rect around
            Shape tmpRect = ShapeFactory.getShape("aMarker", shape.getFromX(), shape.getFromY(), shape.getToX(), shape.getToY(), Color.CORAL, 1, false);
            tmpRect.draw(gc);
        }
    }

    public void changeSize(Shape shape, double toX, double toY) {
        int index = shapes.indexOf(shape);

        if (index < 0) {
            return;
        }

        Shape shapeToChange = shapes.get(index);
        shapeToChange.changeSize(toX, toY);
        //notify observers
        notifyAllObservers();
    }
    
    public void removeSelected() {
        for (Shape shape : selectedShapes) {
            removeShape(shape);
        }
    }

     public void undoAdd(){
        if(!undoCommands.empty()){
            UndoCommand ua = (UndoCommand) undoCommands.pop();
            ua.undo();
        }
        
    }
    public void redoAdd(){
         if(!undoCommands.empty()){
          RedoAdd ra = (RedoAdd) redoCommands.pop();
          ra.redo();   
         }
        
    }
    
    public void repeat(Shape shape) {
        
        shapes.add(shape);
        notifyAllObservers();
    }


    public void clearOneImage(Shape shape, int index) {
        shapes.remove(shape);
        notifyAllObservers();
    } void removeShape(Shape shape) {
        int index = shapes.indexOf(shape);
        shapes.remove(index);
        notifyAllObservers();
    }
    
    public void deselectAll() {
        selectedShapes.clear();
        notifyAllObservers();
    }
    
    public void selectShapes(Shape shape) {
        //find all the object which are inside this area
        selectedShapes.clear();
        for (Shape orig : shapes) {
            if ((orig.getMinX() > shape.getMaxX()) ||  //orig is to right of marker
               (orig.getMaxX()< shape.getMinX()) ||  //orig is to left of marker
               (orig.getMinY() > shape.getMaxY()) || //orig is below marker
               (orig.getMaxY() < shape.getMinY()) ||
               (orig.equals(shape))){  //orig is above marker
                continue;
            }
            selectedShapes.add(orig);
        }
        System.out.println("selectedShapes size: " + selectedShapes.size());
        notifyAllObservers();
    }

    public void clear() {
        shapes = new ArrayList<>();
        notifyAllObservers();
        //notify observers
    }
}
