package com.hurryup.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hurryup.game.hurryupGame;
import com.hurryup.objects.entities.Cursor;

import static com.hurryup.objects.tiles.Tile.renderer;

/**
 * Created by frasse on 2016-09-20.
 */
public class MainMenu implements IView {
    Texture img = new Texture("badlogic.jpg");
    int ipMenuWidth = 400;
    int ipMenuHeight = 80;
    int sgMenuWidth = 180;
    int sgMenuHeight = 80;
    int spaceing = 100;

    Cursor cursor = new Cursor();

    public MainMenu() {}


    @Override
    public void dispose() {

    }

    @Override
    public void draw(SpriteBatch batch, long deltaTime) {
        //batch.draw(img, 0, 0);
        renderer.setColor(Color.BLACK);
        //enter IP-address
        //enter port
        renderer.rect(hurryupGame.WIDTH / 2 - ipMenuWidth / 2, hurryupGame.HEIGHT / 2, ipMenuWidth, ipMenuHeight);

        //join game
        renderer.rect(hurryupGame.WIDTH / 2 - ipMenuWidth / 2, hurryupGame.HEIGHT / 2 - spaceing, sgMenuWidth, sgMenuHeight);
        //host game
        renderer.rect(hurryupGame.WIDTH /2 + sgMenuWidth / 9, hurryupGame.HEIGHT / 2 - spaceing, sgMenuWidth, sgMenuHeight);
        cursor.draw(batch);

    }

    @Override
    public void update(long deltaTime) {
        checkMouseCollisionWithMenu();
        cursor.update(deltaTime);
    }

    private void checkMouseCollisionWithMenu(){

    }

}
