package com.example.lukoil.server;

import static com.example.lukoil.activity.General.HOST;
import static com.example.lukoil.activity.General.PORT;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.AbstractMap;
import java.util.HashMap;

public class ServerConnection {
    protected ObjectOutputStream objectOutputStream;
    protected Socket clientSocket;
    protected InputStream inputStream;
    protected Thread thread;
    ObjectInputStream in;
    HashMap.Entry<String, Object> output;

    protected void tryStopMainThread() {
        try {
            thread.join();
        } catch (Exception e) {
            System.out.println("Error "+ e);
        }
    }
    protected void trySendRequest(String request) {
        output = new AbstractMap.SimpleEntry<>(request, 0);
        try {
            objectOutputStream.writeObject(output);
        } catch (IOException e) {
            System.out.println("Error "+ e);
            throw new RuntimeException(e);
        }
    }

    protected void tryConnection() {
        try {
            System.out.println("Waiting for connection");
            clientSocket = new Socket(HOST, PORT);
            OutputStream outToServer = clientSocket.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outToServer);
            System.out.println("Client connected to socket");
        } catch (UnknownHostException ex) {
            System.out.println("Error "+ ex);
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            System.out.println("Error "+ ex);
            throw new RuntimeException(ex);
        }
    }

    protected void tryGetAndSerializes() {
        try {
            inputStream = clientSocket.getInputStream();
        } catch (IOException e) {
            System.out.println("Error "+ e);
            throw new RuntimeException(e);
        }
    }

    protected void tryDeserializes() {
        try {
            in = new ObjectInputStream(inputStream);
        } catch (IOException e) {
            System.out.println("Error "+ e);
            throw new RuntimeException(e);
        }
    }
}
