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
    private int leverAngle = 40; //40 - - 40

    public Lever(Vector2 position) {
        super(position, LogicColor.Blue,2,0);
        leverBasePosition = new Vector2(position.x + 25,position.y);
        height = 32;
        state = 3;
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);


        renderer.setColor(Color.YELLOW);
        renderer.rect(leverBasePosition.x,leverBasePosition.y + 15,5,0,10,35,1,1,leverAngle);

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
        if(state == 3 && leverAngle > -40){
            leverAngle--;
        }
        //Inactive
        else if(state == 0 && leverAngle < 40){
            leverAngle++;
        }
    }

    public void toggle(int whichToActivate){
        if(state == 0)
            activate(whichToActivate);
        else if(state == 3)
            deactivate(whichToActivate);
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

    @Override
    public Vector2 getPosition() {
        return new Vector2(position.x + 10,position.y);
    }

    @Override
    public float getLeft() {
        return super.getLeft() + 10;
    }

    @Override
    public float getRight() {
        return super.getRight() -20;
    }

    @Override
    public float getTop() {
        return position.y + 20;
    }

    @Override
    public float getBot() {
        return super.getBot();
    }

    @Override
    public float getHeight() {
        return 20;
    }

    @Override
    public float getWidth() {
        return super.getWidth()-20;
    }

    public void setColor(Color color){
        buttonColor = color;
    }

}
