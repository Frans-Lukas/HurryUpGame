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
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Klas on 2016-09-20.
 * Represents the logic required to accept new clients, fetch messages from clients and broadcast messages to clients
 */
public class ServerLogic implements Runnable{

    private int port;
    private ReentrantLock clientLock = new ReentrantLock();

    private ArrayList<Client> clients = new ArrayList<Client>();

    public ServerLogic(int port){
        this.port = port;
    }

    private Client localClient;
    private Iterator<Client> iterator;

    @Override
    public void run() {

        //Socket hints with no timeout for
        ServerSocketHints ssh = new ServerSocketHints();
        ssh.acceptTimeout = 0;

        //Create socket and bind to port
        ServerSocket serverSocket = Gdx.net.newServerSocket(Net.Protocol.TCP, port,ssh);

        while(true){
            Socket cSocket = serverSocket.accept(null);

            System.out.println("Client connected!");
            //New client connected, create a buffer for reading messages and add it to the clients list
            BufferedReader buffer = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
            clientLock.lock();
            clients.add(new Client(cSocket,buffer));
            clientLock.unlock();
        }
    }

    //Removes a client from the client list
    public void removeClient(Client client){
        clientLock.lock();
        clients.remove(client);
        clientLock.unlock();
    }

    //Broadcasts a message to all clients and removes any disconnected clients
    public void broadcast(String msg){
        clientLock.lock();
        iterator = clients.iterator();
        //For each client
        while(iterator.hasNext()){
            localClient = iterator.next();

            if(localClient.isDead()) {
                iterator.remove(); //Client disconnected (removed)
                System.out.println("Removed client");
            }
            else
                localClient.sendMessage(msg);
        }
        clientLock.unlock();
    }

}

