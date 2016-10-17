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
    private static Rectangle noGoException1,noGoException2;
    private static final int connectionMod = 16;
    private static final int connectionModReverse = 32;
    public static void addPair(ConnectionPair pair){
        if (pair != null) {
            Vector2 from = pair.getFrom();
            Vector2 to = pair.getTo();

            connectionPairs.add(pair);
        }
    }

    public static void addNoGo(Vector2 v1){
        noGoZones.add(new Rectangle(v1.x,v1.y,64,64));
    }

    public static void build(){

       for (ConnectionPair pair: connectionPairs) {
            //ConnectionPair pair = connectionPairs.get(1);
            Vector2 from = pair.getFrom();
            Vector2 to = pair.getTo();
            tempConnectionPoints.clear();

            completed = false;

            if(from.x < to.x) {
                //Add first point
                tempConnectionPoints.add(new Vector2(from.x,from.y));
                connect(new Vector2(from.x + 64,from.y), to, false);
                tempConnectionPoints.add(new Vector2(to.x,to.y));
            }
            else {
                //connect(to, from, false);
                tempConnectionPoints.add(new Vector2(to.x,to.y));
                connect(new Vector2(to.x + 64,to.y), from, false);
                tempConnectionPoints.add(new Vector2(from.x,from.y));
            }
            for (Vector2 v:
                tempConnectionPoints) {
                System.out.println(v);
            }
            for (int i = 0; i < tempConnectionPoints.size()-1; i++) {

                //if x
                Vector2 p1 = tempConnectionPoints.get(i);
                Vector2 p2 = tempConnectionPoints.get(i+1);
                Sprite s;
                Sprite b = new Sprite(TextureManager.get("cableBox"));
                Sprite b2 = new Sprite(TextureManager.get("cableBox"));
                b.setSize(10,10);
                b2.setSize(10,10);
                //if x
                if(p1.y == p2.y){
                    s = new Sprite(TextureManager.get("cableHorizontal"));
                    s.setPosition(p1.x,p1.y);
                    s.setSize(p2.x - p1.x,10);
                    b.setPosition(p2.x,p2.y);
                    b2.setPosition(p1.x,p1.y);
                }
                else{
                    s = new Sprite(TextureManager.get("cableVertical"));
                    if(p1.y > p2.y){
                        s.setPosition(p2.x,p2.y);
                        s.setSize(10,p1.y-p2.y);
                    }
                    else{
                        s.setPosition(p1.x,p1.y);
                        s.setSize(10,p2.y - p1.y);
                    }
                }
                sprites.add(s);
                sprites.add(b);
                sprites.add(b2);

            }
        }

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
    private static int called = 0;
    private static boolean completed = false;
    private static void connect(Vector2 from, Vector2 to,boolean y){
        called++;
        if(completed || called > 300 || (new Rectangle(to.x-32,to.y-32,96,96).contains(from))){
            if(called > 300)
                System.out.println("ERROR IN VisualConnection");

            return;
        }


        if (y) {
            Vector2 tmp = new Vector2(from.x,from.y);
            if(from.y > to.y){
                while(tmp.y > to.y){
                    tmp.add(0,-connectionMod);
                    if (new Rectangle(to.x, to.y-32, 64, 64+64).contains(tmp)) {
                        completed = true;
                        return;
                    }
                    else if(Math.abs(to.y - tmp.y) <= connectionMod){
                        tmp.y = to.y;
                    }
                    if(!checkValidity(tmp)){
                        tmp.add(0,connectionModReverse);
                        tempConnectionPoints.add(new Vector2(tmp.x,tmp.y));
                        connect(tmp,to,false);
                        return;
                    }
                }
                tempConnectionPoints.add(new Vector2(tmp.x,tmp.y));
                connect(tmp,to,false);
            }
            else{
                while(tmp.y < to.y){
                    tmp.add(0,connectionMod);
                    if (new Rectangle(to.x, to.y-32, 64, 64+64).contains(tmp)) {
                        completed = true;
                        return;
                    }
                    else if(Math.abs(to.y - tmp.y) <= connectionMod){
                        tmp.y = to.y;
                    }
                    if(!checkValidity(tmp)){
                        tmp.add(0,connectionModReverse);
                        tempConnectionPoints.add(new Vector2(tmp.x,tmp.y));
                        connect(tmp,to,false);
                        return;
                    }
                }
                tempConnectionPoints.add(new Vector2(tmp.x,tmp.y));
                connect(tmp,to,false);
            }

        } else {
            Vector2 tmp = new Vector2(from.x,from.y);
            while (tmp.x < to.x) {
                tmp.add(connectionMod,0);
                if (new Rectangle(to.x-32, to.y, 64+64, 64).contains(tmp)) {
                    completed = true;
                    return;
                }
                else if(Math.abs(to.x - tmp.x) <= connectionMod){
                    tmp.x = to.x;
                }
                if(!checkValidity(tmp)){
                    tmp.add(-connectionModReverse,0);
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
