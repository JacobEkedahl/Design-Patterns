/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kanonkod.drawingapp.command;

import java.util.ArrayList;
import java.util.List;
import model.Drawing;
import model.Shape;
import model.interfaces.RedoCommand;

/**
 *
 * @author fno
 */
public class RedoDelete implements RedoCommand {

    private List<Shape> shapes;
    private Drawing drawing;
    /**
     * 
     * @param shapes
     * @param drawing 
     */
    public RedoDelete(List<Shape> shapes, Drawing drawing) {
        this.shapes = shapes;
        this.drawing = drawing;
    }
    /**
     * deletes the retrieved list of objects from the canvas, copies the list to undodelete,
     * so it can still be restored.
     */  
    @Override
    public void redo() {
        this.drawing.updateUndoStack(new UndoDelete(new ArrayList<Shape>(shapes),this.drawing));
        for(Shape s : shapes){
            drawing.removeShape(s);
        }
    }
}
