package com.hurryup.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.hurryup.views.IView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 * Created by Klas on 2016-09-20.
 */
public class ServerLogic implements Runnable{
    private String ip;
    private int port;

    public ServerLogic(String ip, int port){
        this.port = port;
        this.ip = ip;
    }

    @Override
    public void run() {
        ServerSocketHints ssh = new ServerSocketHints();
        ssh.acceptTimeout = 0;

        ServerSocket serverSocket = Gdx.net.newServerSocket(Net.Protocol.TCP, port,ssh);

        while(true){
            Socket cSocket = serverSocket.accept(null);
            System.out.println("Got connection");
            BufferedReader buffer = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
            try{
                System.out.println("Read stuff");
                if(buffer.readLine().equals("hej")){
                    System.out.println("WOLOLO");
                    throw(new Exception("hej"));
                }
                else {
                    System.out.println("Got other");
                }
            }
            catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void setView(IView view){

    }
}
