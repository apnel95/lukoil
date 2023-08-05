package com.example.lukoil;

import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.DocAct;
import com.example.lukoil.entity.PipeAct;
import com.example.lukoil.entity.PumpAct;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ActsOnServer extends ServerConnection{
    protected ArrayList acts;

    protected ArrayList getActs() {
        return acts;
    }

    protected ArrayList<PipeAct> getPipeActsMin(){
        thread = new Thread(() -> {
            tryConnection();
            trySendRequest("GET_PIPE_ACTS_MIN");
            acts = (ArrayList<PipeAct>) getResponse();
        });
        thread.start();
        tryStopMainThread();
        return acts;
    }

    protected ArrayList<PumpAct> getPumpActsMin(){
        thread = new Thread(() -> {
            tryConnection();
            trySendRequest("GET_PUMP_ACTS_MIN");
            acts = (ArrayList<PumpAct>) getResponse();
        });
        thread.start();
        tryStopMainThread();
        return acts;
    }

    protected ArrayList<DocAct> getDocActsMin(){
        thread = new Thread(() -> {
            tryConnection();
            trySendRequest("GET_DOC_ACTS_MIN");
            acts = (ArrayList<DocAct>) getResponse();
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
