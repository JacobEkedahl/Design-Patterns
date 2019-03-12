/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertychangers;

import model.Shape;

/**
 *
 * @author fno
 */
public class StrokeChange extends PropertyChange{
    double width;
    
    public StrokeChange(double w){
        this.width = w;
    }
    @Override
    public void doChange(Shape shape) {
        shape.setStrokeWidth(width);
    }
    
    @Override
    public PropertyChange getInstance(Shape shape) {
          return new StrokeChange(shape.getStrokeWidth());
    }
    
}
