package com.example.lukoil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;

import com.example.lukoil.entity.Act_trub;

import java.util.ArrayList;

public class Trub_acts extends List_acts {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trub_acts);
        idForm = 1;

        allEds = new ArrayList<View>();
        linear = (LinearLayout) findViewById(R.id.layoutForActs);

        onStartThis();

        context = this;

        uppTextName.setText("Акты - трубопровод");
        changeStyleButton(bToday, ContextCompat.getColor(context, R.color.white), ContextCompat.getDrawable(context, R.drawable.custom_button_1_click));
        changeStyleButton(bJob, ContextCompat.getColor(context, R.color.white), ContextCompat.getDrawable(context, R.drawable.custom_button_1_click));

    }
        protected void onStartThis() {
        super.onStart_list(idForm);

        drawActs(actsTrub);
    }
    @Override
    public void updateList() {
        linear.removeAllViews();
        allEds.clear();
        drawActs(actsTrub);
    }
    public void toView(View v){
            Intent Trub_view = new Intent(v.getContext(), Trub_view.class);
            Trub_view.putExtra(Act_trub.class.getSimpleName(), actsTrub.get((int)v.getTag()));
            startActivity(Trub_view);
        }
    @Override
    public void toPlus(View v){
            Intent Trub_create = new Intent(v.getContext(), Trub_create.class);
            startActivity(Trub_create);
        }

}
