/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databases;

import java.util.List;
import java.util.concurrent.ExecutionException;
import model.Drawing;
import model.DrawingDAO;
import model.interfaces.Observer;

/**
 *
 * @author Jacob
 */
public abstract class Database {
    public abstract void addData(Drawing drawing) throws InterruptedException, ExecutionException ;
    public abstract DrawingDAO getData(String name) throws InterruptedException, ExecutionException;
    public abstract List<String> getNames() throws InterruptedException, ExecutionException;
    public abstract DrawingDAO getDrawing();
    public abstract void setUpDbListener(String name);
    public abstract void notifyAllObservers();
    public abstract void attach(Observer observer);
    
}
