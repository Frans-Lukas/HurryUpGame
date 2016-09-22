package com.hurryup.game.network;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.hurryup.game.network.Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Klas on 2016-09-20.
 */
public class ServerLogic implements Runnable{

    private int port;
    private ReentrantLock clientLock = new ReentrantLock();

    private ArrayList<Client> clients = new ArrayList<Client>();

    public ServerLogic(int port){
        this.port = port;
    }

    @Override
    public void run() {
        ServerSocketHints ssh = new ServerSocketHints();
        ssh.acceptTimeout = 0;

        ServerSocket serverSocket = Gdx.net.newServerSocket(Net.Protocol.TCP, port,ssh);

        while(true){
            Socket cSocket = serverSocket.accept(null);

            System.out.println("Client connected!");

            BufferedReader buffer = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
            clientLock.lock();
            clients.add(new Client(cSocket,buffer));
            clientLock.unlock();
        }
    }

    public void broadcast(String msg){
        clientLock.lock();
        for (Client c: clients) {
            c.sendMessage(msg);
        }
        clientLock.unlock();
    }

}

