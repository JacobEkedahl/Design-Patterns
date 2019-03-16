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
public class FillChange extends PropertyChange {
    private boolean fill;
    public FillChange(boolean fill){
        this.fill = fill;
    }

    @Override
    public void doChange(Shape shape) {
          shape.setFill(fill);
    }

    @Override
    public PropertyChange getInstance(Shape shape) {
         return new FillChange(shape.getFill());
    }
    
}
