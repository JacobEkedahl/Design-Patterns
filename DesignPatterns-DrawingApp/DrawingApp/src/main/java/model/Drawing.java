
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.kanonkod.drawingapp.command.RedoAdd;
import com.kanonkod.drawingapp.command.UndoAdd;
import com.kanonkod.drawingapp.command.UndoChange;
import com.kanonkod.drawingapp.command.UndoDelete;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import propertychangers.ColorChange;
import model.interfaces.Observer;
import model.interfaces.RedoCommand;
import model.interfaces.UndoCommand;
import propertychangers.FillChange;
import propertychangers.PropertyChange;
import propertychangers.StrokeChange;

/**
 *
 * @author Jacob
 */
public class Drawing {

    List<Shape> shapes = new ArrayList<>();
    private Stack<UndoCommand> undoCommands = new Stack<>();
    private Stack<RedoCommand> redoCommands = new Stack<>();
    
    List<Shape> selectedShapes = new ArrayList<>();
    private String name = "";

    public Drawing() {

    }

    /**
     * Parse database shape objects into model objects
     * alert all observers on the position of the objects.
     * @param dbDrawing 
     */
    public void init(DrawingDAO dbDrawing) {
        this.name = dbDrawing.getName();
        for (ShapeDAO shapeDAO : dbDrawing.getShapes()) {
            Shape newShape  = ShapeFactory.getShape(shapeDAO);
            if (!shapes.contains(newShape)) {
                shapes.add(newShape);
            } else {
                
            }
        }

        notifyAllObservers();
    }

    //Object - Subject pattern with methods ------------------------
    List<Observer> observers = new ArrayList<Observer>();

    /**
     * execute observer function.
     */
    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    /**
     * attached to the controller
     * @param observer 
     */
    public void attach(Observer observer) {
        observers.add(observer);
    }
   /**
    * Add a chosen shape
    * @param shape 
    */
    public void addShape(Shape shape) {
        if (shape == null) {
            return;
        }
        shapes.add(shape);       
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

    /**
     * Responds to the dragging of the object by the user.
     * @param shape
     * @param toX
     * @param toY 
     */
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
    /**
     * 
     * In case the drag-on selector boxes in several shape, we'll have the
     * shapes shoved in a list for each instance of undodelete. and recreated
     * with undodelete. 
     */

    public void removeSelected() {
        if(!selectedShapes.isEmpty()){
            updateUndoStack(new UndoDelete(new ArrayList<Shape>(selectedShapes),this));
        }
        for (Shape shape : selectedShapes) {
            removeShape(shape);
        }
        notifyAllObservers();
    }    
    /**
     * pop a stack and execute undo.
     */
     public void undoCommand(){  
        if(!undoCommands.empty()){
            UndoCommand ua =  undoCommands.pop();
            ua.undo();
        } 
    }
     /**
     * pop a stack and execute redo.
     */
    public void redoCommand(){
         if(!redoCommands.empty()){
          RedoCommand ra = (RedoCommand) redoCommands.pop();         
          ra.redo();   
         }
    }
    /**
     * make a shape visible, invoked by undo/redo action.
     * @param shape 
     */
    public void repeat(Shape shape) {
        shapes.add(shape);
        notifyAllObservers();
    }

    /**
     * remove a shape from the list and from the view
     * @param shape 
     */
    public void removeShape(Shape shape) {
        shapes.remove(shape);
        notifyAllObservers();
    }

    /**
     * when the aMarker shape is not in use.
     */
    public void deselectAll() {
        selectedShapes.clear();
        notifyAllObservers();
    }

    /**
     * adjust the strokewidth of the shapes inside the marker box
     * @param width 
     */
    public void changeSelectedWidth(double width) {
        if(selectedShapes!=null && !selectedShapes.isEmpty()){
            StrokeChange stroke = new StrokeChange(0);
            makeUndoChangePreservation(stroke);
        }
        for (Shape shape : selectedShapes) {
            shape.setStrokeWidth(width);
        }
        notifyAllObservers();
    }
    /**
     * Change the color of the marked shape(s)
     * @param newCol 
     */
    public void changeSelectedColor(Color newCol) {
        if(selectedShapes!=null && !selectedShapes.isEmpty()){
           ColorChange colorChange = new ColorChange(Color.WHITE); 
           makeUndoChangePreservation(colorChange);
        }
        System.out.println("adding new change to stack");
        selectedShapes.forEach((shape) -> {
            shape.setCol(newCol);
        });
        notifyAllObservers();
    }
    /**
     * Toggle the paint-fill of the marked shape(s)
     * @param newVal 
     */
    public void changeSelectedFill(boolean newVal) {   
         if(selectedShapes!=null &&  !selectedShapes.isEmpty()){
             FillChange fill = new FillChange(true);
             makeUndoChangePreservation(fill); 
         }
        selectedShapes.forEach((shape) -> {
            shape.setFill(newVal);
        });
        notifyAllObservers();
    }
    /**
     * find all the object which are inside the marker area and add to selectedShapes
     * @param shape 
     */
    public void selectShapes(Shape shape) {
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
    /**
     * clears out all Shapes.
     */
    public void clear() {
        shapes = new ArrayList<>();
        notifyAllObservers();
    }  
  /**
   * Add undocommand to the stack
   * @param undoCommand 
   */
  public void updateUndoStack(UndoCommand undoCommand){
        undoCommands.add(undoCommand);
   }
  /**
   * Add redocommand to the stack
   * @param undoCommand 
   */
   public void updateRedoStack(RedoCommand redoCommand){
       redoCommands.add(redoCommand);
   }
   /**
    * 
    * @param currentShapes
    * @return 
    */
   public Shape getPropetyFromBundle(List<Shape> currentShapes){
       if(currentShapes != null&&!currentShapes.isEmpty()){
           return currentShapes.get(0);
       }
       return null;
   }
   
   /**
    * 
    * @param shapes: key for hashmap.
    * @param pc : value for hashmap
    * @return new hashmap.
    */
   public HashMap<Shape,PropertyChange> getPropertyHashMap(List<Shape> shapes, PropertyChange pc){
         HashMap<Shape,PropertyChange> properties = new HashMap<>();
         for(Shape s : shapes){
             properties.put(s,pc.getInstance(s));
         }
         return properties;
   }
   /**
    * 
    * @param property : hash map with shapes associating with a property that will change.
    */
   public void makeUndoChangePreservation(PropertyChange property){
        HashMap<Shape,PropertyChange> hmap = getPropertyHashMap(selectedShapes,property); 
        UndoChange u = new UndoChange(this,hmap);
        updateUndoStack(u);  
   }
     
    @Override
    public String toString() {
        return "Drawing{" + "shapes=" + shapes + ", selectedShapes=" + selectedShapes + ", name=" + name + '}';
    }

}
