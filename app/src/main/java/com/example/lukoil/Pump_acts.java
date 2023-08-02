package com.example.lukoil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;

import com.example.lukoil.entity.PumpAct;

import java.util.ArrayList;

public class Pump_acts extends List_acts {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pump_acts);
        idForm = 2;

        workplaceElements = new ArrayList<View>();
        workplace = (LinearLayout) findViewById(R.id.layoutForActs);

        onStartThis();

        context = this;

        topTitleActivity.setText("Акты - насосы");
        changeStyleButton(bToday, ContextCompat.getColor(context, R.color.white), ContextCompat.getDrawable(context, R.drawable.custom_button_1_click));
        changeStyleButton(bJob, ContextCompat.getColor(context, R.color.white), ContextCompat.getDrawable(context, R.drawable.custom_button_1_click));

    }
    protected void onStartThis() {
        super.onStart_list(idForm);
        updateList();
    }
    @Override
    public void updateList() {
        workplace.removeAllViews();
        workplaceElements.clear();
        drawActs(pumpActs, (int)0);
    }
    public void toView(View v){
        Intent Pump_view = new Intent(v.getContext(), Pump_view.class);
        Pump_view.putExtra(PumpAct.class.getSimpleName(), pumpActs.get((int)v.getTag()));
        startActivity(Pump_view);
    }
    @Override
    public void toPlus(View v){
        Intent Pump_create = new Intent(v.getContext(), Pump_create.class);
        startActivity(Pump_create);
    }

}
