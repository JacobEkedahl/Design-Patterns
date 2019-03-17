/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.interfaces;

import model.Shape;

/**
 *
 * @author Jacob
 */
public interface ShapeReplacer {
    public abstract void newShape(Shape shape);
    public abstract void modify(Shape shape);
    public abstract void remove(Shape shape);
}
