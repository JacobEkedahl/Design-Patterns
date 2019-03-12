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
public abstract class PropertyChange {
    public abstract void doChange(Shape shape);
   
    public abstract PropertyChange getInstance(Shape shape);
}
