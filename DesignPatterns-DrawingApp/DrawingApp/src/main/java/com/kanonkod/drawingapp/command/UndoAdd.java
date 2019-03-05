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
    private int index;

    public UndoAdd(Shape shape, Drawing model, int index) {
        this.drawing = model;
        this.shape = shape;
        this.index = index;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public void undo() {
      // drawing.clearOneImage(shape, index);
       // model.clearDrawing();
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void execute() {
      
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
