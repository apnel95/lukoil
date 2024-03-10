package com.example.lukoil.activity.pump;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.lukoil.activity.CreateChangeViewAct;
import com.example.lukoil.R;
import com.example.lukoil.activity.workPump.WorkPumpChange;
import com.example.lukoil.entity.act.ActPump;
import com.example.lukoil.entity.Dir;
import com.example.lukoil.activity.event.EventDateTimeChange;
import com.example.lukoil.entity.event.EventDateTime;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;

public class PumpChange extends CreateChangeViewAct {

    ActPump act;
    EditText note;
    Spinner name, mark, reason_stop, status;
    TextView events, works;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pump_change);
        idForm = 2;

        workplaceElements = new ArrayList<View>();
        workplace = findViewById(R.id.layoutPhoto);

        Bundle arguments = getIntent().getExtras();
        act = (ActPump) arguments.getSerializable(ActPump.class.getSimpleName());
        context = this;

        onStartNotHome(idForm);

        topTitleActivity.setText("Изменение акта");

        name = findViewById(R.id.name);
        mark = findViewById(R.id.mark);
        reason_stop = findViewById(R.id.reason_stop);
        note = findViewById(R.id.note);
        status = findViewById(R.id.status);
        events = findViewById(R.id.events);
        works = findViewById(R.id.works);

        note.setText(String.valueOf(act.getNote()));

        String textForEvents = "";
        if (act.getEvents() != null) for (EventDateTime wrk : act.getEvents()) textForEvents += wrk.getName(event_types) + ": " + DateToText(wrk.getDateTime()) + "\n";
        events.setText(textForEvents);

        String textForWorks = "";
        for (Integer wrk : act.getWorks_ready()) {
            textForWorks += wrk + "\n";
        }
        works.setText(textForWorks);

        int cnt=0;
        for(Dir dir: positions){
            if(dir.getId() == act.getId_pump()){
                name.setSelection(cnt);
                break;
            }
            cnt++;
        }

        cnt=0;
        for(Dir dir: marks){
            if(dir.getId() == act.getId_mark()){
                mark.setSelection(cnt);
                break;
            }
            cnt++;
        }

        cnt=0;
        for(Dir dir: reasons_stop_pump){
            if(dir.getId() == act.getId_reason_stop()){
                reason_stop.setSelection(cnt);
                break;
            }
            cnt++;
        }
        cnt=0;
        for(Dir dir: statuses_act){
            if(dir.getId() == act.getId_status()){
                status.setSelection(cnt);
                break;
            }
            cnt++;
        }

    }

    public void toPlus(View v) {
        Intent Plus = new Intent(v.getContext(), PumpCreate.class);
        startActivity(Plus);
    }

    public void toNewEvent(View v) {
        Intent events_change = new Intent(v.getContext(), EventDateTimeChange.class);
        events_change.putExtra(ArrayList.class.getSimpleName(), act.getEvents());
        startActivity(events_change);
    }
    public void toNewWork(View v) {
        Intent events_change = new Intent(v.getContext(), WorkPumpChange.class);
        events_change.putExtra(ArrayList.class.getSimpleName(), act.getWorks_ready());
        startActivity(events_change);
    }
    @Override
    public void toSave(View v) {
        getIds();
        act.setNote(note.getText().toString());
        updateAct();
        finish();
    }
    private void getIds() {
        for (Dir dir: positions) if(dir.getName() == name.getSelectedItem()) act.setId_pump(dir.getId());
        for (Dir dir: marks) if(dir.getName().equals(mark.getSelectedItem().toString())) act.setId_mark(dir.getId());
        for (Dir dir: reasons_stop_pump) if(dir.getName().equals(reason_stop.getSelectedItem().toString())) act.setId_reason_stop(dir.getId());
        for (Dir dir: statuses_act) if(dir.getName().equals(status.getSelectedItem().toString())) act.setId_status(dir.getId());
    }

    private void updateAct() {
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
            output = new AbstractMap.SimpleEntry<>("UPDATE", act);
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
