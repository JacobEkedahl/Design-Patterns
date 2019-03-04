/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

/**
 *
 * @author Jacob
 */
public class aPolygon extends Shape {

    private static final int corners = 6;
    double[] xPoints;
    double[] yPoints;

    @Override
    void drawShape(GraphicsContext gc) {
        System.out.println("drawing polygon: " + Arrays.toString(xPoints));
         gc.strokePolygon(xPoints, yPoints, corners);
    }

    @Override
    public void changeSize(double newX, double newY) {
        redoPoints(super.getFromX(), super.getFromY(), newX, newY);
    }

    private void redoPoints(double fromX, double fromY, double toX, double toY) {
        xPoints = new double[corners];
        yPoints = new double[corners];

        double xRad = (toX - fromX) / 2;
        double yRad = (toY - fromY) / 2;
        double centerX = xRad + fromX;
        double centerY = yRad + fromY;
        
        double increment = 360 / corners;

        int index = 0;
        for (int angle = 0; angle < 360; angle += increment) {
            System.out.println("index: " + index + ", angle: " + angle);
            xPoints[index] = centerX + xRad * Math.cos(angle * Math.PI / 180);
            yPoints[index] = centerY + yRad * Math.sin(angle * Math.PI / 180);
            index++;
        }
    }
}
