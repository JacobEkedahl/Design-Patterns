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
public class anOval extends Shape {

    private boolean drawRightX = true;
    private boolean drawDownY = true;

    @Override
    public void drawHollow(GraphicsContext gc) {
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

    @Override
    public void drawFill(GraphicsContext gc) {
        double width = super.getToX() - super.getFromX();
        double height = super.getToY() - super.getFromY();
        gc.fillOval(super.getFromX(), super.getFromY(), width, height);
    }
}
