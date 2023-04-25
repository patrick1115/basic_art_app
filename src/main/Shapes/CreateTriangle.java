package main.Shapes;

import java.awt.*;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;

import controller.GroupCommand;
import main.AbstractFactory;
import main.Lists.AllShapeLists;
import model.ColorMap;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IApplicationState;
import model.interfaces.IDrawShape;
import view.interfaces.PaintCanvasBase;

public class CreateTriangle extends AbstractFactory implements IDrawShape {

    private Graphics2D graphics2d;
    private ShapeColor primaryColor;
    private ShapeColor secondaryColor;
    private ShapeType shapeType;
    private ShapeShadingType shapeShadingType;
    private Stroke stroke = new BasicStroke(5);

    private Point startPoint;
    private Point endPoint;
    private int width;
    private int height;
    private final List<GroupCommand> groupList = new ArrayList<>();

    public CreateTriangle(Point startPoint, Point endPoint, IApplicationState appState, PaintCanvasBase paintCanvas) {
        setGraphics2D(paintCanvas.getGraphics2D());
        setPrimaryColor(appState.getActivePrimaryColor());
        setSecondaryColor(appState.getActiveSecondaryColor());
        setShapeType(appState.getActiveShapeType());
        setShapeShadingType(appState.getActiveShapeShadingType());

        setStartPoint(startPoint); 
        setEndPoint(endPoint); 
        setWidth(Math.abs( (int)endPoint.getX() - (int)startPoint.getX() ));
        setHeight(Math.abs( (int)endPoint.getY() - (int)startPoint.getY() )); 
    }

    @Override
    public void drawShape() {
        AllShapeLists.currentShapes.add(this);
        Graphics2D graphics2d = getGraphics2D();

        int x[] = {startPoint.x, startPoint.x, startPoint.x+width};
        int y[] = {startPoint.y, startPoint.y+height, startPoint.y+height}; 
        
        if (getShapeShadingType() == ShapeShadingType.FILLED_IN) {
            graphics2d.setColor(ColorMap.getColor(getPrimaryColor()));
            graphics2d.fillPolygon(x, y, 3);
        }
        else if (getShapeShadingType() == ShapeShadingType.OUTLINE) {
            graphics2d.setColor(ColorMap.getColor(getPrimaryColor()));
            graphics2d.setStroke(stroke);
            graphics2d.drawPolygon(x, y, 3);   
        }
        else if (getShapeShadingType() == ShapeShadingType.OUTLINE_AND_FILLED_IN) {
            graphics2d.setColor(ColorMap.getColor(getPrimaryColor()));
            graphics2d.fillPolygon(x, y, 3);
            graphics2d.setColor(ColorMap.getColor(getSecondaryColor()));
            graphics2d.setStroke(stroke);
            graphics2d.drawPolygon(x, y, 3);
        }
    }

    @Override
    public void undo() {
        AllShapeLists.currentShapes.delete(this);        
    }

    @Override
    public void redo() {
        drawShape();        
    }

    @Override
    public void shapeOutline() {
        // Will be for shape outline when selecting shape. 

        int x[] = {startPoint.x-7, startPoint.x-12, startPoint.x+width+12};
        int y[] = {startPoint.y-7, startPoint.y+height+5, startPoint.y+height+5}; 

        Graphics2D graphics2d = getGraphics2D();
        graphics2d.setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0));
        graphics2d.setColor(Color.BLACK);
        graphics2d.drawPolygon(x, y, 3);
    }

    @Override
    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;         
    }

    @Override
    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;         
    }

    @Override
    public void setWidth(int width) {
        this.width = width;         
    }

    @Override
    public void setHeight(int height) {
        this.height = height; 
    }

    @Override
    public void setPrimaryColor(ShapeColor primaryColor) {
        this.primaryColor = primaryColor;        
    }

    @Override
    public void setSecondaryColor(ShapeColor secondaryColor) {
        this.secondaryColor = secondaryColor;         
    }

    @Override
    public void setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType; 
    }

    @Override
    public void setShapeShadingType(ShapeShadingType shapeShadingType) {
        this.shapeShadingType = shapeShadingType;
    }

    @Override
    public void setGraphics2D(Graphics2D graphics2d) {
        this.graphics2d = graphics2d; 
    }

    @Override
    public Point getStartPoint() {        return startPoint;    }

    @Override
    public Point getEndPoint() {        return endPoint;    }

    @Override
    public int getWidth() { return width;   }

    @Override
    public int getHeight() {    return height;  }

    @Override
    public ShapeColor getPrimaryColor() {   return primaryColor;    }

    @Override
    public ShapeColor getSecondaryColor() { return secondaryColor;    }

    @Override
    public ShapeType getShapeType() {   return shapeType; }

    @Override
    public ShapeShadingType getShapeShadingType() { return shapeShadingType; }

    @Override
    public Graphics2D getGraphics2D() { return graphics2d; }

    @Override
    public int[] boundingBox() {
        int[] boundingBox = {startPoint.x, startPoint.y, endPoint.x, endPoint.y, width, height};
        return boundingBox;
    }

    @Override
    public void moveShape(int moveX, int moveY) {
        Point newStartPoint = new Point(startPoint.x + moveX, startPoint.y + moveY);
        Point newEndPoint = new Point(endPoint.x + moveX, endPoint.y + moveY);
        
        setStartPoint(newStartPoint);
        setEndPoint(newEndPoint);        
    }

    @Override
    public void drawCopyShape(ShapeColor primaryColor, ShapeColor secondaryColor, ShapeType shapeType, ShapeShadingType shapeShadingType) {
        setPrimaryColor(primaryColor);
        setSecondaryColor(secondaryColor);
        setShapeType(shapeType);
        setShapeShadingType(shapeShadingType);
        drawShape();
    }

    @Override
    public void addToGroup(GroupCommand shapeGroup) {
        groupList.add(shapeGroup);                
    }

    @Override
    public List<GroupCommand> getGroup() {
        return groupList;
    }

    @Override
    public void removeFromGroup() {
        groupList.remove(groupList.size()-1);
    }

}
