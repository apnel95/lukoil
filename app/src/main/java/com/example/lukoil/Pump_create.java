package com.example.lukoil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.lukoil.entity.PumpAct;
import com.example.lukoil.entity.Dir;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;

public class Pump_create extends Create_change_view_act {
    PumpAct act;
    EditText note;
    Spinner name, mark, reason_stop, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        idForm = 12;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pump_create);
        context = this;
        act = new PumpAct();

        workplaceElements = new ArrayList<View>();
        workplace = findViewById(R.id.layoutPhoto);

        onStartNotHome(idForm);

        topTitleActivity.setText("Создание акта");

        name = findViewById(R.id.name);
        mark = findViewById(R.id.mark);
        reason_stop = findViewById(R.id.reason_stop);
        note = findViewById(R.id.note);
        status = findViewById(R.id.status);

        ArrayAdapter<String> nameA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Spositions);
        nameA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        name.setAdapter(nameA);

        ArrayAdapter<String> markA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Smarks);
        markA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mark.setAdapter(markA);

        ArrayAdapter<String> reason_stopA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Sreasons_stop_pump);
        reason_stopA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reason_stop.setAdapter(reason_stopA);

        ArrayAdapter<String> statusA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Sstatuses_act);
        statusA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(statusA);
    }
    @Override
    public void toPlus(View v) {
        finish();
    }

    @Override
    public void toSave(View v){
        getIds();
        act.setNote(note.getText().toString());
        getNewActPump();
        finish();
    }
    private void getIds() {
        for (Dir dir: positions) if(dir.getName() == name.getSelectedItem()) act.setId_pump(dir.getId());
        for (Dir dir: marks) if(dir.getName().equals(mark.getSelectedItem().toString())) act.setId_mark(dir.getId());
        for (Dir dir: reasons_stop_pump) if(dir.getName().equals(reason_stop.getSelectedItem().toString())) act.setId_reason_stop(dir.getId());
        for (Dir dir: statuses_act) if(dir.getName().equals(status.getSelectedItem().toString())) act.setId_status(dir.getId());
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

                upClientSocket = new Socket(HOST, upPORT);
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
    }
}
