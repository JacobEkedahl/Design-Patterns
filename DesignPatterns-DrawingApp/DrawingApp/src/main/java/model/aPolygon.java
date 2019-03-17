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
public class aPolygon extends Shape {

    private static final int corners = 6;
    double[] xPoints;
    double[] yPoints;

    @Override
    void drawHollow(GraphicsContext gc) {
        redoPoints();
        gc.strokePolygon(xPoints, yPoints, corners);
    }

    @Override
    public void changeSize(double newX, double newY) {
        super.setToX(newX);
        super.setToY(newY);
        redoPoints();
    }

    @Override
    void drawFill(GraphicsContext gc) {
        redoPoints();
        gc.fillPolygon(xPoints, yPoints, corners);
    }

    private void redoPoints() {
        //do not draw any points on init
        if (super.getFromX() == super.getToX() && super.getFromY() == super.getToY()) {
            return;
        }

        xPoints = new double[corners];
        yPoints = new double[corners];

        double xRad = (super.getToX() - super.getFromX()) / 2;
        double yRad = (super.getToY() - super.getFromY()) / 2;
        double centerX = xRad + super.getFromX();
        double centerY = yRad + super.getFromY();

        double increment = 360 / corners;

        int index = 0;
        for (int angle = 0; angle < 360; angle += increment) {
            xPoints[index] = centerX + xRad * Math.cos(angle * Math.PI / 180);
            yPoints[index] = centerY + yRad * Math.sin(angle * Math.PI / 180);
            index++;
        }
    }
}
