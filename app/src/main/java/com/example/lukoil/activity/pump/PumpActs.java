package com.example.lukoil.activity.pump;

import static com.example.lukoil.ListData.pumpActs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.example.lukoil.activity.Activity;
import com.example.lukoil.activity.General;
import com.example.lukoil.activity.ListActs;
import com.example.lukoil.R;
import com.example.lukoil.entity.act.ActPump;

import java.util.ArrayList;

public class PumpActs extends ListActs {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Activity activity = new Activity(General.ID_ACTIVITY_PUMP, this, R.layout.pump_acts, R.id.layoutForActs, new ArrayList<View>(), R.id.layout_menu, "Акты - насосы");
        super.onStartList(activity);

        drawActs(pumpActs, 0);

        changeStyleTextView(bToday, ContextCompat.getColor(CONTEXT, R.color.white), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1_click));
        changeStyleTextView(bJob, ContextCompat.getColor(CONTEXT, R.color.white), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1_click));

    }
    @Override
    public void updateList() {
        WORKPLACE.removeAllViews();
        WORK_PLACE_ELEMENTS.clear();
        drawActs(pumpActs, (int)0);
    }
    public void toView(View v){
        Intent Pump_view = new Intent(v.getContext(), PumpView.class);
        Pump_view.putExtra(ActPump.class.getSimpleName(), pumpActs.get((int)v.getTag()));
        startActivity(Pump_view);
    }
    @Override
    public void toPlus(View v){
        Intent Pump_create = new Intent(v.getContext(), PumpCreate.class);
        startActivity(Pump_create);
    }

}
