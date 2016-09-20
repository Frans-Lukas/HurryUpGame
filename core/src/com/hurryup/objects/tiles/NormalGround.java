package com.hurryup.objects.tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by frasse on 2016-09-20.
 */
public class NormalGround extends Tile {

    Texture img = new Texture("badlogic.jpg");

    public NormalGround(Vector2 position) {
        super(position);
    }

    @Override
    public void draw(SpriteBatch batch) {
        ShapeRenderer renderer = new ShapeRenderer();

        renderer.begin(ShapeRenderer.ShapeType.Filled);

        renderer.setColor(Color.WHITE);
        renderer.rect(position.x, position.y, 32, 32);
        renderer.end();
    }

    @Override
    public void update(long deltaTime) {
        super.update(deltaTime);
    }
}
