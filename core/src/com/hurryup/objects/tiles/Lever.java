package com.hurryup.objects.tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.hurryup.game.TextureManager;
import com.hurryup.game.network.GameClient;
import com.hurryup.objects.logic.IInteractive;
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

    private Sprite leverSprite;

    public Lever(Vector2 position) {
        super(position, LogicColor.Blue,2,0);

        textureRegion = TextureManager.get("leverBase");
        tileSprite = new Sprite(textureRegion);
        tileSprite.setPosition(position.x,position.y);
        leverSprite = new Sprite(TextureManager.get("lever"));
        leverSprite.setPosition(position.x + 30,position.y + 15);
        leverSprite.setOrigin(2.5f,0);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);

        leverSprite.setRotation(leverAngle);
        leverSprite.draw(batch);
        tileSprite.draw(batch);
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
        //leverAngle = 0;
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
    public void connect(IInteractive cn) {
        super.connect(cn);
        connection[0].deactivate(connectionValue);
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
        return super.getRight()-10;
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
        return super.getWidth()-10;
    }

    public void setColor(Color color){
        buttonColor = color;
    }

}
