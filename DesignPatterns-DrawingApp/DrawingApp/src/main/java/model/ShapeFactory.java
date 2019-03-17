/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import org.reflections.Reflections;

/**
 *
 * @author Jacob
 */
public class ShapeFactory {

    private static HashMap<String, Shape> shapeMap = ShapeLoader.getShapeTypes();

    public static Shape getShape(String shapeName, double fromX, double fromY, double toX, double toY, Color col, double strokeWidth, boolean fill) {
        return (Shape) shapeMap.get(shapeName).createCopy(fromX, fromY, toX, toY, col, strokeWidth, fill);
    }

    public static Shape getShape(ShapeDAO s) {
        return (Shape) shapeMap.get(s.getType()).createCopy(s.getFromX(), s.getFromY(),
                s.getToX(), s.getToY(), Color.rgb(s.getRed(), s.getGreen(), s.getBlue()), s.getStrokeWidth(), s.isFill());
    }

    public static ShapeDAO getShapeDAO(Shape shape, String name) {
        String shapeName = shape.getClass().getSimpleName();
        String id = shape.getId();
        int red = (int) (255 * shape.getCol().getRed());
        int green = (int) (255 * shape.getCol().getGreen());
        int blue = (int) (255 * shape.getCol().getBlue());
        ShapeDAO newShape = new ShapeDAO(id, name, shapeName, shape.getFromX(), shape.getFromY(), shape.getToX(), shape.getToY(), red, green, blue, shape.getStrokeWidth(), shape.getFill());

        return newShape;
    }
}
