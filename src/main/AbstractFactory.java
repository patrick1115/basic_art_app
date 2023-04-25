package main;

import java.awt.*;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;

public abstract class AbstractFactory {

    /*
         * Abstract Factory pattern lets you produce families of related objects 
         *      without specifying their concrete classes.
         * https://refactoring.guru/design-patterns/abstract-factory
         * 
         *   found that setting everything below, StartPoint, EndPoint, ApplicationState items, etc. to 
         *      be the best to maintain properties of different shapes if different ones are selected.          
   */ 
    public abstract void drawShape();

    public abstract void drawCopyShape(ShapeColor primaryColor, ShapeColor secondaryColor, ShapeType shapeType, ShapeShadingType shapeShadingType);

    public abstract void undo();

    public abstract void redo();

    // for move, select
    public abstract void shapeOutline();

    public abstract int[] boundingBox();

    public abstract void moveShape(int distanceX, int distanceY);

    // for setters 
    public abstract void setStartPoint (Point startPoint);
    public abstract void setEndPoint (Point endPoint);

    public abstract void setWidth(int width);
    public abstract void setHeight(int height);    
    public abstract void setPrimaryColor(ShapeColor primaryColor);
    public abstract void setSecondaryColor(ShapeColor secondaryColor);
    public abstract void setShapeType(ShapeType shapeType);  
    public abstract void setShapeShadingType(ShapeShadingType shapeShadingType);  
    public abstract void setGraphics2D(Graphics2D graphics2d);

    // for getters 
    public abstract Point getStartPoint();   
    public abstract Point getEndPoint();
    public abstract int getWidth();
    public abstract int getHeight();
    public abstract ShapeColor getPrimaryColor();
    public abstract ShapeColor getSecondaryColor();
    public abstract ShapeType getShapeType(); 
    public abstract ShapeShadingType getShapeShadingType();
    public abstract Graphics2D getGraphics2D();
    

}
