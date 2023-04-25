package model.interfaces;

import java.awt.*;

import java.util.List;

import controller.GroupCommand;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;

public interface IDrawShape {
    
    void addToGroup(GroupCommand shapeGroup);

    List<GroupCommand> getGroup();

    void removeFromGroup();

    void drawShape();

    void drawCopyShape(ShapeColor primaryColor, ShapeColor secondaryColor, ShapeType shapeType, ShapeShadingType shapeShadingType);

    void undo();

    void redo();

    // for move, select
    void shapeOutline();

    int[] boundingBox();

    void moveShape(int distanceX, int distanceY);

    // setters   
    void setStartPoint(Point startPoint);
    void setEndPoint(Point endPoint);
    void setWidth(int width);
    void setHeight(int height);
    void setPrimaryColor(ShapeColor primaryColor);
    void setSecondaryColor(ShapeColor secondaryColor);
    void setShapeType(ShapeType shapeType);
    void setShapeShadingType(ShapeShadingType shapeShadingType);
    void setGraphics2D(Graphics2D graphics2d);

    // getters 
    Point getStartPoint();
    Point getEndPoint();
    int getWidth();
    int getHeight();
    ShapeColor getPrimaryColor();
    ShapeColor getSecondaryColor();
    ShapeType getShapeType();
    ShapeShadingType getShapeShadingType();
    Graphics2D getGraphics2D();


}
