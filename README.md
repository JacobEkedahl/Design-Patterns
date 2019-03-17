## Showcase of the drawingapplication

![](https://github.com/JacobEkedahl/Design-Patterns/blob/master/DesignPatterns-DrawingApp/Media/recording_drawingapp.gif)

## Introduction

This repository displays design patterns with use cases as well as an parallel programming use case.
One of the applications is a drawing application which uses firebase as backend and facilitates simultaneous use of same drawing online.
The other one showcase the perforamance of a parallised implementation of MergeSort and QuickSort as well as testing the threshold values for when to stop dividing a task into smaller tasks.

## Design patterns used
Where the design patterns are used are explained below
### Strategy
- Used in [Parallel-SortingAlgorithms](https://github.com/JacobEkedahl/Design-Patterns/tree/master/Parallel-SortingAlgorithms). All the sorting algorithms tested implements the Strategy interface. The Sorter class gets one of the implemented sortingalgorithms as input in the form of a SortingStrategy and performs either sort or different measurements depending on what user asks.
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

## More information

Please visit the respective applications [DrawingApp](https://github.com/JacobEkedahl/Design-Patterns/tree/master/DesignPatterns-DrawingApp) and [Parallel-SortingAlgorithms](https://github.com/JacobEkedahl/Design-Patterns/tree/master/Parallel-SortingAlgorithms) within this repository to read and find out more.
