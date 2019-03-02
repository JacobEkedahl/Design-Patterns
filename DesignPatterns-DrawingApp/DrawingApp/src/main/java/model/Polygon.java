/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Jacob
 */
public class Polygon extends Shape {
    public Polygon(float fromX, float fromY, float toX, float toY) {
        super(fromX, fromY, toX, toY);
    }

    @Override
    void drawShape() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
