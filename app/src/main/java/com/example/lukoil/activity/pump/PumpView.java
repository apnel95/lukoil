package com.example.lukoil.activity.pump;

import static com.example.lukoil.ListData.actStatuses;
import static com.example.lukoil.ListData.pumpMarks;
import static com.example.lukoil.ListData.pumpPositions;
import static com.example.lukoil.ListData.pumpStopReasons;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

public class PumpView extends GeneralCreateChangeViewAct {

    ActPump act;
    EditText note;
    EditText name, mark, reason_stop, status;
    TextView works, events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getIntent().getExtras();
        act = (ActPump) arguments.getSerializable(ActPump.class.getSimpleName());

        Activity activity = new Activity(General.ID_ACTIVITY_PUMP, this, R.layout.pump_view, R.id.layoutBlock, new ArrayList<View>(), R.id.layout_menu, "Просмотр акта");
        super.onStartList(activity);

        name = findViewById(R.id.name);
        mark = findViewById(R.id.mark);
        reason_stop = findViewById(R.id.reason_stop);
        note = findViewById(R.id.note);
        status = findViewById(R.id.status);
        events = findViewById(R.id.events);
        works = findViewById(R.id.works);

        name.setText(String.valueOf(act.getIdPump()));
        mark.setText(String.valueOf(act.getIdMark()));
        reason_stop.setText(String.valueOf(act.getIdReasonStop()));
        note.setText(String.valueOf(act.getNote()));
        status.setText(String.valueOf(act.getIdStatus()));

        String textForEvents = "";
        for (EventDateTime wrk : act.getEvents()) {
            //textForEvents += wrk.getId_type_event() + ": " + DateToText(wrk.getDate_time()) + "\n";
            textForEvents += wrk.getDateTime() + ": " + wrk.getId_type_event() + "\n";
        }
        events.setText(textForEvents);

        String textForWorks = "";
        for (WorkPump wrk : act.getWorks_ready()) {
            textForWorks += wrk.getIdTypeWork() + "\n";
        }
        works.setText(textForWorks);

        int cnt=0;
        for(Dir dir: pumpPositions){
            if(dir.getId() == act.getIdPump()){
                name.setText(dir.getName());
                break;
            }
            cnt++;
        }

        cnt=0;
        for(Dir dir: pumpMarks){
            if(dir.getId() == act.getIdMark()){
                mark.setText(dir.getName());
                break;
            }
            cnt++;
        }

        cnt=0;
        for(Dir dir: pumpStopReasons){
            if(dir.getId() == act.getIdReasonStop()){
                reason_stop.setText(dir.getName());
                break;
            }
            cnt++;
        }
        cnt=0;
        for(Dir dir: actStatuses){
            if(dir.getId() == act.getIdStatus()){
                status.setText(dir.getName());
                break;
            }
            cnt++;
        }

    }
    public void toChange(View v){
        Intent Pump_change = new Intent(v.getContext(), PumpChange.class);
        Pump_change.putExtra(ActPump.class.getSimpleName(), act);
        startActivity(Pump_change);
    }
}
