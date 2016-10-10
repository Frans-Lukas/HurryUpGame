package com.hurryup.objects.helper;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.hurryup.game.GameTexture;
import com.hurryup.game.TextureManager;
import com.hurryup.game.hurryupGame;

import java.util.ArrayList;

/**
 * Created by Klas on 2016-10-09.
 */
public class VisualConnection {

    private static ArrayList<Vis> visuals = new ArrayList<Vis>();
    private static ArrayList<Integer> usedY = new ArrayList<Integer>();
    private static int currentY = 0;
    public static void Create(ConnectionPair pair){
        if(pair == null)
            return;
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
            start.region = new TextureRegion(start.texture.getTexture(),(int)start.rectangle.x,(int)start.rectangle.y,(int)start.rectangle.width,(int)start.rectangle.height);
            visuals.add(start);
        }

    }

    public static void Draw(SpriteBatch sb){
        for (Vis v: visuals) {
            //sb.draw(v.texture.getTexture(),50,400,50,5,v.rectangle.width,v.rectangle.height,4,4,v.rotation,(int)v.texture.getRegion().x,(int)v.texture.getRegion().y,(int)v.texture.getRegion().height,(int)v.texture.getRegion().width,false,false);
            sb.draw(v.region,v.position.x,v.position.y,0,5,200,10,1,1,0,false);

        }
        //sb.draw(new Texture("Cable.png"),50,600);
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
    public TextureRegion region;
    public Vis(){
        position = new Vector2();
        rectangle = new Rectangle();
    }
}
