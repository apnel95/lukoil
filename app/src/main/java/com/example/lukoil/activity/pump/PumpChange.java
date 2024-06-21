package com.example.lukoil.activity.pump;

import static com.example.lukoil.ListData.actStatuses;
import static com.example.lukoil.ListData.pumpActs;
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
import com.example.lukoil.entity.act.ActPump;
import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.event.EventDateTime;
import com.example.lukoil.entity.work.WorkPump;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class PumpChange extends GeneralCreateChangeViewAct {

    ActPump act;
    EditText note;
    Spinner name, mark, reason_stop, status;
    protected List<View> workList;
    protected LinearLayout workLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getIntent().getExtras();
        act = (ActPump) Objects.requireNonNull(arguments).getSerializable(ActPump.class.getSimpleName());
        String nameActivity = arguments.getString("nameActivity");

        Activity activity = new Activity(General.ID_ACTIVITY_PUMP, this, R.layout.pump_change, R.id.layoutPhoto, new ArrayList<>(), R.id.layout_menu,  nameActivity);
        super.onStartList(activity);

        name = findViewById(R.id.name);
        mark = findViewById(R.id.mark);
        reason_stop = findViewById(R.id.reason_stop);
        note = findViewById(R.id.note);
        status = findViewById(R.id.status);

        note.setText(String.valueOf(act.getNote()));

        name.setSelection(getCntToID(pumpPositions, act.getIdPump()));
        mark.setSelection(getCntToID(pumpMarks, act.getIdMark()));
        reason_stop.setSelection(getCntToID(pumpStopReasons, act.getIdReasonStop()));
        status.setSelection(getCntToID(actStatuses, act.getIdStatus()));

        eventDateTimeList = new ArrayList<>();
        eventDateTimeLayout = findViewById(R.id.layoutEvents);
        drawEvents(act.getEvents(), R.layout.custom_event_date_time_view);

        workList = new ArrayList<>();
        workLayout = findViewById(R.id.layoutWorks);
        drawPumpWorks(act.getWorksReady(), R.layout.custom_remark_view);

    }

    @Override
    public void toPlus(View v) {
        Intent pumpCreate = new Intent(CONTEXT, PumpChange.class);
        pumpCreate.putExtra(ActPump.class.getSimpleName(), new ActPump());
        pumpCreate.putExtra("nameActivity", getResources().getString(R.string.createAct));
        startActivity(pumpCreate);
    }

    public void toWorksPumpChange(View v) { drawPumpWorks(act.getWorksReady(), R.layout.custom_remark_change); }
    public void toEventChange(View v) { drawEvents(act.getEvents(), R.layout.custom_event_date_time_change); }

    @Override
    protected void setDataToAct() {
        for (Dir dir: pumpPositions) if(dir.getName() == name.getSelectedItem()) act.setId_pump(dir.getId());
        for (Dir dir: pumpMarks) if(dir.getName().equals(mark.getSelectedItem().toString())) act.setId_mark(dir.getId());
        for (Dir dir: pumpStopReasons) if(dir.getName().equals(reason_stop.getSelectedItem().toString())) act.setId_reason_stop(dir.getId());
        for (Dir dir: actStatuses) if(dir.getName().equals(status.getSelectedItem().toString())) act.setIdStatus(dir.getId());
        act.setNote(note.getText().toString());
    }

    public void toSave(View v) {
        setDataToAct();
        if (LINE_STATUS == ONLINE_STATUS) updateAct();
        if (pumpActs.contains(act.getId())) pumpActs.set(pumpActs.indexOf(act.getId()), act);
        else pumpActs.add(act);

        finish();
    }

    /*protected void toDelete(View v){
        if (super.toDeleteView(v));
        Dir tag = new Dir((Dir) v.getTag());
        if (tag.getName().equals("event")) act.getEvents().removeIf(nextEvent -> nextEvent.getId() == tag.getId());
        else act.getWorksReady().removeIf(nextWork -> nextWork.getId() == tag.getId());
        Log.d("removeEvent", "id:"+tag.getId());
    }*/

    protected void drawPumpWorks(ArrayList<WorkPump> wrk, int idLayout) {
        workLayout.removeAllViews();
        workList.clear();
        int cnt = 0;
        if (wrk != null) for (WorkPump w : wrk) {
            View view = getLayoutInflater().inflate(idLayout, null);
            TextView textName = view.findViewById(R.id.textName);
            textName.setText(String.valueOf(w.getNameWorkById(pumpWorkTypes)));
            try {
                Button delete = view.findViewById(R.id.toDelete);
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toWorksPumpChange(v);
                    }
                });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            view.setTag(new Dir(w.getId(), "pumpWork"));
            view.setId(cnt);
            workLayout.addView(view);
            workList.add(view);
            cnt++;
        }
    }
    /*protected void updateAct() {
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
    }*/
}
