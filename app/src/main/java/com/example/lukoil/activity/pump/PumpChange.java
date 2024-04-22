package com.example.lukoil.activity.pump;

import static com.example.lukoil.ListData.actEventTypes;
import static com.example.lukoil.ListData.actStatuses;
import static com.example.lukoil.ListData.pumpMarks;
import static com.example.lukoil.ListData.pumpPositions;
import static com.example.lukoil.ListData.pumpStopReasons;
import static com.example.lukoil.ListData.pumpWorkTypes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.lukoil.activity.Activity;
import com.example.lukoil.activity.General;
import com.example.lukoil.activity.GeneralCreateChangeViewAct;
import com.example.lukoil.R;
import com.example.lukoil.activity.workPump.WorkPumpChange;
import com.example.lukoil.entity.act.ActPump;
import com.example.lukoil.entity.Dir;
import com.example.lukoil.activity.event.EventDateTimeChange;
import com.example.lukoil.entity.event.EventDateTime;
import com.example.lukoil.entity.remark.Remark;
import com.example.lukoil.entity.work.WorkDoc;
import com.example.lukoil.entity.work.WorkPump;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PumpChange extends GeneralCreateChangeViewAct {

    ActPump act;
    EditText note;
    Spinner name, mark, reason_stop, status;
    TextView events, works;
    protected List<View> workList;
    protected LinearLayout workLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getIntent().getExtras();
        act = (ActPump) arguments.getSerializable(ActPump.class.getSimpleName());

        Activity activity = new Activity(General.ID_ACTIVITY_PUMP, this, R.layout.pump_change, R.id.layoutPhoto, new ArrayList<View>(), R.id.layout_menu, "Измение акта");
        super.onStartList(activity);

        name = findViewById(R.id.name);
        mark = findViewById(R.id.mark);
        reason_stop = findViewById(R.id.reason_stop);
        note = findViewById(R.id.note);
        status = findViewById(R.id.status);
        events = findViewById(R.id.events);
        works = findViewById(R.id.works);

        note.setText(String.valueOf(act.getNote()));

        String textForEvents = "";
        if (act.getEvents() != null) for (EventDateTime wrk : act.getEvents()) textForEvents += wrk.getNameTypeEvent(actEventTypes) + ": " + DateToText(wrk.getDateTime()) + "\n";
        events.setText(textForEvents);

        String textForWorks = "";
        for (WorkPump wrk : act.getWorks_ready()) {
            textForWorks += wrk.getIdTypeWork() + "\n";
        }
        works.setText(textForWorks);

        int cnt=0;
        for(Dir dir: pumpPositions){
            if(dir.getId() == act.getIdPump()){
                name.setSelection(cnt);
                break;
            }
            cnt++;
        }

        cnt=0;
        for(Dir dir: pumpMarks){
            if(dir.getId() == act.getIdMark()){
                mark.setSelection(cnt);
                break;
            }
            cnt++;
        }

        cnt=0;
        for(Dir dir: pumpStopReasons){
            if(dir.getId() == act.getIdReasonStop()){
                reason_stop.setSelection(cnt);
                break;
            }
            cnt++;
        }
        cnt=0;
        for(Dir dir: actStatuses){
            if(dir.getId() == act.getIdStatus()){
                status.setSelection(cnt);
                break;
            }
            cnt++;
        }

        eventDateTimeList = new ArrayList<View>();
        eventDateTimeLayout = findViewById(R.id.layoutEvents);
        drawEvents(act.getEvents(), R.layout.custom_event_date_time_view);

        workList = new ArrayList<>();
        workLayout = findViewById(R.id.layoutWorks);
        drawPumpWorks(act.getWorks_ready(), R.layout.custom_remark_view);

    }

    public void toPlus(View v) {
        //Intent Plus = new Intent(v.getContext(), PumpCreate.class);
        //startActivity(Plus);
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

    public void toWorksPumpChange(View v) { drawPumpWorks(act.getWorks_ready(), R.layout.custom_remark_change); }
    public void toEventChange(View v) { drawEvents(act.getEvents(), R.layout.custom_event_date_time_change); }
    @Override
    public void toSave(View v) {
        getIds();
        act.setNote(note.getText().toString());
        updateAct();
        finish();
    }
    private void getIds() {
        for (Dir dir: pumpPositions) if(dir.getName() == name.getSelectedItem()) act.setId_pump(dir.getId());
        for (Dir dir: pumpMarks) if(dir.getName().equals(mark.getSelectedItem().toString())) act.setId_mark(dir.getId());
        for (Dir dir: pumpStopReasons) if(dir.getName().equals(reason_stop.getSelectedItem().toString())) act.setId_reason_stop(dir.getId());
        for (Dir dir: actStatuses) if(dir.getName().equals(status.getSelectedItem().toString())) act.setIdStatus(dir.getId());
    }

    private void drawPumpWorks(ArrayList<WorkPump> wrk, int idLayout) {
        workLayout.removeAllViews();
        workList.clear();
        int cnt = 0;
        if (wrk != null) for (WorkPump w : wrk) {
            View view = getLayoutInflater().inflate(idLayout, null);
            TextView textName = view.findViewById(R.id.textName);
            textName.setText(String.valueOf(w.getNameWorkById(pumpWorkTypes)));
            Button delete = view.findViewById(R.id.toDelete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toWorksPumpChange(v);
                }
            });
            view.setTag(w.getId());
            view.setId(cnt);
            workLayout.addView(view);
            workList.add(view);
            cnt++;
        }
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
