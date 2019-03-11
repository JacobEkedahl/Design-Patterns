/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.kanonkod.drawingapp.command.RedoDelete;
import com.kanonkod.drawingapp.command.UndoDelete;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import javafx.scene.paint.Color;
/**
 *
 * @author Jacob
 */
public class DrawingDAO {

    private String name;
    private List<ShapeDAO> shapes;


    private void initShapes(List<Shape> shapes) {
        this.shapes = new ArrayList<>();
        for (Shape shape : shapes) {
            String shapeName = shape.getClass().getSimpleName();
            int red = (int)(255 * shape.getCol().getRed());
            int green = (int) (255 * shape.getCol().getGreen());
            int blue = (int) (255 * shape.getCol().getBlue());
            ShapeDAO newShape = new ShapeDAO(shapeName, shape.getFromX(), shape.getFromY(), shape.getToX(), shape.getToY(), red, green, blue, shape.getStrokeWidth(), shape.getFill());
            this.shapes.add(newShape);
        }
    }
    
   

    public DrawingDAO() {
    }

    public DrawingDAO(Drawing drawing) {
        initShapes(drawing.getShapes());
        this.name = drawing.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ShapeDAO> getShapes() {
        return shapes;
    }

    public void setShapes(List<ShapeDAO> shapes) {
        this.shapes = shapes;
    }

    
}
