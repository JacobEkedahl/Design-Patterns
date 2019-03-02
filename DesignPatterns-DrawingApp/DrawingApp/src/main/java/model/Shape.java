/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Jacob
 */
public abstract class Shape {
    private float fromX, fromY;
    private float toX, toY;

    public Shape(float fromX, float fromY, float toX, float toY) {
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
    }
    
    final void draw(GraphicsContext gc) {
        
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
    
    public abstract void drawShape();
    
    @Override
    public String toString() {
        return "Shape{" + "fromX=" + fromX + ", fromY=" + fromY + ", toX=" + toX + ", toY=" + toY + '}';
    }
}
