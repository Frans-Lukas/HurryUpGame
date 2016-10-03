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

    //Dark Brown color
    private Color lotColorOff = Color.valueOf("006600FF");
    //light brown color
    private Color lotColorOn = Color.valueOf("007f00FF");

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
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        renderer.setColor(Color.FOREST);
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
}
