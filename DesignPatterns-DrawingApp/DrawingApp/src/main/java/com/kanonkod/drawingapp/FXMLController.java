package com.kanonkod.drawingapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.DoubleBinding;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.color;
import javax.imageio.ImageIO;
import model.Drawing;
import model.anOval;
import model.ModelFascade;
import model.aLine;
import model.Shape;
import model.ShapeLoader;
import model.interfaces.Observer;

public class FXMLController extends Observer implements Initializable {

    @FXML
    private Canvas canvas;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Pane canvasPane;

    @FXML
    private HBox buttonBar;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private CheckBox fillBtn;

    @FXML
    Button undoBtn;

    @FXML
    Button redoBtn;

    ModelFascade model;
    Drawing drawing;
    //Subject subject;    

    //mouse dragged, change size/move selected object
    @FXML
    private void changeSize(MouseEvent event) {
        double toX = event.getX();
        double toY = event.getY();
        model.setEnd(toX, toY);
    }

    //add shape, mouse pressed
    @FXML
    private void saveFrom(MouseEvent event) {
        //  model.clearDrawing();
        double fromX = event.getX();
        double fromY = event.getY();
        model.addShape(fromX, fromY);
    }

    //mouse released, deselect
    @FXML
    private void saveTo(MouseEvent event) {
        model.deselect();
    }

    @FXML
    private void setColor(Event event) {
        model.setColor(colorPicker.getValue());
    }

    @FXML
    private void undo(MouseEvent event) {
        //not implemeneted
    }

    @FXML
    private void redo(MouseEvent event) {
        //not implemeneted
    }
    
    @FXML
    private void changeFill(Event event) {
        boolean newVal = ((CheckBox) event.getSource()).isSelected();
        model.setFill(newVal);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("init controller");
        // TODO
        model = ModelFascade.getInstance();
        model.getDrawing().attach(this);
        initDrawButtons();
        initRedoUndo();
        initColorPicker();
        //  mapCanvasToParent();
    }
    
    //init colorpicker with color black
    private void initColorPicker() {
        colorPicker.setValue(Color.BLACK);
        model.setColor(Color.BLACK);
    }

    private void initRedoUndo() {
        ImageView undoImg = new ImageView(new Image("images/undo.png"));
        ImageView redoImg = new ImageView(new Image("images/redo.png"));

        undoBtn.setGraphic(undoImg);
        redoBtn.setGraphic(redoImg);
    }

    private void initDrawButtons() {
        final ToggleGroup group = new ToggleGroup();

        for (String key : ShapeLoader.getShapeKeys()) {
            ToggleButton shapeBtn = new ToggleButton();
            shapeBtn.setToggleGroup(group);

            File path = new File("./shapes/" + key + ".png");
            try {
                shapeBtn.setGraphic(new ImageView(new Image(new FileInputStream(path))));
            } catch (FileNotFoundException ex) {
                shapeBtn.setText(key);
            }

            shapeBtn.setId(key);
            shapeBtn.setOnAction(actionEvent -> {
                if (!shapeBtn.isSelected()) {
                    shapeBtn.setSelected(true);
                }
                String shapeTxt = ((ToggleButton) actionEvent.getSource()).getId();
                model.selectShape(shapeTxt);
            });

            buttonBar.getChildren().add(shapeBtn);
        }
    }

    private void mapCanvasToParent() {
        canvas.widthProperty().bind(canvasPane.widthProperty());
        canvas.heightProperty().bind(canvasPane.heightProperty());
    }

    private void clear() {
        model.clearDrawing();
    }

    @Override
    public void update() {
        //the drawing has been changed, clear and draw it
        model.draw(canvas.getGraphicsContext2D());
    }

    @FXML
    private void clickedItemClose(ActionEvent event) {
        System.out.println("clicked menu item");
    }

    @FXML
    private void clickedHighLight(MouseEvent event) {
        System.out.println("Clicked highlight");
    }
}
