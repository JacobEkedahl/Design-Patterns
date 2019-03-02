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
public class ModelLine extends Shape {
    public ModelLine(float fromX, float fromY, float toX, float toY, Color col, float strokeWidth) {
        super(fromX, fromY, toX, toY, col, strokeWidth);
    }

    @Override
    void drawShape(GraphicsContext gc) {
        gc.setStroke(super.getCol());
        gc.setLineWidth(super.getStrokeWidth());
        gc.strokeLine(super.getFromX(), super.getFromY(), super.getToX(), super.getToY());
    }
}
