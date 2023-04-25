package main.Lists;

import java.util.ArrayList;
import java.util.List;

import model.interfaces.IDrawShape;

public class FormShapeList {
        
    private final List<IDrawShape> shapeList = new ArrayList<>(); 

    public List<IDrawShape> getList() {
        return shapeList; 
    }
    
    public void add(IDrawShape shape) {
        while (!shapeList.contains(shape)) {
            shapeList.add(shape); 
        }
    }
    
    public void delete(IDrawShape shape) {
        shapeList.remove(shape);
    }

    public void clear() {
        shapeList.clear();
    }


}
