/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.interfaces;

import model.Shape;

/**
 *
 * @author fno
 */
public interface Command {
    
    public void execute();
    public String getType();
    public Shape getHost();
    
}
