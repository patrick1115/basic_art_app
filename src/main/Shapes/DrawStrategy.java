package main.Shapes;

import main.CommandHistory;
import model.interfaces.ICommand;
import model.interfaces.IDrawShape;
import model.interfaces.IUndoable;

public class DrawStrategy implements ICommand, IUndoable {

    private final IDrawShape shape; 

    public DrawStrategy(IDrawShape shape) {
        this.shape = shape; 
    }

    public void execute() {
        System.out.println("In drawStrategy, shape drawn");
        CommandHistory.add(this);
        shape.drawShape();
    }

	@Override
	public void undo() {
		System.out.println("undo in drawStrategy called");
        shape.undo();
	}

	@Override
	public void redo() {
		System.out.println("redo in drawStrategy called");
        shape.redo();
	}

}
