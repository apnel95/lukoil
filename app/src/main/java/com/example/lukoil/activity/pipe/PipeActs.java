package com.example.lukoil.activity.pipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

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

        Activity activity = new Activity(ID_ACTIVITY_PIPE, this, R.layout.trub_acts, R.id.layoutEvent, new ArrayList<View>(), R.id.layout_menu, "Акты - трубопровод");
        super.onStartList(activity);

        drawActs(LIST_ACT_PIPE);

        changeStyleButton(bToday, ContextCompat.getColor(CONTEXT, R.color.white), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1_click));
        changeStyleButton(bJob, ContextCompat.getColor(CONTEXT, R.color.white), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1_click));

    }
    @Override
    public void updateList() {
        WORKPLACE.removeAllViews();
        WORK_PLACE_ELEMENTS.clear();
        drawActs(LIST_ACT_PIPE);
    }
    public void toView(View v){
            Intent Trub_view = new Intent(v.getContext(), PipeView.class);
            Trub_view.putExtra(ActPipe.class.getSimpleName(), LIST_ACT_PIPE.get((int)v.getTag()));
            startActivity(Trub_view);
        }
    @Override
    public void toPlus(View v){
            Intent Trub_create = new Intent(v.getContext(), PipeCreate.class);
            startActivity(Trub_create);
        }

}
