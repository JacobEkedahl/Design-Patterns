/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datalayer;

import com.google.api.core.ApiFuture;
import java.io.FileInputStream;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentChange;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.EventListener;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreException;
import com.google.cloud.firestore.ListenerRegistration;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.annotations.Nullable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.ExecutionException;
import javafx.scene.paint.Color;
import model.Drawing;
import model.operations.Command;
import model.shapes.Shape;
import model.ShapeFactory;
import model.interfaces.Observer;
import model.interfaces.ShapeListener;

/**
 *
 * @author Jacob
 */
public class FirebaseDb extends Database {

    List<Observer> observers = new ArrayList<>();
    Firestore db = null;

    public FirebaseDb() throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("drawing-app-13f43-firebase-adminsdk-i43u0-41097af969.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(is))
                .setDatabaseUrl("https://drawing-app-13f43.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);

        db = FirestoreClient.getFirestore();
    }

    public List<String> getNames() throws InterruptedException, ExecutionException {
        Set<String> map = new HashSet<>();
        ApiFuture<QuerySnapshot> future = db.collection("drawings").get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            map.add(document.getString("name"));
        }

        List<String> result = new ArrayList<>(map);
        Collections.sort(result);
        return result;
    }

    private void removeListener() {
        Query query = db.collection("drawings");
        ListenerRegistration registration = query.addSnapshotListener(
                new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot t, FirestoreException fe) {
                System.out.println("firestore exception");
            }
            // ...
        });

// Stop listening to changes
        registration.remove();
    }

    private String name;

    public void setUpDbListener(String name) {
        this.name = name;
        System.out.println("setting up listener!");
        //remove all listeners
        removeListener();

        db.collection("drawings")
                .whereEqualTo("name", name)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot snapshots,
                            @Nullable FirestoreException e) {
                        if (e != null) {
                            System.err.println("Listen failed: " + e);
                            return;
                        }

                        for (DocumentChange dc : snapshots.getDocumentChanges()) {
                            //  System.out.println("Retrieved something from db");
                            ShapeDAO dao = dc.getDocument().toObject(ShapeDAO.class);
                            Shape shape = ShapeFactory.getShape(dao);
                            shape.setId(dc.getDocument().getId());

                            switch (dc.getType()) {
                                case ADDED:
                                    System.out.println("from db, adding");
                                    drawing.newShape(shape);
                                    break;
                                case MODIFIED:
                                    System.out.println("from db, modifying - " + shape + ":" + shape.getId());
                                    drawing.modify(shape);
                                    break;
                                case REMOVED:
                                    System.out.println("from db, removed");
                                    drawing.remove(shape);
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                });
    }

    @Override
    public void newShape(ArrayList<Shape> shapes) {
        System.out.println("NEW SHAPE");
        if (name == "") {
            throw new IllegalArgumentException();
        }

        for (Shape shape : shapes) {
            ShapeDAO newShape = ShapeFactory.getShapeDAO(shape, name);
            db.collection("drawings").document(shape.getId()).set(newShape);
        }
    }

    @Override
    public void removeShape(ArrayList<Shape> shapes) {
        for (Shape shape : shapes) {
            db.collection("drawings").document(shape.getId()).delete();
        }
    }

    @Override
    public void updateColor(ArrayList<Shape> shapes) {
        for (Shape shape : shapes) {

            DocumentReference docRef = db.collection("drawings").document(shape.getId());

            int red = (int) (255 * shape.getCol().getRed());
            int green = (int) (255 * shape.getCol().getGreen());
            int blue = (int) (255 * shape.getCol().getBlue());

            docRef.update("red", red);
            docRef.update("green", green);
            docRef.update("blue", blue);
        }
    }

    @Override
    public void updateWidth(ArrayList<Shape> shapes) {
        for (Shape shape : shapes) {
            DocumentReference docRef = db.collection("drawings").document(shape.getId());
            docRef.update("strokeWidth", shape.getStrokeWidth());
        }
    }

    @Override
    public void updateFill(ArrayList<Shape> shapes) {
        for (Shape shape : shapes) {
            DocumentReference docRef = db.collection("drawings").document(shape.getId());
            docRef.update("fill", shape.getFill());
        }
    }

    @Override
    public void updateSize(Shape shape) {
        DocumentReference docRef = db.collection("drawings").document(shape.getId());

        Map<String, Object> updates = new HashMap<>();
        updates.put("toX", shape.getToX());
        updates.put("toY", shape.getToY());
        
        docRef.update(updates);
    }
}
