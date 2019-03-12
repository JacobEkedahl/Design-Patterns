/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kanonkod.drawingapp.command;

import model.Drawing;
import model.ModelFascade;
import model.Shape;
import model.interfaces.UndoCommand;

/**
 *
 * @author fno
 */
public class UndoAdd implements UndoCommand{
    
    private Shape shape;
    private Drawing drawing;
    /**
     * 
     * @param shape 
     * @param model 
     */
    public UndoAdd(Shape shape, Drawing model) {
        this.drawing = model;
        this.shape = shape;
    }
   
    /**
     * removes the shape from the canvas, adds the shape to 
     * a new instance of Redo that will be added to the stack.
     */
    @Override
    public void undo() {
        drawing.removeShape(shape);
        drawing.updateRedoStack(new RedoAdd(this.shape,this.drawing));
    }
}
