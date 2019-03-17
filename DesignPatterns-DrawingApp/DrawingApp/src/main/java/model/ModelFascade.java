/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import databases.Database;
import databases.FirebaseDb;
import databases.TempDb;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javax.naming.ConfigurationException;
import model.interfaces.Observer;

/**
 *
 * @author Jacob
 */
public class ModelFascade extends Observer {

    private Shape selectedShape;
    private String shapeToDraw;
    private Drawing drawing;
    private static ModelFascade fascadeInstance = null;
    private double fromX;
    private double fromY;
    private Color col;
    private double strokeWidth;
    private boolean fill;
    private Database db = null;

    private ModelFascade() {
        fill = false;
        col = Color.BLACK;
        strokeWidth = 1;
        drawing = new Drawing();
        shapeToDraw = null;
        initDb();
        connectDbWithDrawing();
    }
    
    private void connectDbWithDrawing() {
        this.drawing.attachShapeListener(db);
        this.db.attach(drawing);
    }

    public void undo() {
        this.drawing.undo();
    }
    
    public void redo() {
        this.drawing.redo();
    }

    private void initDb() {
        try {
            db = new FirebaseDb();
        } catch (IOException ex) {
            Logger.getLogger(ModelFascade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<String> getNames() {
        try {
            return db.getNames();
        } catch (InterruptedException ex) {
            Logger.getLogger(ModelFascade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(ModelFascade.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    public void updateFinishedSize() {
        this.drawing.changeSizeFinished(selectedShape);
    }

    public void setName(String newName) {
        this.drawing.setName(newName);
        this.db.setUpDbListener(newName);
    }

    public static ModelFascade getInstance() {
        if (fascadeInstance == null) {
            fascadeInstance = new ModelFascade();
        }

        return fascadeInstance;
    }

    public void draw(GraphicsContext gc) {
        drawing.drawAll(gc);
    }

    public void setEnd(double toX, double toY) {
        if (selectedShape == null) {
            return;
        }

        this.drawing.changeSize(selectedShape, toX, toY);
    }

    public void setColor(Color col) {
        this.col = col;
    }

    public void clearDrawing() {
        this.drawing.clear();
    }

    public void handleMarker() {
        if (selectedShape instanceof aMarker) {
            this.drawing.selectShapes(selectedShape);
            this.drawing.removeShape(selectedShape);
        }
    }

    public void changeSelectedWidth(double newWidth) {
        this.drawing.changeSelectedWidth(newWidth);
    }

    public void changeSelectedFill(boolean newVal) {
        this.drawing.changeSelectedFill(newVal);
    }

    public void changeSelectedColor(Color newCol) {
        this.drawing.changeSelectedColor(newCol);
    }

    public void removeSelected() {
        this.drawing.removeSelected();
    }

    public void deselectAll() {
        this.drawing.deselectAll();
    }

    public void deselect() {
        selectedShape = null;
    }

    public Drawing getDrawing() {
        return drawing;
    }

    public void selectShape(String shape) {
        shapeToDraw = shape;
    }

    public void setFill(boolean newVal) {
        System.out.println("new fill: " + newVal);
        this.fill = newVal;
    }

    public void setWidth(double width) {
        this.strokeWidth = width;
    }

    public void addShape(double fromX, double fromY) throws ConfigurationException {
        if (this.drawing.getName() == "") {
            throw new ConfigurationException("Name has not been set");
        }
        
        if (shapeToDraw == null) {
            return;
        }

        selectedShape = ShapeFactory.getShape(shapeToDraw, fromX, fromY, fromX, fromY, col, strokeWidth, fill);
        selectedShape.generateId();
        drawing.addShape(selectedShape);
    }

    @Override
    public void update() {
        //this.drawing.init(db.getDrawing());
    }
}
