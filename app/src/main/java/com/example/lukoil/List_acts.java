package com.example.lukoil;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import com.example.lukoil.entity.Act_doc;
import com.example.lukoil.entity.Act_pump;
import com.example.lukoil.entity.Act_trub;
import com.example.lukoil.entity.comparation.Act_doc_comparatot;
import com.example.lukoil.entity.comparation.Act_pump_comparatot;
import com.example.lukoil.entity.comparation.Act_trub_comparatot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class List_acts extends GeneralClass {

    int statusNow = 0, timeNow = 0;
    Button bToday, bWeek, bMonth, bAll, bJob, bReady, bAll1;
    View viewTimeStatus;

    protected void onStart_list(int i) {
        super.onStartNotHome(i);

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
            changeStyleButton(bToday, ContextCompat.getColor(context, R.color.white), ContextCompat.getDrawable(context, R.drawable.custom_button_1_click));
            if (timeNow == 1) changeStyleButton(bWeek, ContextCompat.getColor(context, R.color.black), ContextCompat.getDrawable(context, R.drawable.custom_button_1));
            else if (timeNow == 2) changeStyleButton(bMonth, ContextCompat.getColor(context, R.color.black), ContextCompat.getDrawable(context, R.drawable.custom_button_1));
            else changeStyleButton(bAll, ContextCompat.getColor(context, R.color.black), ContextCompat.getDrawable(context, R.drawable.custom_button_1));
            timeNow = 0;
            updateList();
        }
        return;
    }

    public void toWeek(View v){
        if (timeNow == 1){return;}
        else{
            changeStyleButton(bWeek, ContextCompat.getColor(context, R.color.white), ContextCompat.getDrawable(context, R.drawable.custom_button_1_click));
            if (timeNow == 0) changeStyleButton(bToday, ContextCompat.getColor(context, R.color.black), ContextCompat.getDrawable(context, R.drawable.custom_button_1));
            else if (timeNow == 2) changeStyleButton(bMonth, ContextCompat.getColor(context, R.color.black), ContextCompat.getDrawable(context, R.drawable.custom_button_1));
            else changeStyleButton(bAll, ContextCompat.getColor(context, R.color.black), ContextCompat.getDrawable(context, R.drawable.custom_button_1));
            timeNow = 1;
            updateList();
        }
        return;
    }

    public void toMonth(View v){
        if (timeNow == 2){return;}
        else{
            changeStyleButton(bMonth, ContextCompat.getColor(context, R.color.white), ContextCompat.getDrawable(context, R.drawable.custom_button_1_click));
            if (timeNow == 1) changeStyleButton(bWeek, ContextCompat.getColor(context, R.color.black), ContextCompat.getDrawable(context, R.drawable.custom_button_1));
            else if (timeNow == 0) changeStyleButton(bToday, ContextCompat.getColor(context, R.color.black), ContextCompat.getDrawable(context, R.drawable.custom_button_1));
            else changeStyleButton(bAll, ContextCompat.getColor(context, R.color.black), ContextCompat.getDrawable(context, R.drawable.custom_button_1));
            timeNow = 2;
            updateList();
        }
        return;
    }

    public void toAll(View v){
        if (timeNow == 3){return;}
        else{
            changeStyleButton(bAll, ContextCompat.getColor(context, R.color.white), ContextCompat.getDrawable(context, R.drawable.custom_button_1_click));
            if (timeNow == 1) changeStyleButton(bWeek, ContextCompat.getColor(context, R.color.black), ContextCompat.getDrawable(context, R.drawable.custom_button_1));
            else if (timeNow == 2) changeStyleButton(bMonth, ContextCompat.getColor(context, R.color.black), ContextCompat.getDrawable(context, R.drawable.custom_button_1));
            else changeStyleButton(bToday, ContextCompat.getColor(context, R.color.black), ContextCompat.getDrawable(context, R.drawable.custom_button_1));
            timeNow = 3;
            updateList();
        }
        return;
    }
    public void toJob(View v){
        if (statusNow == 0){return;}
        else{
            changeStyleButton(bJob, ContextCompat.getColor(context, R.color.white), ContextCompat.getDrawable(context, R.drawable.custom_button_1_click));
            if (statusNow == 1) changeStyleButton(bReady, ContextCompat.getColor(context, R.color.black), ContextCompat.getDrawable(context, R.drawable.custom_button_1));
            else changeStyleButton(bAll1, ContextCompat.getColor(context, R.color.black), ContextCompat.getDrawable(context, R.drawable.custom_button_1));
            statusNow = 0;
            updateList();
        }
        return;
    }
    public void toReady(View v){
        if (statusNow == 1){return;}
        else{
            changeStyleButton(bReady, ContextCompat.getColor(context, R.color.white), ContextCompat.getDrawable(context, R.drawable.custom_button_1_click));
            if (statusNow == 0) changeStyleButton(bJob, ContextCompat.getColor(context, R.color.black), ContextCompat.getDrawable(context, R.drawable.custom_button_1));
            else changeStyleButton(bAll1, ContextCompat.getColor(context, R.color.black), ContextCompat.getDrawable(context, R.drawable.custom_button_1));
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
        linear.removeAllViews();
        allEds.clear();
    }

    public void drawActs(ArrayList<Act_trub> acts){
        Collections.sort(acts, new Act_trub_comparatot());
        Date nowDate = new Date(0,0,1);
        int cnt = 0;
        for (Act_trub act: acts) {
            if ((statusNow == 2 || statusNow == ((act.getId_status() == STATUS_JOB)?0:1) || statusNow == ((act.getId_status() == STATUS_READY)?1:0)) && ((timeNow == 3) || ((timeNow == 0) && ((long) ((trim(new Date())).getTime() - trim(act.getDate_time_stop()).getTime())<86400000)) || ((timeNow == 1) && ((long)((trim(new Date())).getTime() - trim(act.getDate_time_stop()).getTime())<86400000*7))||((timeNow == 2) && ((long)((trim(new Date())).getTime() - trim(act.getDate_time_stop()).getTime())<2678400000L)))) {
                if (trim(act.getDate_time_stop()).equals(trim(nowDate))) {
                } else {
                    final View view = getLayoutInflater().inflate(R.layout.custom_block_date, null);
                    TextView textDate = (TextView) view.findViewById(R.id.dateText);
                    nowDate = act.getDate_time_stop();
                    textDate.setText(DateToText(nowDate));
                    allEds.add(view);
                    linear.addView(view);
                }
                nowDate = act.getDate_time_stop();
                final View view1 = getLayoutInflater().inflate(R.layout.custom_block_name, null);
                TextView textTime = (TextView) view1.findViewById(R.id.textTime);
                TextView textName = (TextView) view1.findViewById(R.id.textName);
                ImageView status = (ImageView) view1.findViewById(R.id.status);
                view1.setTag((int) cnt);
                if (act.getId_status() == STATUS_READY) status.setImageResource(R.drawable.job_green);
                textName.setText(act.getName(trubs)+"");
                SimpleDateFormat formatForDate = new SimpleDateFormat("HH:mm");
                textTime.setText(formatForDate.format(nowDate));
                allEds.add(view1);
                linear.addView(view1);
                cnt++;
            }
        }
    }
    public void drawActs(ArrayList<Act_pump> acts, int i){
        for (Act_pump act:acts) if(act.getDate_time_stop() == null) act.setDate_time_stop(new Date());
        Collections.sort(acts, new Act_pump_comparatot());
        Date nowDate = new Date(0,0,1);
        int cnt = 0;
        for (Act_pump act: acts) {
            if ((statusNow == 2 || statusNow == ((act.getId_status() == STATUS_JOB)?0:1) || statusNow == ((act.getId_status() == STATUS_READY)?1:0)) && ((timeNow==3)||((timeNow == 0) && ((long) ((trim(new Date())).getTime() - trim(act.getDate_time_stop()).getTime())<86400000)) || ((timeNow == 1) && ((long)((trim(new Date())).getTime() - trim(act.getDate_time_stop()).getTime())<86400000*7))||((timeNow == 2) && ((long)((trim(new Date())).getTime() - trim(act.getDate_time_stop()).getTime())<2678400000L)))) {
                if (trim(act.getDate_time_stop()).equals(trim(nowDate))) {
                } else {
                    final View view = getLayoutInflater().inflate(R.layout.custom_block_date, null);
                    TextView textDate = (TextView) view.findViewById(R.id.dateText);
                    nowDate = act.getDate_time_stop();
                    textDate.setText(DateToText(nowDate));
                    allEds.add(view);
                    linear.addView(view);
                }
                nowDate = act.getDate_time_stop();
                final View view1 = getLayoutInflater().inflate(R.layout.custom_block_name, null);
                TextView textTime = (TextView) view1.findViewById(R.id.textTime);
                TextView textName = (TextView) view1.findViewById(R.id.textName);
                ImageView status = (ImageView) view1.findViewById(R.id.status);
                view1.setTag((int) cnt);
                if (act.getId_status() == STATUS_READY) status.setImageResource(R.drawable.job_green);
                textName.setText(act.getName(positions)+"");
                SimpleDateFormat formatForDate = new SimpleDateFormat("HH:mm");
                textTime.setText(formatForDate.format(nowDate));
                allEds.add(view1);
                linear.addView(view1);
                cnt++;
            }
        }
    }
}
