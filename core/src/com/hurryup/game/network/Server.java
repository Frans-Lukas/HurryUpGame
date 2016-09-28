package com.hurryup.game.network;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Klas on 2016-09-21.
 */
public final class Server {

    private static Thread serverThread;
    public static ReentrantLock serverLock = new ReentrantLock();
    private static ServerLogic serverLogic;
    private static boolean started = false;

    private Server(){

    }
    //Start the server and start listening for incoming connections
    public static void start(int port){
        serverLogic = new ServerLogic(port);
        serverThread = new Thread(serverLogic);
        serverThread.start();
        started = true;
    }

    //Updates the server, read any new messages and forwards them to all clients
    public static void update(){
        String msg;
        //While the client has new unread messages
        while((msg = Client.getMessage()) != null){
            serverLogic.broadcast(msg);
            System.out.println("Got message " + msg);
        }
    }

    public static boolean isStarted(){
        return started;
    }
}
