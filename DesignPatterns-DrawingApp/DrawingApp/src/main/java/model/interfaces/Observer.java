/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.interfaces;

/**
 *
 * @author Jacob
 */
public abstract class Observer {
    public Object subject;
    public abstract void update();
}
