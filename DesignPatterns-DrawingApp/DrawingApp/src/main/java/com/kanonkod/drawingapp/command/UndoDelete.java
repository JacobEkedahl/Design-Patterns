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
import model.ShapeFactory;
import model.interfaces.UndoCommand;

/**
 *
 * @author fno
 */
public class UndoDelete implements UndoCommand {
    private List<Shape> shapes;
    private Drawing drawing;
    private int doRepeat;
    
    
   
    public UndoDelete(List<Shape> shapes, Drawing drawing) {
        this.shapes = shapes;
        this.drawing = drawing;
        
    }
  
    @Override
    public void undo() {
        
        this.drawing.updateRedoStack(new RedoDelete(new ArrayList<Shape>(shapes),this.drawing));
        
        for(Shape s : shapes){
            s = ShapeFactory.getShape(s.getClass().getSimpleName(),s.getFromX(), s.getFromY(), s.getToX(), s.getToY(), s.getCol(),s.getStrokeWidth(),s.isFill());  
            drawing.repeat(s);
         }
        
   
    }

    public int getDoRepeat() {
        return doRepeat;
    }

    
}
