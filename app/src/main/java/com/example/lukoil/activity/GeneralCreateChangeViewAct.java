package com.example.lukoil.activity;

import static com.example.lukoil.ListData.actEventTypes;
import static com.example.lukoil.ListData.sActEventTypes;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.lukoil.R;
import com.example.lukoil.Removable;
import com.example.lukoil.entity.event.EventDateTime;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class GeneralCreateChangeViewAct extends General implements Removable {


    protected void onStartList(Activity activity) {
        super.initializationActivity(activity);
    }

    protected List<View> eventDateTimeList;
    protected LinearLayout eventDateTimeLayout;

    protected TextView textDate;
    protected TextView textName;
    protected Button toDelete;
    protected Calendar date = new GregorianCalendar();


    public Calendar dateAndTime=Calendar.getInstance();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode) {
            case 100:
                if(resultCode == RESULT_OK){
                    try {
                        final Uri imageUri = imageReturnedIntent.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        final View view1 = getLayoutInflater().inflate(R.layout.custom_image, null);
                        ImageView img = view1.findViewById(R.id.img);
                        WORK_PLACE_ELEMENTS.add(view1);
                        WORKPLACE.addView(view1);
                        img.setImageBitmap(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        }}
    public void toDeletePhoto(View v) {
        WORKPLACE.removeView((LinearLayout)v.getParent());
        WORK_PLACE_ELEMENTS.remove((LinearLayout)v.getParent());
    }
    public void toSave(View v){
    }

    protected void drawEvents(ArrayList<EventDateTime> events, int idLayout) {
        eventDateTimeLayout.removeAllViews();
        eventDateTimeList.clear();
        int cnt = 0;
        if (events != null) for (EventDateTime wrk: events) {
            View view = getLayoutInflater().inflate(idLayout, null);
            TextView textDate = view.findViewById(R.id.textDateTime);
            TextView textName = view.findViewById(R.id.textName);
            textName.setText(String.valueOf(wrk.getNameTypeEvent(actEventTypes)));
            Date nowDate = wrk.getDateTime();
            view.setTag(wrk.getId());
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
        textName = v.findViewById(R.id.textName);

        CustomDialog dialog = new CustomDialog();

        Bundle args = new Bundle();
        args.putStringArrayList("list", sActEventTypes);

        View vP = (View) v.getParent().getParent();
        args.putInt("viewId", vP.getId());
        args.putString("typeDialog", "setTypeEvent");
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "custom");
    }

    public void toDelete(View v) {
        toDelete = v.findViewById(R.id.toDelete);
        CustomDialog dialog = new CustomDialog();
        Bundle args = new Bundle();
        View vP = (View) v.getParent();
        args.putInt("viewId", vP.getId());
        args.putString("typeDialog", "toDelete");
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "custom");
    }
    @Override
    public void removeEventDateView(int vId) {
        System.out.println(vId);
        eventDateTimeList.remove(findViewById(vId));
        eventDateTimeLayout.removeView(findViewById(vId));

    }

    @Override
    public void changeNameEventDateList(int vId, String name) {
        textName = eventDateTimeLayout.findViewById(vId).findViewById(R.id.textName);
        textName.setText(name);
    }
}
