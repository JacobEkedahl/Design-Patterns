/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

/**
 *
 * @author Jacob
 */
public class ModelCircle extends Shape {
    public ModelCircle(double fromX, double fromY, double toX, double toY, Color col, double strokeWidth) {
        super(fromX, fromY, toX, toY, col, strokeWidth);
    }

    @Override
    void drawShape(GraphicsContext gc) {
        gc.setStroke(super.getCol());
        gc.setLineWidth(super.getStrokeWidth());
        gc.strokeOval(super.getFromX(), super.getFromY(), super.getToX(), super.getToY());
    }
}
