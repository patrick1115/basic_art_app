package controller;

import model.interfaces.ICommand;
import model.interfaces.IDrawShape;

public class DrawCommandProxy implements ICommand {

    private final IDrawShape newShape;
    private final IDrawShape oldShape; 

    public DrawCommandProxy(IDrawShape oldShape, IDrawShape newShape) {
        this.oldShape = oldShape; 
        this.newShape = newShape; 
    }

    @Override
    public void execute() {
        //System.out.println("pasted shape from ");

        newShape.drawCopyShape(oldShape.getPrimaryColor(), oldShape.getSecondaryColor(), oldShape.getShapeType(), oldShape.getShapeShadingType());
        
    }

}
