package com.example.lukoil.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.lukoil.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CreateWorksEvents extends GeneralCreateChangeViewAct{

    protected TextView textDate;
    protected TextView textName;
    protected View view;

    protected Calendar date = new GregorianCalendar();

}
