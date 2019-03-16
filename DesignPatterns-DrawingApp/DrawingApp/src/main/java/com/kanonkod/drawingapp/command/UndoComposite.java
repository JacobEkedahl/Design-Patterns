/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kanonkod.drawingapp.command;

import java.util.ArrayList;
import java.util.List;
import model.Composite;
import model.Drawing;
import model.Shape;
import model.interfaces.UndoCommand;

/**
 *
 * @author fno
 */
public class UndoComposite implements UndoCommand{
    private Composite composite;
    private Drawing drawing;

    public UndoComposite(Composite shape, Drawing drawing) {
        this.composite = shape;
        this.drawing = drawing;
    }
        
    
    @Override
    public void undo() {
       List<Shape> shapes = composite.getShapes();
       this.drawing.updateRedoStack(new RedoComposite(composite,drawing));
       for(Shape s : shapes){
           this.drawing.updateUndoStack(new UndoAdd(s,this.drawing));
           this.drawing.addShape(s);
       }
       this.drawing.removeShape(composite);
       
    }

    
    
}
