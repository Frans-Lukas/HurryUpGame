package com.hurryup.objects.tiles.logicoperatortiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.hurryup.game.network.GameClient;
import com.hurryup.objects.logic.IInteractive;
import com.hurryup.objects.logic.LogicColor;
import com.hurryup.objects.tiles.LogicTile;

import static com.hurryup.game.hurryupGame.camera;

/**
 * Created by frasse on 2016-09-28.
 * STATES:
 * 0: NONE
 * 1: 1 T 2 F
 * 2: 1 F 2 T
 * 3: 1 T 2 T
 * 4: ACTIVE
 * 5: SENT
 * 6: DEACTIVATE
 */
public class LOTand extends LogicTile{
    private boolean firstActivate = false;
    private boolean secondActivate = false;

    //dark purple
    private Color lotColorOff = Color.valueOf("2E0854FF");

    //light purple
    private Color lotColorOn = Color.valueOf("7F00FFFF");

    public LOTand(Vector2 position, LogicColor logicColor, int id, int state) {
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
            System.out.println(state);
        }
    }

    @Override
    public int getState() {
        return super.getState();
    }

    @Override
    public void connect(IInteractive cn) {
        super.connect(cn);
    }

    @Override
    public void setState(int state) {
        super.setState(state);
    }

    @Override
    public LogicColor getLogicColor() {
        return super.getLogicColor();
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public String serialize() {
        return super.serialize();
    }

    @Override
    public void activate(int whichToActivate) {

        if(whichToActivate == 0){
            if(state == 2)
                nextState = 3;
            else
                nextState = 1;
        }
        else if(whichToActivate == 1){
            if(state == 1)
                nextState = 3;
            else {
                nextState = 2;
            }
        }

        if(state != 5 && state != 4){

            //Both active
            if(nextState == 3) {
                state = 5;
                nextState = 4;
            }
            GameClient.sendMessage(serialize());
        }
    }

    @Override
    public void deactivate(int whichToDeactivate) {
        int locState = state;

        if(whichToDeactivate == 0){
            if(state == 3 || state == 2) {
                nextState = 2;
            }
            else
                nextState = 0;

        } else if(whichToDeactivate == 1){
            if(state == 3 || state == 1)
                nextState = 1;
            else
                nextState = 0;
        }
        if(state != nextState && state != 5 && state != 6){
            state = 5;
            nextState = 6;
            GameClient.sendMessage(serialize());
        }
    }
}
