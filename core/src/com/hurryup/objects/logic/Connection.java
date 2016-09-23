package com.hurryup.objects.logic;

/**
 * Created by Administrator on 2016-09-23.
 */
public class Connection {
    private IInteractive connection;

    public void connect(IInteractive iInteractive){
        connection = iInteractive;
    }

    public void activate(){
        connection.activate();
    }
}
