package com.kanonkod.drawingapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.DoubleBinding;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
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

    private Pane canvasPane;

    @FXML
    private HBox buttonBar;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private CheckBox fillBtn;

    @FXML
    private Button undoBtn;

    @FXML
    private Button thrashcan;

    @FXML
    private Button redoBtn;

    @FXML
    private ComboBox widthSelector;

    ModelFascade model;
    Drawing drawing;
    //Subject subject;    
    @FXML
    private BorderPane border;
    @FXML
    private VBox vbox;
    @FXML
    private MenuBar menubar;
    @FXML
    private Menu File;

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
        double fromX = event.getX();
        double fromY = event.getY();
        model.deselectAll();
        model.addShape(fromX, fromY);
    }

    //mouse released, deselect
    @FXML
    private void saveTo(MouseEvent event) {
        model.deselectAll();
        model.handleMarker();
        model.deselect();
        save();
    }

    @FXML
    private void setColor(Event event) {
        //fill selected

        model.changeSelectedColor(colorPicker.getValue());
        model.setColor(colorPicker.getValue());
        this.save();
    }

    @FXML
    private void setWidth(Event event) {
        String widthSelValue = (String) widthSelector.getValue();
        String valueStr = widthSelValue.substring(0, widthSelValue.length() - 2);
        double value = Double.valueOf(valueStr);

        model.changeSelectedWidth(value);
        model.setWidth(value);
        this.save();
    }

    @FXML
    private void createNew(Event event) {
        //show popup with textbox
        this.createNew();
    }

    @FXML
    private void openDrawing(Event event) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(canvas.getScene().getWindow());
        dialog.setResizable(false);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/DrawingChooser.fxml"));
        Parent rootNode = null;
        try {
            rootNode = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(LoadViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene dialogScene = new Scene(rootNode, 230, 400);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    @FXML
    private void saveDrawing(Event event) {
        //save to db
        this.save();
    }

    @FXML
    private void undo(Event event) {
        model.getDrawing().undoAdd();
    }

    @FXML
    private void showAbout(Event event) {
        //not implemeneted
    }

    @FXML
    private void redo(Event event) {
        model.getDrawing().redoAdd();
    }

    @FXML
    private void removeSelected(Event event) {
        this.model.removeSelected();
        model.deselectAll();
        this.save();
    }

    @FXML
    private void changeFill(Event event) {
        boolean newVal = ((CheckBox) event.getSource()).isSelected();
        model.changeSelectedFill(newVal);
        model.setFill(newVal);
        save();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("init controller");
        // TODO
        model = ModelFascade.getInstance();
        model.getDrawing().attach(this);
        initDrawButtons();
        initRedoUndo();
        initThrascan();
        initColorPicker();
        //  mapCanvasToParent();
    }

    private void save() {
        try {
            model.saveData();
        } catch (IllegalArgumentException ex) {
            this.createNew();
            model.saveData();
        }
    }

    //init colorpicker with color black
    private void initColorPicker() {
        colorPicker.setValue(Color.BLACK);
        model.setColor(Color.BLACK);
    }

    private void initThrascan() {
        ImageView undoImg = new ImageView(new Image("images/thrashcan.png"));
        thrashcan.setGraphic(undoImg);
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

            try {
                Image image = new Image("shapes/" + key + ".png");
                shapeBtn.setGraphic(new ImageView(image));
            } catch (NullPointerException ex) {
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
        this.save();
    }

    private void createNew() {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("New drawing");
        dialog.setHeaderText("Enter the name of the drawing:");
        dialog.setContentText("Name:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name -> {
            model.clearDrawing();
            model.setName(name);
        });
    }

    @Override
    public void update() {
        //the drawing has been changed, clear and draw it
        model.draw(canvas.getGraphicsContext2D());
    }
}
