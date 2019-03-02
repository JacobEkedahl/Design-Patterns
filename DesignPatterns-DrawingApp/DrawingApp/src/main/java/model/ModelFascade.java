/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Jacob
 */
public class ModelFascade {

    private String shapeToDraw;
    private Drawing drawing;
    private static ModelFascade fascadeInstance = null;
    private double fromX;
    private double fromY;
    private double toX;
    private double toY;
    private Color col;
    private double strokeWidth;

    private ModelFascade() {
        col = Color.BLACK;
        strokeWidth = 5;
        drawing = new Drawing();
        shapeToDraw = "Circle";
    }

    public static ModelFascade getInstance() {
        if (fascadeInstance == null) {
            fascadeInstance = new ModelFascade();
        }

        return fascadeInstance;
    }

    public void setFrom(double fromX, double fromY) {
        this.fromX = fromX;
        this.fromY = fromY;
    }

    public void setTo(double toX, double toY) {
        this.toX = toX;
        this.toY = toY;
    }

    public void drawCircle(GraphicsContext gc) {
        ModelCircle circle = new ModelCircle(fromX, fromY, toX, toY, col, strokeWidth);
        circle.drawShape(gc);
    }

    public void clearDrawing() {
        drawing.clear();
    }

    public void addShape(GraphicsContext gc) {
        if (shapeToDraw == "Circle") {
            ModelCircle circle = new ModelCircle(fromX, fromY, toX, toY, col, strokeWidth);
            circle.drawShape(gc);
            drawing.addShape(circle);
        }
    }

}
