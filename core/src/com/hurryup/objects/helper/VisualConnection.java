package com.hurryup.objects.helper;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.hurryup.game.TextureManager;

import java.util.ArrayList;

/**
 * Created by Klas on 2016-10-12.
 */
public class VisualConnection {
    private static ArrayList<ConnectionPair> connectionPairs = new ArrayList<ConnectionPair>();
    private static ArrayList<Sprite> sprites = new ArrayList<Sprite>();
    private static final int cableThickness = 10;
    private static ArrayList<Rectangle> noGoZones = new ArrayList<Rectangle>();
    private static ArrayList<Vector2> tempConnectionPoints = new ArrayList<Vector2>();
    private static float tmpx,tmpy;
    public static void addPair(ConnectionPair pair){
        if (pair != null) {
            Vector2 from = pair.getFrom();
            Vector2 to = pair.getTo();
            noGoZones.add(new Rectangle(from.x,from.y,64,64));
            noGoZones.add(new Rectangle(to.x,to.y,64,64));
            connectionPairs.add(pair);
        }
    }

    public static void build(){

        noGoZones.add(new Rectangle(tmpx,tmpy,64,64));

        //for (ConnectionPair pair: connectionPairs) {
            ConnectionPair pair = connectionPairs.get(0);
            Vector2 from = pair.getFrom();
            Vector2 to = pair.getTo();
            tempConnectionPoints.clear();
            if(from.x < to.x) {
                //Add first point
                tempConnectionPoints.add(new Vector2(from.x,from.y));
                connect(new Vector2(from.x + 64,from.y), to, false);
                tempConnectionPoints.add(new Vector2(to.x,to.y));
            }
            else {
                connect(to, from, false);
            }
            for (Vector2 v:
                 tempConnectionPoints) {
                System.out.println(v);
            }
            for (Vector2 v:
                    tempConnectionPoints) {
                    Sprite s = new Sprite(TextureManager.get("error"));
                    s.setPosition(v.x,v.y);
                    s.setSize(10,10);
                    sprites.add(s);
            }
       // }

    }
/*
Sprite xSprite = new Sprite(TextureManager.get("cableVertical"));
            if (to.x > from.x) {
                xSprite.setPosition(from.x, from.y);
                xSprite.setSize(to.x - from.x, cableThickness);
            } else {
                xSprite.setPosition(to.x,from.y);
                xSprite.setSize(from.x - to.x,cableThickness);
            }
            sprites.add(xSprite);
 */
    //Connects two points, works around "no go zones", from left to right, down to up (lower values to greater)
    private static void connect(Vector2 from, Vector2 to,boolean y){
        if((new Rectangle(to.x,to.y,64,64).contains(from))){
            return;
        }


        if (y) {
            Vector2 tmp = new Vector2(from.x,from.y);
            if(from.y > to.y){
                while(tmp.y > to.y){
                    tmp.add(0,-16);
                    if(!checkValidity(tmp)){
                        tmp.add(0,32);
                        tempConnectionPoints.add(new Vector2(tmp.x,tmp.y));
                        connect(tmp,to,false);
                        return;
                    }
                }
                //tempConnectionPoints.add(new Vector2(tmp.x,tmp.y));
                connect(tmp,to,false);
            }

        } else {
            Vector2 tmp = new Vector2(from.x,from.y);
            while (tmp.x < to.x) {
                tmp.add(16,0);
                if(!checkValidity(tmp)){
                    tmp.add(-32,0);
                    tempConnectionPoints.add(new Vector2(tmp.x,tmp.y));
                    connect(tmp,to,true);
                    return;
                }
            }
            tempConnectionPoints.add(new Vector2(tmp.x,tmp.y));
            connect(tmp,to,true);
        }
    }

    private static boolean checkValidity(Vector2 v){
        for (Rectangle r:noGoZones){
            if(r.contains(v)) {
                return false;
            }
        }
        return true;
    }

    public static void draw(SpriteBatch batch) {
        for (Sprite sprite: sprites) {
            sprite.draw(batch);
        }
    }
}
