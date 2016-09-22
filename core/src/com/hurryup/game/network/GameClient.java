package com.hurryup.game.network;

import com.sun.xml.internal.ws.resources.ClientMessages;

import java.util.ArrayList;

/**
 * Created by Klas on 2016-09-21.
 */
public class GameClient {

    private ClientLogic clientLogic;
    private Thread clientThread;

    private String ip;
    private int port;
    public GameClient(String ip, int port){
        this.ip = ip;
        this.port = port;
    }
    public void connect(){
        clientLogic = new ClientLogic(ip,port);
        clientThread = new Thread(clientLogic);
        clientThread.start();
    }
    public boolean connected(){
        return clientLogic.connected();
    }
    public ArrayList<String> getMessages(){
        if(ClientLogic.messageCount() > 0)
            return ClientLogic.getMessages();
        else
            return null;
    }
    public static String getMessage(){
        return ClientLogic.getMessage();
    }


    public void update(){
        String d = ClientLogic.getMessage();
        if(d != null)
            System.out.println("Message: " + d);
    }

    public void sendMessage(String msg){
        ClientLogic.sendMessage(msg);
    }
}
