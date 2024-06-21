package com.example.lukoil;

import static com.example.lukoil.ListData.docActs;
import static com.example.lukoil.ListData.pipeActs;
import static com.example.lukoil.ListData.pumpActs;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lukoil.activity.General;
import com.example.lukoil.activity.doc.DocChange;
import com.example.lukoil.activity.doc.DocCreate;
import com.example.lukoil.activity.doc.DocView;
import com.example.lukoil.activity.pipe.PipeChange;
import com.example.lukoil.activity.pipe.PipeCreate;
import com.example.lukoil.activity.pipe.PipeView;
import com.example.lukoil.activity.pump.PumpChange;
import com.example.lukoil.activity.pump.PumpCreate;
import com.example.lukoil.activity.pump.PumpView;
import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.act.ActDoc;
import com.example.lukoil.entity.act.ActPipe;
import com.example.lukoil.entity.act.ActPump;
import com.example.lukoil.entity.field.ActField;
import com.example.lukoil.entity.field.Field;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GeneraActList extends General {


    public void toViewAct(View v) {
        Dir tag = (Dir) v.getTag();
        startNewActivity(v.getContext(),  tag.getName()+"View", tag.getId());
    }
    public void startNewActivity(Context context, String classSimpleName, int idAct){
        if (classSimpleName.contains("Pipe")){
            if (classSimpleName.contains("View")) startActivityPipeView(context, idAct);
            else if (classSimpleName.contains("Create")) startActivityPipeCreate(context, idAct);
            else startActivityPipeChange(context, idAct);
        }
        else if (classSimpleName.contains("Pump")){
            if (classSimpleName.contains("View")) startActivityPumpView(context, idAct);
            else if (classSimpleName.contains("Create")) startActivityPumpCreate(context, idAct);
            else startActivityPumpChange(context, idAct);
        }
        else{
            if (classSimpleName.contains("View")) startActivityDocView(context, idAct);
            else if (classSimpleName.contains("Create")) startActivityDocCreate(context, idAct);
            else startActivityDocChange(context, idAct);
        }
    }

    private void startActivityDocChange(Context context,  int idAct) {
        Intent newIntent = new Intent(context, DocChange.class);
        newIntent.putExtra("idAct", idAct);
        newIntent.putExtra("nameActivity", getResources().getString(R.string.createDoc));
        startActivity(newIntent);
    }

    private void startActivityDocCreate(Context context,  int idAct) {
        Intent newIntent = new Intent(context, DocCreate.class);
        newIntent.putExtra("idAct", idAct);
        newIntent.putExtra("nameActivity", getResources().getString(R.string.createAct));
        startActivity(newIntent);
    }

    private void startActivityDocView(Context context,  int idAct) {
        Intent newIntent = new Intent(context, DocView.class);
        newIntent.putExtra("idAct", idAct);
        newIntent.putExtra("nameActivity", getResources().getString(R.string.viewDoc));
        startActivity(newIntent);
    }

    private void startActivityPumpChange(Context context,  int idAct) {
        Intent newIntent = new Intent(context, PumpChange.class);
        newIntent.putExtra("idAct", idAct);
        newIntent.putExtra("nameActivity", getResources().getString(R.string.changeAct));
        startActivity(newIntent);
    }

    private void startActivityPumpCreate(Context context,  int idAct) {
        Intent newIntent = new Intent(context, PumpCreate.class);
        newIntent.putExtra("idAct", idAct);
        newIntent.putExtra("nameActivity", getResources().getString(R.string.createAct));
        startActivity(newIntent);
    }

    private void startActivityPumpView(Context context,  int idAct) {
        Intent newIntent = new Intent(context, PumpView.class);
        newIntent.putExtra("idAct", idAct);
        newIntent.putExtra("nameActivity", getResources().getString(R.string.viewAct));
        startActivity(newIntent);
    }

    private void startActivityPipeChange(Context context,  int idAct) {
        Intent newIntent = new Intent(context, PipeChange.class);
        newIntent.putExtra("idAct", idAct);
        newIntent.putExtra("nameActivity", getResources().getString(R.string.changeAct));
        startActivity(newIntent);
    }

    private void startActivityPipeCreate(Context context,  int idAct) {
        Intent newIntent = new Intent(context, PipeCreate.class);
        newIntent.putExtra("idAct", idAct);
        newIntent.putExtra("nameActivity", getResources().getString(R.string.createAct));
        startActivity(newIntent);
    }

    private void startActivityPipeView(Context context,  int idAct) {
        Intent newIntent = new Intent(context, PipeView.class);
        newIntent.putExtra("idAct", idAct);
        newIntent.putExtra("nameActivity", getResources().getString(R.string.viewAct));
        startActivity(newIntent);
    }


    public static Date trim(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    protected void drawNewAct(ActField fieldAct) {
        final View view = getLayoutInflater().inflate(fieldAct.getIdView(), null);
        TextView textName = view.findViewById(fieldAct.getIdTextView());
        TextView textSecond = view.findViewById(fieldAct.getIdSecondTextView());
        ImageView status = view.findViewById(fieldAct.getIdStatusLayout());
        view.setTag(fieldAct.getTag());
        textName.setText(String.format("%s", fieldAct.getTextForTextView()));
        textSecond.setText(fieldAct.getTextForSecondTextView());
        if (fieldAct.getIdStatusAct() == ACT_STATUS_READY) status.setImageResource(R.drawable.job_green);
        WORK_PLACE_ELEMENTS.add(view);
        WORKPLACE.addView(view);
    }
    protected boolean isDatesNotEquivalent(Date dateTimeStop, Date dateStopLastAct) {
        return (!(trim(dateTimeStop).equals(trim(dateStopLastAct))));
    }
    protected void drawNewFieldForAct(Field field) {
        final View view = getLayoutInflater().inflate(field.getIdView(), null);
        TextView textView = view.findViewById(field.getIdTextView());
        textView.setText(field.getTextForTextView());
        WORK_PLACE_ELEMENTS.add(view);
        WORKPLACE.addView(view);
    }

}
