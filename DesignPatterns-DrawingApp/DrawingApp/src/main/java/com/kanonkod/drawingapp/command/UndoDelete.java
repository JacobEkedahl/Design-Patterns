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
    private int numRecursion;
    private boolean doRecursion;
    private int limiter;
    private static final String type = "UndoDelete";
    public UndoDelete(Shape shape, Drawing drawing) {
        this.shape = shape;
        this.drawing = drawing;
    }
    public UndoDelete(Shape shape, Drawing drawing, int numRecursion , int limiter, boolean doRecursion) {
        this.shape = shape;
        this.drawing = drawing;
        this.limiter = limiter;
        this.numRecursion = numRecursion;
        this.doRecursion = doRecursion;
    }
    @Override
    public void undo() {
        Shape newShape = shape.createCopy(shape.getFromX(), shape.getFromY(), shape.getToX(), shape.getToY(), shape.getCol(),shape.getStrokeWidth(),shape.isFill());       
        drawing.repeat(newShape);
        
        if((this.doRecursion) && this.numRecursion>0){
            drawing.updateRedoStack(new RedoDelete(newShape,drawing,this.numRecursion,this.limiter,this.doRecursion)); 
            drawing.undoCommand();
        }
        else if((this.doRecursion) && this.numRecursion ==0){
            drawing.updateRedoStack(new RedoDelete(newShape,drawing,this.numRecursion,this.limiter,this.doRecursion));
        }
        else if(this.limiter==0 && !this.doRecursion){
             drawing.updateRedoStack(new RedoDelete(newShape,drawing,this.numRecursion,this.limiter,this.doRecursion));
        }
         
    }

    @Override
    public String toString() {
        return "UndoDelete{" + "dominoEffect=" + numRecursion + ", limiter=" + limiter + '}';
    }
    
    @Override
    public String getType(){
        return type;
    }
    
    @Override
    public Shape getHost(){
        return shape;
    }

    public int getNumRecursion() {
        return numRecursion;
    }

    public boolean isDoRecursion() {
        return doRecursion;
    }

    public int getLimiter() {
        return limiter;
    }

    @Override
    public void execute() {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
