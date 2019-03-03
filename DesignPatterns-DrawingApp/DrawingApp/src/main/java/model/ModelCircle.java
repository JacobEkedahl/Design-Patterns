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
public class ModelCircle extends Shape {

    private boolean drawRightX;
    private boolean drawDownY;

    public ModelCircle(double fromX, double fromY, double toX, double toY, Color col, double strokeWidth) {
        super(fromX, fromY, toX, toY, col, strokeWidth);
        drawRightX = true;
        drawDownY = true;
    }

    public ModelCircle() {
        super();
    }

    @Override
    void drawShape(GraphicsContext gc) {
        double width = super.getToX() - super.getFromX();
        double height = super.getToY() - super.getFromY();
        gc.strokeOval(super.getFromX(), super.getFromY(), width, height);
    }

    @Override
    public void changeSize(double newX, double newY) {
        double width;
        double height;

        if (drawRightX) {
            width = newX - super.getFromX();
            super.setToX(newX);
        } else {
            width = super.getToX() - newX;
            super.setFromX(newX);
        }

        //calculate height and width for next step and change the x and y value
        if (drawDownY) {
            height = newY - super.getFromY();
            super.setToY(newY);
        } else {
            height = super.getToY() - newY;
            super.setFromY(newY);
        }

        //whether to change which direction to draw the circle at or not
        if (width < 0) {
            drawRightX = !drawRightX;
        }
        if (height < 0) {
            drawDownY = !drawDownY;
        }
    }
}
