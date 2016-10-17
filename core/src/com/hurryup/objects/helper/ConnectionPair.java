package com.hurryup.objects.helper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Klas on 2016-10-09.
 */
public class ConnectionPair {
    private Vector2 to;
    private Vector2 from;
    private Color cableColor;

    public Color getCableColor() {
        return cableColor;
    }

    private static int colorIndex = 0;
    private static Color colors[] =
            {Color.BLUE,Color.BROWN, Color.CHARTREUSE,Color.CORAL,
            Color.CYAN,Color.DARK_GRAY,Color.FIREBRICK,Color.FOREST,Color.GOLD,Color.GOLDENROD,
            Color.GRAY,Color.GREEN,Color.LIME,Color.MAGENTA, Color.MAROON,Color.NAVY,Color.OLIVE,
            Color.ORANGE,Color.PINK,Color.PURPLE,Color.RED,Color.ROYAL,Color.SALMON,Color.SCARLET,
            Color.SKY,Color.SLATE,Color.TEAL,Color.YELLOW,Color.VIOLET};

    public void setCableColor(Color cableColor) {
        this.cableColor = cableColor;
    }

    public ConnectionPair(Vector2 to, Vector2 from) {
        this.to = to;
        this.from = from;
        cableColor = new Color(colors[colorIndex].r,colors[colorIndex].g,colors[colorIndex].b,0.8f);
        colorIndex++;
        if(colorIndex > colors.length-1)
            colorIndex = 0;
    }

    public Vector2 getTo() {
        return to;
    }

    public void setTo(Vector2 to) {
        this.to = to;
    }

    public Vector2 getFrom() {
        return from;
    }

    public void setFrom(Vector2 from) {
        this.from = from;
    }

}
