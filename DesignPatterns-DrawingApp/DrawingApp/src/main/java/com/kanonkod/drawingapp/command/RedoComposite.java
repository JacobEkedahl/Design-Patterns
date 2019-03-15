/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kanonkod.drawingapp.command;

import java.util.List;
import model.Composite;
import model.Drawing;
import model.Shape;
import model.interfaces.RedoCommand;


/**
 *
 * @author fno
 */
public class RedoComposite implements RedoCommand{
    private Drawing drawing;
    private Composite composite;

    public RedoComposite(Composite composite, Drawing drawing) {
        this.drawing = drawing;
        this.composite = composite;
    }
   
    @Override
    public void redo() {
       List<Shape> shapes = composite.getShapes();
       this.drawing.updateUndoStack(new UndoComposite(composite,drawing));
       this.drawing.repeat(composite);
       for(Shape s : shapes){
           this.drawing.removeShape(s);
       }
    
    }

    
    
}
