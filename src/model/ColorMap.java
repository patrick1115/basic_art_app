package model;

import java.awt.*;
import java.awt.Color;
import java.util.EnumMap;

public class ColorMap {

    private static final EnumMap<ShapeColor, Color> colorMapping = new EnumMap<>(ShapeColor.class);

    public static Color getColor(ShapeColor color) {
          return colorMapping.get(color);
    }

    static {
        colorMapping.put(ShapeColor.GREEN, Color.GREEN);
        colorMapping.put(ShapeColor.RED, Color.RED);
        colorMapping.put(ShapeColor.PINK, Color.PINK);
        colorMapping.put(ShapeColor.GRAY, Color.GRAY);
        colorMapping.put(ShapeColor.DARK_GRAY, Color.DARK_GRAY);
        colorMapping.put(ShapeColor.CYAN, Color.CYAN);
        colorMapping.put(ShapeColor.MAGENTA, Color.MAGENTA);
        colorMapping.put(ShapeColor.ORANGE, Color.ORANGE);
        colorMapping.put(ShapeColor.YELLOW, Color.YELLOW);
        colorMapping.put(ShapeColor.WHITE, Color.WHITE);
        colorMapping.put(ShapeColor.BLACK, Color.BLACK);
        colorMapping.put(ShapeColor.LIGHT_GRAY, Color.LIGHT_GRAY);
        colorMapping.put(ShapeColor.BLUE, Color.BLUE);
    }

}
