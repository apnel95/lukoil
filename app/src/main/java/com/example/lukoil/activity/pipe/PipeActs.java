package com.example.lukoil.activity.pipe;

import static com.example.lukoil.ListData.pipeActs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.example.lukoil.activity.Activity;
import com.example.lukoil.activity.ListActs;
import com.example.lukoil.R;
import com.example.lukoil.entity.act.ActPipe;

import java.util.ArrayList;

public class PipeActs extends ListActs {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Activity activity = new Activity(ID_ACTIVITY_PIPE, this, R.layout.pipe_acts, R.id.layoutForActs, new ArrayList<View>(), R.id.layout_menu, "Акты - трубопровод");
        super.onStartList(activity);

        drawActs(pipeActs);

        changeStyleButton(bToday, ContextCompat.getColor(CONTEXT, R.color.white), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1_click));
        changeStyleButton(bJob, ContextCompat.getColor(CONTEXT, R.color.white), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1_click));

    }
    @Override
    public void updateList() {
        WORKPLACE.removeAllViews();
        WORK_PLACE_ELEMENTS.clear();
        drawActs(pipeActs);
    }
    public void toView(View v){
            Intent pipeView = new Intent(v.getContext(), PipeView.class);
            pipeView.putExtra(ActPipe.class.getSimpleName(), pipeActs.get((int)v.getTag()));
            startActivity(pipeView);
        }
    @Override
    public void toPlus(View v){
            Intent pipeChange = new Intent(v.getContext(), PipeChange.class);
            pipeChange.putExtra(ActPipe.class.getSimpleName(), new ActPipe());
            pipeChange.putExtra("nameActivity", getResources().getString(R.string.createAct));
            startActivity(pipeChange);
        }

}
