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
    private int index;

    public RedoAdd(Shape shape, Drawing drawing, int index) {
        this.shape = shape;
        this.drawing = drawing;
        this.index = index;
    }
    
    
    @Override
    public void redo() {
       
       /* Shape newShape = shape.createCopy(shape.getFromX(), shape.getFromY(), shape.getToX(), shape.getToY(), shape.getCol(),shape.getStrokeWidth(),shape.isFill());
        
       
        newShape.setFromX(shape.getFromX()+30);
        newShape.setFromY(shape.getFromY()+30);
        newShape.setToX(shape.getToX()+30);
        newShape.setToY(shape.getToY()+30);
        drawing.repeat(newShape);*/
    }

    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
