package com.example.lukoil.activity.pump;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

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

        drawActs(LIST_ACT_PUMP, 0);

        changeStyleButton(bToday, ContextCompat.getColor(CONTEXT, R.color.white), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1_click));
        changeStyleButton(bJob, ContextCompat.getColor(CONTEXT, R.color.white), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1_click));

    }
    @Override
    public void updateList() {
        WORKPLACE.removeAllViews();
        WORK_PLACE_ELEMENTS.clear();
        drawActs(LIST_ACT_PUMP, (int)0);
    }
    public void toView(View v){
        Intent Pump_view = new Intent(v.getContext(), PumpView.class);
        Pump_view.putExtra(ActPump.class.getSimpleName(), LIST_ACT_PUMP.get((int)v.getTag()));
        startActivity(Pump_view);
    }
    @Override
    public void toPlus(View v){
        Intent Pump_create = new Intent(v.getContext(), PumpCreate.class);
        startActivity(Pump_create);
    }

}
