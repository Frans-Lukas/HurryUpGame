package com.hurryup.objects.tiles.logicoperatortiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.hurryup.game.network.GameClient;
import com.hurryup.objects.logic.LogicColor;
import com.hurryup.objects.tiles.LogicTile;

/**
 * Created by frasse on 2016-09-28.
 */
public class LOTor extends LogicTile{
    boolean firstActivate = false;
    boolean secondActivate = false;
    public LOTor(Vector2 position, LogicColor logicColor, int id, int state) {
        super(position, logicColor, id, state);
        setCollidable(false);
    }

    @Override
    public void activate(int whichToActivate) {
        super.activate(whichToActivate);
        if(whichToActivate == 0){
            firstActivate = true;
        } else if(whichToActivate == 1){
            secondActivate = true;
        }
        if(state != 1 && state != 2 && firstActivate || secondActivate){
            connection[0].activate(connectionValue);
            //gate is activated.
            state = 1;
            //draw that the gate is activated.
            nextState = 2;
            GameClient.sendMessage(serialize());
        }
    }

    @Override
    public void deactivate(int whichToDeactivate) {
        if(whichToDeactivate == 0){
            firstActivate = false;
        } else if(whichToDeactivate == 1){
            secondActivate = false;
        }
        if(state != 0 && !firstActivate && !secondActivate){
            connection[0].deactivate(connectionValue);
            nextState = 0;
        }
        super.deactivate(whichToDeactivate);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        renderer.setColor(Color.FOREST);
        renderer.rect(position.x, position.y, 64, 64);
    }

    @Override
    public void update(long deltaTime) {
        super.update(deltaTime);
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

    @Override
    public void setDrawable(boolean drawable) {
        super.setDrawable(drawable);
    }

    @Override
    public boolean isDrawable() {
        return super.isDrawable();
    }

    @Override
    public void setCollidable(boolean isCollidable) {
        super.setCollidable(isCollidable);
    }

    @Override
    public boolean isCollidable() {
        return super.isCollidable();
    }
}
