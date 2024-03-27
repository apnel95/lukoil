package com.example.lukoil.activity;

import static com.example.lukoil.GeneraActList.trim;
import static com.example.lukoil.ListData.pipeNames;
import static com.example.lukoil.ListData.pumpPositions;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.lukoil.R;
import com.example.lukoil.entity.act.ActPump;
import com.example.lukoil.entity.act.ActPipe;
import com.example.lukoil.entity.comparation.ActPumpComparatot;
import com.example.lukoil.entity.comparation.ActPipeComparatot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class ListActs extends General {

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

    protected void onStartList(Activity activity) {
        super.initializationActivity(activity);

        viewTimeStatus = findViewById(R.id.timeStatus);

        bToday = (Button) viewTimeStatus.findViewById(R.id.buttonToday);
        bWeek = (Button) viewTimeStatus.findViewById(R.id.buttonWeek);
        bMonth = (Button) viewTimeStatus.findViewById(R.id.buttonMonth);
        bAll = (Button) viewTimeStatus.findViewById(R.id.buttonAll);
        bJob = (Button) viewTimeStatus.findViewById(R.id.buttonJob);
        bReady = (Button) viewTimeStatus.findViewById(R.id.buttonReady);
        bAll1 = (Button) viewTimeStatus.findViewById(R.id.buttonAll1);
    }

    public void toToday(View v){
        if (timeNow == 0){return;}
        else{
            changeStyleButton(bToday, ContextCompat.getColor(CONTEXT, R.color.white), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1_click));
            if (timeNow == 1) changeStyleButton(bWeek, ContextCompat.getColor(CONTEXT, R.color.black), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1));
            else if (timeNow == 2) changeStyleButton(bMonth, ContextCompat.getColor(CONTEXT, R.color.black), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1));
            else changeStyleButton(bAll, ContextCompat.getColor(CONTEXT, R.color.black), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1));
            timeNow = 0;
            updateList();
        }
        return;
    }

    public void toWeek(View v){
        if (timeNow == 1){return;}
        else{
            changeStyleButton(bWeek, ContextCompat.getColor(CONTEXT, R.color.white), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1_click));
            if (timeNow == 0) changeStyleButton(bToday, ContextCompat.getColor(CONTEXT, R.color.black), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1));
            else if (timeNow == 2) changeStyleButton(bMonth, ContextCompat.getColor(CONTEXT, R.color.black), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1));
            else changeStyleButton(bAll, ContextCompat.getColor(CONTEXT, R.color.black), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1));
            timeNow = 1;
            updateList();
        }
        return;
    }

    public void toMonth(View v){
        if (timeNow == 2){return;}
        else{
            changeStyleButton(bMonth, ContextCompat.getColor(CONTEXT, R.color.white), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1_click));
            if (timeNow == 1) changeStyleButton(bWeek, ContextCompat.getColor(CONTEXT, R.color.black), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1));
            else if (timeNow == 0) changeStyleButton(bToday, ContextCompat.getColor(CONTEXT, R.color.black), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1));
            else changeStyleButton(bAll, ContextCompat.getColor(CONTEXT, R.color.black), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1));
            timeNow = 2;
            updateList();
        }
        return;
    }

    public void toAll(View v){
        if (timeNow == 3){return;}
        else{
            changeStyleButton(bAll, ContextCompat.getColor(CONTEXT, R.color.white), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1_click));
            if (timeNow == 1) changeStyleButton(bWeek, ContextCompat.getColor(CONTEXT, R.color.black), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1));
            else if (timeNow == 2) changeStyleButton(bMonth, ContextCompat.getColor(CONTEXT, R.color.black), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1));
            else changeStyleButton(bToday, ContextCompat.getColor(CONTEXT, R.color.black), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1));
            timeNow = 3;
            updateList();
        }
        return;
    }
    public void toJob(View v){
        if (statusNow == 0){return;}
        else{
            changeStyleButton(bJob, ContextCompat.getColor(CONTEXT, R.color.white), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1_click));
            if (statusNow == 1) changeStyleButton(bReady, ContextCompat.getColor(CONTEXT, R.color.black), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1));
            else changeStyleButton(bAll1, ContextCompat.getColor(CONTEXT, R.color.black), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1));
            statusNow = 0;
            updateList();
        }
        return;
    }
    public void toReady(View v){
        if (statusNow == 1){return;}
        else{
            changeStyleButton(bReady, ContextCompat.getColor(CONTEXT, R.color.white), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1_click));
            if (statusNow == 0) changeStyleButton(bJob, ContextCompat.getColor(CONTEXT, R.color.black), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1));
            else changeStyleButton(bAll1, ContextCompat.getColor(CONTEXT, R.color.black), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1));
            statusNow = 1;
            updateList();
        }
        return;
    }

    public void toAll1(View v){
        if (statusNow == 2){return;}
        else{
            changeStyleButton(bAll1, ContextCompat.getColor(v.getContext(), R.color.white), ContextCompat.getDrawable(v.getContext(), R.drawable.custom_button_1_click));
            if (statusNow == 1) changeStyleButton(bReady, ContextCompat.getColor(v.getContext(), R.color.black), ContextCompat.getDrawable(v.getContext(), R.drawable.custom_button_1));
            else changeStyleButton(bJob, ContextCompat.getColor(v.getContext(), R.color.black), ContextCompat.getDrawable(v.getContext(), R.drawable.custom_button_1));
            statusNow = 2;
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
        Collections.sort(acts, new ActPipeComparatot());
        Date nowDate = new Date(0,0,1);
        int cnt = 0;
        for (ActPipe act: acts) {
            if ((statusNow == 2 || statusNow == ((act.getIdStatus() == ACT_STATUS_JOB)?0:1) || statusNow == ((act.getIdStatus() == ACT_STATUS_READY)?1:0)) && ((timeNow == 3) || ((timeNow == 0) && ((long) ((trim(new Date())).getTime() - trim(act.getDateTimeStop()).getTime())<86400000)) || ((timeNow == 1) && ((long)((trim(new Date())).getTime() - trim(act.getDateTimeStop()).getTime())<86400000*7))||((timeNow == 2) && ((long)((trim(new Date())).getTime() - trim(act.getDateTimeStop()).getTime())<2678400000L)))) {
                if (trim(act.getDateTimeStop()).equals(trim(nowDate))) {
                } else {
                    final View view = getLayoutInflater().inflate(R.layout.custom_block_date, null);
                    TextView textDate = (TextView) view.findViewById(R.id.dateText);
                    nowDate = act.getDateTimeStop();
                    textDate.setText(DateToText(nowDate));
                    WORK_PLACE_ELEMENTS.add(view);
                    WORKPLACE.addView(view);
                }
                nowDate = act.getDateTimeStop();
                final View view1 = getLayoutInflater().inflate(R.layout.custom_block_name, null);
                TextView textTime = (TextView) view1.findViewById(R.id.textTime);
                TextView textName = (TextView) view1.findViewById(R.id.textName);
                ImageView status = (ImageView) view1.findViewById(R.id.status);
                view1.setTag((int) cnt);
                if (act.getIdStatus() == ACT_STATUS_READY) status.setImageResource(R.drawable.job_green);
                textName.setText(act.getName(pipeNames)+"");
                SimpleDateFormat formatForDate = new SimpleDateFormat("HH:mm");
                textTime.setText(formatForDate.format(nowDate));
                WORK_PLACE_ELEMENTS.add(view1);
                WORKPLACE.addView(view1);
                cnt++;
            }
        }
    }
    public void drawActs(ArrayList<ActPump> acts, int i){
        for (ActPump act:acts) if(act.getDateTimeStop() == null) act.setDateTimeStop(new Date());
        Collections.sort(acts, new ActPumpComparatot());
        Date nowDate = new Date(0,0,1);
        int cnt = 0;
        for (ActPump act: acts) {
            if ((statusNow == 2 || statusNow == ((act.getIdStatus() == ACT_STATUS_JOB)?0:1) || statusNow == ((act.getIdStatus() == ACT_STATUS_READY)?1:0)) && ((timeNow==3)||((timeNow == 0) && ((long) ((trim(new Date())).getTime() - trim(act.getDateTimeStop()).getTime())<86400000)) || ((timeNow == 1) && ((long)((trim(new Date())).getTime() - trim(act.getDateTimeStop()).getTime())<86400000*7))||((timeNow == 2) && ((long)((trim(new Date())).getTime() - trim(act.getDateTimeStop()).getTime())<2678400000L)))) {
                if (trim(act.getDateTimeStop()).equals(trim(nowDate))) {
                } else {
                    final View view = getLayoutInflater().inflate(R.layout.custom_block_date, null);
                    TextView textDate = (TextView) view.findViewById(R.id.dateText);
                    nowDate = act.getDateTimeStop();
                    textDate.setText(DateToText(nowDate));
                    WORK_PLACE_ELEMENTS.add(view);
                    WORKPLACE.addView(view);
                }
                nowDate = act.getDateTimeStop();
                final View view1 = getLayoutInflater().inflate(R.layout.custom_block_name, null);
                TextView textTime = (TextView) view1.findViewById(R.id.textTime);
                TextView textName = (TextView) view1.findViewById(R.id.textName);
                ImageView status = (ImageView) view1.findViewById(R.id.status);
                view1.setTag((int) cnt);
                if (act.getIdStatus() == ACT_STATUS_READY) status.setImageResource(R.drawable.job_green);
                textName.setText(act.getName(pumpPositions)+"");
                SimpleDateFormat formatForDate = new SimpleDateFormat("HH:mm");
                textTime.setText(formatForDate.format(nowDate));
                WORK_PLACE_ELEMENTS.add(view1);
                WORKPLACE.addView(view1);
                cnt++;
            }
        }
    }
}
