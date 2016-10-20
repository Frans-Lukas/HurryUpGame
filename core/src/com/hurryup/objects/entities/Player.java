package com.hurryup.objects.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.hurryup.game.TextureManager;
import com.hurryup.game.hurryupGame;
import com.hurryup.game.network.GameClient;
import com.hurryup.objects.MasterClass;
import com.hurryup.objects.tiles.Button;
import com.hurryup.objects.tiles.Lever;
import com.hurryup.objects.tiles.Tile;
import com.hurryup.objects.tiles.ZDoor;
import com.hurryup.views.TutorialMenu;

import java.util.ArrayList;

import static com.hurryup.game.hurryupGame.camera;
import static com.hurryup.game.hurryupGame.isHosting;
import static java.lang.Math.abs;


/**
 * Created by frasse on 2016-09-20.
 */
public class Player extends MasterClass {
    Rectangle player;

    float playerSpeed = 4;
    float jumpSpeed = 50;
    int maxJumpSpeed = 19;
    int maxVerticalSpeed = 5;
    int gravity = 1;
    int velocityX = 0;
    int velocityY = 0;
    float prevX = 0;
    float prevY = 0;
    boolean jumping = false;
    boolean noUpdate = false;
    float timeCounter = 0;
    public boolean player1 = false;
    public boolean cameraFollows = false;
    private TextureRegion textureRegion;

    public Player(){
        //init player variables.
        player = new Rectangle();
        player.x = 200;
        player.y = 200;

        if(hurryupGame.isHosting()) {
            textureRegion = TextureManager.get("playerOne");
        }else{
            textureRegion = TextureManager.get("playerTwo");
        }
        player.width = textureRegion.getRegionWidth();
        player.height = textureRegion.getRegionHeight();
    }
    public Player(boolean noUpdate){
        this.noUpdate = noUpdate;
        player = new Rectangle();
        player.x = 200;
        player.y = 200;

        if(hurryupGame.isHosting()) {
            textureRegion = TextureManager.get("playerTwo");
        }else{
            textureRegion = TextureManager.get("playerOne");
        }
        player.width = textureRegion.getRegionWidth();
        player.height = textureRegion.getRegionHeight();
    }
    @Override
    public void draw(SpriteBatch batch) {
        //render player.

        batch.draw(textureRegion,player.x,player.y);

    }

    @Override
    public void update(long deltaTime, ArrayList<Tile> tiles) {
        if(!noUpdate) {
            super.update(deltaTime, tiles);

            timeCounter += deltaTime;
            handleInput(tiles);


            if (timeCounter >= 10 && (abs(prevX - player.x) > 0 || abs(prevY - player.y) > 0)) {
                GameClient.sendMessage(serializePosition());
                prevX = player.x;
                prevY = player.y;
                timeCounter = 0;
            }
        } else if(hurryupGame.isHosting()){
            buttonCollision(tiles);
        }

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
        boolean activate = Gdx.input.isKeyPressed(Input.Keys.E) || Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT);
        boolean menu = Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE);

        //open tutorial menu
        if(menu){
            hurryupGame.pushView(new TutorialMenu());
        }


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

        //max velocity
        if(velocityX >= maxVerticalSpeed){
            velocityX = maxVerticalSpeed;
        } else if(velocityX <= -maxVerticalSpeed){
            velocityX = -maxVerticalSpeed;
        }
        if(velocityY >= maxJumpSpeed){
            velocityY = maxJumpSpeed;
        }

        //gravity
        velocityY -= gravity;

        //collision check!
        for(Tile tile : tiles){
            if(tile.isCollidable()) {
                if (checkCollision(player.x + velocityX, tile.getPosition().x, player.y, tile.getPosition().y, (int) tile.getWidth(), (int) tile.getHeight())) {
                    //keep player 0 distance from wall if collision is detected.
                    if (velocityX < 0) {
                        while (checkCollision(player.x + velocityX, tile.getPosition().x, player.y, tile.getPosition().y, (int) tile.getWidth(), (int) tile.getHeight())) {
                            velocityX++;
                        }
                    } else if (velocityX > 0) {
                        while (checkCollision(player.x + velocityX, tile.getPosition().x, player.y, tile.getPosition().y, (int) tile.getWidth(), (int) tile.getHeight())) {
                            velocityX--;
                        }
                    }
                }

                //check vertical collision down;
                if (checkCollision(player.x, tile.getPosition().x, player.y + velocityY, tile.getPosition().y, (int) tile.getWidth(), (int) tile.getHeight())) {
                    //keep the player at 0 above ground.

                    if (tile instanceof ZDoor && activate){
                        ((ZDoor) tile).activate(0, "level2.xml");
                    }
                    if (tile instanceof Lever && activate){
                        ((Lever) tile).toggle(0);
                    }
                    if (tile instanceof Button && hurryupGame.isHosting()) {
                        ((Button) tile).activate(0);
                    }
                    if(velocityY < 0){
                        while (checkCollision(player.x, tile.getPosition().x, player.y + velocityY, tile.getPosition().y, (int) tile.getWidth(), (int) tile.getHeight())) {
                            velocityY += gravity;
                            jumping = false;
                        }
                    } else if(velocityY > 0){
                        while (checkCollision(player.x, tile.getPosition().x, player.y + velocityY, tile.getPosition().y, (int) tile.getWidth(), (int) tile.getHeight())) {
                            velocityY -= gravity;
                        }
                    }
                }
            }
        }

        //move player
        player.x += velocityX;
        player.y += velocityY;
    }
    public void buttonCollision(ArrayList<Tile> tiles){
        for(Tile tile : tiles) {
            //TODO: REWRITE BUTTON LOGIC ( -8 because remoteplayer has no gravity. )
            if (checkCollision(player.x, tile.getPosition().x, player.y - 8, tile.getPosition().y, (int) tile.getWidth(), (int) tile.getHeight())) {
                //keep the player at 0 above ground.
                if (tile instanceof Button) {
                    ((Button) tile).activate(0);
                }

            }
        }
    }

    //checks collision with blocks of the same size only.
    boolean checkCollision(float x1, float x2, float y1, float y2, int width, int height){
        boolean collision = false;
        if(x1 < x2 + width &&
                x1 + player.width > x2 &&
                y1 < y2 + height &&
                y1 + player.height > y2){
            collision = true;
        }
        return collision;
    }

    public String serializePosition(){
        return "2," + (hurryupGame.isHosting() ? "1" : "0") + ","
                + Integer.toString((int)player.x) + "," + Integer.toString((int)player.y);
    }

    public void setX(int x){
        player.x = x;
    }
    public void setY(int y){
        player.y = y;
    }

    public float getX(){
        return player.x;
    }
    public float getY(){
        return player.y;
    }

}