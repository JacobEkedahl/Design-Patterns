/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kanonkod.drawingapp.command;

import javafx.scene.paint.Color;
import model.Drawing;
import model.Shape;
import model.interfaces.RedoCommand;

/**
 *
 * @author fno
 */
public class RedoAdd implements RedoCommand{
    private Shape shape;
    private Drawing drawing;

    public RedoAdd(Shape shape, Drawing drawing) {
        this.shape = shape;
        this.drawing = drawing;
    }
    
    
    @Override
    public void redo() {     
        Shape newShape = shape.createCopy(shape.getFromX(), shape.getFromY(), shape.getToX(), shape.getToY(), shape.getCol(),shape.getStrokeWidth(),shape.isFill());       
        drawing.repeat(newShape);
        drawing.updateUndoStack(newShape);
    }

    @Override
    public void execute() {
    }
    
}
