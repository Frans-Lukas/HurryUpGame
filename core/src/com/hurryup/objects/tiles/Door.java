package com.hurryup.objects.tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.hurryup.game.network.GameClient;
import com.hurryup.objects.logic.LogicColor;

import static com.hurryup.game.hurryupGame.camera;

/**
 * Created by frasse on 2016-09-21.
 */
public class Door extends LogicTile {

    public Door(Vector2 position) {
        super(position, LogicColor.Blue,1,0);
        state = 0;
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);

        renderer.setColor(Color.GREEN);
        renderer.rect(position.x, position.y, width, height);
    }

    @Override
    public void update(long deltaTime) {
        super.update(deltaTime);
        if(state == 2 && height > 0){
            height--;
        } else if(state == 0 && height < 64){
            height++;
        }
    }

    @Override
    public void deactivate(int whichToDeactivate) {
        //close door
        nextState = 0;
        GameClient.sendMessage(serialize());
    }

    @Override
    public void activate(int whichToActivate){
        if(state != 1 && state != 2){
            //door activated
            state = 1;
            //door opens for both players.
            nextState = 2;
            GameClient.sendMessage(serialize());
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
    public float getHeight() {
        return super.getHeight();
    }

    @Override
    public float getWidth() {
        return super.getWidth();
    }

}
