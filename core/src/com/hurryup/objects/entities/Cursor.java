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

    public void Cursor(){

    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.FOREST);
        renderer.rect(x, y, 10, 10);
        renderer.end();
    }


    public void update(long deltaTime) {
        x = Gdx.input.getX() - 10;
        y = hurryupGame.HEIGHT - Gdx.input.getY();
    }
}
