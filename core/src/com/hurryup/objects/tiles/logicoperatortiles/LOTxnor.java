package com.hurryup.objects.tiles.logicoperatortiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.hurryup.game.network.GameClient;
import com.hurryup.objects.logic.LogicColor;
import com.hurryup.objects.tiles.LogicTile;

/**
 * Created by Klas on 2016-09-29.
 */
public class LOTxnor extends LogicTile {

    private boolean firstActivate = false;
    private boolean secondActivate = false;

    //Dark Brown color
    private Color lotColorOff = Color.valueOf("000099FF");
    //light brown color
    private Color lotColorOn = Color.valueOf("0000b2FF");

    public LOTxnor(Vector2 position, LogicColor logicColor, int id, int state) {
        super(position, logicColor, id, state);
        //logic operators are not collidable
        setCollidable(false);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        if(state != 5) {
            renderer.setColor(lotColorOff);
        } else{
            renderer.setColor(lotColorOn);
        }
        renderer.rect(position.x, position.y, 64, 64);
        if(connection[0] != null) {
            Vector2 tmpVector = new Vector2(vector.x + width / 2, vector.y + height / 2);
            renderer.setColor(Color.DARK_GRAY);
            renderer.line(tmpVector, connection[0].getVector2());
        }
    }

    @Override
    public void update(long deltaTime) {
        super.update(deltaTime);
        if(state == 4){
            connection[0].activate(connectionValue);
            state = 5;
        }
        else if(state == 6){
            connection[0].deactivate(connectionValue);
            state = 5;
        }
    }

    @Override
    public void activate(int whichToActivate) {

        if(whichToActivate == 0){
            firstActivate = true;
        } else if(whichToActivate == 1){
            secondActivate = true;
        }

        if(state != 3 && state != 4){
            if((firstActivate && secondActivate)) {
                connection[0].activate(connectionValue);
                //gate is activated.
                state = 3;
                //draw that the gate is activated.
                nextState = 4;
            }
            else if((!firstActivate && !secondActivate)){
                connection[0].activate(connectionValue);
                state = 3;
                nextState = 4;
            }
            else{
                connection[0].deactivate(connectionValue);
                state = 3;
                nextState = 0;
            }
        }
    }

    @Override
    public void deactivate(int whichToDeactivate) {
        super.deactivate(whichToDeactivate);

        if(whichToDeactivate == 0){
            firstActivate = false;
        } else if(whichToDeactivate == 1){
            secondActivate = false;
        }

        if(state != 3 && state != 4){

            if((firstActivate && secondActivate)) {
                connection[0].activate(connectionValue);
                //gate is activated.
                state = 3;
                //draw that the gate is activated.
                nextState = 4;
            }
            else if((!firstActivate && !secondActivate)){
                connection[0].activate(connectionValue);
                state = 3;
                nextState = 4;
            }
            else{
                connection[0].deactivate(connectionValue);
                state = 3;
                nextState = 0;
            }
        }
    }
}
