package controller;

import java.net.IDN;
import java.util.ArrayList;
import java.util.List;

import main.CommandHistory;
import main.Lists.AllShapeLists;
import model.interfaces.ICommand;
import model.interfaces.IDrawShape;
import model.interfaces.IUndoable;

public class DeleteCommand implements ICommand, IUndoable {

    private final List<IDrawShape> deleteShape = new ArrayList<IDrawShape>();
    

    @Override
    public void execute() {
        deleteShape.addAll(AllShapeLists.selectedShapeList.getList());
        
        for (IDrawShape shape : deleteShape) {
            AllShapeLists.currentShapes.delete(shape);
        }
        System.out.println("Deleted Shape");
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        //deleteShape.addAll(AllShapeLists.selectedShapeList.getList());

        for (IDrawShape shape : deleteShape) {
            AllShapeLists.currentShapes.add(shape);
        }       
    }

    @Override
    public void redo() {
        //deleteShape.addAll(AllShapeLists.selectedShapeList.getList());

        for (IDrawShape shape : deleteShape) {
            AllShapeLists.currentShapes.delete(shape);
        }        
    }


}
