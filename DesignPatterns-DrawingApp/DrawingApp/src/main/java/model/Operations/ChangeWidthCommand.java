/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Operations;

import java.util.ArrayList;
import model.Shape;

/**
 *
 * @author Jacob
 */
public class ChangeWidthCommand extends Command {

    private double width;

    public ChangeWidthCommand(ArrayList<Shape> selectedShapes, double width) {
        myShapes = copy(selectedShapes);
        this.width = width;
    }

    @Override
    public ArrayList<Shape> execute(ArrayList<Shape> orig) {
        mementos = new ShapeMementos();
        mementos.setState(copy(myShapes));

        orig.removeAll(myShapes);

        for (Shape shape : myShapes) {
            shape.setStrokeWidth(width);
        }

        System.out.println("execute mementos: " + printWidth(mementos.getState()));
        orig.addAll(myShapes);

        return orig;
    }

    @Override
    public ArrayList<Shape> unExecute(ArrayList<Shape> orig) {
        System.out.println("unexecute mementos: " + printWidth(mementos.getState()));
        orig.removeAll(myShapes);

        orig.addAll(mementos.getState());
        myShapes = mementos.getState();

        return orig;
    }

    private ArrayList<Shape> copy(ArrayList<Shape> orig) {
        ArrayList<Shape> res = new ArrayList<>();

        for (Shape shape : orig) {
            res.add(shape.createCopy());
        }

        return res;
    }

    private String printWidth(ArrayList<Shape> shapes) {
        String res = "";
        for (Shape s : shapes) {
            res += s.getStrokeWidth() + " : ";
        }

        return res;
    }
}
