/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ChangeStrategies;

import javafx.scene.paint.Color;
import model.Shape;
import model.interfaces.ChangeStrategy;

/**
 *
 * @author Jacob
 */
public class ChangeColStrategy implements ChangeStrategy {
    
    private final Color col;
    public ChangeColStrategy(Color col) {
        this.col = col;
    }

    @Override
    public void change(Shape shape) {
        shape.setCol(col);
    }

    @Override
    public StrategyType getStrategy() {
        return StrategyType.COLOR;
    }
    
}
