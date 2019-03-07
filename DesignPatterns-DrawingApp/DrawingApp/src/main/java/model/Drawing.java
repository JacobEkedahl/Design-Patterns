
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
import java.util.Iterator;
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
    private String name = "";

    public Drawing() {

    }

    public void init(DrawingDAO dbDrawing) {
        this.name = dbDrawing.getName();
        selectedShapes.clear();
        this.name = dbDrawing.getName();
        this.shapes.clear();
        for (ShapeDAO shapeDAO : dbDrawing.getShapes()) {
            shapes.add(ShapeFactory.getShape(shapeDAO));
        }

        notifyAllObservers();
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }

    public List<Observer> getObservers() {
        return observers;
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
        //notify observers
        notifyAllObservers();
    }

    public void removeSelected() {
        for (Shape shape : selectedShapes) {
            removeShape(shape);
        }

        notifyAllObservers();
    }
      

    public void repeat(Shape shape) {
        
        shapes.add(shape);
        notifyAllObservers();
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

    public void changeSelectedWidth(double width) {
        for (Shape shape : selectedShapes) {
            shape.setStrokeWidth(width);
        }

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

     
     
    public ArrayList<Shape> retrieveShapesWithin(Shape selected){
         if(shapes.size()<=1){
          return null;
        }
        ArrayList<Shape> insideShape = new ArrayList<Shape>();         
        for (Shape s : shapes) {
            if(!s.equals(selected)){              
               if(selected.isCoveringAnotherShape(s)){            
                      insideShape.add(s);  
            }                         
         }
        } 
        if(insideShape.size()>0){           
            return insideShape;
        }
        return null;
     }
    public ArrayList<Shape> retrieveCoveringShapes(Shape selected){
         if(shapes.size()<=1){
          return null;
        }
        ArrayList<Shape> outer = new ArrayList<Shape>();         
        for (Shape s : shapes) {
            if(!s.equals(selected)){              
               if(selected.isInsideAnotherShape(s)){            
                      outer.add(s);  
            }                         
         }
        } 
        if(outer.size()>0){           
            return outer;
        }
        return null;
     }
   
     public ShapeComposite initializeComposite(ShapeComposite composite, Shape shape, ArrayList<Shape> outer){
        System.out.println("Initialize comp");
        for(Shape s : outer){
            shapes.remove(s);
            composite.add(s);
        }
        shapes.remove(shape);
        composite.add(shape);
        shapes.add(composite);
        return composite;
    }
     public ShapeComposite initializeComposite(ShapeComposite composite,Shape outer, Shape shape){
        shapes.remove(outer);
        composite.add(outer);
        shapes.remove(shape);
        composite.add(shape);
        shapes.add(composite);
        return composite;
        
        
    }
     
    public void selectShapes(Shape shape) {
        //find all the object which are inside the marker area and add to selectedShapes
        selectedShapes.clear();
        for (Shape orig : shapes) {
            if ((orig.getMinX() > shape.getMaxX()) //orig is to right of marker
                    || (orig.getMaxX() < shape.getMinX())//orig is to left of marker
                    || (orig.getMinY() > shape.getMaxY())//orig is below marker
                    || (orig.getMaxY() < shape.getMinY()) //orig is above marker
                    || (orig.equals(shape))) { //this shape is the marker
                continue;
            }
            selectedShapes.add(orig);
            System.out.println("orig: " + orig);
        }

        System.out.println("selectedShapes size: " + selectedShapes.size());
        notifyAllObservers();
    }

    public void clear() {
        shapes = new ArrayList<>();
        notifyAllObservers();
    }
     public void printAll(){
        for(Shape e: shapes){
            System.out.println("PA: " + e.toString());
        }
    }
      public void printAll(ArrayList<Shape> ksd){
        for(Shape e: ksd){
            System.out.println("PP: " + e.toString());
        }
    }
    public void undoAdd(){
        
    }
    public void undoDelete(){
        
    }
    public void redoAdd(){
        
    }

    @Override
    public String toString() {
        return "Drawing{" + "shapes=" + shapes + ", selectedShapes=" + selectedShapes + ", name=" + name + '}';
    }
}
