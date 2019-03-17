## Showcase of the drawingapplication

![](https://github.com/JacobEkedahl/Design-Patterns/blob/master/DesignPatterns-DrawingApp/Media/recording_drawingapp.gif)

## Introduction

This repository displays two different applications in which suitable design patterns has been implemented in various forms.
One of the applications is a drawing application which uses firestore as backend and facilitates simultaneous use of same drawing online.
The other one showcases the performance of a parallised implementation of MergeSort and QuickSort as well as testing for and finding a reasonable threshold value for when to stop dividing a task into smaller tasks.

## Design patterns used
Where in the applications the design patterns are used is explained below
### Strategy
- Used in [Parallel-SortingAlgorithms](https://github.com/JacobEkedahl/Design-Patterns/tree/master/Parallel-SortingAlgorithms). All the sorting algorithms tested implements the Strategy interface. The Sorter class gets one of the implemented sortingalgorithms as input in the form of a SortingStrategy and either sorts, measure the performance of the sorting algorithm or finding a reasonable threshold value.

- Used in [DrawingApp](https://github.com/JacobEkedahl/Design-Patterns/tree/master/DesignPatterns-DrawingApp) for the different types of changes of the shape the user can do (Change color, strokewidth, etc).

### Model-View-Controller
- Implemented in [DrawingApp](https://github.com/JacobEkedahl/Design-Patterns/tree/master/DesignPatterns-DrawingApp) where all static object as defined in a seperate FXML file and with a dedicated controller where some of the dynamic elements are created upon initialization. The model is in a separate package and is communicating with the objects in the datalayer as well as controller/view.

### Subject-Observer
- Implemented in [DrawingApp](https://github.com/JacobEkedahl/Design-Patterns/tree/master/DesignPatterns-DrawingApp) and is used for updating the view when the drawing is modified.

### Template
- Implemented in [DrawingApp](https://github.com/JacobEkedahl/Design-Patterns/tree/master/DesignPatterns-DrawingApp) in the case of superclass Shape which has the final method draw which all the other shapes such as aLine, anOval etc inherits.

### Fascade
- Implemented in [DrawingApp](https://github.com/JacobEkedahl/Design-Patterns/tree/master/DesignPatterns-DrawingApp), the ModelFascade which hides the complexity between the model and the controller/view. Also initialises the listeners in the datalayer and other classes in the model.

### Prototype
- Implemented in [DrawingApp](https://github.com/JacobEkedahl/Design-Patterns/tree/master/DesignPatterns-DrawingApp), and the ShapeFactory returns a shape of the kind determined by the name of the class of the subtype of Shape which user wants to add to the drawing.

### Memento and Command
- Used for the undo/redo functionality. Two stacks in the drawing are used to facilitate this behaviour. The implementation can be found under [Operations](https://github.com/JacobEkedahl/Design-Patterns/tree/master/DesignPatterns-DrawingApp/DrawingApp/src/main/java/model/Operations)

## More information

Please visit the documentation of the applications [DrawingApp](https://github.com/JacobEkedahl/Design-Patterns/tree/master/DesignPatterns-DrawingApp) and [Parallel-SortingAlgorithms Report](https://github.com/JacobEkedahl/Design-Patterns/blob/master/Parallel-SortingAlgorithms/Documentation/Report.pdf) within this repository to read and find out more.
