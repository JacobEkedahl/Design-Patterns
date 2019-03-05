/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javax.imageio.ImageIO;
import org.reflections.Reflections;

/**
 * Loads the map with name to shapeobject to the ShapeFactory Loads the images
 * if not already loaded
 *
 * @author Jacob
 */
public class ShapeLoader {

    static final String directoryName = "shapes";
    public static void initImages(GraphicsContext gc) {
        File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdir();
        }
        
        WritableImage wim = new WritableImage(25, 25);
        //draw an image from 0,0 to 30,30 with each shape if that has not already been done
        for (String key : getShapeKeys()) {
            System.out.println("key: " + key);
            String fileName = directoryName + File.separator + key + ".png";
            File tmpFile = new File(fileName);
            if (tmpFile.exists()) {
                continue;
            }

            gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
            Shape shape = ShapeFactory.getShape(key, 2, 2, 23, 23, Color.BLACK, 1, false);
            shape.draw(gc);
            gc.getCanvas().snapshot(null, wim);

            File file = new File(fileName);
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", file);
            } catch (Exception s) {
            }
        }
    }

    public static List<String> getShapeKeys() {
        Reflections reflections = new Reflections("model");
        List<String> result = reflections.getSubTypesOf(Shape.class)
                .stream()
                .map(s -> s.getSimpleName())
                .collect(Collectors.toList());
        Collections.sort(result);
        return result;
    }

    public static HashMap<String, Shape> getShapeTypes() {
        HashMap<String, Shape> result = new HashMap<>();

        //getting the names of the subclasses
        Reflections reflections = new Reflections("model");
        Set<Class<? extends Shape>> subTypes
                = reflections.getSubTypesOf(Shape.class);

        for (Class type : subTypes) {
            try {
                result.put(type.getSimpleName(), (Shape) type.newInstance());
            } catch (InstantiationException ex) {
                Logger.getLogger(ShapeLoader.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ShapeLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return result;
    }
}
