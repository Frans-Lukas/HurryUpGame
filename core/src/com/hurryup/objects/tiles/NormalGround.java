package com.hurryup.objects.tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import static com.hurryup.game.hurryupGame.camera;

/**
 * Created by frasse on 2016-09-20.
 */
public class NormalGround extends Tile {

    Texture img = new Texture("badlogic.jpg");
    BitmapFont font = new BitmapFont();

    public NormalGround(Vector2 position) {
        super(position);
        font.setColor(Color.RED);
    }
    @Override
    public void draw(SpriteBatch batch) {
        //render entire map from tilemap.
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setProjectionMatrix(camera.combined);
        renderer.setColor(Color.BLACK);
        renderer.rect(position.x, position.y, 64, 64);
        renderer.end();
    }
    @Override
    public void update(long deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public Vector2 getPosition() {
        return super.getPosition();
    }
}
