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
public class AddShapeCommand extends Command {

    public AddShapeCommand(Shape shape) {
        myShapes = new ArrayList<>();
        myShapes.add(shape);
    }

    @Override
    public ArrayList<Shape> execute(ArrayList<Shape> orig) {
        mementos = new ShapeMementos();
        mementos.setState((ArrayList<Shape>) myShapes.clone());
        orig.addAll(myShapes);
        listener.newShape(myShapes);

        return orig;
    }

    @Override
    public ArrayList<Shape> unExecute(ArrayList<Shape> orig) {
        orig.removeAll(mementos.getState());
        listener.removeShape(mementos.getState());
        myShapes = mementos.getState();

        return orig;
    }
}
