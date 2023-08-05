package com.example.lukoil;

import static com.example.lukoil.GeneralClass.OPEN_APPLICATION_NOW;

import android.util.Log;
import android.widget.Toast;

import com.example.lukoil.entity.DepartmentObject;
import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.Employee;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DirsOnServer extends ServerConnection{
    Dictionary dictionary;

    public Dictionary getDirectory(){
        System.out.println("Обновление списков");
        Thread thread = new Thread(() -> {
            tryConnection();
            trySendRequest("GET_ALL_DIRS");
            getDictionary();
        });
        thread.start();
        tryStopMainThread();
        Toast toast = Toast.makeText(OPEN_APPLICATION_NOW, "Обновление списков успешно завершено", Toast.LENGTH_LONG);
        toast.show();
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
