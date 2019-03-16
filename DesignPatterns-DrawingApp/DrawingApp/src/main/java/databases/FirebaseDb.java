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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ExecutionException;
import model.Drawing;
import model.Operations.Command;
import model.Shape;
import model.ShapeDAO;
import model.ShapeFactory;
import model.interfaces.Observer;

/**
 *
 * @author Jacob
 */
public class FirebaseDb extends Database {

    Stack<Command> commands = new Stack<>();
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

    public void setUpDbListener(String name) {
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
                            switch (dc.getType()) {
                                case ADDED:
                                    System.out.println("New shape: " + dc.getDocument().getData());
                                    break;
                                case MODIFIED:
                                    System.out.println("Modified shape: " + dc.getDocument().getData());
                                    break;
                                case REMOVED:
                                    System.out.println("Removed shape: " + dc.getDocument().getData());
                                    break;
                                default:
                                    break;
                            }
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

    @Override
    public void addShape(Shape shape, String drawingID) {
        if (drawing.getName() == "") {
            throw new IllegalArgumentException();
        }
        ShapeDAO newShape = ShapeFactory.getShapeDAO(shape);
        DocumentReference docRef = db.collection("drawings").document(drawing.getName());
        docRef.set(dbDrawing);
    }

    @Override
    public void removeShape(Shape shape, String drawingID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
