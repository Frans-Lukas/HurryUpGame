package com.hurryup.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.hurryup.objects.tiles.Tile;

import java.util.ArrayList;

/**
 * Created by frasse on 2016-09-20.
 */
public class MasterClass {

    protected static ShapeRenderer renderer = new ShapeRenderer();
    public Vector2 position = new Vector2(0,0);
    public static final float width = 64;
    public static final float height = 64;

    public void draw(SpriteBatch batch){

    }
    public void update(long deltaTime, ArrayList<Tile> tiles){

    }
    public float getLeft(){

        return position.x;
    }
    public float getRight(){

        return position.x + width;
    }
    public float getTop(){

        return position.y + height;
    }
    public float getBot(){

        return position.y + height;
    }
}
