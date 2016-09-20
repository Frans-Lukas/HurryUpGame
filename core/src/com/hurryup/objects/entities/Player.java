package com.hurryup.objects.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.hurryup.objects.MasterClass;

import static com.hurryup.game.hurryupGame.HEIGHT;
import static com.hurryup.game.hurryupGame.WIDTH;


/**
 * Created by frasse on 2016-09-20.
 */
public class Player extends MasterClass {
    int height = 32;
    int width = 32;
    Texture playerTexture = new Texture("badlogic.jpg");
    Rectangle player;

    public Player(){

        //init player variables.
        player = new Rectangle();
        player.x = 200;
        player.y = 200;
        player.width = width;
        player.height = height;
    }

    @Override
    public void draw(SpriteBatch batch) {

        //draw player
        batch.draw(playerTexture, WIDTH, HEIGHT);
    }

    @Override
    public void update(long deltaTime) {
        super.update(deltaTime);
    }
}
