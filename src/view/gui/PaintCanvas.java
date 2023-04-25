package view.gui;

import view.interfaces.PaintCanvasBase;

import javax.swing.JComponent;

import controller.GroupCommand;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import main.Lists.AllShapeLists;
import main.Lists.FormShapeList;
import model.interfaces.IDrawShape;

public class PaintCanvas extends PaintCanvasBase {
    
    //List<IDrawShape> groupList = new ArrayList<>();
    int startX = 0;
    int endX = 0; 
    int startY = 0;
    int endY = 0;
    
    public Graphics2D getGraphics2D() {
        return (Graphics2D)getGraphics();
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D graphics2d = (Graphics2D) g;
        List<IDrawShape> selectedShapesList = AllShapeLists.selectedShapeList.getList().stream().distinct().collect(Collectors.toList());
        List<IDrawShape> shapeList = AllShapeLists.currentShapes.getList(); 
        List<GroupCommand> groupBounds = new ArrayList<>(); 
        List<IDrawShape> groupList = new ArrayList<>(); 
        IDrawShape _shape; 
        //
        for (IDrawShape shape : shapeList) {
            shape.setGraphics2D(graphics2d);
            shape.drawShape();
            
            //_shape = (IDrawShape) shape.getGroup();
            boolean checkGroup = shape.getGroup().isEmpty(); 
            boolean checkSelect = selectedShapesList.contains(shape);
            
            //will be for selecting a single shape
            //if(checkSelect==true) {
            if (checkSelect==true && checkGroup==true) {
                shape.shapeOutline();
            }  
            else if (checkSelect==true && checkGroup==false){
                GroupCommand shapeGroup = shape.getGroup().get(shape.getGroup().size()-1 );
                groupBounds.add(shapeGroup);
                
            }  
            groupBounds = groupBounds.stream().distinct().collect(Collectors.toList());
            ///*
            for (GroupCommand grouping : groupBounds) {
                for (IDrawShape groupShape : shapeList) {
                    boolean check = groupShape.getGroup().isEmpty();
                    
                    if (check==false) {                    
                        GroupCommand shapeGroup = groupShape.getGroup().get(groupShape.getGroup().size()-1);
                        
                        boolean check2 = grouping.equals(shapeGroup);
                        if (check2==true) {
                            groupList.add(groupShape);
                        }
                        
                    }
                }
                // Need to pass graphics2d to prevent screen blinking
                formBounds(groupList, graphics2d);
            }
            //*/
            
        } 
    //System.out.println("comepleting undo/redo");
        this.repaint();
    }

    private void formBounds(List<IDrawShape> groupList, Graphics2D graphics2d) {
        
        for (int i=0; i<groupList.size(); i++) {
            if (i==0) {
              // initially, start setting these as the bounds (i.e. when i==0)
                startX = groupList.get(i).boundingBox()[0];
                startY = groupList.get(i).boundingBox()[1];
                endX = groupList.get(i).boundingBox()[2];
                endY = groupList.get(i).boundingBox()[3];
            }
            else {
              // will move relative to position/size of the List
                int fStartX = groupList.get(i).boundingBox()[0];
                int fStartY = groupList.get(i).boundingBox()[1];
                int fEndX = groupList.get(i).boundingBox()[2];
                int fEndY = groupList.get(i).boundingBox()[3];

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
        //Graphics2D graphics2d = getGraphics2D();
        graphics2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0));
        graphics2d.setColor(Color.BLUE);
        graphics2d.drawRect(startX, startY, width, height);        
    }

   

}
