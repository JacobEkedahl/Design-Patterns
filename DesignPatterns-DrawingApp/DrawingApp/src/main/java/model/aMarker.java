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
public class aMarker extends Shape {
    @Override
    void drawHollow(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1.0);
        
        gc.setLineDashes(5);
        gc.strokeLine(super.getFromX(), super.getFromY(), super.getToX(), super.getFromY()); //top
        gc.strokeLine(super.getFromX(), super.getFromY(), super.getFromX(), super.getToY()); //left
        gc.strokeLine(super.getToX(), super.getFromY(), super.getToX(), super.getToY()); //right
        gc.strokeLine(super.getFromX(), super.getToY(), super.getToX(), super.getToY()); //bot
        gc.setLineDashes(0);
    }

    @Override
    public void changeSize(double newX, double newY) {
        super.setEnd(newX, newY);
    }

    @Override
    void drawFill(GraphicsContext gc) {
        drawHollow(gc);
    }
}
