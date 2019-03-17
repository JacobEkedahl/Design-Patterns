/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javafx.scene.paint.Color;
import model.Drawing;
import model.Shape;
import model.interfaces.Observer;

/**
 *
 * @author Jacob
 */
public class TempDb extends Database {

    List<Observer> observers = new ArrayList<>();

    public TempDb() throws IOException {

    }

    @Override
    public List<String> getNames() throws InterruptedException, ExecutionException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
    }
    @Override
    public void setUpDbListener(String name) {
        //
    }

    @Override
    public void updateColor(ArrayList<Shape> shapes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateWidth(ArrayList<Shape> shapes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateFill(ArrayList<Shape> shapes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateSize(Shape shape) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void newShape(ArrayList<Shape> shapes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeShape(ArrayList<Shape> shapes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
