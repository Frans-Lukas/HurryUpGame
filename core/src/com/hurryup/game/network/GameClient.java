package com.hurryup.game.network;

import com.sun.xml.internal.ws.resources.ClientMessages;

import java.util.ArrayList;

/**
 * Created by Klas on 2016-09-21.
 */
public class GameClient {

    private ClientLogic clientLogic;
    private Thread clientThread;

    public GameClient(String ip, int port){
        clientLogic = new ClientLogic(ip,port);
        clientThread = new Thread(clientLogic);
        clientThread.start();
    }

    public ArrayList<String> getMessages(){
        if(ClientLogic.messageCount() > 0)
            return ClientLogic.getMessages();
        else
            return null;
    }

    public void sendMessage(String msg){
        clientLogic.sendMessage(msg);
    }
}
