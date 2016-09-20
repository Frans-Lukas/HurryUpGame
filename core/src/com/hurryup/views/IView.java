package com.hurryup.views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hurryup.game.hurryupGame;
import com.hurryup.objects.MasterClass;

import java.util.ArrayList;

/**
 * Created by frasse on 2016-09-20.
 */
public interface IView {

    void draw(SpriteBatch batch);

    void update(long deltaTime);

}
