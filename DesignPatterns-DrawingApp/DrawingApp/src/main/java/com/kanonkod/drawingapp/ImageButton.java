/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kanonkod.drawingapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Jacob
 */
public class ImageButton extends Button {

    private final String STYLE_NORMAL = "-fx-border-width: 1; fx-border-color: black; -fx-padding: 5, 5, 5, 5;";
    private final String STYLE_PRESSED = "-fx-border-width: 1; fx-border-color: black; -fx-padding: 6 4 4 6;";
    private boolean isPressed = false;
    ImageView view;
    private boolean viewIsSet = false;

    private void select() {
        if (viewIsSet) {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(-0.2);
            view.setEffect(colorAdjust);
        }

        setStyle(STYLE_PRESSED);
    }

    private void deselect() {
        if (viewIsSet) {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(0.0);
            view.setEffect(colorAdjust);
        }

        setStyle(STYLE_NORMAL);
    }

    public ImageButton(String key) {

        try {
            File path = new File("./shapes/" + key + ".png");
            view = new ImageView(new Image(new FileInputStream(path)));
            setGraphic(view);
            viewIsSet = true;
        } catch (FileNotFoundException ex) {
            setText(key);
        }
        setId(key);

        setStyle(STYLE_NORMAL);
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (isPressed) {
                    deselect();
                } else {
                    select();
                }

                isPressed = !isPressed;
            }
        });
    }
}
