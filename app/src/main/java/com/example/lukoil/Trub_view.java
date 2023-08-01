package com.example.lukoil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.lukoil.entity.Act_trub;
import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.Employee;
import com.example.lukoil.entity.Event_date_time;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Trub_view extends Create_change_view_act {

    Act_trub act;
    EditText name, diameter, wall, coating, piketach, leak_type, leak_parameter, leak_location, subst, who, area, status;
    TextView events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trub_view);

        Bundle arguments = getIntent().getExtras();
        act = (Act_trub) arguments.getSerializable(Act_trub.class.getSimpleName());
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

        uppTextName.setText("Просмотр акта");


        String textForEvents = "";
        if (act.getWorks() != null) for (Event_date_time wrk : act.getWorks()) textForEvents += wrk.getName(event_types) + ": " + DateToText(wrk.getDate_time()) + "\n";
        events.setText(textForEvents);

        int cnt=0;
        for(Dir dir: trubs){
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
        Intent Trub_change = new Intent(context, Trub_change.class);
        Trub_change.putExtra(Act_trub.class.getSimpleName(), act);
        startActivity(Trub_change);
    }
}
