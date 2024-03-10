package com.example.lukoil.activity.pipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lukoil.activity.CreateChangeViewAct;
import com.example.lukoil.R;
import com.example.lukoil.entity.act.ActPipe;
import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.Employee;
import com.example.lukoil.entity.event.EventDateTime;

public class PipeView extends CreateChangeViewAct {

    ActPipe act;
    EditText name, diameter, wall, coating, piketach, leak_type, leak_parameter, leak_location, subst, who, area, status;
    TextView events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trub_view);

        Bundle arguments = getIntent().getExtras();
        act = (ActPipe) arguments.getSerializable(ActPipe.class.getSimpleName());
        idForm = 1;
        context = this;

        onStartNotHome(idForm);

        name = findViewById(R.id.name);
        diameter = findViewById(R.id.diameter);
        wall = findViewById(R.id.wall);
        coating = findViewById(R.id.coating);
        piketach = findViewById(R.id.piketach);
        events = findViewById(R.id.events);
        leak_type = findViewById(R.id.leak_type);
        leak_parameter = findViewById(R.id.leak_parameter);
        leak_location = findViewById(R.id.leak_location);
        who = findViewById(R.id.who);
        area = findViewById(R.id.area);
        subst = findViewById(R.id.subst);
        status = findViewById(R.id.status);

        name.setText(String.valueOf(act.getId_trub()));
        diameter.setText(String.valueOf(act.getDiameter()));
        wall.setText(String.valueOf(act.getWall()));
        coating.setText(String.valueOf(act.getId_type_coating()));
        piketach.setText(String.valueOf(act.getPiketash()));
        leak_type.setText(String.valueOf(act.getId_leak_type()));
        leak_parameter.setText(String.valueOf(act.getLeak_parameter()));
        leak_location.setText(String.valueOf(act.getLeak_position()));
        who.setText(String.valueOf(act.getId_who()));
        leak_location.setText(String.valueOf(act.getLeak_position()));
        area.setText(String.valueOf(act.getSpill_area()));
        subst.setText(String.valueOf(act.getId_substance()));
        status.setText(String.valueOf(act.getId_status()));

        topTitleActivity.setText("Просмотр акта");


        String textForEvents = "";
        if (act.getWorks() != null) for (EventDateTime wrk : act.getWorks()) textForEvents += wrk.getName(event_types) + ": " + DateToText(wrk.getDateTime()) + "\n";
        events.setText(textForEvents);

        int cnt=0;
        for(Dir dir: pipes){
            if(dir.getId() == act.getId_trub()){
                name.setText(dir.getName());
                break;
            }
            cnt++;
        }

        cnt=0;
        for(Dir dir: types_coating){
            if(dir.getId() == act.getId_type_coating()){
                coating.setText(dir.getName());
                break;
            }
            cnt++;
        }

        cnt=0;
        for(Dir dir: types_leak){
            if(dir.getId() == act.getId_leak_type()){
                leak_type.setText(dir.getName());
                break;
            }
            cnt++;
        }
        cnt=0;
        for(Dir dir: substances){
            if(dir.getId() == act.getId_substance()){
                subst.setText(dir.getName());
                break;
            }
            cnt++;
        }
        cnt=0;
        for(Employee dir: employees){
            if(dir.getId() == act.getId_who()){
                who.setText(dir.getFIO());
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
        Intent Trub_change = new Intent(context, PipeChange.class);
        Trub_change.putExtra(ActPipe.class.getSimpleName(), act);
        startActivity(Trub_change);
    }
}
