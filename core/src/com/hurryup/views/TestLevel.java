package com.hurryup.views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hurryup.objects.tiles.Tile;

/**
 * Created by frasse on 2016-09-20.
 */
public class TestLevel extends MasterLevel{
    public TestLevel() {
        super();
    }

    @Override
    public void draw(SpriteBatch batch) {
        for(Tile tile : tiles){
            tile.draw(batch);
        }
    }

    @Override
    public void update(long deltaTime) {
        for(Tile tile: tiles){
            tile.update(deltaTime);
        }
    }
}
