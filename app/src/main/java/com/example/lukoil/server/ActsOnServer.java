package com.example.lukoil.server;

import com.example.lukoil.entity.act.ActDoc;
import com.example.lukoil.entity.act.ActPipe;
import com.example.lukoil.entity.act.ActPump;

import java.io.IOException;
import java.util.ArrayList;

public class ActsOnServer extends ServerConnection {
    protected ArrayList acts;

    protected ArrayList getActs() {
        return acts;
    }

    protected ArrayList<ActPipe> getPipeActsMin(){
        thread = new Thread(() -> {
            tryConnection();
            trySendRequest("GET_PIPE_ACTS_MIN");
            acts = (ArrayList<ActPipe>) getResponse();
        });
        thread.start();
        tryStopMainThread();
        return acts;
    }

    protected ArrayList<ActPump> getPumpActsMin(){
        thread = new Thread(() -> {
            tryConnection();
            trySendRequest("GET_PUMP_ACTS_MIN");
            acts = (ArrayList<ActPump>) getResponse();
        });
        thread.start();
        tryStopMainThread();
        return acts;
    }

    protected ArrayList<ActDoc> getDocActsMin(){
        thread = new Thread(() -> {
            tryConnection();
            trySendRequest("GET_DOC_ACTS_MIN");
            acts = (ArrayList<ActDoc>) getResponse();
        });
        thread.start();
        tryStopMainThread();
        return acts;
    }
    protected ArrayList getResponse() {
        tryGetAndSerializes();
        tryDeserializes();
        tryWriteResponse();
        return acts;
    }

    protected void tryWriteResponse() {
        try {
            acts = (ArrayList) in.readObject();
        } catch (IOException e) {
            acts = new ArrayList<>();
            System.out.println("Error "+ e);
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            acts = new ArrayList<>();
            System.out.println("Error "+ e);
            throw new RuntimeException(e);
        }
    }
}
