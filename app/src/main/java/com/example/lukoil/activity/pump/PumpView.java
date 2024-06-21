package com.example.lukoil.activity.pump;

import static com.example.lukoil.ListData.actStatuses;
import static com.example.lukoil.ListData.pumpMarks;
import static com.example.lukoil.ListData.pumpPositions;
import static com.example.lukoil.ListData.pumpStopReasons;
import static com.example.lukoil.ListData.pumpWorkTypes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lukoil.activity.Activity;
import com.example.lukoil.activity.General;
import com.example.lukoil.activity.GeneralCreateChangeViewAct;
import com.example.lukoil.R;
import com.example.lukoil.activity.pipe.PipeChange;
import com.example.lukoil.entity.act.ActPipe;
import com.example.lukoil.entity.act.ActPump;
import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.event.EventDateTime;
import com.example.lukoil.entity.work.WorkPump;

import java.util.ArrayList;
import java.util.List;

public class PumpView extends GeneralCreateChangeViewAct {

    ActPump act;
    EditText note;
    EditText name, mark, reason_stop, status;
    protected List<View> workList;
    protected LinearLayout workLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getIntent().getExtras();
        act = (ActPump) arguments.getSerializable(ActPump.class.getSimpleName());

        Activity activity = new Activity(General.ID_ACTIVITY_PUMP, this, R.layout.pump_view, R.id.layoutBlock, new ArrayList<View>(), R.id.layout_menu, getResources().getString(R.string.viewAct));
        super.onStartList(activity);

        name = findViewById(R.id.name);
        mark = findViewById(R.id.mark);
        reason_stop = findViewById(R.id.reason_stop);
        note = findViewById(R.id.note);
        status = findViewById(R.id.status);

        name.setText(getNameToId(pumpPositions, act.getIdPump()));
        mark.setText(getNameToId(pumpMarks, act.getIdMark()));
        reason_stop.setText(getNameToId(pumpStopReasons, act.getIdReasonStop()));
        status.setText(getNameToId(actStatuses, act.getIdStatus()));
        note.setText(String.valueOf(act.getNote()));

        eventDateTimeList = new ArrayList<>();
        eventDateTimeLayout = findViewById(R.id.layoutEvents);
        drawEvents(act.getEvents(), R.layout.custom_event_date_time_view);

        workList = new ArrayList<>();
        workLayout = findViewById(R.id.layoutWorks);
        drawPumpWorks(act.getWorksReady(), R.layout.custom_remark_view);
    }
    @Override
    public void toChange(View v){
        Intent pumpChange = new Intent(v.getContext(), PumpChange.class);
        pumpChange.putExtra(ActPump.class.getSimpleName(), act);
        pumpChange.putExtra("nameActivity", getResources().getString(R.string.changeAct));
        startActivity(pumpChange);
    }

    @Override
    public void toPlus(View v) {
        Intent pumpCreate = new Intent(CONTEXT, PumpChange.class);
        pumpCreate.putExtra(ActPump.class.getSimpleName(), new ActPump());
        pumpCreate.putExtra("nameActivity", getResources().getString(R.string.createAct));
        startActivity(pumpCreate);
    }

    protected void drawPumpWorks(ArrayList<WorkPump> wrk, int idLayout) {
        workLayout.removeAllViews();
        workList.clear();
        int cnt = 0;
        if (wrk != null) for (WorkPump w : wrk) {
            View view = getLayoutInflater().inflate(idLayout, null);
            TextView textName = view.findViewById(R.id.textName);
            textName.setText(String.valueOf(w.getNameWorkById(pumpWorkTypes)));
            view.setTag(new Dir(w.getId(), "pumpWork"));
            view.setId(cnt);
            workLayout.addView(view);
            workList.add(view);
            cnt++;
        }
    }
}
