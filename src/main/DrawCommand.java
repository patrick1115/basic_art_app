package main;

import java.awt.Point;

import main.Shapes.DrawStrategy;
import main.Shapes.ShapeFactory;
import model.interfaces.IApplicationState;
import model.interfaces.ICommand;
import model.interfaces.IDrawShape;
import model.interfaces.IUndoable;
import view.interfaces.PaintCanvasBase;
import model.ShapeType;

public class DrawCommand implements ICommand, IUndoable {

    private final IApplicationState appState; 
    private final PaintCanvasBase paintCanvas; 
    private final Point startPoint; 
    private final Point endPoint; 

    public DrawCommand(Point startPoint, Point endPoint, IApplicationState appState, PaintCanvasBase paintCanvas) {
        this.appState = appState; 
        this.paintCanvas = paintCanvas; 
        this.startPoint = startPoint; 
        this.endPoint = endPoint; 
    }

    @Override
    public void execute() {
        
        /*
         * Abstract Factory pattern lets you produce families of related objects 
         *      without specifying their concrete classes.
         * https://refactoring.guru/design-patterns/abstract-factory
         * 
         *   create abstract class for shapes
            then createShape from ShapeFactory which will utilize Null object pattern
         */ 

        ShapeType shapeType = appState.getActiveShapeType();
        // need to keep shapeType in when pasting different shapes, to past different kinds. 
        
        AbstractFactory newShape = ShapeFactory.createShape(startPoint, endPoint, appState, paintCanvas, shapeType); 
        DrawStrategy drawShape = new DrawStrategy((IDrawShape) newShape);
        drawShape.execute();
    }

    @Override
    public void undo() {
       
    }

    @Override
    public void redo() {
        
    }

}
