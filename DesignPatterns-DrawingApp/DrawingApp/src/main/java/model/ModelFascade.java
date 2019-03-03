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

    private Shape selectedShape;
    private String shapeToDraw;
    private Drawing drawing;
    private static ModelFascade fascadeInstance = null;
    private double fromX;
    private double fromY;
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

    public void setEnd(double toX, double toY) {
        this.drawing.changeSize(selectedShape, toX, toY);
    }

    public void clearDrawing() {
        this.drawing.clear();
    }
    
    public void deselect() {
      //  selectedShape = null;
    }

    public void addShape(double fromX, double fromY) {
        if (shapeToDraw == "Circle") {
            ModelCircle circle = new ModelCircle(fromX, fromY, 0, 0, col, strokeWidth);
            selectedShape = circle;
            drawing.addShape(circle);
        }
    }
}
