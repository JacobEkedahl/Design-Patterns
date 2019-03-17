/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Operations;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import model.Shape;
import model.interfaces.ChangeStrategy;
import model.interfaces.ShapeListener;

/**
 *
 * @author Jacob
 */
public class ChangeCommand extends Command {

    private Color col;
    private ChangeStrategy strategy;

    public ChangeCommand(ArrayList<Shape> selectedShapes, ChangeStrategy strategy) {
        myShapes = copy(selectedShapes);
        this.strategy = strategy;
    }

    @Override
    public ArrayList<Shape> execute(ArrayList<Shape> orig) {
        mementos = new ShapeMementos();
        mementos.setState(copy(myShapes));

        orig.removeAll(myShapes);

        for (Shape shape : myShapes) {
            strategy.change(shape);
        }

        orig.addAll(myShapes);
        updateDb();

        return orig;
    }

    @Override
    public ArrayList<Shape> unExecute(ArrayList<Shape> orig) {
        orig.removeAll(myShapes);
        orig.addAll(mementos.getState());
        myShapes = mementos.getState();
        updateDb();

        return orig;
    }

    private void updateDb() {

        switch (strategy.getStrategy()) {
            case FILL:
                listener.updateFill(myShapes);
                break;
            case COLOR:
                listener.updateColor(myShapes);
                break;
            case WIDTH:
                listener.updateWidth(myShapes);
                break;
        }
    }

}
