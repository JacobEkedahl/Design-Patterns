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
}