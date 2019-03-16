/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Operations;

import java.util.ArrayList;
import java.util.List;
import model.Shape;

/**
 *
 * @author Jacob
 */
public class DeleteShapeCommand extends Command {

    public DeleteShapeCommand(ArrayList<Shape> shapes) {
        myShapes = shapes;
    }

    @Override
    public ArrayList<Shape> execute(ArrayList<Shape> orig) {
        mementos = new ShapeMementos();
        System.out.println("inside delete command: " + myShapes.size());
        mementos.setState((ArrayList<Shape>) myShapes.clone());

        for (Shape s : myShapes) {
            int index = orig.indexOf(s);
            orig.remove(index);
        }
        
        return orig;
    }

    @Override
    public ArrayList<Shape> unExecute(ArrayList<Shape> orig) {
        orig.addAll(mementos.getState());
        myShapes = mementos.getState();
        return orig;
    }
}