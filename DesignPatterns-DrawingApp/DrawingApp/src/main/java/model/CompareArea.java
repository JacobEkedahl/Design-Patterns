/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Comparator;

/**
 *
 * @author fno
 */
public class CompareArea implements Comparator<Shape>{

    public CompareArea() {
    }

    
    @Override
    public int compare(Shape o1, Shape o2) {
          double area1 = Math.pow(o1.getToX() - o1.getFromX(),2) *   Math.pow(o1.getToY() - o1.getFromY(),2);
          double area2 = Math.pow(o2.getToX() - o2.getFromX(),2) *   Math.pow(o2.getToY() - o2.getFromY(),2);
          return (int)(area1 - area2);

    }
    
}
