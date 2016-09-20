package com.hurryup.objects.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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

    int playerSpeed = 5;

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

        ShapeRenderer renderer = new ShapeRenderer();
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        renderer.setColor(Color.BLUE);
        renderer.rect(player.x, player.y, 32, 32);
        renderer.end();
    }

    @Override
    public void update(long deltaTime) {
        super.update(deltaTime);
        handleInput();

    }

    public void handleInput(){
        boolean left = Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A);
        boolean right = Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D);
        boolean up = Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W);
        boolean down = Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S);

        if(left){
            player.x -= playerSpeed;
        }
        if(right){
            player.x += playerSpeed;
        }
        if(up){
            player.y += playerSpeed;
        }
        if(down){
            player.y -= playerSpeed;
        }


    }
}





