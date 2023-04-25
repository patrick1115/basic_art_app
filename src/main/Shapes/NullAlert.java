package main.Shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.util.List;

import controller.GroupCommand;
import main.AbstractFactory;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IDrawShape;

public class NullAlert extends AbstractFactory implements IDrawShape {

	@Override
	public void drawShape() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawCopyShape(ShapeColor primaryColor, ShapeColor secondaryColor, ShapeType shapeType,
			ShapeShadingType shapeShadingType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shapeOutline() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int[] boundingBox() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void moveShape(int moveX, int moveY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStartPoint(Point startPoint) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEndPoint(Point endPoint) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setWidth(int width) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHeight(int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPrimaryColor(ShapeColor primaryColor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSecondaryColor(ShapeColor secondaryColor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setShapeType(ShapeType shapeType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setShapeShadingType(ShapeShadingType shapeShadingType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGraphics2D(Graphics2D graphics2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Point getStartPoint() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point getEndPoint() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ShapeColor getPrimaryColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShapeColor getSecondaryColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShapeType getShapeType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShapeShadingType getShapeShadingType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Graphics2D getGraphics2D() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addToGroup(GroupCommand shapeGroup) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<GroupCommand> getGroup() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeFromGroup() {
		// TODO Auto-generated method stub
		
	}

}
