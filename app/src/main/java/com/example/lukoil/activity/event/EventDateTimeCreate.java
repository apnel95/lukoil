package com.example.lukoil.activity.event;

import static com.example.lukoil.ListData.actEventTypes;
import static com.example.lukoil.ListData.actEvents;
import static com.example.lukoil.ListData.sActEventTypes;

import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.lukoil.activity.Activity;
import com.example.lukoil.activity.CreateWorksEvents;
import com.example.lukoil.R;
import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.event.EventDateTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

public class EventDateTimeCreate extends CreateWorksEvents {
    EventDateTime event;
    Spinner name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Activity activity = new Activity(4, this, R.layout.event_date_time_create, R.id.layoutBlock, new ArrayList<View>(), R.id.layout_menu, "Добавление события");
        super.onStartList(activity);

        name = findViewById(R.id.spinner);
        ArrayAdapter<String> nameA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sActEventTypes);
        nameA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        name.setAdapter(nameA);

        textDate = findViewById(R.id.dateTime);
        Calendar calendar = new GregorianCalendar();
        textDate.setText((CharSequence) calendar.getTime());


    }
    public void toSave(View v){
        if (LINE_STATUS ==  ONLINE_STATUS) ;  //Request to dateBase
        else actEvents.add(new EventDateTime(newId_(actEvents), ID_ACT, getIdTypeEvent(name.getSelectedItem().toString(), actEventTypes), date.getTime()));
        //!!!!
        finish();
        /*Intent _change = new Intent(Event_date_time_change.this, q.class);
        _change.putExtra(ArrayList.class.getSimpleName(), events);
        startActivity(_change);*/
    }

    private int getIdTypeEvent(String name, ArrayList<Dir> dir) {
        for (Dir dr: dir){
            if (Objects.equals(dr.getName(), name)){
                return dr.getId();
            }
        }
        return -1;
    }
}
