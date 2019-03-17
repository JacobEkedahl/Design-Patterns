/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ChangeStrategies;

import model.Shape;
import model.interfaces.ChangeStrategy;

/**
 *
 * @author Jacob
 */
public class ChangeFillStrategy implements ChangeStrategy {

    private boolean fill;

    public ChangeFillStrategy(boolean fill) {
        this.fill = fill;
    }

    @Override
    public void change(Shape shape) {
        shape.setFill(fill);
    }

    @Override
    public StrategyType getStrategy() {
        return StrategyType.FILL;
    }

}
