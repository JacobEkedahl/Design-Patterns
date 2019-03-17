package com.kanonkod.drawingapp.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.ShapeLoader;

/**
 * FXML Controller class
 *
 * @author Jacob
 */
public class LoadViewController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Canvas canvas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // loadImages();
        changeToDrawing();
    }

    private void changeToDrawing() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ShapeLoader.initImages(canvas.getGraphicsContext2D());
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/DrawingView.fxml"));
                Parent rootNode = null;
                try {
                    rootNode = loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(LoadViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene scene = new Scene(rootNode, 1000, 800);

                scene.getStylesheets().add("/styles/Styles.css");
                Stage stage = (Stage) anchorPane.getScene().getWindow();
                stage.setScene(scene);
            }
        });
    }

    private void loadImages() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
            }
        });

    }

}
