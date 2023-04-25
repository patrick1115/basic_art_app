package controller;

import java.util.List;

import main.Lists.AllShapeLists;
import model.interfaces.ICommand;
import model.interfaces.IDrawShape;

public class CopyCommand implements ICommand {

    @Override
    public void execute() {
        List<IDrawShape> copySelectedList = AllShapeLists.selectedShapeList.getList();

        if (!AllShapeLists.selectedShapeList.getList().isEmpty()) {
            for (IDrawShape shape : copySelectedList) {
                AllShapeLists.shapesClipboard.add(shape); 
            }
        }
        System.out.println("Copied Shape ");
    }

 

}
