package com.example.lukoil.activity.pump;

import static com.example.lukoil.ListData.actStatuses;
import static com.example.lukoil.ListData.pumpMarks;
import static com.example.lukoil.ListData.pumpPositions;
import static com.example.lukoil.ListData.pumpStopReasons;
import static com.example.lukoil.ListData.sActStatuses;
import static com.example.lukoil.ListData.sPumpMarks;
import static com.example.lukoil.ListData.sPumpPositions;
import static com.example.lukoil.ListData.sPumpStopReasons;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.lukoil.activity.Activity;
import com.example.lukoil.activity.General;
import com.example.lukoil.activity.GeneralCreateChangeViewAct;
import com.example.lukoil.R;
import com.example.lukoil.entity.act.ActPump;
import com.example.lukoil.entity.Dir;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;

public class PumpCreate extends GeneralCreateChangeViewAct {
    ActPump act;
    EditText note;
    Spinner name, mark, reason_stop, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        act = new ActPump();

        Activity activity = new Activity(General.ID_ACTIVITY_PUMP_PLUS, this, R.layout.pump_create, R.id.layoutPhoto, new ArrayList<View>(), R.id.layout_menu, "Создание акта");
        super.onStartList(activity);

        name = findViewById(R.id.name);
        mark = findViewById(R.id.mark);
        reason_stop = findViewById(R.id.reason_stop);
        note = findViewById(R.id.note);
        status = findViewById(R.id.status);

        ArrayAdapter<String> nameA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sPumpPositions);
        nameA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        name.setAdapter(nameA);

        ArrayAdapter<String> markA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sPumpMarks);
        markA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mark.setAdapter(markA);

        ArrayAdapter<String> reason_stopA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sPumpStopReasons);
        reason_stopA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reason_stop.setAdapter(reason_stopA);

        ArrayAdapter<String> statusA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sActStatuses);
        statusA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(statusA);
    }
    /*protected void setDataToAct() {
        for (Dir dir: pumpPositions) if(dir.getName() == name.getSelectedItem()) act.setId_pump(dir.getId());
        for (Dir dir: pumpMarks) if(dir.getName().equals(mark.getSelectedItem().toString())) act.setId_mark(dir.getId());
        for (Dir dir: pumpStopReasons) if(dir.getName().equals(reason_stop.getSelectedItem().toString())) act.setId_reason_stop(dir.getId());
        for (Dir dir: actStatuses) if(dir.getName().equals(status.getSelectedItem().toString())) act.setIdStatus(dir.getId());
    }
    private void getNewActPump() {
        Thread thread = new Thread(() -> {
            System.out.println("Waiting for connection");
            ObjectOutputStream outgetboard;
            Socket clientSocket, upClientSocket;
            try {
                clientSocket = new Socket(HOST, PORT);
                OutputStream outToServer = clientSocket.getOutputStream();
                outgetboard = new ObjectOutputStream(outToServer);

                upClientSocket = new Socket(HOST, UP_PORT);
                OutputStream outToUpdateServer = upClientSocket.getOutputStream();
                ObjectOutputStream outUpdate = new ObjectOutputStream(outToUpdateServer);

                System.out.println("Client connected to socket");
                Runtime.getRuntime().addShutdownHook(new Thread( () -> {
                    try {
                        HashMap.Entry<String, Object> output2 = new AbstractMap.SimpleEntry<>("QUIT", null);
                        outgetboard.writeObject(output2);
                        clientSocket.close();
                        outUpdate.writeObject(output2);
                        upClientSocket.close();
                        System.out.println("Closing the connection");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }));
            } catch (UnknownHostException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            HashMap.Entry<String, Object> output;
            output = new AbstractMap.SimpleEntry<>("INSERT", act);
            try {
                outgetboard.writeObject(output);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            Log.d("Clown", e.toString());
        }
    }*/
}
