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
    private float fromX, fromY;
    private float toX, toY;
    private Color col;
    private float strokeWidth;

    public Shape(float fromX, float fromY, float toX, float toY, Color col, float strokeWidth) {
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

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }
    
    
    
    public void setStart(float fromX, float fromY) {
        this.fromX = fromX;
        this.fromY = fromY;
    }
    
    public void setEnd(float toX, float toY) {
        this.toX = toX;
        this.toY = toY;
    }

    public float getFromX() {
        return fromX;
    }
    
    public float getFromY() {
        return fromY;
    }
    
    public float getToX() {
        return toX;
    }

    public float getToY() {
        return toY;
    }
    
    abstract void drawShape(GraphicsContext gc);
    
    @Override
    public String toString() {
        return "Shape{" + "fromX=" + fromX + ", fromY=" + fromY + ", toX=" + toX + ", toY=" + toY + '}';
    }
}
