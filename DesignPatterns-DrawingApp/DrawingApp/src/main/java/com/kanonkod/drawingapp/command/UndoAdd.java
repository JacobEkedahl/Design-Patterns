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
    private static final String type = "UndoAdd";

    public UndoAdd(Shape shape, Drawing model) {
        this.drawing = model;
        this.shape = shape;
    }
    
    @Override
    public void undo() {
        //Remove what was previously inserted
        drawing.removeShape(shape);
        drawing.updateRedoStack(new RedoAdd(this.shape,this.drawing));
    }
    
    
      @Override
    public String getType(){
        return type;
    }
    
     @Override
    public Shape getHost(){
        return shape;
    }
    

    @Override
    public void execute() {
      
    }
    
}
