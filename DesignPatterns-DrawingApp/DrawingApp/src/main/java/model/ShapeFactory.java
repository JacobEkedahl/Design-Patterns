/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import javafx.scene.paint.Color;

/**
 *
 * @author Jacob
 */
public class ShapeFactory {
    private static HashMap<String, Shape> shapeMap = new HashMap<String, Shape>();
    
    static {
        shapeMap.put("Oval", new ModelOval());
        shapeMap.put("Line", new ModelLine());
        shapeMap.put("Polygon", new Polygon());
    }
    
    public static Shape getShape(String shapeName, double fromX, double fromY, double toX, double toY, Color col, double strokeWidth) {
        return (Shape) shapeMap.get(shapeName).createCopy(fromX, fromY, toX, toY, col, strokeWidth);
    }
}