/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kanonkod.drawingapp.command;

import model.Drawing;
import model.Shape;
import model.interfaces.UndoCommand;

/**
 *
 * @author fno
 */
public class UndoDelete implements UndoCommand {
    private Shape shape;
    private Drawing drawing;
    private int dominoEffect;
    private boolean doRecursion;
    private int limiter;
    public UndoDelete(Shape shape, Drawing drawing) {
        this.shape = shape;
        this.drawing = drawing;
    }
    public UndoDelete(Shape shape, Drawing drawing, int dominoEffect , int limiter, boolean doRecursion) {
        this.shape = shape;
        this.drawing = drawing;
        this.limiter = limiter;
        this.dominoEffect = dominoEffect;
        this.doRecursion = doRecursion;
    }
    @Override
    public void undo() {
        Shape newShape = shape.createCopy(shape.getFromX(), shape.getFromY(), shape.getToX(), shape.getToY(), shape.getCol(),shape.getStrokeWidth(),shape.isFill());       
        drawing.repeat(newShape);
        
        if((this.doRecursion) && this.dominoEffect>0){
            drawing.updateRedoStack(new RedoDelete(newShape,drawing,this.dominoEffect,this.limiter,this.doRecursion)); 
            drawing.undoCommand();
        }
        else if((this.doRecursion) && this.dominoEffect ==0){
            drawing.updateRedoStack(new RedoDelete(newShape,drawing,this.dominoEffect,this.limiter,this.doRecursion));
        }
        else if(this.limiter==0 && !this.doRecursion){
             drawing.updateRedoStack(new RedoDelete(newShape,drawing,this.dominoEffect,this.limiter,this.doRecursion));
        }
         
    }

    @Override
    public String toString() {
        return "UndoDelete{" + "dominoEffect=" + dominoEffect + ", limiter=" + limiter + '}';
    }
    
    

    @Override
    public void execute() {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
