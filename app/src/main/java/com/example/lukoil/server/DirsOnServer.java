package com.example.lukoil.server;

import static com.example.lukoil.activity.General.CONTEXT_NOW;

import android.widget.Toast;

import com.example.lukoil.entity.Dictionary;

import java.io.IOException;

public class DirsOnServer extends ServerConnection {
    Dictionary dictionary;

    public Dictionary getDirectory(){
        final Dictionary[] dictionary1 = new Dictionary[1];
        System.out.println("Обновление списков");
        Thread thread = new Thread(() -> {
            tryConnection();
            trySendRequest("GET_ALL_DIRS");
            dictionary1[0] = getDictionary();
        });
        thread.start();
        tryStopMainThread();
        Toast toast = Toast.makeText(CONTEXT_NOW, "Обновление списков успешно завершено", Toast.LENGTH_LONG);
        toast.show();
        return dictionary1[0];
    }

    private Dictionary getDictionary() {
        tryGetAndSerializes();
        tryDeserializes();
        tryWriteResponse();
        return dictionary;
    }

    private void tryWriteResponse() {
        try {
            dictionary = (Dictionary) in.readObject();
        } catch (IOException e) {
            dictionary = new Dictionary();
            System.out.println("Error "+ e);
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            dictionary = new Dictionary();
            System.out.println("Error "+ e);
            throw new RuntimeException(e);
        }
    }
}
