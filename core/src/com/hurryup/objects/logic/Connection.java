package com.hurryup.objects.logic;

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
}
