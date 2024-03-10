package com.example.lukoil.activity.pipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;

import com.example.lukoil.server.ListActs;
import com.example.lukoil.R;
import com.example.lukoil.entity.act.ActPipe;

import java.util.ArrayList;

public class PipeActs extends ListActs {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trub_acts);
        idForm = 1;

        workplaceElements = new ArrayList<View>();
        workplace = (LinearLayout) findViewById(R.id.layoutForActs);

        onStartThis();

        context = this;

        topTitleActivity.setText("Акты - трубопровод");
        changeStyleButton(bToday, ContextCompat.getColor(context, R.color.white), ContextCompat.getDrawable(context, R.drawable.custom_button_1_click));
        changeStyleButton(bJob, ContextCompat.getColor(context, R.color.white), ContextCompat.getDrawable(context, R.drawable.custom_button_1_click));

    }
        protected void onStartThis() {
        super.onStart_list(idForm);

        drawActs(pipeActs);
    }
    @Override
    public void updateList() {
        workplace.removeAllViews();
        workplaceElements.clear();
        drawActs(pipeActs);
    }
    public void toView(View v){
            Intent Trub_view = new Intent(v.getContext(), PipeView.class);
            Trub_view.putExtra(ActPipe.class.getSimpleName(), pipeActs.get((int)v.getTag()));
            startActivity(Trub_view);
        }
    @Override
    public void toPlus(View v){
            Intent Trub_create = new Intent(v.getContext(), PipeCreate.class);
            startActivity(Trub_create);
        }

}
