package com.example.lukoil.activity.pipe;

import static com.example.lukoil.ListData.actEventTypes;
import static com.example.lukoil.ListData.actStatuses;
import static com.example.lukoil.ListData.employees;
import static com.example.lukoil.ListData.pipeCoatingTypes;
import static com.example.lukoil.ListData.pipeLeakTypes;
import static com.example.lukoil.ListData.pipeNames;
import static com.example.lukoil.ListData.pipeSubstances;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lukoil.activity.Activity;
import com.example.lukoil.activity.GeneralCreateChangeViewAct;
import com.example.lukoil.R;
import com.example.lukoil.entity.act.ActPipe;
import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.Employee;
import com.example.lukoil.entity.event.EventDateTime;

import java.util.ArrayList;
import java.util.Objects;

public class PipeView extends GeneralCreateChangeViewAct {

    ActPipe act;
    EditText name, diameter, wall, coating, piketach, leak_type, leak_parameter, leak_location, subst, who, area, status;
    TextView events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getIntent().getExtras();
        act = (ActPipe) Objects.requireNonNull(arguments).getSerializable(ActPipe.class.getSimpleName());

        Activity activity = new Activity(ID_ACTIVITY_PIPE, getApplicationContext(), R.layout.trub_view, R.id.layoutForActs, new ArrayList<View>(), R.id.layout_menu, "Просмотр акта");
        super.onStartList(activity);

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

        name.setText(String.valueOf(act.getIdPipe()));
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
        status.setText(String.valueOf(act.getIdStatus()));

        String textForEvents = "";
        if (act.getWorks() != null) for (EventDateTime wrk : act.getWorks()) textForEvents += wrk.getName(actEventTypes) + ": " + DateToText(wrk.getDateTime()) + "\n";
        events.setText(textForEvents);

        for(Dir dir: pipeNames){
            if(dir.getId() == act.getIdPipe()){
                name.setText(dir.getName());
                break;
            }
        }
        for(Dir dir: pipeCoatingTypes){
            if(dir.getId() == act.getId_type_coating()){
                coating.setText(dir.getName());
                break;
            }
        }
        for(Dir dir: pipeLeakTypes){
            if(dir.getId() == act.getId_leak_type()){
                leak_type.setText(dir.getName());
                break;
            }
        }
        for(Dir dir: pipeSubstances){
            if(dir.getId() == act.getId_substance()){
                subst.setText(dir.getName());
                break;
            }
        }
        for(Employee dir: employees){
            if(dir.getId() == act.getId_who()){
                who.setText(dir.getFIO());
                break;
            }
        }
        for(Dir dir: actStatuses){
            if(dir.getId() == act.getIdStatus()){
                status.setText(dir.getName());
                break;
            }
        }
    }
    public void toChange(View v){
        Intent Trub_change = new Intent(CONTEXT, PipeChange.class);
        Trub_change.putExtra(ActPipe.class.getSimpleName(), act);
        startActivity(Trub_change);
    }
}
