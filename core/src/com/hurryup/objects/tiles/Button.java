package com.hurryup.objects.tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.hurryup.objects.logic.LogicColor;

import static com.hurryup.game.hurryupGame.camera;

/**
 * Created by frasse on 2016-09-20.
 */
public class Button extends LogicTile {

    Color buttonColor = Color.RED;
    boolean buttonPushed = false;
    boolean restartButton = false;

    public Button(Vector2 position) {
        super(position, LogicColor.Blue,2,0);
        height = 32;
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
            height -= 0.5;
            restartButton = false;
        }
        if(restartButton && height < 32){
            height += 0.5;
            if(height == 32){
                restartButton = false;
            }
        }
        if(height <= 16){
            //MessageManager.doorOpen = true;
        }
    }

    public void pushButton(){
        buttonPushed = true;
    }

    public void unPushButton(){
        buttonPushed = false;
        restartButton = true;
    }

    public void setColor(Color color){
        buttonColor = color;
    }
}
