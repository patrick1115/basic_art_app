# Basic Art App

## Table of Contents
* [List of Bugs, Miscellaneous](#List-Bugs,-Miscellaneous)
* [Some Design Patterns](#Some-Design-Patterns)

### List of Bugs, Miscellaneous 
  * For the most part, all required features are useable in the application. 
     
    > Bug - Select a Shape (Triangle)
      
      A visual bug, selecting a triangle, I was not able to get the shapeoutline to form perfectly around the triangle shape drawn. Rectangles and Ellipses are fine (except for a really wide ellipse drawn).
    
    > Bug - Selecting:  
    
      Selecting the actual shape would select the shape correctly, but when selecting an area of shapes, the bounding region sometimes requires 
        you to select the shape fully. 
     
    > Bug - Group / Ungroup 
      
      Again, they work but when ungrouping sometimes, typically after the first "Group" call, "Ungroup" would set the ArrayList to 0 
      because of how the list's size would decrease by 1. Therefore, "Ungroup" would not work after a nested group occurs. 
      
      Group #1 would have to re-selected, and the "ungroup" function would work again. 
   

### Some Design Patterns
  *1* Command Pattern:
  
  A behavioral pattern that utilizes encapsulation with the method call. Its main encapsulation calls are for Draw, Select, Move, Copy, Paste, Delete, Undo, Redo, Group, and Ungroup
  
  The flexibility of the Command Pattern allows for further functionality such as the addition of the DrawCommandProxy for pasting the selected, copied shapes. 
    
      > Interfaces: 
           
           ICommand, IUndoable, (implements) IDrawShape
    
      > Classes: 
           
           DrawCommand, SelectShapeCommand, MoveCommand, CopyCommand, PasteCommand, DrawCommandProxy, DeleteCommand, GroupCommand, UnGroupCommand
 
 *2* Strategy Pattern:
 
 A behavioral pattern which utilizes the IDrawShape interface for CreateRectangle, CreateTriangle, and CreateEllipse to draw their respective shapes. This patterns would be useful should other shapes be added to reinforce the Single Responsibility Principle. ICommand and IUndoable are implemented for the class to run, and for undoing/redoing the drawing of shapes. 
    
      > Interfaces: 
           
           ICommand, IUndoable, (implements) IDrawShape
    
      > Classes: 
           
           DrawStrategy
           
 *3* Null Object Pattern:

Behavioral pattern to define default behaviour by implementing the IDrawShape interface, and if there was a NullReference error, be used to help debug for choosing items in the application. 
    
      > Interfaces: 
           
           (implements) IDrawShape, utlizes AbstractFactory
    
      > Classes: 
           
           NullAlert        
           
 *4* Proxy Pattern:
 
 Wrapper pattern that will implement the same interface as its delegated command. In this case, IDrawShape would be implemented to help form the copied shape where the proxy adds functionality to the paste class by creating the subject with the references: PrimaryColor, SecondaryColor, ShapeType, and ShapeShadingType to the copied and pasted shape being made.  
    
      > Interfaces: 
           
           ICommand, IDrawShape
    
      > Classes: 
           
           DrawCommand, DrawCommandProxy (Delegates to DrawCommand)
        
  *5* Singleton Pattern:
  
  Creational Pattern for a single instance of an object. Used to create and maintain primary list of shapes - whether it be currently drawn, selected, copied on the clipboard, and grouped. I used this pattern so once initialized, I could use the Singleton Pattern of calling AllShapeLists when needing to access anything related to a list of shapes. 
    
      > Interfaces: 
           
           N/A
    
      > Classes: 
           
           AllShapeLists, FormShapeList (instance)     
           
  *6* Composite Pattern:
  
  Structural Pattern that would use the component class, GroupCommand, to interact with objects in the composition design. GroupCommand would be the Composite and Leaf, given it would be unkown if a Group would be a leaf or for another grouping.
  
      > Interfaces: 
           
           ICommand, IDrawShape
    
      > Classes: 
           
           GroupCommand, UngroupCommand         
           
 *7* Abstract Factory Pattern:
  
  Abstract Factory pattern lets me produce families of related objects without specifying their concrete classes. The abstract factory would create methods to return abstract shape types represented with the ShapeFactory class for CreateRectangle, CreateEllipse, and CreateTriangle to create their shape. This way the client/user doesn't have to be aware of the factory's createShape class.   
  
   With the Abstract Factory Pattern, the Single Responsibility Principle and Open/Close Principle is maintained.             
           
           

 
 
