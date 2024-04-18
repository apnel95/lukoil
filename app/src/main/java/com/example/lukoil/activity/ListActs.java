package com.example.lukoil.activity;

import static com.example.lukoil.GeneraActList.trim;
import static com.example.lukoil.ListData.pipeNames;
import static com.example.lukoil.ListData.pumpMarks;
import static com.example.lukoil.ListData.pumpPositions;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;

import androidx.core.content.ContextCompat;

import com.example.lukoil.GeneraActList;
import com.example.lukoil.R;
import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.act.ActPump;
import com.example.lukoil.entity.act.ActPipe;
import com.example.lukoil.entity.comparation.ActPumpComparatot;
import com.example.lukoil.entity.comparation.ActPipeComparatot;
import com.example.lukoil.entity.field.ActField;
import com.example.lukoil.entity.field.Field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class ListActs extends GeneraActList {

    public int statusNow = 0;
    public int timeNow = 0;
    public Button bToday;
    Button bWeek;
    Button bMonth;
    Button bAll;
    public Button bJob;
    Button bReady;
    Button bAll1;
    View viewTimeStatus;
    public static int TODAY = 0, WEEK = 1, MONTH = 2, ALL_DAYS = 3;
    public static int IN_WORK = 0, READY = 1, ALL_TYPE_STATUS = 2;


    protected void onStartList(Activity activity) {
        super.initializationActivity(activity);

        viewTimeStatus = findViewById(R.id.timeStatus);

        bToday = viewTimeStatus.findViewById(R.id.buttonToday);
        bWeek =viewTimeStatus.findViewById(R.id.buttonWeek);
        bMonth = viewTimeStatus.findViewById(R.id.buttonMonth);
        bAll = viewTimeStatus.findViewById(R.id.buttonAll);
        bJob = viewTimeStatus.findViewById(R.id.buttonJob);
        bReady =  viewTimeStatus.findViewById(R.id.buttonReady);
        bAll1 = viewTimeStatus.findViewById(R.id.buttonAll1);
    }

    public void toToday(View v){
        if (timeNow == TODAY){return;}
        else{
            changeStyleButton(bToday, ContextCompat.getColor(CONTEXT, R.color.white), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1_click));
            if (timeNow == WEEK) changeStyleButton(bWeek, ContextCompat.getColor(CONTEXT, R.color.black), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1));
            else if (timeNow == MONTH) changeStyleButton(bMonth, ContextCompat.getColor(CONTEXT, R.color.black), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1));
            else changeStyleButton(bAll, ContextCompat.getColor(CONTEXT, R.color.black), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1));
            timeNow = TODAY;
            updateList();
        }
        return;
    }

    public void toWeek(View v){
        if (timeNow == WEEK){return;}
        else{
            changeStyleButton(bWeek, ContextCompat.getColor(CONTEXT, R.color.white), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1_click));
            if (timeNow == TODAY) changeStyleButton(bToday, ContextCompat.getColor(CONTEXT, R.color.black), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1));
            else if (timeNow == MONTH) changeStyleButton(bMonth, ContextCompat.getColor(CONTEXT, R.color.black), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1));
            else changeStyleButton(bAll, ContextCompat.getColor(CONTEXT, R.color.black), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1));
            timeNow = WEEK;
            updateList();
        }
        return;
    }

    public void toMonth(View v){
        if (timeNow == MONTH){return;}
        else{
            changeStyleButton(bMonth, ContextCompat.getColor(CONTEXT, R.color.white), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1_click));
            if (timeNow == WEEK) changeStyleButton(bWeek, ContextCompat.getColor(CONTEXT, R.color.black), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1));
            else if (timeNow == TODAY) changeStyleButton(bToday, ContextCompat.getColor(CONTEXT, R.color.black), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1));
            else changeStyleButton(bAll, ContextCompat.getColor(CONTEXT, R.color.black), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1));
            timeNow = MONTH;
            updateList();
        }
        return;
    }

    public void toAll(View v){
        if (timeNow == ALL_DAYS){return;}
        else{
            changeStyleButton(bAll, ContextCompat.getColor(CONTEXT, R.color.white), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1_click));
            if (timeNow == WEEK) changeStyleButton(bWeek, ContextCompat.getColor(CONTEXT, R.color.black), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1));
            else if (timeNow == MONTH) changeStyleButton(bMonth, ContextCompat.getColor(CONTEXT, R.color.black), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1));
            else changeStyleButton(bToday, ContextCompat.getColor(CONTEXT, R.color.black), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1));
            timeNow = ALL_DAYS;
            updateList();
        }
        return;
    }
    public void toJob(View v){
        if (statusNow == IN_WORK){return;}
        else{
            changeStyleButton(bJob, ContextCompat.getColor(CONTEXT, R.color.white), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1_click));
            if (statusNow == READY) changeStyleButton(bReady, ContextCompat.getColor(CONTEXT, R.color.black), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1));
            else changeStyleButton(bAll1, ContextCompat.getColor(CONTEXT, R.color.black), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1));
            statusNow = IN_WORK;
            updateList();
        }
        return;
    }
    public void toReady(View v){
        if (statusNow == READY){return;}
        else{
            changeStyleButton(bReady, ContextCompat.getColor(CONTEXT, R.color.white), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1_click));
            if (statusNow == IN_WORK) changeStyleButton(bJob, ContextCompat.getColor(CONTEXT, R.color.black), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1));
            else changeStyleButton(bAll1, ContextCompat.getColor(CONTEXT, R.color.black), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1));
            statusNow = READY;
            updateList();
        }
        return;
    }

    public void toAll1(View v){
        if (statusNow == ALL_TYPE_STATUS){return;}
        else{
            changeStyleButton(bAll1, ContextCompat.getColor(v.getContext(), R.color.white), ContextCompat.getDrawable(v.getContext(), R.drawable.custom_button_1_click));
            if (statusNow == READY) changeStyleButton(bReady, ContextCompat.getColor(v.getContext(), R.color.black), ContextCompat.getDrawable(v.getContext(), R.drawable.custom_button_1));
            else changeStyleButton(bJob, ContextCompat.getColor(v.getContext(), R.color.black), ContextCompat.getDrawable(v.getContext(), R.drawable.custom_button_1));
            statusNow = ALL_TYPE_STATUS;
            updateList();
        }
        return;
    }
    public void changeStyleButton(Button button, int colorText, Drawable colorBack){
        button.setTextColor(colorText);
        button.setBackground(colorBack);
    }
    public void updateList() {
        WORKPLACE.removeAllViews();
        WORK_PLACE_ELEMENTS.clear();
    }

    public void drawActs(ArrayList<ActPipe> acts){
        for (ActPipe act:acts) if(act.getDateTimeStop() == null) act.setDateTimeStop(new Date());
        Collections.sort(acts, new ActPipeComparatot());
        Date nowDate = new Date(0,0,1);
        int cnt = 0;
        Date dateStopInLastAct = new Date(1);
        for (ActPipe act: acts) {
            if ((statusNow == ALL_TYPE_STATUS || statusNow == ((act.getIdStatus() == ACT_STATUS_JOB)?IN_WORK:READY) || statusNow == ((act.getIdStatus() == ACT_STATUS_READY)?READY:IN_WORK)) && ((timeNow == ALL_DAYS) || ((timeNow == TODAY) && ((long) ((trim(new Date())).getTime() - trim(act.getDateTimeStop()).getTime())<86400000)) || ((timeNow == WEEK) && ((long)((trim(new Date())).getTime() - trim(act.getDateTimeStop()).getTime())<86400000*7))||((timeNow == MONTH) && ((long)((trim(new Date())).getTime() - trim(act.getDateTimeStop()).getTime())<2678400000L)))) {
                if (isDatesNotEquivalent(act.getDateTimeStop(), dateStopInLastAct)) {
                    drawNewFieldForAct(new Field(R.layout.custom_block_date, R.id.dateText, DateToText(act.getDateTimeStop())));
                }
                drawNewAct(new ActField(R.layout.custom_block_name, R.id.textName, act.getName(pipeNames), R.id.textTime, FORMAT_FOR_DATE.format(act.getDateTimeStop()), R.id.status, act.getIdStatus(), new Dir(act.getId(), "Pipe")));
                dateStopInLastAct = act.getDateTimeStop();
            }
        }
    }
    public void drawActs(ArrayList<ActPump> acts, int i){
        for (ActPump act:acts) if(act.getDateTimeStop() == null) act.setDateTimeStop(new Date());
        Collections.sort(acts, new ActPumpComparatot());
        for (ActPump act: acts) {
            if ((statusNow == ALL_TYPE_STATUS || statusNow == ((act.getIdStatus() == ACT_STATUS_JOB)?IN_WORK:READY) || statusNow == ((act.getIdStatus() == ACT_STATUS_READY)?READY:IN_WORK)) && ((timeNow==ALL_DAYS)||((timeNow == TODAY) && ((long) ((trim(new Date())).getTime() - trim(act.getDateTimeStop()).getTime())<86400000)) || ((timeNow == WEEK) && ((long)((trim(new Date())).getTime() - trim(act.getDateTimeStop()).getTime())<86400000*7))||((timeNow == MONTH) && ((long)((trim(new Date())).getTime() - trim(act.getDateTimeStop()).getTime())<2678400000L)))) {
                Date dateStopInLastAct = act.getDateTimeStop();
                if (isDatesNotEquivalent(act.getDateTimeStop(), dateStopInLastAct)) {
                    drawNewFieldForAct(new Field(R.layout.custom_block_date, R.id.dateText, DateToText(act.getDateTimeStop())));
                }
                drawNewAct(new ActField(R.layout.custom_block_name, R.id.textName, act.getName(pumpPositions) +", "+ act.getName(pumpMarks), R.id.textTime, FORMAT_FOR_DATE.format(act.getDateTimeStop()), R.id.status, act.getIdStatus(), new Dir(act.getId(), "Pump")));
            }
        }
    }
}
