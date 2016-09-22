package com.hurryup.objects.tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.hurryup.objects.MessageHandler;

import static com.hurryup.game.hurryupGame.camera;

/**
 * Created by frasse on 2016-09-21.
 */
public class Door extends Tile {
    private boolean open = false;


    public Door(Vector2 position) {
        super(position);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setProjectionMatrix(camera.combined);
        renderer.setColor(Color.GREEN);
        renderer.rect(position.x, position.y, width, height);
        renderer.end();
    }

    @Override
    public void update(long deltaTime) {
        super.update(deltaTime);
        if(MessageHandler.doorOpen && height > 0){
            height--;
        }
    }

    @Override
    public Vector2 getPosition() {
        return super.getPosition();
    }

    @Override
    public float getLeft() {
        return super.getLeft();
    }

    @Override
    public float getRight() {
        return super.getRight();
    }

    @Override
    public float getTop() {
        return super.getTop();
    }

    @Override
    public float getBot() {
        return super.getBot();
    }

    @Override
    public int getHeight() {
        return super.getHeight();
    }

    @Override
    public int getWidth() {
        return super.getWidth();
    }

}
