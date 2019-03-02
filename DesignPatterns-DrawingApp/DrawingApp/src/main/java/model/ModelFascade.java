/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

/**
 *
 * @author Jacob
 */
public class ModelFascade {

    private Drawing drawing;
    private static ModelFascade fascadeInstance = null;

    private ModelFascade() {
        drawing = new Drawing();
    }

    public static ModelFascade getInstance() {
        if (fascadeInstance == null) {
            fascadeInstance = new ModelFascade();
        }

        return fascadeInstance;
    }
    
    public void clearDrawing() {
        drawing.clear();
    }

    public void addShape() {
        //placeholder code for testing
        ModelCircle circle = new ModelCircle(40, 40, 100, 100,Color.AZURE,40);
        drawing.addShape(circle);
    }

}
