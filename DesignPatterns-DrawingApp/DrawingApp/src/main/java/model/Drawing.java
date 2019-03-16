
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
import model.Operations.AddShapeCommand;
import model.Operations.ChangeWidthCommand;
import model.Operations.Command;
import model.Operations.DeleteShapeCommand;
import model.interfaces.Observer;

/**
 *
 * @author Jacob
 */
public class Drawing {

    ArrayList<Shape> shapes = new ArrayList<>();
    ArrayList<Shape> selectedShapes = new ArrayList<>();
    Stack<Command> undoStack = new Stack<>();
    Stack<Command> redoStack = new Stack<>();

    private int undoRedoPointer = -1;
    private String name = "";

    public Drawing() {
    }

    public void init(DrawingDAO dbDrawing) {
        this.name = dbDrawing.getName();
        for (ShapeDAO shapeDAO : dbDrawing.getShapes()) {
            Shape newShape = ShapeFactory.getShape(shapeDAO);
            if (!shapes.contains(newShape)) {
                shapes.add(newShape);
            } else {

            }
        }

        notifyAllObservers();
    }

    public void undo() {
        if (undoStack.size() == 0) {
            return;
        }

        Command command = undoStack.pop();
        shapes = command.unExecute((ArrayList<Shape>) shapes.clone());
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

        notifyAllObservers();
    }

    private void addCommand(Command command) {
        shapes = command.execute((ArrayList<Shape>) shapes.clone());
        undoStack.push(command);
        redoStack.clear();
    }

    private void removeCommand() {
        addCommand(new DeleteShapeCommand((ArrayList<Shape>) selectedShapes.clone()));
    }

    private void addShapeCommand(Shape shape) {
        addCommand(new AddShapeCommand(shape));
    }
    
    private void changeWidthCommand(double width) {
        addCommand(new ChangeWidthCommand((ArrayList<Shape>) selectedShapes.clone(), width));
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

        //does not want to be able to redo/undo the marker
        if (shape instanceof aMarker) {
            shapes.add(shape);
        } else {
            addShapeCommand(shape);
        }
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
            if (shape instanceof aMarker) {
                int index = shapes.indexOf(shape);
                shapes.remove(index);
                System.out.println("removed marker at index: " + index);
            }
        }

        removeCommand();
        notifyAllObservers();
    }

    public void removeShape(Shape shape) {
        System.out.println("removed: " + shape);
        int index = shapes.indexOf(shape);
        shapes.remove(index);
        notifyAllObservers();
    }

    public void deselectAll() {
        selectedShapes.clear();
        notifyAllObservers();
    }

    public void changeSelectedWidth(double width) {
        changeWidthCommand(width);
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

    @Override
    public String toString() {
        return "Drawing{" + "shapes=" + shapes + ", selectedShapes=" + selectedShapes + ", name=" + name + '}';
    }
}
