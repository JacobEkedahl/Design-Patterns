/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.shapes;

import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Jacob
 */
public class aLine extends Shape {
    @Override
    public void drawHollow(GraphicsContext gc) {
        gc.strokeLine(super.getFromX(), super.getFromY(), super.getToX(), super.getToY());
    }

    @Override
    public void changeSize(double newX, double newY) {
        super.setEnd(newX, newY);
    }

    @Override
    public void drawFill(GraphicsContext gc) {
        drawHollow(gc);
    }
}
