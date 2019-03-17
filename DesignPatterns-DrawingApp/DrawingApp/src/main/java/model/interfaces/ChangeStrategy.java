/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.interfaces;

import model.changeStrategies.StrategyType;
import model.shapes.Shape;

/**
 *
 * @author Jacob
 */
public interface ChangeStrategy {
    public void change(Shape shape);
    public StrategyType getStrategy();
}
