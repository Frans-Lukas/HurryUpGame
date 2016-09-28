package com.hurryup.objects.tiles;

import com.badlogic.gdx.math.Vector2;
import com.hurryup.objects.logic.Connection;
import com.hurryup.objects.logic.IInteractive;
import com.hurryup.objects.logic.LogicColor;

/**
 * Created by Klas on 2016-09-23.
 */
public class LogicTile extends Tile implements IInteractive{
    protected LogicColor logicColor;
    protected int id;
    protected int state;
    protected Connection connection[] = new Connection[2];

    public LogicTile(Vector2 position, LogicColor logicColor, int id, int state) {
        super(position);
        this.logicColor = logicColor;
        this.id = id;
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void connect(IInteractive cn){
        if(connection[0] != null) {
            connection[0].connect(cn);
        } else{
            connection[1].connect(cn);
        }
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
    public void setId(int id){ this.id = id; }

    //id|logicColor|state
    public String serialize(){
        return Integer.toString(id) + "|" + Integer.toString(logicColor.getId()) + "|" + Integer.toString(state);
    }

    @Override
    public void activate(int whichToActivate) {
        if(connection[whichToActivate] != null)
            connection[whichToActivate].activate(whichToActivate);
    }
}
