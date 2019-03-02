/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Jacob
 */
public abstract class Shape {
    private double fromX, fromY;
    private double toX, toY;
    private Color col;
    private double strokeWidth;

    public Shape(double fromX, double fromY, double toX, double toY, Color col, double strokeWidth) {
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
        this.col = col;
        this.strokeWidth = strokeWidth;
    }
    
    final void draw(GraphicsContext gc) {
        
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
    
    abstract void drawShape(GraphicsContext gc);
    
    @Override
    public String toString() {
        return "Shape{" + "fromX=" + fromX + ", fromY=" + fromY + ", toX=" + toX + ", toY=" + toY + '}';
    }
}
