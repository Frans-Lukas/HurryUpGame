package com.hurryup.objects.tiles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.hurryup.game.TextureManager;
import com.hurryup.game.hurryupGame;
import com.hurryup.objects.logic.LogicColor;
import com.hurryup.views.TestLevel;

/**
 * Created by frasse on 2016-10-19.
 */
public class ZDoor extends LogicTile {

    Sprite backgroundRegion;


    public ZDoor(Vector2 position, LogicColor logicColor, int id, int state) {
        super(position, logicColor, id, state);
        state = 0;

        textureRegion = TextureManager.get("zDoor");
        backgroundRegion = new Sprite(TextureManager.get("zDoorBackground"));
        backgroundRegion.setPosition(position.x, position.y);
        tileSprite = new Sprite(textureRegion);
        tileSprite.setPosition(position.x,position.y);

    }
    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        backgroundRegion.draw(batch);
        tileSprite.draw(batch);
    }

    @Override
    public void update(long deltaTime) {
        super.update(deltaTime);
        if(state == 1 && height > 0){
            height--;
            tileSprite.setSize(64,height);
        } else if(state == 0 && height < 64){
            height++;
            tileSprite.setSize(64,height);
        }
    }

    @Override
    public void deactivate(int whichToDeactivate) {
        //close door
        state = 0;
    }

    public void activate(int whichToActivate, String map){
        state = 1;
        if(height < 10){
            hurryupGame.pushView(new TestLevel(map));
        }
    }
}
