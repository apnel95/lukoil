package com.example.lukoil.activity.workPump;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lukoil.activity.Activity;
import com.example.lukoil.R;
import com.example.lukoil.activity.GeneralCreateChangeViewAct;

import java.util.ArrayList;

public class WorkPumpChange extends GeneralCreateChangeViewAct {
    View view;
    ArrayList<Integer> works;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remark_change);

        Bundle arguments = getIntent().getExtras();
        works = (ArrayList<Integer>) arguments.getSerializable(ArrayList.class.getSimpleName());

        Activity activity = new Activity(ID_ACTIVITY_EVENT, this, R.layout.remark_change, R.id.layoutEvent, new ArrayList<View>(), R.id.layout_menu, "Изменение списка");
        super.onStartList(activity);
    }

    @Override
    protected void onStart() {
        super.onStart();
        drawEvents();

    }

    private void drawEvents() {
        WORKPLACE.removeAllViews();
        WORK_PLACE_ELEMENTS.clear();
        int cnt = 0;
        for (Integer wrk: works) {
            view = getLayoutInflater().inflate(R.layout.custom_event_date_time_change, null);
            TextView textName = (TextView) view.findViewById(R.id.textName);
            textName.setText(String.valueOf(wrk));
            view.setTag((int)wrk);
            WORKPLACE.addView(view);
            WORK_PLACE_ELEMENTS.add(view);
            cnt++;
        }

    }
    public void toDelete(View v){
        try {
            WORKPLACE.removeView((LinearLayout)v.getParent());
            WORK_PLACE_ELEMENTS.remove((LinearLayout)v.getParent());
        } catch(IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }
    public void toNewWork(View v){
        Intent event_create = new Intent(v.getContext(), WorkPumpCreate.class);
        startActivity(event_create);
    }
    public void toSave(View v){
        finish();
    }
}
