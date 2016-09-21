package com.hurryup.game;

import java.io.BufferedReader;
import java.net.Socket;

/**
 * Created by Klas on 2016-09-21.
 */
public class Client {
    private Socket socket;
    private BufferedReader reader;

    public Client(Socket s, BufferedReader reader)
    {
        socket = s;
        this.reader = reader;
    }

    public Socket getSocket(){
        return socket;
    }
    public BufferedReader(){
        return reader;
    }
}
