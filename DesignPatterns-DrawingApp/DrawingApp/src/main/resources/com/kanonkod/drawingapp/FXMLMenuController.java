/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kanonkod.drawingapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author fno
 */
public class FXMLMenuController implements Initializable {

    @FXML
    private AnchorPane Anchor;
    @FXML
    private BorderPane border;
    @FXML
    private VBox vbox;
    @FXML
    private MenuBar menubar;
    @FXML
    private Menu File;
    @FXML
    private HBox Hbox;
    @FXML
    private ImageView highlight;
    @FXML
    private Canvas canvas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickedItemClose(ActionEvent event) {
        
        System.out.println("Item was clicked");
    }

    @FXML
    private void clickedHighLight(MouseEvent event) {
    }

    @FXML
    private void saveTo(MouseEvent event) {
    }

    @FXML
    private void changeSize(MouseEvent event) {
    }

    @FXML
    private void saveFrom(MouseEvent event) {
    }

    @FXML
    private void itemClick(MouseEvent event) {
    }
    
}
