package com.example.lukoil;

import static com.example.lukoil.GeneralClass.Sevent_types;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatDelegate;

import com.example.lukoil.entity.Event_date_time;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Event_date_time_create extends Create_change_view_act {
    EditText editDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_date_time_create);
        idForm = 4;

        context = this;
        onStartNotHome(idForm);

        uppTextName.setText("Добавление события");

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
