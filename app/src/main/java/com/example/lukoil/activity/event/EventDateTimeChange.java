package com.example.lukoil.activity.event;

import static com.example.lukoil.ListData.actEventTypes;
import static com.example.lukoil.ListData.actEvents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lukoil.activity.Activity;
import com.example.lukoil.R;
import com.example.lukoil.activity.CreateWorksEvents;
import com.example.lukoil.activity.GeneralCreateChangeViewAct;
import com.example.lukoil.entity.event.EventDateTime;

import java.util.ArrayList;
import java.util.Date;

public class EventDateTimeChange extends CreateWorksEvents {

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
        if (actEvents != null) for (EventDateTime wrk: actEvents) {
            view = getLayoutInflater().inflate(R.layout.custom_event_date_time_change, null);
            textDate = view.findViewById(R.id.textDateTime);
            textName = view.findViewById(R.id.textName);
            textName.setText(String.valueOf(wrk.getNameTypeEvent(actEventTypes)));
            Date  nowDate = wrk.getDateTime();
            view.setTag(wrk.getId());
            String date = DateToText(nowDate);
            textDate.setText(String.format("%s, %s", date, FORMAT_FOR_DATE.format(nowDate)));
            WORKPLACE.addView(view);
            WORK_PLACE_ELEMENTS.add(view);
        }

    }
    public void toDelete(View v){
        try {
            WORKPLACE.removeView((LinearLayout)v.getParent());
            WORK_PLACE_ELEMENTS.remove((LinearLayout)v.getParent());
            actEvents.remove((int)v.getTag());
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