package com.example.lukoil.activity;

import static com.example.lukoil.ListData.actEventTypes;
import static com.example.lukoil.ListData.docActs;
import static com.example.lukoil.ListData.pipeActs;
import static com.example.lukoil.ListData.pumpActs;
import static com.example.lukoil.ListData.sActEventTypes;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.lukoil.R;
import com.example.lukoil.entity.act.ActDoc;
import com.example.lukoil.entity.act.ActPipe;
import com.example.lukoil.entity.act.ActPump;
import com.example.lukoil.interfaces.ChangeNameList;
import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.Employee;
import com.example.lukoil.entity.event.EventDateTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class GeneralCreateChangeViewAct extends General implements ChangeNameList {
    protected List<View> eventDateTimeList;
    protected LinearLayout eventDateTimeLayout;

    protected TextView textDate, textName;
    protected Button toDelete, buttonSave;
    protected Calendar date = new GregorianCalendar();

    public Calendar dateAndTime = Calendar.getInstance();


    protected void onStartList(Activity activity) {
        super.initializationActivity(activity);

        buttonSave = findViewById(R.id.buttonSave);
        toDelete = findViewById(R.id.toDelete);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSaveAct(v);
            }
        });
    }

    protected void toSaveAct(View v) {
    }

    public void toChange(View v){
    }

    public void toLoadPhoto(View v){
        System.out.println("aaaaaaa");
//!!!!!!!!!!!!!!!!!!
    }
    public void toEventChange(View v){
    }
    public void toNewPhoto(View v) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        photoPickerIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityForResult(photoPickerIntent, 100);

    }
    public void toDeletePhoto(View v) {
        WORKPLACE.removeView((LinearLayout)v.getParent());
        WORK_PLACE_ELEMENTS.remove((LinearLayout)v.getParent());
    }

    protected void drawEvents(ArrayList<EventDateTime> events, int idLayout) {
        eventDateTimeLayout.removeAllViews();
        eventDateTimeList.clear();
        int cnt = 0;
        if (events != null) for (EventDateTime evnt: events) {
            View view = getLayoutInflater().inflate(idLayout, null);
            TextView textDate = view.findViewById(R.id.textDateTime);
            TextView textName = view.findViewById(R.id.textName);
            textName.setText(String.valueOf(evnt.getNameTypeEvent(actEventTypes)));
            Date nowDate = evnt.getDateTime();
            view.setTag(new Dir(evnt.getId(), "event"));
            view.setId(cnt);
            String date = DateToText(nowDate);
            textDate.setText(String.format("%s, %s", date, FORMAT_FOR_DATE.format(nowDate)));
            eventDateTimeLayout.addView(view);
            eventDateTimeList.add(view);
            cnt++;
        }
    }

    public void setDate(View v) {
        textDate = v.findViewById(R.id.textDateTime);
        setTime(v);
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

    public void setTypeEvent(View v) {
        Log.d("setTypeEvent", "start");

        textName = v.findViewById(R.id.textName);

        CustomDialogForList dialog = new CustomDialogForList();
        Bundle args = new Bundle();

        View vP = (View) v.getParent().getParent();
        args.putStringArrayList("list", sActEventTypes);
        args.putInt("viewId", vP.getId());
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "custom");
    }

    protected void toDeleteView(View v) {
        Log.d("removeView", "start");

        CustomDialogForList dialog = new CustomDialogForList();
        Bundle args = new Bundle();

        View vP = (View) v.getParent();
        args.putInt("idEvent", ((Dir) vP.getTag()).getId());
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "custom");
    }

    protected void setDataToAct() {
    }

    protected void updateAct() {
    }

    protected String getNameToId(ArrayList<Dir> dir, int id) {
        for(Dir dr: dir){
            if(dr.getId() == id){
                return dr.getName();
            }
        }
        return "";
    }

    protected String getNameToIdForEmployee(ArrayList<Employee> dir, int id) {
        for(Employee dr: dir){
            if(dr.getId() == id){
                return dr.getFIO();
            }
        }
        return "";
    }

    protected int getCntToID(ArrayList<Dir> dir, int id) {
        int cnt=0;
        for(Dir dr: dir){
            if(dr.getId() == id){
                return cnt;
            }
            cnt++;
        }
        return 0;
    }

    protected int getCntToIdForEmployee(ArrayList<Employee> emp, int id) {
        int cnt=0;
        for(Employee employee: emp){
            if(employee.getId() == id){
                return cnt;
            }
            cnt++;
        }
        return 0;
    }

    public void removeEventDateView(int vId) {
        eventDateTimeList.remove(findViewById(vId));
        eventDateTimeLayout.removeView(findViewById(vId));
    }

    private void deleteEventToAct() {
    }

    @Override
    public void changeNameEventDateList(int vId, String name) {
        textName = eventDateTimeLayout.findViewById(vId).findViewById(R.id.textName);
        textName.setText(name);
    }

    public ActPipe getActPipeForCnt(int idAct) {
        Log.d("getActPipeForCnt", idAct+"");
        for (ActPipe actPipe: pipeActs) if (actPipe.getId() == idAct) return actPipe;
        return new ActPipe();
    }
    public ActPump getActPumpForCnt(int idAct) {
        for (ActPump actPipe: pumpActs) if (actPipe.getId() == idAct) return actPipe;
        return new ActPump();
    }
    public ActDoc getActDocForCnt(int idAct) {
        for (ActDoc actPipe: docActs) if (actPipe.getId() == idAct) return actPipe;
        return new ActDoc();
    }
}
