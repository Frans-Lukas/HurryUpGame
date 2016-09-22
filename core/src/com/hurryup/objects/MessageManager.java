package com.hurryup.objects;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by frasse on 2016-09-22.
 */
public class MessageManager {
    private static ReentrantLock messageLock = new ReentrantLock();
    private static ArrayList<String> messages = new ArrayList<String>();

    public static void pushMessage(String msg){
        messageLock.lock();
        messages.add(msg);
        messageLock.unlock();
    }

    public static String nextMessage(){
        messageLock.lock();
        if(messages.size() > 0){
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

}
