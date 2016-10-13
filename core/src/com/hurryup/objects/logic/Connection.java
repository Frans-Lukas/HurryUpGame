package com.hurryup.objects.logic;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Administrator on 2016-09-23.
 */
public class Connection implements IInteractive{
    private IInteractive connection;

    public void connect(IInteractive iInteractive){
        connection = iInteractive;
    }

    public void activate(int whichToActivate){
        connection.activate(whichToActivate);
    }

    public void deactivate(int whichToActivate){
        connection.deactivate(whichToActivate);
    }

    public Vector2 getVector2(){
        return connection.getVector2();
    }

    @Override
    public Vector2 getConnectionInPos(){

        return connection.getConnectionInPos();
    }

    @Override
    public Vector2 getFirstOutPos() {
        return connection.getFirstOutPos();
    }

    @Override
    public Vector2 getSecondOutPos() {
        return connection.getSecondOutPos();
    }
}
