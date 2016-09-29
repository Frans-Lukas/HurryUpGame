package com.hurryup.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.hurryup.game.hurryupGame;
import com.hurryup.objects.MasterClass;
import com.hurryup.objects.entities.Player;
import com.hurryup.objects.tiles.Tile;

import java.util.ArrayList;

import static com.hurryup.game.hurryupGame.HEIGHT;
import static com.hurryup.game.hurryupGame.WIDTH;
import static com.hurryup.objects.tiles.Tile.renderer;

/**
 * Created by frasse on 2016-09-20.
 */
public class TestLevel extends MasterLevel{
    //arraylist for all entites in level.

    BitmapFont font = new BitmapFont();


    public TestLevel() {
        super("map1.txt");
        font.setColor(Color.RED);
    }

    //Draw Level
    @Override
    public void draw(SpriteBatch batch, long deltaTime) {
        for(Tile tile : tiles){
            tile.draw(batch);
        }
        for(MasterClass entity : entities){
            entity.draw(batch);
        }
    }

    @Override
    public void dispose() {
    }

    @Override
    public void update(long deltaTime) {
        for(Tile tile: tiles){
            tile.update(deltaTime);
        }
        for(MasterClass entity : entities){
            entity.update(deltaTime, tiles);
        }

    }
}
