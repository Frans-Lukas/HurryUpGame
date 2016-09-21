package com.hurryup.objects.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.hurryup.objects.MasterClass;
import com.hurryup.objects.tiles.Button;
import com.hurryup.objects.tiles.Tile;

import java.util.ArrayList;

import static com.hurryup.game.hurryupGame.camera;


/**
 * Created by frasse on 2016-09-20.
 */
public class Player extends MasterClass {
    Texture playerTexture = new Texture("badlogic.jpg");
    Rectangle player;

    int playerSpeed = 6;
    int jumpSpeed = 50;
    int gravity = 1;
    int velocityX = 0;
    int velocityY = 0;
    boolean jumping = false;

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

        //render player.
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
        boolean restart = Gdx.input.isKeyPressed(Input.Keys.ESCAPE);

        //move player
        if(left || right){
            if(left) {
                velocityX -= playerSpeed;
            }
            if(right){
                velocityX += playerSpeed;
            }
        } else{
            velocityX = 0;
        }
        //can't double jump
        if(up && !jumping){
            velocityY += jumpSpeed;
            jumping = true;
        }
        /*if(down){
            velocityY -= playerSpeed;
        }*/
        if(restart){
            for(Tile tile : tiles){
                if(tile instanceof Button){
                    ((Button) tile).unPushButton();
                }
            }
        }

        //max velocity
        if(velocityX >= 7){
            velocityX = 7;
        } else if(velocityX <= -7){
            velocityX = -7;
        }
        if(velocityY >= 16){
            velocityY = 16;
        }

        //gravity
        velocityY -= gravity;

        //collision check!
        for(Tile tile : tiles){
            if(checkCollision(player.x + velocityX, tile.getPosition().x, player.y, tile.getPosition().y, tile.getWidth(), tile.getHeight())){
                //keep player 0 distance from wall if collision is detected.

                if(velocityX < 0){
                    while(checkCollision(player.x + velocityX, tile.getPosition().x, player.y, tile.getPosition().y, tile.getWidth(), tile.getHeight())){
                        velocityX++;
                    }
                } else if(velocityX > 0){
                    while(checkCollision(player.x + velocityX, tile.getPosition().x, player.y, tile.getPosition().y, tile.getWidth(), tile.getHeight())){
                        velocityX--;
                    }

                }

            }
            //check vertical collision;
            if(checkCollision(player.x, tile.getPosition().x, player.y + velocityY, tile.getPosition().y, tile.getWidth(), tile.getHeight())){
                //keep the player at 0 above ground.
                if(tile instanceof Button){
                    ((Button) tile).pushButton();
                }
                while(checkCollision(player.x, tile.getPosition().x, player.y + velocityY, tile.getPosition().y, tile.getWidth(), tile.getHeight())){
                    velocityY++;
                }
                jumping = false;
            }
        }

        //move player
        player.x += velocityX;
        player.y += velocityY;
    }

    //checks collision with blocks of the same size only.
    boolean checkCollision(float x1, float x2, float y1, float y2, int width, int height){
        boolean collision = false;
        if(x1 < x2 + width &&
                x1 + width > x2 &&
                y1 < y2 + height &&
                y1 + height > y2){
            collision = true;
        }
        return collision;
    }

}