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
    
    public void drawAll(GraphicsContext gc) {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
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
        //notify observers
        notifyAllObservers();
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
    }
    public void clear() {
        shapes = new ArrayList<>();
        notifyAllObservers();
        //notify observers
    }
}
