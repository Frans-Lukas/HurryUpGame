package com.hurryup.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.hurryup.game.XMLReader;
import com.hurryup.game.hurryupGame;
import com.hurryup.objects.MasterClass;
import com.hurryup.objects.entities.Player;

import com.hurryup.objects.helper.VisualConnection;
import com.hurryup.objects.tiles.LogicTile;
import com.hurryup.objects.tiles.Tile;

import java.util.ArrayList;

import static com.hurryup.game.hurryupGame.HEIGHT;
import static com.hurryup.game.hurryupGame.WIDTH;
import static com.hurryup.game.hurryupGame.camera;


/**
 * Created by frasse on 2016-09-20.
 */
public class TestLevel extends MasterLevel{
    //arraylist for all entites in level.

    BitmapFont font = new BitmapFont();
    private static int cameraY = HEIGHT / 2;
    private static int cameraX = WIDTH / 2;

    public TestLevel(String mapName) {
        super();
        font.setColor(Color.RED);
        XMLReader.readMap(mapName,this);
        buildConnections();
    }

    //Draw Level
    @Override
    public void draw(SpriteBatch batch, long deltaTime) {
        super.draw(batch,deltaTime);

        for(Tile tile : tiles){
            if(tile.getDrawLevel() == 0)
                tile.draw(batch);
        }
        VisualConnection.draw(batch);
        for(Tile tile : tiles){
            if(tile.getDrawLevel() == 1)
                tile.draw(batch);
        }
        localPlayer.draw(batch);
        remotePlayer.draw(batch);

        moveCamera();

        /*for(MasterClass entity : entities){
            entity.draw(batch);

            //if you want camera to follow player, put camerafollows to true.

        }*/
        for(Tile tile : tiles){
            if(tile.getDrawLevel() == 2)
                tile.draw(batch);
        }
    }
    public void moveCamera(){
        if(getLocalPlayer().getY() > HEIGHT / 2 && getLocalPlayer().getY() < XMLReader.getMapHeight() - HEIGHT / 2){
            cameraY = (int) getLocalPlayer().getY();
        } else if(getLocalPlayer().getY() < HEIGHT / 2){
            cameraY = HEIGHT / 2;
        }

        if(getLocalPlayer().isCameraFollows() && getLocalPlayer().getX() > WIDTH / 2 &&
                getLocalPlayer().getX() < XMLReader.getMapWidth() - WIDTH / 2){
            cameraX = (int)getLocalPlayer().getX();

        } else if(getLocalPlayer().getX() < WIDTH / 2){
            cameraX = WIDTH / 2;
        } else{
            cameraX = XMLReader.getMapWidth() - WIDTH / 2;
        }
        camera.position.set(cameraX, cameraY, 0);
    }


    @Override
    public void dispose() {
    }

    @Override
    public void update(long deltaTime) {
        for(Tile tile: tiles){
            tile.update(deltaTime);
        }
        for(MasterClass entity : entities){
            entity.update(deltaTime, tiles);
        }
    }

    public void pollInput(){
        boolean menu = Gdx.input.isKeyPressed(Input.Keys.ESCAPE);

        if(menu){
            hurryupGame.pushView(new TutorialMenu());
        }

    }

}
