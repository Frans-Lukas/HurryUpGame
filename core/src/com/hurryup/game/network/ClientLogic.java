package com.hurryup.game.network;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Klas on 2016-09-20.
 * Represents the logic required to fetch and send messages to and from a server
 */
class ClientLogic implements Runnable {

    private static int port;
    private static String ip;
    private static ArrayList<String> messages = new ArrayList<String>();
    private static ReentrantLock messageLock = new ReentrantLock();
    private static ReentrantLock incomingMessageLock = new ReentrantLock();
    private static ArrayList<String> incomingMessages = new ArrayList<String>();

    private ClientMessageReader clientMessageReader;
    private Thread clientReaderWorker;
    private boolean exit = false;

    private static boolean connected = false;

    public ClientLogic(String ip, int port){
        this.ip = ip;
        this.port = port;
    }


    public static boolean connected(){
        return connected;
    }

    @Override
    public void run() {
        //Create socket hints, no connection timeout
        SocketHints socketHints = new SocketHints();
        socketHints.connectTimeout = 500;
        Socket clientSocket;

        try {

            clientSocket = Gdx.net.newClientSocket(Net.Protocol.TCP, ip, port, socketHints);
            clientMessageReader = new ClientMessageReader(new BufferedReader(new InputStreamReader(clientSocket.getInputStream())));
            clientReaderWorker = new Thread(clientMessageReader);
            clientReaderWorker.start();
        } catch(Exception e){
            return;
        }
        //While the client is connected
        connected = true;
        while(!exit){
            //send all queued messages to the server
            messageLock.lock();
            for (String msg: messages) {
                try{
                    clientSocket.getOutputStream().write((msg + "\n").getBytes());
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
            messages.clear();
            messageLock.unlock();
        }
    }
    //Add incoming messsage to the incoming message queue
    public static void addMessage(String msg) {
        incomingMessageLock.lock();
        incomingMessages.add(msg);
        incomingMessageLock.unlock();

    }

    //Add messsage to the outgoing message queue
    public static void sendMessage(String msg){
        messageLock.lock();
        messages.add(msg);
        messageLock.unlock();
    }

    //Gets the oldest inbound message
    public static String getMessage(){
        incomingMessageLock.lock();
        if(incomingMessages.size() > 0) {
            String msg = incomingMessages.get(0);
            incomingMessages.remove(0);
            incomingMessageLock.unlock();
            return msg;
        }
        else{
            incomingMessageLock.unlock();
            return null;
        }

    }

    //Returns the count of inbound messages
    public static int messageCount(){
        incomingMessageLock.lock();
        int size = incomingMessages.size();
        incomingMessageLock.unlock();
        return size;
    }

    //Returns all the inbound messages if any
    public static ArrayList<String> getMessages(){
        incomingMessageLock.lock();
        if(incomingMessages.size() > 0) {
            ArrayList<String> msgs = (ArrayList<String>)incomingMessages.clone();
            incomingMessages.clear();
            incomingMessageLock.unlock();
            return msgs;
        }
        else{
            incomingMessageLock.unlock();
            return null;
        }


    }

}

//Reads inbound messages from the server and adds them to the inbound message list
class ClientMessageReader implements Runnable{

    private BufferedReader reader;
    private boolean exit = false;

    public ClientMessageReader(BufferedReader reader){
        this.reader = reader;
    }

    public void exit(){
        exit = true;
    }

    @Override
    public void run() {
        while(!exit){
            String msg;
            try{
                //Read message
                msg = reader.readLine();
                ClientLogic.addMessage(msg);
            }
            catch(Exception e){
                System.out.println("Error reading message");
            }
        }
    }
}
