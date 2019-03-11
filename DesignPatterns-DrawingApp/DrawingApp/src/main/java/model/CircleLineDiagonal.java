/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author fno
 */
public class CircleLineDiagonal extends Shape{
     private Shape lineComponent = null;
    private Shape circleComponent = null;
    /**
     * Initialize the components
     */
    public void init(){
        if(lineComponent==null ){
             this.lineComponent = initComponent("aLine");//ShapeFactory.getShape("aLine", super.getFromX(), super.getFromY(), super.getToX(), super.getToY(), super.getCol(), super.getStrokeWidth(), super.getFill());
         }
        if(circleComponent==null ){
             this.circleComponent = initComponent("anOval");//ShapeFactory.getShape("anOval", super.getFromX(), super.getFromY(), super.getToX(), super.getToY(), super.getCol(), super.getStrokeWidth(), super.getFill());
         }   
    }
    public Shape initComponent(String shape){
        return  ShapeFactory.getShape(shape, super.getFromX(), super.getFromY(), super.getToX(), super.getToY(), super.getCol(), super.getStrokeWidth(), super.getFill());
    }
    public void initDraw(GraphicsContext gc){
        init();
        drawComponents(gc);
    }
    public void drawComponents(GraphicsContext gc){
       this.circleComponent.draw(gc);
       this.lineComponent.draw(gc);
    }
    public void initChange(double newX, double newY){
        init();
        change(newX,newY);
    }
    public void change(double newX, double newY){
        this.setEnd(newX, newY);
        lineComponent.changeSize(newX, newY);
        circleComponent.changeSize(newX, newY); 
    }
    @Override
    void drawFill(GraphicsContext gc) {
         initDraw(gc);         
    }

    @Override
    void drawHollow(GraphicsContext gc) {
         initDraw(gc);
    }
    @Override
    void changeSize(double newX, double newY) {
         initChange(newX,newY);       
    }
    
    
    
}
