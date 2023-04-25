package main.Shapes;

import java.awt.Point;

import main.AbstractFactory;
import model.ShapeType;
import model.interfaces.IApplicationState;
import view.interfaces.PaintCanvasBase;

public class ShapeFactory {

    public static AbstractFactory createShape(Point startPoint, Point endPoint, IApplicationState appState, PaintCanvasBase paintCanvas, ShapeType shapeType) {
        
        AbstractFactory drawShape; 

    // why shapeType is important to maintain
        if (shapeType == ShapeType.RECTANGLE) {
            drawShape = new CreateRectangle(startPoint, endPoint, appState, paintCanvas); 
        }
        else if (shapeType == ShapeType.ELLIPSE) {
            drawShape = new CreateEllipse(startPoint, endPoint, appState, paintCanvas); 
        }
        else if (shapeType == ShapeType.TRIANGLE) {
            drawShape = new CreateTriangle(startPoint, endPoint, appState, paintCanvas); 
        }
        else
            drawShape = new NullAlert();
        
        
        return drawShape;
    }

}
