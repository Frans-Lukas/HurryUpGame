package com.hurryup.objects.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.hurryup.objects.MasterClass;

/**
 * Created by frasse on 2016-09-20.
 */
public class Tile extends MasterClass{
    public static final int height = 32;
    public static final int width = 32;
    protected Vector2 position;

    public Tile(Vector2 position){
        this.position = position;
    }

    @Override
    public void Draw(SpriteBatch batch) {
    }

    @Override
    public void Update(long deltaTime) {
    }
}
