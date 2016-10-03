package com.hurryup.objects.tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.hurryup.game.network.GameClient;
import com.hurryup.objects.logic.LogicColor;

/**
 * Created by Klas on 2016-10-03.
 */
public class Lever extends LogicTile {

    Color buttonColor = Color.RED;
    private final int baseHeight = 20;
    private Vector2 leverBasePosition;
    private boolean active = false;
    private float leverMod = 0; //0-PI* 0,75

    public Lever(Vector2 position) {
        super(position, LogicColor.Blue,2,0);
        leverBasePosition = new Vector2(position.x + 30,position.y);
        height = 32;
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);

        renderer.setColor(Color.BROWN);
        renderer.line(leverBasePosition,getLeverPos(leverMod).add(leverBasePosition));
        renderer.setColor(buttonColor);
        renderer.rect(position.x + 10, position.y, width-20, baseHeight);

        if(connection[0] != null) {
            Vector2 tmpVector = new Vector2(vector.x + width / 2, vector.y + height / 2);
            renderer.setColor(Color.DARK_GRAY);
            renderer.line(tmpVector, connection[0].getVector2());
        }
    }
    //*1.5 - x
    private Vector2 getLeverPos(float x){
        return new Vector2(20 * (float)Math.cos((Math.PI*0.75) + x),20 * (float)((Math.PI*0.75) + x));
    }

    @Override
    public void update(long deltaTime) {
        super.update(deltaTime);
        //Active
        if(state == 3 && leverMod < Math.PI/2){
            leverMod += 0.1;
        }
        //Inactive
        else if(state == 0 && leverMod > 0.1){
            leverMod -= 0.1;
        }
    }

    @Override
    public void activate(int whichToActivate) {

        if(state == 0) {

            state = 1;
            nextState = 2;

            GameClient.sendMessage(serialize(true));
        }
        else if(state == 2){
            connection[0].activate(connectionValue);
            state = 3;
        }
    }

    @Override
    public void deactivate(int whichToDeactivate) {

        if(state == 3){
            state = 1;
            nextState = 2;

            GameClient.sendMessage(serialize(false));
        }
        else if(state == 2){
            connection[0].deactivate(connectionValue);
            state = 0;
        }

    }

    public void setColor(Color color){
        buttonColor = color;
    }

}
