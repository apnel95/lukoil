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


    public void setDate(View v) {

        new DatePickerDialog(v.getContext(), d, dateAndTime.get(Calendar.YEAR), dateAndTime.get(Calendar.MONTH), dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
    }
    public void setTime(View v) {
        new TimePickerDialog(v.getContext(), t, dateAndTime.get(Calendar.HOUR_OF_DAY), dateAndTime.get(Calendar.MINUTE), true).show();
    }
    public void setInitialDateTime() {

        textDate.setText(DateUtils.formatDateTime(this, dateAndTime.getTimeInMillis(), DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_TIME));
    }
    TimePickerDialog.OnTimeSetListener t=new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);
            date = dateAndTime;
            setInitialDateTime();
        }
    };
    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            date = dateAndTime;
            setInitialDateTime();
        }
    };
}
