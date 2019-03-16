/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databases;

import java.util.List;
import java.util.concurrent.ExecutionException;
import javafx.scene.paint.Color;
import model.Drawing;
import model.Shape;
import model.interfaces.Observer;
import model.interfaces.ShapeListener;

/**
 *
 * @author Jacob
 */
public abstract class Database implements ShapeListener {
    ShapeListener drawing;
    
    public void attach(ShapeListener listener) {
        this.drawing = listener;
    }
    
    public abstract List<String> getNames() throws InterruptedException, ExecutionException;
    public abstract void setUpDbListener(String name);
    public abstract void notifyAllObservers();
    public abstract void attach(Observer observer);
    
}
