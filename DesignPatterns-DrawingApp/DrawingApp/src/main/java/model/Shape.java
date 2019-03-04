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
public abstract class Shape implements Cloneable{

    private double fromX, fromY;
    private double toX, toY;
    private Color col;
    private double strokeWidth;

    abstract void drawShape(GraphicsContext gc);
    abstract void changeSize(double newX, double newY);

    public Shape createCopy(double fromX, double fromY, double toX, double toY, Color col, double strokeWidth) {
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
        this.col = col;
        this.strokeWidth = strokeWidth;
        
        try {
            return (Shape)this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Shape.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    final void draw(GraphicsContext gc) {
        gc.setStroke(col);
        gc.setLineWidth(strokeWidth);
        drawShape(gc);
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

    @Override
    public String toString() {
        return "Shape{" + "fromX=" + fromX + ", fromY=" + fromY + ", toX=" + toX + ", toY=" + toY + '}';
    }
}
