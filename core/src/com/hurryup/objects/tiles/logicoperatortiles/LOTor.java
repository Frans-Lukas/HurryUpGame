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
        if(whichToActivate == 0){
            firstActivate = true;
        } else if(whichToActivate == 1){
            secondActivate = true;
        }
        if(firstActivate || secondActivate){
            connection[0].activate(connectionValue);
        }
    }

    @Override
    public void deactivate(int whichToDeactivate) {
        if(whichToDeactivate == 0){
            firstActivate = false;
        } else if(whichToDeactivate == 1){
            secondActivate = false;
        }

        if(!firstActivate && !secondActivate){
            connection[0].deactivate(connectionValue);
        }
    }

    @Override
    public void update(long deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        renderer.setColor(Color.FOREST);
        if(firstActivate || secondActivate) {
            renderer.setColor(lotColorOn);
        } else{
            renderer.setColor(lotColorOff);
        }
        renderer.rect(position.x, position.y, 64, 64);

        if(connection[0] != null) {
            Vector2 tmpVector = new Vector2(vector.x + width / 2, vector.y + height / 2);
            renderer.setColor(Color.DARK_GRAY);
            renderer.line(tmpVector, connection[0].getVector2());
        }
    }
}
