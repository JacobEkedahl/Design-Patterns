## About
This is a drawingapplciation using Spring, Maven and JavaFX.
Dependencies used are: [Firebase Admin SDK](https://mvnrepository.com/artifact/com.google.firebase/firebase-admin/6.6.0) and [Reflections](https://mvnrepository.com/artifact/org.reflections/reflections/0.9.10).
Firestore is used as backend to store shapes which belongs to a drawing. 

The drawing class gets updated as soon as a update to a shape, new shape, removed shape has happened to a drawing. The database gets notified whenever a drawing is added via the command or the drawing object.
The ShapeFactory gets the names of all the subtypes of shapes by using [Reflections](https://mvnrepository.com/artifact/org.reflections/reflections/0.9.10) which are used as a key to generate the desired shape as well as setting id of the buttons in GUI.

## To compile run:
mvn clean install

## To add a shape
Make sure to create a class that inherits from Shape. Implement the abstract methods. Run the application once to generate the image which is automatically generated inside upon start [resources/shapes](https://github.com/JacobEkedahl/Design-Patterns/tree/master/DesignPatterns-DrawingApp/DrawingApp/src/main/resources/shapes).

## Setup:
- Optimally add the admin SDK to your preffered server using this [guide](https://firebase.google.com/docs/admin/setup?authuser=0)

- Otherwise you can [setup a firestore] https://firebase.google.com/docs/firestore/quickstart
- Generate a secret, jsonfile and add it under [resources](https://github.com/JacobEkedahl/Design-Patterns/tree/master/DesignPatterns-DrawingApp/DrawingApp/src/main/resources).
- Reference the secret in [FirebaseDb](https://github.com/JacobEkedahl/Design-Patterns/blob/master/DesignPatterns-DrawingApp/DrawingApp/src/main/java/databases/FirebaseDb.java) constructor instead of the current.

## To download and testrun:
[Download](https://github.com/JacobEkedahl/Design-Patterns/releases/download/v1.0/DrawingApp.jar). And run. :)
