package com.example.lukoil.activity.event;

import static com.example.lukoil.ListData.sActEventTypes;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.lukoil.activity.Activity;
import com.example.lukoil.activity.GeneralCreateChangeViewAct;
import com.example.lukoil.R;

import java.util.ArrayList;

public class EventDateTimeCreate extends GeneralCreateChangeViewAct {
    EditText editDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Activity activity = new Activity(4, this, R.layout.event_date_time_create, R.id.layoutBlock, new ArrayList<View>(), R.id.layout_menu, "Добавление события");
        super.onStartList(activity);

        Spinner name = findViewById(R.id.spinner);
        ArrayAdapter<String> nameA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sActEventTypes);
        nameA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        name.setAdapter(nameA);


    }
    public void toSave(View v){
        //!!!!
        finish();
        /*Intent _change = new Intent(Event_date_time_change.this, q.class);
        _change.putExtra(ArrayList.class.getSimpleName(), events);
        startActivity(_change);*/
    }
}
