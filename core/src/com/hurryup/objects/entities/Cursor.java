package com.hurryup.objects.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.hurryup.game.hurryupGame;
import com.hurryup.objects.MasterClass;
import com.hurryup.objects.tiles.Tile;

import java.util.ArrayList;

/**
 * Created by frasse on 2016-10-04.
 */
public class Cursor extends MasterClass {
    private int x = 0;
    private int y = 0;

    private int width = 10;
    private int height = 10;

    public void Cursor(){

    }

    public void draw(SpriteBatch batch, ShapeRenderer renderer) {
        renderer.setColor(Color.FOREST);
        renderer.rect(x, y, width, height);
    }


    public void update(long deltaTime) {
        x = Gdx.input.getX() - 10;
        y = hurryupGame.HEIGHT - Gdx.input.getY();
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }


}
