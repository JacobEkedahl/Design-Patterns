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
public class Polygon extends Shape {

    public Polygon(double fromX, double fromY, double toX, double toY, Color col, double strokeWidth) {
        super(fromX, fromY, toX, toY, col, strokeWidth);
    }
    
    public Polygon() {
        super();
    }

    @Override
    void drawShape(GraphicsContext gc) {
        gc.strokePolygon(new double[]{super.getFromX(), super.getFromY(), super.getToX(), super.getToY()},
                new double[]{210, 210, 240, 240}, 4);
    }

    @Override
    public void changeSize(double newX, double newY) {
        super.setEnd(newX, newY);
    }
}
