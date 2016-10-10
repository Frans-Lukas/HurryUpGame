package com.hurryup.objects.tiles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.hurryup.objects.helper.ConnectionPair;
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
    protected int nextState;
    protected int connectionValue = 0;
    protected Vector2 vector;
    protected Sprite tileSprite;

    public int getConnectionValue() {
        return connectionValue;
    }

    public void setConnectionValue(int connectionValue) {
        this.connectionValue = connectionValue;
    }

    public int getNextState() {
        return nextState;
    }

    public void setNextState(int nextState) {
        this.nextState = nextState;
    }

    protected Connection connection[] = new Connection[2];

    public LogicTile(Vector2 position, LogicColor logicColor, int id, int state) {
        super(position);
        this.logicColor = logicColor;
        this.id = id;
        this.state = state;
        vector = position;
    }

    public int getState() {
        return state;
    }

    public void connect(IInteractive cn){
        if(connection[0] == null) {
            connection[0] = new Connection();
            connection[0].connect(cn);
        } else{
            connection[1] = new Connection();
            connection[1].connect(cn);
        }
    }

    public ConnectionPair getConnectionPair()
    {
        if(connection[0] != null)
            return new ConnectionPair(this.position,connection[0].getVector2());
        else
            return null;
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
    public String serialize(boolean activate){
        return 1 + "," + Integer.toString(id) + "," + (activate ? "1" : "0") + "," + Integer.toString(nextState);
    }

    @Override
    public void activate(int whichToActivate) {
        if(connection[connectionValue] != null)
            connection[connectionValue].activate(connectionValue);
    }

    @Override
    public void deactivate(int whichToDeactivate) {
        if(connection[connectionValue] != null){
            connection[connectionValue].deactivate(connectionValue);
        }
    }

    public Vector2 getVector2(){
        //plus one so straight lines will not turn invisible.
        Vector2 tmpVector = new Vector2(vector.x + width / 2 + 1, vector.y + height / 2 + 1);
        return tmpVector;
    }

}
