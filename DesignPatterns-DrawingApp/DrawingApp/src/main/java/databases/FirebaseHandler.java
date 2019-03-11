/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databases;

import com.google.api.core.ApiFuture;
import java.io.FileInputStream;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import model.Drawing;
import model.DrawingDAO;
import model.Shape;
import model.ShapeDAO;
import model.interfaces.Observer;

/**
 *
 * @author Jacob
 */
public class FirebaseHandler {

    private DrawingDAO updatedDrawing;
    List<Observer> observers = new ArrayList<>();
    Firestore db = null;

    /**
     * Sets up a connection with the firebase back-end.
     * @throws IOException 
     */
    public FirebaseHandler() throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("drawing-app-13f43-firebase-adminsdk-i43u0-41097af969.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(is))
                .setDatabaseUrl("https://drawing-app-13f43.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);

        db = FirestoreClient.getFirestore();
    }
    /**
     * Adds data to the database
     * @param drawing
     * @throws InterruptedException
     * @throws ExecutionException 
     */
    public void addData(Drawing drawing) throws InterruptedException, ExecutionException {
        if (drawing.getName() == "") {
            throw new IllegalArgumentException();
        }
        DrawingDAO dbDrawing = new DrawingDAO(drawing);
        DocumentReference docRef = db.collection("drawings").document(drawing.getName());
        docRef.set(dbDrawing);
        
    }
    /**
     * 
     * @param name name of file that containts the shape
     * @return the database entity
     * @throws InterruptedException
     * @throws ExecutionException 
     */
    public DrawingDAO getData(String name) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("drawings").document(name);

        ApiFuture<DocumentSnapshot> future = docRef.get();
        // block on response
        DocumentSnapshot document = future.get();
        DrawingDAO drawing = null;
        if (document.exists()) {
            // convert document to POJO
            System.out.println("drawingDao: " + document.getClass().toGenericString());
            drawing = document.toObject(DrawingDAO.class);
            System.out.println(drawing);
        } else {
            throw new NullPointerException();
        }
        // [END fs_get_doc_as_entity]
        return drawing;
    }
    /**
     * 
     * @return
     * @throws InterruptedException
     * @throws ExecutionException 
     */
    public List<String> getNames() throws InterruptedException, ExecutionException {
        List<String> result = new ArrayList<>();
        //asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = db.collection("drawings").get();
// future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            result.add(document.getId());
        }

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

    public synchronized DrawingDAO getDrawing() {
        return this.updatedDrawing;
    }

    private synchronized void setDrawing(DrawingDAO drawing) {
        this.updatedDrawing = drawing;
        notifyAllObservers();
    }

    public void setUpDbListener(String name) {
        System.out.println("setting up listener!");
        //remove all listeners
        removeListener();

        DocumentReference docRef = db.collection("drawings").document(name);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                    @Nullable FirestoreException e) {
                if (e != null) {
                    System.err.println("Listen failed: " + e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    System.out.println("Current data: " + snapshot.getData());
                    setDrawing(snapshot.toObject(DrawingDAO.class));
                } else {
                    System.out.print("Current data: null");
                }
            }
        });
    }

    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
    public void attach(Observer observer) {
        observers.add(observer);
    }
}
