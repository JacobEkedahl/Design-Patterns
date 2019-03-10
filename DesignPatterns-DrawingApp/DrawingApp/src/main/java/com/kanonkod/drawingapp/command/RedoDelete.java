/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kanonkod.drawingapp.command;

import model.Drawing;
import model.Shape;
import model.interfaces.RedoCommand;

/**
 *
 * @author fno
 */
public class RedoDelete implements RedoCommand {

    private Shape shape;
    private Drawing drawing;
    private int dominoFlag;
    private int limiter;
    private int index;
    private boolean doRecusion;
    public RedoDelete(Shape shape, Drawing drawing) {
        this.shape = shape;
        this.drawing = drawing;
    }

    public RedoDelete(Shape shape, Drawing drawing,int reverseIndex , int delimit, boolean doRecursion) {
        this.shape = shape;
        this.drawing = drawing;
        this.index = reverseIndex;
        this.limiter = delimit;
        this.doRecusion = doRecursion;
    }

    @Override
    public void redo() {
        drawing.removeShape(shape);
        if(this.limiter > 0 && this.index < this.limiter){
            drawing.updateUndoStack(new UndoDelete(shape,drawing,this.index,this.limiter,this.doRecusion));
            drawing.redoCommand();
        }
        else if(this.doRecusion && this.index == this.limiter){
            drawing.updateUndoStack(new UndoDelete(shape,drawing,this.index,this.limiter,this.doRecusion));
        }
        if(this.limiter==0 && !this.doRecusion){
            drawing.updateUndoStack(new UndoDelete(shape,drawing,this.index,this.limiter,this.doRecusion));
        }
        
       
        
        //drawing.updateRedoStack(shape, this);
    }

    @Override
    public void execute() {

    }
    
}
