package com.hurryup.objects.tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.hurryup.game.TextureManager;

import static com.hurryup.game.hurryupGame.camera;

/**
 * Created by frasse on 2016-09-20.
 */
public class NormalGround extends Tile {

    private Sprite tileSprite;

    @Override
    public float getLeft() {
        return super.getLeft() - 5;
    }

    @Override
    public float getRight() {
        return super.getRight() - 5;
    }

    public NormalGround(Vector2 position) {
        super(position);
        textureRegion = TextureManager.get("normalGround");
        tileSprite = new Sprite(textureRegion);
        tileSprite.setPosition(position.x,position.y);
    }
    @Override
    public void draw(SpriteBatch batch) {
        tileSprite.draw(batch);
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
