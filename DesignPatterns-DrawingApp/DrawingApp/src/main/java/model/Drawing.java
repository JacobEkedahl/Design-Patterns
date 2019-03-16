
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.ChangeStrategies.ChangeColStrategy;
import model.ChangeStrategies.ChangeFillStrategy;
import model.ChangeStrategies.ChangeWidthStrategy;
import model.Operations.AddShapeCommand;
import model.Operations.ChangeCommand;
import model.Operations.Command;
import model.Operations.DeleteShapeCommand;
import model.interfaces.ChangeStrategy;
import model.interfaces.Observer;
import model.interfaces.ShapeListener;

/**
 *
 * @author Jacob
 */
public class Drawing implements ShapeListener {

    ArrayList<Shape> shapes = new ArrayList<>();
    ArrayList<Shape> selectedShapes = new ArrayList<>();
    Stack<Command> undoStack = new Stack<>();
    Stack<Command> redoStack = new Stack<>();
    ShapeListener shapeListener;

    private int undoRedoPointer = -1;
    private String name = "";

    public Drawing() {
    }

    //this listener is then added to the commands
    public void attachShapeListener(ShapeListener listener) {
        shapeListener = listener;
    }

    public void undo() {
        if (undoStack.size() == 0) {
            return;
        }

        selectedShapes.clear();
        Command command = undoStack.pop();
        shapes = command.unExecute((ArrayList<Shape>) shapes.clone());
        updateSelected();
        redoStack.push(command);
        notifyAllObservers();
    }

    public void redo() {
        if (redoStack.size() == 0) {
            return;
        }

        Command command = redoStack.pop();
        shapes = command.execute((ArrayList<Shape>) shapes.clone());
        undoStack.push(command);
        updateSelected();

        notifyAllObservers();
    }

    private void addCommand(Command command) {
        command.attach(shapeListener);
        shapes = command.execute((ArrayList<Shape>) shapes.clone());
        updateSelected();
        undoStack.push(command);
        redoStack.clear();
    }
    
    private void updateSelected() {
        ArrayList<Shape> newSelected = new ArrayList<>();
        for (Shape shape : selectedShapes) {
            int index = shapes.indexOf(shape);
            newSelected.add(shapes.get(index));
        }
        
        selectedShapes = newSelected;
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

    public void addShape(Shape shape) {
        if (shape == null) {
            return;
        }

        //does not want to be able to redo/undo the marker
        if (shape instanceof aMarker) {
            shapes.add(shape);
        } else {
            addCommand(new AddShapeCommand(shape));
        }
    }

    public void removeSelected() {
        addCommand(new DeleteShapeCommand((ArrayList<Shape>) selectedShapes.clone()));
        notifyAllObservers();
    }

    public void changeSelectedWidth(double width) {
        ChangeStrategy widthStrategy = new ChangeWidthStrategy(width);
        addCommand(new ChangeCommand((ArrayList<Shape>) selectedShapes.clone(), widthStrategy));
        notifyAllObservers();
    }

    public void changeSelectedColor(Color newCol) {
        ChangeStrategy colStrategy = new ChangeColStrategy(newCol);
        addCommand(new ChangeCommand((ArrayList<Shape>) selectedShapes.clone(), colStrategy));
        notifyAllObservers();
    }

    public void changeSelectedFill(boolean newVal) {
        ChangeStrategy fillStrategy = new ChangeFillStrategy(newVal);
        addCommand(new ChangeCommand((ArrayList<Shape>) selectedShapes.clone(), fillStrategy));
        notifyAllObservers();
    }

    public void changeSize(Shape shape, double toX, double toY) {
        int index = shapes.indexOf(shape);

        if (index < 0) {
            return;
        }

        Shape shapeToChange = shapes.get(index);
        shapeToChange.changeSize(toX, toY);
        shapeListener.updateSize(shape);
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

    private ArrayList<Shape> markers = new ArrayList<>();

    //is called from the views update method
    public void drawAll(GraphicsContext gc) {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        for (Shape shape : selectedShapes) {
            //draw rect around
            Shape tmpRect = ShapeFactory.getShape("aMarker", shape.getFromX(), shape.getFromY(), shape.getToX(), shape.getToY(), Color.CORAL, 1, false);
            tmpRect.draw(gc);
            //  markers.add(shape);
        }

        for (Shape shape : shapes) {
            shape.draw(gc);
        }
    }

    public void clear() {
        shapes = new ArrayList<>();
        notifyAllObservers();
    }

    @Override
    public String toString() {
        return "Drawing{" + "shapes=" + shapes + ", selectedShapes=" + selectedShapes + ", name=" + name + '}';
    }

    @Override
    public void newShape(ArrayList<Shape> shapes) {
        
    }

    @Override
    public void removeShape(ArrayList<Shape> shapes) {
        
    }

    @Override
    public void updateColor(ArrayList<Shape> shapes) {
        
    }

    @Override
    public void updateWidth(ArrayList<Shape> shapes) {
        
    }

    @Override
    public void updateFill(ArrayList<Shape> shapes) {
        
    }

    @Override
    public void updateSize(Shape shape) {
        
    }
}
