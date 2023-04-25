package model;

import java.awt.*;

public enum ShapeColor {
    BLACK {
        public Color getColor(){
            return Color.BLACK;
        }
    },
    BLUE,
    CYAN,
    DARK_GRAY,
    GRAY,
    GREEN,
    LIGHT_GRAY,
    MAGENTA,
    ORANGE,
    PINK,
    RED,
    WHITE,
    YELLOW

    /* 
     *    public static EnumMap<ShapeColor, Color> colorMap = new EnumMap<>(ShapeColor.class);

    public static Color getColor(ShapeColor color) {
        colorMap.put(ShapeColor.GREEN, Color.GREEN);
        colorMap.put(ShapeColor.RED, Color.RED);
        colorMap.put(ShapeColor.PINK, Color.PINK);
        colorMap.put(ShapeColor.GRAY, Color.GRAY);
        colorMap.put(ShapeColor.DARK_GRAY, Color.DARK_GRAY);
        colorMap.put(ShapeColor.CYAN, Color.CYAN);
        colorMap.put(ShapeColor.MAGENTA, Color.MAGENTA);
        colorMap.put(ShapeColor.ORANGE, Color.ORANGE);
        colorMap.put(ShapeColor.YELLOW, Color.YELLOW);
        colorMap.put(ShapeColor.WHITE, Color.WHITE);
        colorMap.put(ShapeColor.BLACK, Color.BLACK);
        colorMap.put(ShapeColor.LIGHT_GRAY, Color.LIGHT_GRAY);
        colorMap.put(ShapeColor.BLUE, Color.BLUE);
        return colorMap.get(color);
    }
    */
}
