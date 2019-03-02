/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jacob
 */
public class Drawing {
    List<Shape> shapes = new ArrayList<>();
    public Drawing() {
        
    }
    
    public void addShape(Shape shape) {
        //notify observers
        shapes.add(shape);
    }
}
