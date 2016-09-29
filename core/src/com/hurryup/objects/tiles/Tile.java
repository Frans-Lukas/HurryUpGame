package com.hurryup.objects.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.hurryup.objects.MasterClass;

/**
 * Created by frasse on 2016-09-20.
 */
public class Tile{
    protected Vector2 position;
    public float width = 64;
    public float height = 64;
    public static ShapeRenderer renderer = new ShapeRenderer();
    private boolean collidable = true;
    private boolean drawable = true;


    //get position for every tile
    public Tile(Vector2 position){
        this.position = position;
        //renderer.setAutoShapeType(true);
    }

    public void draw(SpriteBatch batch) {
    }

    public void update(long deltaTime) {
    }

    public Vector2 getPosition(){
        return position;
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
    public float getHeight(){
        return height;
    }

    public float getWidth(){
        return width;
    }

    public void setDrawable(boolean drawable) {
        this.drawable = drawable;
    }
    public boolean isDrawable(){
        return drawable;
    }

    public void setCollidable(boolean isCollidable){
        collidable = isCollidable;
    }
    public boolean isCollidable(){
        return collidable;
    }

}
