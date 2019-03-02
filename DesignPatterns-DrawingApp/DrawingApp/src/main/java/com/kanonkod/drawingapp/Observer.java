/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kanonkod.drawingapp;

import model.Shape;
import model.Subject;

/**
 *
 * @author fno
 */
public interface Observer {
    
    public void update(Shape shp);
    public void setSubject(Subject sub);
    
}
