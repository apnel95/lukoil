package com.example.lukoil.activity.event;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.lukoil.activity.CreateChangeViewAct;
import com.example.lukoil.R;

public class EventDateTimeCreate extends CreateChangeViewAct {
    EditText editDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_date_time_create);
        idForm = 4;

        context = this;
        onStartNotHome(idForm);

        topTitleActivity.setText("Добавление события");

        Spinner name = findViewById(R.id.spinner);
        ArrayAdapter<String> nameA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Sevent_types);
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
