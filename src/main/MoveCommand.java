package main;

import java.awt.Point;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.x500.X500Principal;
import javax.swing.text.DefaultEditorKit.DefaultKeyTypedAction;

import main.Lists.AllShapeLists;
import model.interfaces.IApplicationState;
import model.interfaces.ICommand;
import model.interfaces.IDrawShape;
import model.interfaces.IUndoable;
import view.interfaces.IUiModule;
import view.interfaces.PaintCanvasBase;

public class MoveCommand implements ICommand, IUndoable{

    private final IApplicationState appState; 
    private final PaintCanvasBase paintCanvas;
    private final Point startPoint; 
    private final Point endPoint;
    private final List<IDrawShape> selectedShapes = new ArrayList<>(); 

    public MoveCommand(Point startPoint, Point endPoint, IApplicationState appState, PaintCanvasBase paintCanvas) {
        this.appState = appState;
        this.paintCanvas = paintCanvas;
        this.startPoint = startPoint; 
        this.endPoint = endPoint;
    }

    @Override
    public void execute() {
        int deltaX = endPoint.x - startPoint.x;
        int deltaY = endPoint.y - startPoint.y; 
        System.out.println("Move shape");
        selectedShapes.addAll(AllShapeLists.selectedShapeList.getList());
        for (IDrawShape shape : selectedShapes) {
            shape.moveShape(deltaX, deltaY);
        }
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        int deltaX = startPoint.x - endPoint.x;
        int deltaY = startPoint.y - endPoint.y; 
        for (IDrawShape shape : selectedShapes) {
            shape.moveShape(deltaX, deltaY);
        }
    }

    @Override
    public void redo() {
        int deltaX = endPoint.x - startPoint.x;
        int deltaY = endPoint.y - startPoint.y; 
        for (IDrawShape shape : selectedShapes) {
            shape.moveShape(deltaX, deltaY);
        }         
    }

}
