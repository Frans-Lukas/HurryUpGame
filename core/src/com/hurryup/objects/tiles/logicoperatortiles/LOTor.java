package com.hurryup.objects.tiles.logicoperatortiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.hurryup.game.TextureManager;
import com.hurryup.game.network.GameClient;
import com.hurryup.objects.logic.LogicColor;
import com.hurryup.objects.tiles.LogicTile;

/**
 * Created by frasse on 2016-09-28.
 */
public class LOTor extends LogicTile{
    boolean firstActivate = false;
    boolean secondActivate = false;

    //Dark Brown color
    private Color lotColorOff = Color.valueOf("006600FF");
    //light brown color
    private Color lotColorOn = Color.valueOf("007f00FF");

    public LOTor(Vector2 position, LogicColor logicColor, int id, int state) {
        super(position, logicColor, id, state);
        setCollidable(false);
        textureRegion = TextureManager.get("LOTor");
        tileSprite = new Sprite(textureRegion);
        tileSprite.setPosition(position.x,position.y);
    }

    @Override
    public void activate(int whichToActivate) {
        if(whichToActivate == 0){
            firstActivate = true;
        } else if(whichToActivate == 1){
            secondActivate = true;
        }
        if(firstActivate || secondActivate){
            connection[0].activate(connectionValue);
        }
    }

    @Override
    public void deactivate(int whichToDeactivate) {
        if(whichToDeactivate == 0){
            firstActivate = false;
        } else if(whichToDeactivate == 1){
            secondActivate = false;
        }

        if(!firstActivate && !secondActivate){
            connection[0].deactivate(connectionValue);
        }
    }

    @Override
    public void update(long deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        if(firstActivate || secondActivate) {
            tileSprite.setColor(lotColorOn);
            tileSprite.draw(batch);
        } else{
            tileSprite.setColor(lotColorOn);
            tileSprite.draw(batch);
        }

    }
}
