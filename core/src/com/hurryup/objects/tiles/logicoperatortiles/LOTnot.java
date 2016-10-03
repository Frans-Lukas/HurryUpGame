package com.hurryup.objects.tiles.logicoperatortiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.hurryup.game.network.GameClient;
import com.hurryup.objects.logic.Connection;
import com.hurryup.objects.logic.IInteractive;
import com.hurryup.objects.logic.LogicColor;
import com.hurryup.objects.tiles.LogicTile;



/**
 * Created by frasse on 2016-09-28.
 * STATES:
 * 0: INACTIVE
 * 1: ACTIVE
 * 2: ACTIVATE
 * 3: SENT
 * 4: DEACTIVATE
 */
public class LOTnot extends LogicTile{
    //Dark Brown color
    private Color lotColorOff = Color.valueOf("802A2AFF");
    //light brown color
    private Color lotColorOn = Color.valueOf("CD5C5CFF");

    public LOTnot(Vector2 position, LogicColor logicColor, int id, int state) {
        super(position, logicColor, id, state);
        setCollidable(false);
    }

    @Override
    public void connect(IInteractive cn) {
        if(connection[0] == null) {
            connection[0] = new Connection();
            connection[0].connect(cn);
            connection[0].activate(0);
        } else{
            connection[1] = new Connection();
            connection[1].connect(cn);
            connection[1].activate(0);
        }

    }

    @Override
    public void activate(int whichToActivate) {
        //if active, deactivate
        if(state == 1){
            nextState = 4;
        }
        //send deactivate gate if not already
        if(state != 3){
            state = 3;
            GameClient.sendMessage(serialize());
        }
    }

    @Override
    public void deactivate(int whichToDeactivate) {
        //if inactive, set to activate
        if(state == 0){
            nextState = 2;
        }
        if(state != 3){
            state = 3;
            GameClient.sendMessage(serialize());
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        if(state == 0) {
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
            state = 0;
            connection[0].deactivate(connectionValue);
        }
        else if(state == 2){
            state = 1;
            connection[0].activate(connectionValue);
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
