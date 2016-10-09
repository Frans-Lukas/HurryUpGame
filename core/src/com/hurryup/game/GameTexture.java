package com.hurryup.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;


/**
 * Created by Klas on 2016-10-09.
 */
public class GameTexture {
    private Texture texture;
    private Rectangle region;

    public GameTexture(Texture t, Rectangle r) {
        texture = t;
        region = r;
    }
    public GameTexture(){

    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Rectangle getRegion() {
        return region;
    }

    public void setRegion(Rectangle region) {
        this.region = region;
    }
}
