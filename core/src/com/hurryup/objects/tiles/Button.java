package com.hurryup.objects.tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.hurryup.game.network.GameClient;
import com.hurryup.objects.logic.LogicColor;

import static com.hurryup.game.hurryupGame.camera;

/**
 * Created by frasse on 2016-09-20.
 */
public class Button extends LogicTile {

    Color buttonColor = Color.RED;


    public Button(Vector2 position) {
        super(position, LogicColor.Blue,2,0);
        height = 32;
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        renderer.setColor(buttonColor);
        renderer.rect(position.x, position.y, width, height);
        if(connection[0] != null) {
            Vector2 tmpVector = new Vector2(vector.x + width / 2, vector.y + height / 2);
            renderer.setColor(Color.DARK_GRAY);
            renderer.line(tmpVector, connection[0].getVector2());
        }
    }

    @Override
    public void update(long deltaTime) {
        super.update(deltaTime);
        if((state == 3 || state == 2) && height > 16){
            height -= 0.5;
        } else if(state == 0 && height < 32){
            height += 0.5;
        }
    }

    @Override
    public void activate(int whichToActivate) {
        if(state != 1 && state != 2 && state != 3) {
            state = 1;
            nextState = 2;

            GameClient.sendMessage(serialize());
        }
        else if(state == 2){
            connection[0].activate(connectionValue);
            state = 3;
        }
    }

    @Override
    public void deactivate(int whichToDeactivate) {
        if(state != 2 && state != 1){
            state = 1;
            nextState = 4;

            GameClient.sendMessage(serialize());
        }
        else if(state == 4){
            connection[0].deactivate(connectionValue);
        }

    }

    public void setColor(Color color){
        buttonColor = color;
    }
}
