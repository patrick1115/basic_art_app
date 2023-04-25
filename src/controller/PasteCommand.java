package controller;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import main.AbstractFactory;
import main.CommandHistory;
import main.DrawCommand;
import main.Lists.AllShapeLists;
import main.Shapes.ShapeFactory;
import model.ShapeColor;
import model.ShapeType;
import model.interfaces.IApplicationState;
import model.interfaces.ICommand;
import model.interfaces.IDrawShape;
import model.interfaces.IUndoable;
import view.interfaces.PaintCanvasBase;

public class PasteCommand implements ICommand, IUndoable{

    private final IApplicationState appState; 
    private final PaintCanvasBase paintCanvas;
    private final List<IDrawShape> shapesList = new ArrayList<IDrawShape>(); 
    private final List<IDrawShape> pasteList = new ArrayList<IDrawShape>(); 

    public PasteCommand(IApplicationState appState, PaintCanvasBase paintCanvas) {
        this.appState = appState;
        this.paintCanvas = paintCanvas; 
        this.shapesList.addAll(AllShapeLists.shapesClipboard.getList());
    }

    @Override
    public void execute() {
        
        for (IDrawShape shape : shapesList) {
            Point startPoint = (Point) shape.getStartPoint().clone();
            Point endPoint = (Point) shape.getEndPoint().clone(); 
            
            startPoint.translate(50, 50);
            endPoint.translate(50, 50);
            
            /*
             * will create a new shape based on what is selected; 
             *  use the proxy pattern to make a new shape - my issue before was calling DrawCommand again 
             *  and the shapes coming out incorrectly 
             */

            IDrawShape newShape = createShape(startPoint, endPoint, shape.getShapeType());
            pasteList.add(newShape);
            DrawCommandProxy proxy = new DrawCommandProxy(shape, newShape);
            proxy.execute();
            CommandHistory.add(this); 
        } 
        System.out.println("Pasted Shape");      
    }

    private IDrawShape createShape(Point startPoint, Point endPoint, ShapeType shapeType) {
        AbstractFactory drawShape = ShapeFactory.createShape(startPoint, endPoint, appState, paintCanvas, shapeType);
        return (IDrawShape) drawShape; 
    }

    @Override
    public void undo() {
        for (IDrawShape shape : pasteList) {
            AllShapeLists.currentShapes.delete(shape);
        }
        
    }

    @Override
    public void redo() {
        for (IDrawShape shape : pasteList) {
            AllShapeLists.currentShapes.add(shape);
        }        
    }


}
