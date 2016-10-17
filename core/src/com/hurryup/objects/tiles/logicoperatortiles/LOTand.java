package com.hurryup.objects.tiles.logicoperatortiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.hurryup.game.TextureManager;
import com.hurryup.game.network.GameClient;
import com.hurryup.objects.logic.IInteractive;
import com.hurryup.objects.logic.LogicColor;
import com.hurryup.objects.tiles.LogicTile;

import static com.hurryup.game.hurryupGame.camera;

/**
 * Created by frasse on 2016-09-28.
 * STATES:
 * 1 CALLED
 * 2 CALL
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
        textureRegion = TextureManager.get("LOTand");
        tileSprite = new Sprite(textureRegion);
        tileSprite.setPosition(position.x,position.y);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        if(firstActivate && secondActivate) {
            tileSprite.setColor(lotColorOn);
            tileSprite.draw(batch);
        } else{
            tileSprite.setColor(lotColorOff);
            tileSprite.draw(batch);
        }
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
    public void activate(int whichToActivate) {

        if(whichToActivate == 0){
            firstActivate = true;
        }
        else if(whichToActivate == 1){
            secondActivate = true;
        }

        System.out.println(firstActivate + ":" + secondActivate);
        if(firstActivate && secondActivate){
            if(connection[0] != null) {
                connection[0].activate(0);
            }
        }
    }

    @Override
    public void deactivate(int whichToDeactivate) {

        if(whichToDeactivate == 0){
            firstActivate = false;

        } else if(whichToDeactivate == 1){
            secondActivate = false;
        }
        if(connection[0] != null) {
            connection[0].deactivate(0);
        }
    }
}
