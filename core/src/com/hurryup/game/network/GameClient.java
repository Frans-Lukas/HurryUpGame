package com.hurryup.game.network;

import com.hurryup.game.hurryupGame;
import com.hurryup.objects.tiles.LogicTile;
import com.hurryup.views.IView;
import com.hurryup.views.MasterLevel;
import com.sun.xml.internal.ws.resources.ClientMessages;

import java.util.ArrayList;

/**
 * Created by Klas on 2016-09-21.
 */
public final class GameClient {

    private static ClientLogic clientLogic;
    private static Thread clientThread;

    private static String ip;
    private static int port;
    private GameClient(){

    }

    public static void configure(String i, int p){
        ip = i;
        port = p;
    }

    public static void connect(){
        clientLogic = new ClientLogic(ip,port);
        clientThread = new Thread(clientLogic);
        clientThread.start();
    }
    public static boolean connected(){
        return ClientLogic.connected();
    }

    public static ArrayList<String> getMessages(){
        if(ClientLogic.messageCount() > 0)
            return ClientLogic.getMessages();
        else
            return null;
    }
    public static String getMessage(){
        return ClientLogic.getMessage();
    }

    public static void update(){
        String d = ClientLogic.getMessage();
        if(d != null){
            System.out.println("Message: " + d);
            String[] s = d.split("|");
            if(s.length < 3)
                return;

            MasterLevel level = (MasterLevel)hurryupGame.peekView();
            LogicTile tile = level.getTileById(Integer.parseInt(s[0]));
            tile.setState(Integer.parseInt(s[2]));
        }
    }

    public static void sendMessage(String msg){
        ClientLogic.sendMessage(msg);
    }
}
