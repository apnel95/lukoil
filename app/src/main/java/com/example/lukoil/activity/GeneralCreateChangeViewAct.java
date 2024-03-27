package com.example.lukoil.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.example.lukoil.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;

public class GeneralCreateChangeViewAct extends General {

    protected void onStartList(Activity activity) {
        super.initializationActivity(activity);
    }


    public Calendar dateAndTime=Calendar.getInstance();
    public void toChange(View v){
    }
    public void toLoadPhoto(View v){
        System.out.println("aaaaaaa");
    }
    public void toEventChange(View v){

    }
    public void toNewPhoto(View v) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
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
        LIST_ACT_DOC.remove((LinearLayout)v.getParent());
    }
    public void toSave(View v){

    }

    public void setDate(View v) {

        new DatePickerDialog(v.getContext(), d, dateAndTime.get(Calendar.YEAR), dateAndTime.get(Calendar.MONTH), dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
    }
    public void setTime(View v) {
        new TimePickerDialog(v.getContext(), t, dateAndTime.get(Calendar.HOUR_OF_DAY), dateAndTime.get(Calendar.MINUTE), true).show();
    }
    private void setInitialDateTime() {
//!!!!!!!!!        dateTime.setText(DateUtils.formatDateTime(this, dateAndTime.getTimeInMillis(), DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_TIME));
    }
    TimePickerDialog.OnTimeSetListener t=new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);
            setInitialDateTime();
        }
    };
    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };

}
