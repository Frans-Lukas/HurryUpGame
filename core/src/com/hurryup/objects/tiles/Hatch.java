package com.hurryup.objects.tiles;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.hurryup.game.TextureManager;
import com.hurryup.objects.logic.LogicColor;

/**
 * Created by Klas on 2016-10-17.
 */

public class Hatch extends LogicTile {

    public Hatch(Vector2 position) {
        super(position, LogicColor.Blue,1,0);
        state = 0;

        textureRegion = TextureManager.get("hatch");
        tileSprite = new Sprite(textureRegion);
        tileSprite.setPosition(position.x,position.y);

    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        tileSprite.setSize(width,64);
        tileSprite.draw(batch);

    }

    @Override
    public void update(long deltaTime) {
        super.update(deltaTime);
        if(state == 1 && width > 0){
            width--;
        } else if(state == 0 && width < 64){
            width++;
        }
    }

    @Override
    public void deactivate(int whichToDeactivate) {
        //close door
        state = 0;
    }

    @Override
    public void activate(int whichToActivate){
        state = 1;
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
    public float getHeight() {
        return super.getHeight();
    }

    @Override
    public float getWidth() {
        return super.getWidth();
    }

}
