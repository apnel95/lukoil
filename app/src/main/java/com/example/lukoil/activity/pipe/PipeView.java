package com.example.lukoil.activity.pipe;

import static com.example.lukoil.ListData.actStatuses;
import static com.example.lukoil.ListData.employees;
import static com.example.lukoil.ListData.pipeActs;
import static com.example.lukoil.ListData.pipeCoatingTypes;
import static com.example.lukoil.ListData.pipeLeakTypes;
import static com.example.lukoil.ListData.pipeNames;
import static com.example.lukoil.ListData.pipeSubstances;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lukoil.activity.Activity;
import com.example.lukoil.activity.GeneralCreateChangeViewAct;
import com.example.lukoil.R;
import com.example.lukoil.entity.act.ActPipe;

import java.util.ArrayList;
import java.util.Objects;

public class PipeView extends GeneralCreateChangeViewAct {

    int idAct;

    ActPipe act;
    EditText name, diameter, wall, coating, piketach, leak_type, leak_parameter, leak_location, subst, who, area, status;
    TextView events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getIntent().getExtras();
        int cntAct = (Objects.requireNonNull(arguments).getInt("idAct"));

        act = getActPipeForCnt(cntAct);


        Activity activity = new Activity(ID_ACTIVITY_PIPE, getApplicationContext(), R.layout.pipe_view, R.id.layoutForActs, new ArrayList<View>(), R.id.layout_menu, getResources().getString(R.string.viewAct));
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

        updateData(idAct);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateData(idAct);
    }

    protected void updateData(int idAct) {
        act = pipeActs.get(idAct);

        name.setText(String.valueOf(act.getIdPipe()));
        diameter.setText(String.valueOf(act.getDiameter()));
        wall.setText(String.valueOf(act.getWall()));
        coating.setText(String.valueOf(act.getIdTypeCoating()));
        piketach.setText(String.valueOf(act.getPiketash()));
        leak_type.setText(String.valueOf(act.getIdLeakType()));
        leak_parameter.setText(String.valueOf(act.getLeak_parameter()));
        leak_location.setText(String.valueOf(act.getLeak_position()));
        who.setText(String.valueOf(act.getIdWho()));
        leak_location.setText(String.valueOf(act.getLeak_position()));
        area.setText(String.valueOf(act.getSpill_area()));
        subst.setText(String.valueOf(act.getIdSubstance()));
        status.setText(String.valueOf(act.getIdStatus()));

        name.setText(getNameToId(pipeNames, act.getIdPipe()));
        coating.setText(getNameToId(pipeCoatingTypes, act.getIdTypeCoating()));
        leak_type.setText(getNameToId(pipeLeakTypes, act.getIdLeakType()));
        subst.setText(getNameToId(pipeSubstances, act.getIdSubstance()));
        status.setText(getNameToId(actStatuses, act.getIdStatus()));
        who.setText(getNameToIdForEmployee(employees, act.getIdWho()));

        eventDateTimeList = new ArrayList<View>();
        eventDateTimeLayout = findViewById(R.id.layoutEvents);
        drawEvents(act.getEvents(), R.layout.custom_event_date_time_view);
    }

    @Override
    public void toChange(View v){
        Intent pipeChange = new Intent(CONTEXT, PipeChange.class);
        pipeChange.putExtra("idAct", act.getId());
        pipeChange.putExtra("nameActivity",getResources().getString(R.string.changeAct));
        startActivity(pipeChange);
    }

    @Override
    protected void toPlus(View v){
        Intent pipeCreate = new Intent(CONTEXT, PipeChange.class);
        pipeCreate.putExtra("idAct", (new ActPipe()).getId());
        pipeCreate.putExtra("nameActivity", getResources().getString(R.string.createAct));
        startActivity(pipeCreate);
    }
}
