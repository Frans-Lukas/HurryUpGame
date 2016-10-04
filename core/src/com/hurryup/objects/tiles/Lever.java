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
    private int leverLength = 40;
    private int leverWidth = 10;

    public Lever(Vector2 position) {
        super(position, LogicColor.Blue,2,0);
        //+ 25 and + 15 for better looks.
        leverBasePosition = new Vector2(position.x + 25,position.y + 15);
        height = 32;
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);



        renderer.setColor(Color.BROWN);
        renderer.rect(leverBasePosition.x,leverBasePosition.y, leverWidth / 2, 0, leverWidth, leverLength, 1, 1,leverAngle);

        renderer.setColor(buttonColor);
        renderer.rect(position.x, position.y, width, baseHeight);
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

        if(state == 0 && leverAngle == 40) {

            state = 1;
            nextState = 2;

            GameClient.sendMessage(serialize(true));
        }
        else if(state == 2 && connection[connectionValue] != null){
            connection[0].activate(connectionValue);
            state = 3;
        }
    }

    @Override
    public void deactivate(int whichToDeactivate) {

        if(state == 3 && leverAngle == -40){
            state = 1;
            nextState = 2;

            GameClient.sendMessage(serialize(false));
        }
        else if(state == 2 && connection[connectionValue] != null){
            connection[0].deactivate(connectionValue);
            state = 0;
        }

    }

    @Override
    public Vector2 getPosition() {
        return new Vector2(position.x,position.y);
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
        return position.y;
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
        return super.getWidth();
    }

    public void setColor(Color color){
        buttonColor = color;
    }

}
