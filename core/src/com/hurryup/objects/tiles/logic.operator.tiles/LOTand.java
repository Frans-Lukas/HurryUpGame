package com.hurryup.objects.tiles.logic.operator.tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.hurryup.game.network.GameClient;
import com.hurryup.objects.logic.IInteractive;
import com.hurryup.objects.logic.LogicColor;
import com.hurryup.objects.tiles.LogicTile;

import static com.hurryup.game.hurryupGame.camera;

/**
 * Created by frasse on 2016-09-28.
 */
public class LOTand extends LogicTile{
    private boolean firstActivate = false;
    private boolean secondActivate = false;

    public LOTand(Vector2 position, LogicColor logicColor, int id, int state) {
        super(position, logicColor, id, state);
        //not activated
        state = 0;
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);

        renderer.setColor(Color.PURPLE);
        renderer.rect(position.x, position.y, 64, 64);
     }

    @Override
    public void update(long deltaTime) {
        super.update(deltaTime);
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
        if(whichToActivate == 1){
            firstActivate = true;
        } else if(whichToActivate == 2){
            secondActivate = true;
        }
        if(state != 1 && state != 2 && firstActivate && secondActivate){
            activate(connectionValue);
            //gate is activated.
            state = 1;
            //draw that the gate is activated.
            nextState = 2;
            GameClient.sendMessage(serialize());
        }
    }

    @Override
    public void deactivate(int whichToDeactivate) {
        if(whichToDeactivate == 1){
            firstActivate = false;
        } else if(whichToDeactivate == 2){
            secondActivate = false;
        }
        nextState = 0;
        GameClient.sendMessage(serialize());
    }
}
