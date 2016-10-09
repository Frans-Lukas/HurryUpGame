package com.hurryup.objects.tiles.logicoperatortiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.hurryup.game.network.GameClient;
import com.hurryup.objects.logic.IInteractive;
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
    private Color lotColorOn = Color.valueOf("0000ddFF");


    public LOTxnor(Vector2 position, LogicColor logicColor, int id, int state) {
        super(position, logicColor, id, state);
        //logic operators are not collidable
        setCollidable(false);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        if((!firstActivate && !secondActivate) || (firstActivate && secondActivate)) {
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

    @Override
    public void update(long deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void activate(int whichToActivate) {

        if(whichToActivate == 0){
            firstActivate = true;
        } else if(whichToActivate == 1){
            secondActivate = true;
        }

        if(connection[connectionValue] != null) {
            if (firstActivate && secondActivate) {
                connection[connectionValue].activate(connectionValue);
            } else {
                connection[connectionValue].deactivate(connectionValue);
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

        if(connection[0] != null) {
            if (!firstActivate && !secondActivate) {
                connection[0].activate(connectionValue);
            } else {
                connection[0].deactivate(connectionValue);
            }
        }
    }

    @Override
    public void connect(IInteractive cn) {
        super.connect(cn);
        if((firstActivate && secondActivate) || (!firstActivate && !secondActivate)) {
            connection[0].deactivate(connectionValue);
        }
    }
}
