package com.example.lukoil.activity.pump;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lukoil.activity.CreateChangeViewAct;
import com.example.lukoil.R;
import com.example.lukoil.entity.act.ActPump;
import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.event.EventDateTime;

public class PumpView extends CreateChangeViewAct {

    ActPump act;
    EditText note;
    EditText name, mark, reason_stop, status;
    TextView works, events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pump_view);

        Bundle arguments = getIntent().getExtras();
        act = (ActPump) arguments.getSerializable(ActPump.class.getSimpleName());
        idForm = 2;
        context = this;

        onStartNotHome(idForm);

        name = findViewById(R.id.name);
        mark = findViewById(R.id.mark);
        reason_stop = findViewById(R.id.reason_stop);
        note = findViewById(R.id.note);
        status = findViewById(R.id.status);
        events = findViewById(R.id.events);
        works = findViewById(R.id.works);

        name.setText(String.valueOf(act.getId_pump()));
        mark.setText(String.valueOf(act.getId_mark()));
        reason_stop.setText(String.valueOf(act.getId_reason_stop()));
        note.setText(String.valueOf(act.getNote()));
        status.setText(String.valueOf(act.getId_status()));

        topTitleActivity.setText("Просмотр акта");

        String textForEvents = "";
        for (EventDateTime wrk : act.getEvents()) {
            //textForEvents += wrk.getId_type_event() + ": " + DateToText(wrk.getDate_time()) + "\n";
            textForEvents += wrk.getDateTime() + ": " + wrk.getId_type_event() + "\n";
        }
        events.setText(textForEvents);

        String textForWorks = "";
        for (Integer wrk : act.getWorks_ready()) {
            textForWorks += wrk + "\n";
        }
        works.setText(textForWorks);

        int cnt=0;
        for(Dir dir: positions){
            if(dir.getId() == act.getId_pump()){
                name.setText(dir.getName());
                break;
            }
            cnt++;
        }

        cnt=0;
        for(Dir dir: marks){
            if(dir.getId() == act.getId_mark()){
                mark.setText(dir.getName());
                break;
            }
            cnt++;
        }

        cnt=0;
        for(Dir dir: reasons_stop_pump){
            if(dir.getId() == act.getId_reason_stop()){
                reason_stop.setText(dir.getName());
                break;
            }
            cnt++;
        }
        cnt=0;
        for(Dir dir: statuses_act){
            if(dir.getId() == act.getId_status()){
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
