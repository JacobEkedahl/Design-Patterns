/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.interfaces;

import java.util.ArrayList;
import model.shapes.Shape;

/**
 *
 * @author Jacob
 */
public interface ShapeListener {
    public abstract void newShape(ArrayList<Shape> shapes);
    public abstract void removeShape(ArrayList<Shape> shapes);
    public abstract void updateColor(ArrayList<Shape> shapes);
    public abstract void updateWidth(ArrayList<Shape> shapes);
    public abstract void updateFill(ArrayList<Shape> shapes);
    public abstract void updateSize(Shape shape);
}
