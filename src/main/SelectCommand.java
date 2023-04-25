package main;

import java.awt.Point;
import java.awt.Shape;
import java.security.cert.PolicyNode;
import java.util.List;

import controller.GroupCommand;

import java.awt.geom.Area;

import main.Lists.AllShapeLists;
import model.interfaces.IApplicationState;
import model.interfaces.ICommand;
import model.interfaces.IDrawShape;
import view.interfaces.PaintCanvasBase;

public class SelectCommand implements ICommand {

    private final IApplicationState appState; 
    private final PaintCanvasBase paintCanvas;
    private final Point startPoint; 
    private final Point endPoint; 
    private final int[] boundingBox = {0,0,0,0,0,0}; 
    private final int width;
    private final int height; 
    private Point finalPoint; 

    public SelectCommand(Point startPoint, Point endPoint, IApplicationState appState, PaintCanvasBase paintCanvas) {
        this.appState = appState;
        this.paintCanvas = paintCanvas; 
        this.startPoint = startPoint;
        this.endPoint = endPoint; 

        width = (int)endPoint.getX() - (int)startPoint.getX();
        height = (int)endPoint.getY() - (int)startPoint.getY(); 
        boundingBox[0] = startPoint.x;
        boundingBox[1] = startPoint.y;
        boundingBox[2] = endPoint.x;
        boundingBox[3] = endPoint.y;
        boundingBox[4] = width;
        boundingBox[5] = height; 
    }

    @Override
    public void execute() {
        // bounding box will be used to detect for collisions - have a separate list for selected shapes. 
        AllShapeLists.selectedShapeList.clear();
        AllShapeLists.shapesClipboard.clear(); 
       
        List<IDrawShape> shapeList = AllShapeLists.currentShapes.getList();
        finalPoint = new Point(Math.min((int)startPoint.getX(), (int)endPoint.getX()),
                         Math.min((int)startPoint.getY(), (int)endPoint.getY()));
        
        for (IDrawShape shape : shapeList) {
            if (checkIntersect(shape, boundingBox)) {
            //if (withinBounds(shape, shapeList)) {
                 /*
                  * next if /else statements will check and obtain all the shapes within the previous group. 
                  - noticed I forgot to include this from sprint 4
                  */
                boolean check = shape.getGroup().isEmpty();
                if(check == false) {
                    GroupCommand grouping = shape.getGroup().get(shape.getGroup().size()-1); 
                    for (IDrawShape shapeS : shapeList) {
                        boolean check2 = shapeS.getGroup().isEmpty(); 
                        if(check2 == false) {
                            GroupCommand grouping2 = shapeS.getGroup().get(shapeS.getGroup().size()-1);
                            if (grouping.equals(grouping2)) {
                                AllShapeLists.selectedShapeList.add(shapeS);
                                //System.out.println("Group within the group selected");
                            }
                        }
                    }
                }
                else
                    AllShapeLists.selectedShapeList.add(shape);
            }
        }
        System.out.println("In select Command"); 
    }

    /* 
    public static boolean withinBounds(IDrawShape shape, List<IDrawShape> shapeList) {
        if (shape.getStartPoint().getX() + shape.getWidth())
    }
    */

    public static boolean checkIntersect(IDrawShape shape, int[] shapeTwo) {
        int[] shapeOne = shape.boundingBox(); 
        if (shapeOne[0] < shapeTwo[0] + shapeTwo[4] && 
            shapeOne[0] + shapeOne[4] > shapeTwo[0] &&
            shapeOne[1] < shapeTwo[1] + shapeTwo[5] && 
            shapeOne[1] + shapeOne[5] > shapeTwo[1]) {
            System.out.println("Collision detected");
            return true; 
        }
        else if (shapeOne[0] > shapeTwo[0] + shapeTwo[4] && 
                shapeOne[0] + shapeOne[4] < shapeTwo[0] &&
                shapeOne[1] > shapeTwo[1] + shapeTwo[5] && 
                shapeOne[1] + shapeOne[5] < shapeTwo[1]) {
            System.out.println("Collision detected");
            return true; 
        }
        System.out.println("No Collision");
        return false;
        
    }

}
