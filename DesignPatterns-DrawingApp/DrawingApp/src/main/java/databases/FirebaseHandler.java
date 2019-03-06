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
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import model.Drawing;
import model.DrawingDAO;

/**
 *
 * @author Jacob
 */
public class FirebaseHandler {

    Firestore db = null;

    public FirebaseHandler() throws IOException {
        FileInputStream serviceAccount
                = new FileInputStream("./db/drawing-app-13f43-firebase-adminsdk-i43u0-41097af969.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://drawing-app-13f43.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);

        db = FirestoreClient.getFirestore();
    }

    public void addData(Drawing drawing) throws InterruptedException, ExecutionException {
        if (drawing.getName() == "") {
            throw new IllegalArgumentException();
        }
        DrawingDAO dbDrawing = new DrawingDAO(drawing);
        DocumentReference docRef = db.collection("drawings").document(drawing.getName());
        docRef.set(dbDrawing);
    }

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
}
