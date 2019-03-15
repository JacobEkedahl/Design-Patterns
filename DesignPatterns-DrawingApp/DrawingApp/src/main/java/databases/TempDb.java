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
import model.Drawing;
import model.DrawingDAO;
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
    public void addData(Drawing drawing) throws InterruptedException, ExecutionException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DrawingDAO getData(String name) throws InterruptedException, ExecutionException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
    }

    @Override
    public List<String> getNames() throws InterruptedException, ExecutionException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
    }

    @Override
    public DrawingDAO getDrawing() {
        return null;
    }

    @Override
    public void setUpDbListener(String name) {
        //
    }

    @Override
    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

}
