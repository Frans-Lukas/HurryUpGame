package com.hurryup.game.network;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import com.badlogic.gdx.net.Socket;

/**
 * Created by Klas on 2016-09-21.
 */
class Client {
    private Socket socket;
    private BufferedReader reader;
    private Thread worker;

    private static ArrayList<String> messages = new ArrayList<String>();
    private static ReentrantLock messageLock = new ReentrantLock();

    public Client(Socket s, BufferedReader reader)
    {
        socket = s;
        this.reader = reader;

        worker = new Thread(new MessageReader(this.reader));
        worker.start();
    }

    public void sendMessage(String msg){
        try {
            socket.getOutputStream().write((msg + "\n").getBytes());
        }
        catch(Exception e){
            System.out.println("Error writing " + msg + " to client");
        }
    }

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

    public static void addMessage(String msg){
        messageLock.lock();
        messages.add(msg);
        messageLock.unlock();
    }

}

class MessageReader implements Runnable{

    private BufferedReader reader;
    private boolean stop = false;

    public MessageReader(BufferedReader reader){
        this.reader = reader;
    }

    public void stop(){
        stop = true;
    }

    @Override
    public void run() {
        while(!stop){
            try{
                String msg = reader.readLine();
                Client.addMessage(msg);
            }
            catch(Exception e) {
                System.out.println("Error reading message from client");
            }
        }
    }
}
