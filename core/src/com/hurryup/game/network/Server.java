package com.hurryup.game.network;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Klas on 2016-09-21.
 */
public class Server {

    private Thread serverThread;
    public static ReentrantLock serverLock = new ReentrantLock();
    private ServerLogic serverLogic;
    public Server(int port){

        serverLogic = new ServerLogic(port);
        serverThread = new Thread(serverLogic);
        serverThread.start();
    }

    public void update(){
        String msg;
        while((msg = Client.getMessage()) != null){
            serverLogic.broadcast(msg);
            System.out.println("Got message " + msg);
        }
    }

}
