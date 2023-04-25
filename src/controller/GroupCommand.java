package controller;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import main.CommandHistory;
import main.Lists.AllShapeLists;
import model.interfaces.ICommand;
import model.interfaces.IDrawShape;
import model.interfaces.IUndoable;
import view.gui.PaintCanvas;
import view.interfaces.PaintCanvasBase;

public class GroupCommand implements ICommand, IUndoable{

    private final List<IDrawShape> groupShapeLists = new ArrayList<>(); 
    private final PaintCanvasBase paintCanvas;

    public GroupCommand(PaintCanvasBase paintCanvas) {
        this.paintCanvas = paintCanvas; 
    }
/*
 * moved formbounds to PaintCanvas for Ungrouping of the shapes. 
 */
    @Override
    public void execute() {
        
        AllShapeLists grouping = new AllShapeLists(); 

        grouping.groupShapeLists.clear();
        groupShapeLists.addAll(AllShapeLists.selectedShapeList.getList());

        List<IDrawShape> shapeList = AllShapeLists.currentShapes.getList(); 
        List<IDrawShape> selectedShapesList = AllShapeLists.selectedShapeList.getList().stream().distinct().collect(Collectors.toList());
        List<GroupCommand> groupBounds = new ArrayList<>(); 
        List<IDrawShape> groupList = new ArrayList<>();

        for (IDrawShape shape : groupShapeLists) {
            shape.addToGroup(this);
            /* 
            boolean checkGroup = shape.getGroup().isEmpty(); 
            boolean checkSelect = selectedShapesList.contains(shape);
            
            //will be for selecting a single shape
            //if(checkSelect==true) {
            if (checkSelect==true && checkGroup==false){
                GroupCommand shapeGroup = shape.getGroup().get(shape.getGroup().size()-1 );
                groupBounds.add(shapeGroup);
                groupBounds = groupBounds.stream().distinct().collect(Collectors.toList());
            }  
           

            for (GroupCommand groupChecking : groupBounds) {
                for (IDrawShape groupShape : shapeList) {
                    boolean check = groupShape.getGroup().isEmpty();
                    
                    if (check==false) {                    
                        GroupCommand shapeGroup = groupShape.getGroup().get(groupShape.getGroup().size()-1);
                        
                        boolean check2 = groupChecking.equals(shapeGroup);
                        if (check2==true) {
                            groupList.add(groupShape);
                        }
                        
                    }
                }
                // Need to pass graphics2d to prevent screen blinking
                formBounds(groupList);
            }
            */
            //paintCanvas.repaint();
        } 
        //paintCanvas.repaint();
        CommandHistory.add(this);
        //formBounds(graphics2d, groupLists);
        System.out.println("group has been formed");
    }

    @Override
    public void undo() {
        for (IDrawShape shape : groupShapeLists) {
            shape.removeFromGroup();
        }  
    }

    @Override
    public void redo() {
        for (IDrawShape shape : groupShapeLists) {
            AllShapeLists.selectedShapeList.add(shape);
            shape.addToGroup(this);
        }        
    }

    public void formBounds(List<IDrawShape> groupLists) {
        
        Graphics2D graphics2d = paintCanvas.getGraphics2D(); 
        
        int startX = 0;
        int endX = 0; 
        int startY = 0;
        int endY = 0;
        
        for (int i=0; i<groupLists.size(); i++) {
            if (i==0) {
                startX = groupLists.get(i).boundingBox()[0];
                startY = groupLists.get(i).boundingBox()[1];
                endX = groupLists.get(i).boundingBox()[2];
                endY = groupLists.get(i).boundingBox()[3];
            }
            else {
                int fStartX = groupLists.get(i).boundingBox()[0];
                int fStartY = groupLists.get(i).boundingBox()[1];
                int fEndX = groupLists.get(i).boundingBox()[2];
                int fEndY = groupLists.get(i).boundingBox()[3];

                if(fStartX < startX) {
                    startX = fStartX; 
                }
                if (fStartY < startY) {
                    startY = fStartY; 
                }
                if (fEndX > endX) {
                    endX = fEndX; 
                }
                if (fEndY > endY) {
                    endY= fEndY;
                }
            }
        }

        int width = Math.abs(startX - endX);
        int height = Math.abs(startY - endY);
        graphics2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0));
        graphics2d.setColor(Color.BLUE);
        graphics2d.drawRect(startX, startY, width, height);
        //paintCanvas.repaint();        
    }
}
