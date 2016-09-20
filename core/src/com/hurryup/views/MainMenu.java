package com.hurryup.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hurryup.views.View;

/**
 * Created by frasse on 2016-09-20.
 */
public class MainMenu extends View {
    Texture img = new Texture("badlogic.jpg");

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(img, 0, 0);
    }

    @Override
    public void update(long deltaTime) {
    }
}
