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
import javafx.scene.shape.*;

/**
 *
 * @author Jacob
 */
public class ModelLine extends Shape {

    public ModelLine(double fromX, double fromY, double toX, double toY, Color col, double strokeWidth) {
        super(fromX, fromY, toX, toY, col, strokeWidth);
    }

    public ModelLine() {
        super();
    }

    @Override
    void drawShape(GraphicsContext gc) {
        System.out.println("from: " + super.getFromX() + " y: " + super.getFromY() + " toX: " + super.getToX() + " toY: " + super.getToY());
        gc.strokeLine(super.getFromX(), super.getFromY(), super.getToX(), super.getToY());
    }

    @Override
    public void changeSize(double newX, double newY) {
        super.setEnd(newX, newY);
    }
}
