package com.example.lukoil.activity.event;

import static com.example.lukoil.ListData.actEvents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lukoil.activity.Activity;
import com.example.lukoil.R;
import com.example.lukoil.activity.GeneralCreateChangeViewAct;
import com.example.lukoil.entity.event.EventDateTime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EventDateTimeChange extends GeneralCreateChangeViewAct {
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Activity activity = new Activity(4, this, R.layout.event_date_time_change, R.id.layoutEvent, new ArrayList<View>(), R.id.layout_menu, "Изменение списка");
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
        if (actEvents != null) for (EventDateTime wrk: actEvents) {
            view = getLayoutInflater().inflate(R.layout.custom_event_date_time_change, null);
            TextView textDate = (TextView) view.findViewById(R.id.textDateTime);
            TextView textName = (TextView) view.findViewById(R.id.textName);
            textName.setText(String.valueOf(wrk.getId_type_event()));
            Date  nowDate = wrk.getDateTime();
            view.setTag((int)wrk.getId());
            String date = DateToText(nowDate);
            SimpleDateFormat formatForDate = new SimpleDateFormat("HH:mm");
            textDate.setText(String.valueOf(date+", "+formatForDate.format(nowDate)));
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
    public void toNewEvent(View v){
        Intent event_create = new Intent(v.getContext(), EventDateTimeCreate.class);
        startActivity(event_create);
    }
    public void toSave(View v){
        finish();
    }
}