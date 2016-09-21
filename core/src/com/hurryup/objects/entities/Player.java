package com.hurryup.objects.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.hurryup.objects.MasterClass;
import com.hurryup.objects.tiles.Tile;
import com.sun.deploy.util.ArrayUtil;

import java.util.ArrayList;

import static com.hurryup.game.hurryupGame.HEIGHT;
import static com.hurryup.game.hurryupGame.WIDTH;
import static com.hurryup.game.hurryupGame.camera;


/**
 * Created by frasse on 2016-09-20.
 */
public class Player extends MasterClass {
    Texture playerTexture = new Texture("badlogic.jpg");
    Rectangle player;

    int playerSpeed = 4;
    int velocityX = 0;
    int velocityY = 0;

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


        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setProjectionMatrix(camera.combined);
        renderer.setColor(Color.BLUE);
        renderer.rect(player.x, player.y, width, height);
        renderer.end();
    }

    @Override
    public void update(long deltaTime, ArrayList<Tile> tiles) {
        super.update(deltaTime, tiles);
        handleInput(tiles);
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

    public void handleInput(ArrayList<Tile> tiles){
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

        /*for(Tile tile : tiles){
            if(player.x < tile.getRight()){
                player.x = tile.getRight();
            }
            /*if(player.x + width > tile.getLeft() && player.x < tile.getLeft()){
                player.x = tile.getLeft();
            }
            if(player.y < tile.getTop() && player.y + height > tile.getTop()){
                player.y = tile.getTop();
            }
        }*/






    }

}





