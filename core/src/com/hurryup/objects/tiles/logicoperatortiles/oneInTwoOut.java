package com.hurryup.objects.tiles.logicoperatortiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.hurryup.objects.logic.LogicColor;
import com.hurryup.objects.tiles.LogicTile;

/**
 * Created by frasse on 2016-10-10.
 */
public class oneInTwoOut extends LogicTile{

    private boolean activate = false;
    //dark purple
    private Color lotColorOff = Color.valueOf("ff66ffFF");

    //light purple
    private Color lotColorOn = Color.valueOf("ff99ffFF");

    public oneInTwoOut(Vector2 position, LogicColor logicColor, int id, int state) {
        super(position, logicColor, id, state);
        if(activate) {
            renderer.setColor(lotColorOn);
        } else{
            renderer.setColor(lotColorOff);
        }
        renderer.rect(position.x, position.y, 64, 64);
        if(connection[0] != null) {
            Vector2 tmpVector = new Vector2(vector.x + width / 2, vector.y + height / 2);
            renderer.setColor(Color.DARK_GRAY);
            renderer.line(tmpVector, connection[0].getVector2());

            //connection 1 can only exist if connection 0 exists
            if(connection[1] != null){
                renderer.line(tmpVector, connection[1].getVector2());
            }
        }
    }


    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    @Override
    public void update(long deltaTime) {
        super.update(deltaTime);
    }


    @Override
    public void activate(int whichToActivate) {
        super.activate(whichToActivate);
        if(connection[0] != null){
            connection[0].activate(connectionValue);
        }
        if(connection[1] != null){
            connection[1].activate(connectionValue);
        }
    }

    @Override
    public void deactivate(int whichToDeactivate) {
        super.deactivate(whichToDeactivate);
        if(connection[0] != null){
            connection[0].deactivate(connectionValue);
        }
        if(connection[1] != null){
            connection[1].deactivate(connectionValue);
        }
    }
}
