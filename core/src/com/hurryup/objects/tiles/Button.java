package com.hurryup.objects.tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.hurryup.game.network.GameClient;
import com.hurryup.objects.logic.LogicColor;

import static com.hurryup.game.hurryupGame.camera;

/**
 * Created by frasse on 2016-09-20.
 */
public class Button extends LogicTile {

    Color buttonColor = Color.RED;

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
        if(state == 2 && height > 16){
            height -= 0.5;
        }
    }

    public void pushButton(){
        state = 1;
        nextState = 2;
        activate(connectionValue);
        GameClient.sendMessage(serialize());
    }

    public void setColor(Color color){
        buttonColor = color;
    }
}
