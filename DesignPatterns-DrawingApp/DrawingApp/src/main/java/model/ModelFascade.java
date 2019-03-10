/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.kanonkod.drawingapp.command.UndoAdd;
import databases.FirebaseHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
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
    private FirebaseHandler db = null;

    private ModelFascade() {
        fill = false;
        col = Color.BLACK;
        strokeWidth = 1;
        drawing = new Drawing();
        shapeToDraw = null;
        initDb();
        attachDrawingToDb();
    }

    private void attachDrawingToDb() {
        db.attach(this);
    }

    private void initDb() {
        try {
            db = new FirebaseHandler();
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

    public void setName(String newName) {
        this.drawing.setName(newName);
        this.db.setUpDbListener(newName);
    }

    public void saveData() {
        try {
            db.addData(this.drawing);
        } catch (IllegalArgumentException ex) {
            //show popup for saving a name
            throw new IllegalArgumentException("Error: " + ex.getMessage());
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(ModelFascade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    public void getData(String name) {
        try {
            System.out.println("name to get: " + name);
            this.drawing.init(db.getData(name));
        } catch (InterruptedException ex) {
            Logger.getLogger(ModelFascade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(ModelFascade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

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
        //here I can update the stacks
       // this.drawing.
        if(selectedShape!=null && !(selectedShape instanceof aMarker)){
            drawing.updateUndoStack(new UndoAdd(selectedShape,drawing ));
         //   drawing.updateUndoStack(selectedShape);     
        }
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

    public void addShape(double fromX, double fromY) {
        if (shapeToDraw == null) {
            return;
        }

        selectedShape = ShapeFactory.getShape(shapeToDraw, fromX, fromY, fromX, fromY, col, strokeWidth, fill);
        drawing.addShape(selectedShape);
    }

    @Override
    public void update() {
        this.drawing.init(db.getDrawing());
        //retrieve the data from the database
    }
}
