/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Jacob
 */
public class ModelFascade {

    private Shape selectedShape;
    private String shapeToDraw;
    private Drawing drawing;
    private static ModelFascade fascadeInstance = null;
    private double fromX;
    private double fromY;
    private Color col;
    private double strokeWidth;
    private boolean fill;

    private ModelFascade() {
        fill = false;
        col = Color.BLACK;
        strokeWidth = 1;
        drawing = new Drawing();
        shapeToDraw = null;
    }

    public static ModelFascade getInstance() {
        if (fascadeInstance == null) {
            fascadeInstance = new ModelFascade();
        }

        return fascadeInstance;
    }

    public void draw(GraphicsContext gc) {
        drawing.drawAll(gc);
    }

    public void setEnd(double toX, double toY) {
        if (selectedShape == null) {
            return;
        }
        this.drawing.changeSize(selectedShape, toX, toY);
    }

    public void setColor(Color col) {
        this.col = col;
    }

    public void clearDrawing() {
        this.drawing.clear();
    }
    
    public void handleMarker() {
        if (selectedShape instanceof aMarker) {
            this.drawing.selectShapes(selectedShape);
            this.drawing.removeShape(selectedShape);
        }
    }
    
    public void changeSelectedFill(boolean newVal) {
        this.drawing.changeSelectedFill(newVal);
    }
    
   public void changeSelectedColor(Color newCol) {
        this.drawing.changeSelectedColor(newCol);
    }
    public void deselectAll() {
        this.drawing.deselectAll();
    }
    public void deselect() {
       // Shape comp = drawing.addComponent(selectedShape);
        handleComposite();
        selectedShape = null;
    }
    public void handleComposite(){
        if(selectedShape==null){
            return;
        }
        if(drawing.updateComposite(selectedShape)){
            return;
        }
        Shape outerShape = drawing.retrieveComposite(selectedShape);
        if(outerShape!=null){
            ShapeComposite newComposite = makeNewComposite(outerShape);
            drawing.initializeComposite(newComposite,selectedShape,outerShape);
            return;
        }
    }
    public ShapeComposite makeNewComposite(Shape outline){
        return  (ShapeComposite) ShapeFactory.getShape("ShapeComposite", outline.getFromX(), outline.getFromY(), outline.getToX(), outline.getToY());
    }
    public Drawing getDrawing() {
        return drawing;
    }

    public void selectShape(String shape) {
        shapeToDraw = shape;
    }

    public void setFill(boolean newVal) {
        this.fill = newVal;
    }

    public void setWidth(double width) {
        this.strokeWidth = width;
    }

    public void addShape(double fromX, double fromY) {
        if (shapeToDraw == null) {
            return;
        }
        selectedShape = ShapeFactory.getShape(shapeToDraw, fromX, fromY, fromX, fromY, col, strokeWidth, fill);
        drawing.addShape(selectedShape);
    }
}
