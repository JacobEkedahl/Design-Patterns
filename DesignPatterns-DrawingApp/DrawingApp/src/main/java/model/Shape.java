/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Jacob
 */
//DO NOT CHANGE NAME OF THIS CLASS
public abstract class Shape implements Cloneable {

    private double fromX, fromY;
    private double toX, toY;
    private Color col;
    private double strokeWidth;
    private boolean fill;

    abstract void drawFill(GraphicsContext gc);

    abstract void drawHollow(GraphicsContext gc);

    abstract void changeSize(double newX, double newY);

    public Shape createCopy(double fromX, double fromY, double toX, double toY, Color col, double strokeWidth, boolean fill) {
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
        this.col = col;
        this.strokeWidth = strokeWidth;
        this.fill = fill;

        try {
            return (Shape) this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Shape.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean getFill() {
        return fill;
    }
    
    public double getMinX() {
        return (fromX < toX) ? fromX : toX;
    }
    
    public double getMinY() {
        return (fromY < toY) ? fromY : toY;
    }
        
    public double getMaxX() {
        return (fromX > toX) ? fromX : toX;
    }
    
    public double getMaxY() {
        return (fromY > toY) ? fromY : toY;
    }

    final void draw(GraphicsContext gc) {
        gc.setFill(col);
        gc.setStroke(col);
        gc.setLineWidth(strokeWidth);

        if (fill) {
            drawFill(gc);
        } else {
            drawHollow(gc);
        }
    }

    public void invertX() {
        double tmpX = fromX;
        this.fromX = this.toX;
        this.toX = this.fromX;
    }

    public void invertY() {
        double tmpY = fromY;
        this.fromY = this.toY;
        this.toY = this.fromY;
    }

    public Color getCol() {
        return col;
    }

    public void setCol(Color col) {
        this.col = col;
    }

    public double getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(double strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public void setFromX(double fromX) {
        this.fromX = fromX;
    }

    public void setFromY(double fromY) {
        this.fromY = fromY;
    }

    public void setToX(double toX) {
        this.toX = toX;
    }

    public void setToY(double toY) {
        this.toY = toY;
    }

    public void setStart(double fromX, double fromY) {
        this.fromX = fromX;
        this.fromY = fromY;
    }

    public void setEnd(double toX, double toY) {
        this.toX = toX;
        this.toY = toY;
    }

    public double getFromX() {
        return fromX;
    }

    public double getFromY() {
        return fromY;
    }

    public double getToX() {
        return toX;
    }

    public double getToY() {
        return toY;
    }
    
    public void setFill(boolean newVal) {
        this.fill = newVal;
    }
    
    public boolean isFill() {
        return this.fill;
    }

    @Override
    public String toString() {
        return "Shape{" + "fromX=" + fromX + ", fromY=" + fromY + ", toX=" + toX + ", toY=" + toY + '}';
    }
}
