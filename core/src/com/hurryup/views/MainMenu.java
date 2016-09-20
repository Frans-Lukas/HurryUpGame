package com.hurryup.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hurryup.game.hurryupGame;

/**
 * Created by frasse on 2016-09-20.
 */
public class MainMenu implements IView {
    Texture img = new Texture("badlogic.jpg");

    public MainMenu() {
        System.out.print("Hello, world1");
        hurryupGame.pushView(new TestLevel());
    }


    @Override
    public void draw(SpriteBatch batch) {

        batch.draw(img, 0, 0);
    }

    @Override
    public void update(long deltaTime) {
        //hurryupGame.pushView(new TestLevel());
    }
}
