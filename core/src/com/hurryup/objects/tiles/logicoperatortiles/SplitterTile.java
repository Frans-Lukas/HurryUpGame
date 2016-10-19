package com.hurryup.objects.tiles.logicoperatortiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.hurryup.game.TextureManager;
import com.hurryup.objects.logic.LogicColor;
import com.hurryup.objects.tiles.LogicTile;

/**
 * Created by frasse on 2016-10-10.
 */
public class SplitterTile extends LogicTile{

    private boolean activate = false;
    //dark purple
    private Color lotColorOff = Color.valueOf("ff66ffFF");

    //light purple
    private Color lotColorOn = Color.valueOf("ff99ffFF");

    public SplitterTile(Vector2 position, LogicColor logicColor, int id, int state) {
        super(position, logicColor, id, state);
        textureRegion = TextureManager.get("SplitterTile");
        tileSprite = new Sprite(textureRegion);
        tileSprite.setPosition(position.x,position.y);
        setCollidable(false);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        if(activate) {
            tileSprite.setColor(lotColorOn);
        } else{
            tileSprite.setColor(lotColorOff);
        }
        tileSprite.draw(batch);
    }

    @Override
    public void update(long deltaTime) {
        super.update(deltaTime);
    }


    @Override
    public void activate(int whichToActivate) {
        super.activate(whichToActivate);
        if(connection[0] != null){
            connection[0].activate(connectionValue);
        }
        if(connection[1] != null){
            connection[1].activate(connectionValue);
        }
    }

    @Override
    public void deactivate(int whichToDeactivate) {
        super.deactivate(whichToDeactivate);
        if(connection[0] != null){
            connection[0].deactivate(connectionValue);
        }
        if(connection[1] != null){
            connection[1].deactivate(connectionValue);
        }
    }
}
