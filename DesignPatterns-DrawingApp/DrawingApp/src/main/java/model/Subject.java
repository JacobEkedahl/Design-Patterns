/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.kanonkod.drawingapp.Observer;

/**
 *
 * @author fno
 */
public interface Subject {
    
   public void register(Observer obj);
   public void notifyObservers(Drawing drawing);
   public Shape getUpdate(Observer obj); 
}
