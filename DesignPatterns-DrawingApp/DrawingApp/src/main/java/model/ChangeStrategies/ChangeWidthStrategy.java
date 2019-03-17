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
public class ChangeWidthStrategy implements ChangeStrategy {

    private double width;

    public ChangeWidthStrategy(double width) {
        this.width = width;
    }

    @Override
    public void change(Shape shape) {
        shape.setStrokeWidth(width);
    }

    @Override
    public StrategyType getStrategy() {
        return StrategyType.WIDTH;
    }

}
