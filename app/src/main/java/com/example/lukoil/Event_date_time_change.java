package com.example.lukoil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lukoil.entity.Event_date_time;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event_date_time_change extends GeneralClass {
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_date_time_change);
        
        idForm = 4;

        context = this;

        onStartNotHome(idForm);

        allEds = new ArrayList<View>();
        linear = (LinearLayout) findViewById(R.id.layoutEvent);

        uppTextName.setText("Изменение списка");
    }

    @Override
    protected void onStart() {
        super.onStart();
        drawEvents();
    }

    private void drawEvents() {
        linear.removeAllViews();
        allEds.clear();
        int cnt = 0;
        if (this_events != null) for (Event_date_time wrk: this_events) {
            view = getLayoutInflater().inflate(R.layout.custom_event_date_time_change, null);
            TextView textDate = (TextView) view.findViewById(R.id.textDateTime);
            TextView textName = (TextView) view.findViewById(R.id.textName);
            textName.setText(String.valueOf(wrk.getId_type_event()));
            Date  nowDate = wrk.getDate_time();
            view.setTag((int)wrk.getId());
            String date = DateToText(nowDate);
            SimpleDateFormat formatForDate = new SimpleDateFormat("HH:mm");
            textDate.setText(String.valueOf(date+", "+formatForDate.format(nowDate)));
            linear.addView(view);
            allEds.add(view);
            cnt++;
        }

    }
    public void toDelete(View v){
        try {
            linear.removeView((LinearLayout)v.getParent());
            allEds.remove((LinearLayout)v.getParent());
        } catch(IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }
    public void toNewEvent(View v){
        Intent event_create = new Intent(v.getContext(), Event_date_time_create.class);
        startActivity(event_create);
    }
    public void toSave(View v){
        finish();
    }
}