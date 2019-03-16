/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Operations;

import java.util.ArrayList;
import java.util.List;
import model.Shape;

/**
 *
 * @author Jacob
 */
public class ShapeMementos {
    ArrayList<Shape> shapes;
    
    public ArrayList<Shape> getState() {
        return shapes;
    }
    
    public void setState(ArrayList<Shape> shapes) {
        this.shapes = shapes;
    }
}
