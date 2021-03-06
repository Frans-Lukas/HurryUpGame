package com.hurryup.objects.tiles.logicoperatortiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.hurryup.game.TextureManager;
import com.hurryup.game.network.GameClient;
import com.hurryup.objects.logic.Connection;
import com.hurryup.objects.logic.IInteractive;
import com.hurryup.objects.logic.LogicColor;
import com.hurryup.objects.tiles.LogicTile;



/**
 * Created by frasse on 2016-09-28.
 */
public class LOTnot extends LogicTile{
    //Dark Brown color
    private Color lotColorOff = Color.valueOf("802A2AFF");
    //light brown color
    private Color lotColorOn = Color.valueOf("CD5C5CFF");

    private boolean activated = false;

    public LOTnot(Vector2 position, LogicColor logicColor, int id, int state) {
        super(position, logicColor, id, state);
        setCollidable(false);
        textureRegion = TextureManager.get("LOTnot");
        tileSprite = new Sprite(textureRegion);
        tileSprite.setPosition(position.x,position.y);
    }

    @Override
    public void activate(int whichToActivate) {
        super.deactivate(connectionValue);
        if(connection[0] != null){
            connection[0].deactivate(connectionValue);
        }
        activated = false;
    }

    @Override
    public void deactivate(int whichToDeactivate) {
        super.activate(connectionValue);
        if(connection[0] != null){
            connection[0].activate(connectionValue);
        }
        activated = true;
    }

    @Override
    public void connect(IInteractive cn) {
        super.connect(cn);
        if(activated = true) {
            connection[0].activate(connectionValue);
        } else if(!activated){
            connection[0].deactivate(connectionValue);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        if(activated) {
            tileSprite.setColor(lotColorOn);
            tileSprite.draw(batch);
        } else{
            tileSprite.setColor(lotColorOn);
            tileSprite.draw(batch);
        }
    }

    @Override
    public void update(long deltaTime) {
        super.update(deltaTime);
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
