package main;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseAdapter;

import org.w3c.dom.events.MouseEvent;

import main.Shapes.NullAlert;
import model.interfaces.IApplicationState;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;
import model.MouseMode;

public class ClickHandler extends MouseAdapter {

    private IApplicationState appState; 
    private PaintCanvasBase paintCanvas; 

    private Point startPoint;
    private Point endPoint;  
    
    public ClickHandler(PaintCanvasBase paintCanvas, ApplicationState appState) {
        this.appState = appState; 
        this.paintCanvas = paintCanvas; 
    }

    public void mouseClicked(MouseEvent e) {
        // System.out.println("At click X:" + e.getX() + ", " + "Y:" + e.getY());
    }

    public void mousePressed(java.awt.event.MouseEvent e) {
        /*
         * Stuff the return value of getPoint into the custom Point class you create
                 that has an int x and an int y.
         * 
         *  System.out.println("At Press X:" + e.getX() + ", " + "Y:" + e.getY());
         */
        startPoint = new Point(e.getX(), e.getY()); 
    }

    public void mouseReleased(java.awt.event.MouseEvent e) {
        endPoint = new Point(e.getX(), e.getY()); 

        MouseMode mouseMode = appState.getActiveMouseMode(); 
        switch(mouseMode) {
            case DRAW:
                DrawCommand draw = new DrawCommand(startPoint, endPoint, appState, paintCanvas);  
                draw.execute(); 
                break;
            case SELECT:
                SelectCommand select = new SelectCommand(startPoint, endPoint, appState, paintCanvas);
                select.execute();
                break;
            case MOVE:
                MoveCommand move = new MoveCommand(startPoint, endPoint, appState, paintCanvas); 
                move.execute(); 
                break;
            default:
                new NullAlert(); 
                break; 
        }
    }
}
