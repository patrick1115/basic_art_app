package controller;

import java.util.ArrayList;
import java.util.List;

import main.CommandHistory;
import main.Lists.AllShapeLists;
import model.interfaces.ICommand;
import model.interfaces.IDrawShape;
import model.interfaces.IUndoable;
import view.gui.PaintCanvas;

public class UnGroupCommand implements ICommand, IUndoable {

    private final List<IDrawShape> ungroupLists = new ArrayList<>();
    private GroupCommand shapeGroupCommand; 

    @Override
    public void execute() {
        AllShapeLists ungrouping = new AllShapeLists();
        ungroupLists.addAll(ungrouping.selectedShapeList.getList());  
        
        IDrawShape lastShape = ungroupLists.get(ungroupLists.size()-1);
        shapeGroupCommand = lastShape.getGroup().get(lastShape.getGroup().size()-1);

        for(IDrawShape shape : ungroupLists) {
            shape.removeFromGroup();
        }

        CommandHistory.add(this);
        System.out.println("Ungrouping previous group" );
    }

    @Override
    public void undo() {
        for (IDrawShape shape : ungroupLists) {
            shape.addToGroup(shapeGroupCommand);
            AllShapeLists.selectedShapeList.add(shape);
        }
        System.out.println("Undo the ungroup, reforming group");
        
    }

    @Override
    public void redo() {
        for (IDrawShape shape : ungroupLists) {
            shape.removeFromGroup();
        }
        System.out.println("ungrouping again");       
    }

    
}
