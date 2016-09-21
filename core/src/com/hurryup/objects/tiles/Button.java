package com.hurryup.objects.tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import static com.hurryup.game.hurryupGame.camera;

/**
 * Created by frasse on 2016-09-20.
 */
public class Button extends Tile {

    Color buttonColor = Color.RED;
    boolean buttonPushed = false;

    public Button(Vector2 position) {
        super(position);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setProjectionMatrix(camera.combined);
        renderer.setColor(buttonColor);
        renderer.rect(position.x, position.y, width, height);
        renderer.end();
    }

    @Override
    public void update(long deltaTime) {
        super.update(deltaTime);
        if(buttonPushed && height > 16){
            height--;
        }

    }

    public void pushButton(){
        buttonPushed = true;
    }
}
