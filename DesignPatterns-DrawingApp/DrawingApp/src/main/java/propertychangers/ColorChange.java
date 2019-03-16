/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertychangers;

import javafx.scene.paint.Color;
import model.Shape;

/**
 *
 * @author fno
 */
public class ColorChange extends PropertyChange{
    private Color toChange;
    public ColorChange(Color color) {
        this.toChange = color;
    }
    @Override
    public void doChange(Shape shape) {
         shape.setCol(toChange);
    }
    @Override
    public ColorChange getInstance(Shape shape) {
             return new ColorChange(shape.getCol());
    }

   
    
}
