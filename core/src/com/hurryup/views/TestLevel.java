package com.hurryup.views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hurryup.objects.MasterClass;
import com.hurryup.objects.entities.Player;
import com.hurryup.objects.tiles.Tile;

import java.util.ArrayList;

/**
 * Created by frasse on 2016-09-20.
 */
public class TestLevel extends MasterLevel{
    //arraylist for all entites in level.
    private ArrayList<MasterClass> entities = new ArrayList<MasterClass>();

    public TestLevel() {

        super();
        //add player to entity array.
        entities.add(new Player());
    }

    //Draw Level
    @Override
    public void draw(SpriteBatch batch) {
        for(MasterClass entity : entities){
            entity.draw(batch);
        }
        for(Tile tile : tiles){
            tile.draw(batch);
        }

    }

    @Override
    public void update(long deltaTime) {
        for(MasterClass entity : entities){
            entity.update(deltaTime);
        }
        for(Tile tile: tiles){
            tile.update(deltaTime);
        }

    }
}
