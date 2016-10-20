package com.hurryup.game.network;

import com.badlogic.gdx.math.Vector2;
import com.hurryup.game.hurryupGame;
import com.hurryup.objects.tiles.LogicTile;
import com.hurryup.views.IView;
import com.hurryup.views.MasterLevel;
import com.hurryup.views.TestLevel;
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
    private static boolean ready = false;

    private GameClient(){

    }

    public static void configure(String i, int p){
        ip = i;
        port = p;
    }

    //Connects the client
    public static void connect(){
        clientLogic = new ClientLogic(ip,port);
        clientThread = new Thread(clientLogic);
        clientThread.start();
        ready = true;
    }
    //Returns a value specifying if client is connected or not
    public static boolean connected(){
        return ClientLogic.connected();
    }
    //Returns all messages
    public static ArrayList<String> getMessages(){
        if(ClientLogic.messageCount() > 0)
            return ClientLogic.getMessages();
        else
            return null;
    }
    //Returns the latest message recieved
    public static String getMessage(){
        return ClientLogic.getMessage();
    }

    public static void update(){

        if(!ready)
            return;
        //While client has new messages
        String d = ClientLogic.getMessage();
        if(d != null){

            String[] s = d.split(",");
            //Check if message is of (serialized) type
            if(s.length < 1)
                return;
            /*
                        *Message Syntax*
                1: [TYPE],[ID],[Activate],[NewState]
                2: [TYPE],[ID],[X],[Y]
            */

            switch(Integer.parseInt(s[0])){
                //Serialized change
                case 1:
                    MasterLevel level = (MasterLevel)hurryupGame.peekView();
                    LogicTile tile = level.getTileById(Integer.parseInt(s[1]));

                    int activate = Integer.parseInt(s[2]);
                    tile.setState(Integer.parseInt(s[3]));
                    //1 == activate, 0 == deactivate
                    if(activate == 1){
                        tile.activate(tile.getConnectionValue());
                        System.out.printf("[%s] Activated!\n",s[1]);
                    }
                    else{
                        tile.deactivate(tile.getConnectionValue());
                        System.out.printf("[%s] Deactivated!\n",s[1]);
                    }


                    break;
                //Client position
                case 2:
                    //1 = host client
                    //0 = not hosting
                    if(s[1].equals("0") && hurryupGame.isHosting() || s[1].equals("1") && !hurryupGame.isHosting()) {
                        hurryupGame.updateRemotePostition(new Vector2(Integer.parseInt(s[2]),Integer.parseInt(s[3])));
                    }
                    break;
                case 3:
                    System.out.println(d);
                    hurryupGame.changeLevel(s[1]);
                    break;
            }

        }
    }

    //Send message to
    public static void sendMessage(String msg){
        ClientLogic.sendMessage(msg);
    }
}
