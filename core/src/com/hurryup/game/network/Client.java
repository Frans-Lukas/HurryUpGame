package com.hurryup.game.network;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import com.badlogic.gdx.net.Socket;

/**
 * Created by Klas on 2016-09-21.
 * Represents the read/write part of a servers client
 */
class Client {
    private Socket socket;
    private BufferedReader reader;
    private Thread worker;

    private static ArrayList<String> messages = new ArrayList<String>();
    private static ReentrantLock messageLock = new ReentrantLock();

    private volatile boolean dead;
    private volatile int errorCount;

    //Create a new Client, with socket s and messageReader reader
    public Client(Socket s, BufferedReader reader)
    {
        socket = s;
        this.reader = reader;

        worker = new Thread(new MessageReader(this.reader,this));
        worker.start();
    }

    //Send message to (this) client
    public void sendMessage(String msg){
        try {
            socket.getOutputStream().write((msg + "\n").getBytes());
        }
        catch(Exception e){
            System.out.println("Error writing " + msg + " to client");
        }
    }

    //Gets the latest inbound message if any
    public static String getMessage(){
        messageLock.lock();
        if(messages.size() > 0) {
            String msg = messages.get(0);
            messages.remove(0);
            messageLock.unlock();
            return msg;
        }
        else{
            messageLock.unlock();
            return null;
        }
    }

    //Adds a message to the inbound message queue
    public static void addMessage(String msg){
        messageLock.lock();
        messages.add(msg);
        messageLock.unlock();
    }

    public boolean isDead() {
        return dead;
    }

    public void error()
    {
        errorCount++;
        if(errorCount > 3)
            dead = true;
    }
}

//Reads inbound messages from the server
class MessageReader implements Runnable{

    private BufferedReader reader;
    private boolean stop = false;
    private Client host;

    //Creates a new MessageReader with the specified BufferedReader and Client to which the messages are added
    public MessageReader(BufferedReader reader, Client host) {
        this.reader = reader;
        this.host = host;
    }

    public void stop(){
        stop = true;
    }

    @Override
    public void run() {
        while(!stop){
            try{
                //Read message and add it to host Clients message queue
                String msg = reader.readLine();
                Client.addMessage(msg);
            }
            catch(Exception e) {
                System.out.println("Error reading message from client");
                host.error();
                if(host.isDead())
                    stop = true;
            }
        }
    }
}
