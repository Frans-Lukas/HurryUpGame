package com.hurryup.objects.helper;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.hurryup.game.GameTexture;
import com.hurryup.game.TextureManager;

import java.util.ArrayList;

/**
 * Created by Klas on 2016-10-09.
 */
public class VisualConnection {

    private static ArrayList<Vis> visuals = new ArrayList<Vis>();
    private static ArrayList<Integer> usedY = new ArrayList<Integer>();
    private static int currentY = 0;
    public static void Create(ConnectionPair pair){
        Vis start = new Vis();
        start.position = pair.getFrom();

        //Connect towards the right
        if(pair.getFrom().x > pair.getTo().x && pair.getFrom().y >= pair.getTo().y){
            //Create down
            float ydiff = pair.getFrom().y - pair.getTo().y;
            start.rotation = -90;
            currentY = (int)ydiff + 50;
            start.rectangle.set(start.position.x,start.position.y,10,currentY);
            start.texture = TextureManager.get("cable");
            visuals.add(start);
        }

    }

    public static void Draw(SpriteBatch sb){
        for (Vis v: visuals) {
            sb.draw(v.texture.getTexture(),v.position.x,v.position.y,50,5,v.rectangle.width,v.rectangle.height,1,1,v.rotation,(int)v.texture.getRegion().x,(int)v.texture.getRegion().y,(int)v.texture.getRegion().height,(int)v.texture.getRegion().width,false,false);
        }
    }

    private static boolean isYUsed(int i){
        boolean exists = false;
        for (int y: usedY) {
            if(y == i){
                exists = true;
                break;
            }

        }
        return exists;
    }
}

class Vis{
    public Vector2 position;
    public GameTexture texture;
    public float rotation;
    public Rectangle rectangle;
}
