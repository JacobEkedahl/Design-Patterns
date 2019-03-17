/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datalayer;

import java.util.List;
import java.util.concurrent.ExecutionException;
import javafx.scene.paint.Color;
import model.Drawing;
import model.shapes.Shape;
import model.interfaces.Observer;
import model.interfaces.ShapeListener;
import model.interfaces.ShapeReplacer;

/**
 *
 * @author Jacob
 */
public abstract class Database implements ShapeListener {
    //The drawing which will receive updates from the database in form of deleted shapes, new shapes, modified shapes
    ShapeReplacer drawing;
    
    public void attach(ShapeReplacer listener) {
        this.drawing = listener;
    }
    
    //Will return all the names of the drawings inside the database (used when user clicks open button in menu)
    public abstract List<String> getNames() throws InterruptedException, ExecutionException;
    public abstract void setUpDbListener(String name);
    
}
