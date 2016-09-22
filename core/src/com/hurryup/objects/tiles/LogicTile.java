package com.hurryup.objects.tiles;

import com.badlogic.gdx.math.Vector2;
import com.hurryup.objects.logic.LogicColor;

/**
 * Created by Klas on 2016-09-23.
 */
public class LogicTile extends Tile {
    private LogicColor logicColor;
    private int id;
    private int state;

    public LogicTile(Vector2 position, LogicColor logicColor, int id, int state) {
        super(position);
        this.logicColor = logicColor;
        this.id = id;
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public LogicColor getLogicColor() {

        return logicColor;
    }

    public int getId() {
        return id;
    }

    //id|logicColor|state
    public String serialize(){
        return Integer.toString(id) + "|" + Integer.toString(logicColor.getId()) + "|" + Integer.toString(state);
    }
}
