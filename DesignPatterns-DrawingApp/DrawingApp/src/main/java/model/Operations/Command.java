/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Operations;

import java.util.ArrayList;
import model.Shape;
import model.interfaces.ShapeListener;

/**
 * All the commands executed and put on the undo and redo stack are implemented
 * by extending this command.
 * Contains a log of what the shape looked before the execution/unexecution for reversal functionality
 * @author Jacob
 */
public abstract class Command {

    ArrayList<Shape> myShapes;
    ShapeMementos mementos;
    ShapeListener listener;
    
    public void attach(ShapeListener listener) {
        this.listener = listener;
    }

    public abstract ArrayList<Shape> execute(ArrayList<Shape> orig);
    public abstract ArrayList<Shape> unExecute(ArrayList<Shape> orig);

    ArrayList<Shape> copy(ArrayList<Shape> orig) {
        ArrayList<Shape> res = new ArrayList<>();

        for (Shape shape : orig) {
            res.add(shape.createCopy());
        }

        return res;
    }
}
