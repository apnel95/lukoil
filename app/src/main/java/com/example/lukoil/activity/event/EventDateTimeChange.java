package com.example.lukoil.activity.event;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lukoil.GeneralClass;
import com.example.lukoil.R;
import com.example.lukoil.entity.event.EventDateTime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EventDateTimeChange extends GeneralClass {
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_date_time_change);
        
        idForm = 4;

        context = this;

        onStartNotHome(idForm);

        workplaceElements = new ArrayList<View>();
        workplace = (LinearLayout) findViewById(R.id.layoutEvent);

        topTitleActivity.setText("Изменение списка");
    }

    @Override
    protected void onStart() {
        super.onStart();
        drawEvents();
    }

    private void drawEvents() {
        workplace.removeAllViews();
        workplaceElements.clear();
        int cnt = 0;
        if (this_events != null) for (EventDateTime wrk: this_events) {
            view = getLayoutInflater().inflate(R.layout.custom_event_date_time_change, null);
            TextView textDate = (TextView) view.findViewById(R.id.textDateTime);
            TextView textName = (TextView) view.findViewById(R.id.textName);
            textName.setText(String.valueOf(wrk.getId_type_event()));
            Date  nowDate = wrk.getDateTime();
            view.setTag((int)wrk.getId());
            String date = DateToText(nowDate);
            SimpleDateFormat formatForDate = new SimpleDateFormat("HH:mm");
            textDate.setText(String.valueOf(date+", "+formatForDate.format(nowDate)));
            workplace.addView(view);
            workplaceElements.add(view);
            cnt++;
        }

    }
    public void toDelete(View v){
        try {
            workplace.removeView((LinearLayout)v.getParent());
            workplaceElements.remove((LinearLayout)v.getParent());
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