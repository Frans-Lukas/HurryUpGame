package com.hurryup.objects.tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.hurryup.game.TextureManager;
import com.hurryup.game.network.GameClient;
import com.hurryup.objects.logic.IInteractive;
import com.hurryup.objects.logic.LogicColor;
import com.hurryup.game.hurryupGame;
import com.hurryup.views.MasterLevel;

import static com.hurryup.game.hurryupGame.camera;
import static com.hurryup.game.hurryupGame.isHosting;

/**
 * Created by frasse on 2016-09-20.
 * 0 = inactive
 * 1 = sent
 * 2 = modify
 * 3 = active
 */
public class Button extends LogicTile {

    Color buttonColor = Color.RED;
    private int timeSinceReset = 0;
    private int resetTimer = 100;


    public Button(Vector2 position) {
        super(position, LogicColor.Blue,2,0);
        height = 32;
        textureRegion = TextureManager.get("button");
        tileSprite = new Sprite(textureRegion);
        tileSprite.setPosition(position.x,position.y);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);

        tileSprite.setSize(64,height);
        tileSprite.draw(batch);
    }

    @Override
    public void update(long deltaTime) {
        super.update(deltaTime);
        if((state == 3) && height > 16){
            height -= 0.5;
        } else if(state == 0 && height < 32){
            height += 0.5;
        }
        if(hurryupGame.isHosting() && state == 3){
            timeSinceReset += deltaTime;
            if(timeSinceReset > resetTimer){
                timeSinceReset = 0;
                deactivate(connectionValue);
            }
        }
    }


    @Override
    public void connect(IInteractive cn) {
        super.connect(cn);
        connection[0].deactivate(connectionValue);
    }

    @Override
    public void activate(int whichToActivate) {
        timeSinceReset = 0;
        if(state == 0) {
            System.out.println("not here");
            state = 1;
            nextState = 2;
            GameClient.sendMessage(serialize(true));
        }
        else if(state == 2 && connection[0] != null){
            connection[0].activate(connectionValue);
            state = 3;
        }
    }

    @Override
    public Vector2 getConnectionInPos() {
        return super.getConnectionInPos();
    }

    @Override
    public Vector2 getFirstOutPos() {
        return new Vector2(position.x + 27, position.y);
    }

    @Override
    public Vector2 getSecondOutPos() {
        return super.getSecondOutPos();
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

    public void setColor(Color color){
        buttonColor = color;
    }
}
