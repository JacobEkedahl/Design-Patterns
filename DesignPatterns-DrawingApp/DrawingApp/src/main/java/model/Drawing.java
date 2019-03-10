
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.kanonkod.drawingapp.command.RedoAdd;
import com.kanonkod.drawingapp.command.UndoAdd;
import com.kanonkod.drawingapp.command.UndoDelete;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javafx.scene.canvas.GraphicsContext;
import model.interfaces.Command;
import javafx.scene.paint.Color;
import model.interfaces.Observer;
import model.interfaces.RedoCommand;
import model.interfaces.UndoCommand;

/**
 *
 * @author Jacob
 */
public class Drawing {

    List<Shape> shapes = new ArrayList<>();
    private Stack<Command> undoCommands = new Stack<>();
    private Stack<Command> redoCommands = new Stack<>();
    
    List<Shape> selectedShapes = new ArrayList<>();
    private String name = "";

    public Drawing() {

    }

    public void init(DrawingDAO dbDrawing) {
        this.name = dbDrawing.getName();
       // selectedShapes.clear();
       // this.shapes.clear();
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

    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
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
    /**
     * For undo-redo delete, we use recursion we include in the constructor,
     * how often it should invoke the command, which is the size of the selected
     * list and subtract with one because the client personally choose undo.
     */

    public void removeSelected() {
        
        int numAutoPops = 0;
        boolean doRecursion = false;
        if(selectedShapes.size()>1){
             numAutoPops = selectedShapes.size()-1;
             doRecursion = true;
        }
        else{
             numAutoPops = 0;
        }
        int popIndex =0;
        for (Shape shape : selectedShapes) {          
             updateUndoStack(new UndoDelete(shape,this,popIndex,numAutoPops,doRecursion));
             popIndex++;
             removeShape(shape);
          }
        
        notifyAllObservers();
    }

     public void undoCommand(){  
        if(!undoCommands.empty()){
            UndoCommand ua = (UndoCommand) undoCommands.pop();
            ua.undo();
        }
        
    }
    public void redoCommand(){
         if(!redoCommands.empty()){
          RedoCommand ra = (RedoCommand) redoCommands.pop();         
          ra.redo();   
         }
    }
    
    public void repeat(Shape shape) {
        shapes.add(shape);
        notifyAllObservers();
    }

    public void removeShape(Shape shape) {
        shapes.remove(shape);
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
    
   public void updateUndoStack(Shape newShape){
       //we don't want the stack to recognize the marker thingy
       if(!(newShape instanceof aMarker)){
           undoCommands.add(new UndoAdd(newShape,this));
       }
   }
  
  public void updateUndoStack(UndoCommand undoCommand){
        undoCommands.add(undoCommand);
        
   }
   
   public void updateRedoStack(RedoCommand redoCommand){
       redoCommands.add(redoCommand);
   }
  
    @Override
    public String toString() {
        return "Drawing{" + "shapes=" + shapes + ", selectedShapes=" + selectedShapes + ", name=" + name + '}';
    }
}
