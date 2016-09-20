package com.hurryup.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;

/**
 * Created by Klas on 2016-09-20.
 */
public class ClientLogic implements Runnable {
    private int port;
    private String ip;

    public ClientLogic(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void run() {
        SocketHints socketHints = new SocketHints();
        socketHints.connectTimeout = 4000; //4 seconds to connect

        Socket clientSocket = Gdx.net.newClientSocket(Net.Protocol.TCP,ip,port,socketHints);
        try{
            clientSocket.getOutputStream().write("hej\n".getBytes());
            System.out.println("wrotemsg");
        }
        catch(Exception e){

        }
    }
}
