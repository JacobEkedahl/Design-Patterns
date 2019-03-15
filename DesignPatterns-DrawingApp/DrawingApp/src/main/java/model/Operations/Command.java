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
public abstract class Command {
    ArrayList<Shape> myShapes;
    ShapeMementos mementos;
        
    public abstract ArrayList<Shape> execute(ArrayList<Shape> orig);
    public abstract ArrayList<Shape> unExecute(ArrayList<Shape> orig);
    
}
