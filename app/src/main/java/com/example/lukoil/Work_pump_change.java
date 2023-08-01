package com.example.lukoil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lukoil.entity.Remark;

import java.util.ArrayList;

public class Work_pump_change extends GeneralClass {
    View view;
    ArrayList<Integer> works;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remark_change);

        Bundle arguments = getIntent().getExtras();
        works = (ArrayList<Integer>) arguments.getSerializable(ArrayList.class.getSimpleName());
        idForm = 4;

        context = this;

        onStartNotHome(idForm);

        allEds = new ArrayList<View>();
        linear = (LinearLayout) findViewById(R.id.layoutEvent);

        uppTextName.setText("Изменение списка");
    }

    @Override
    protected void onStart() {
        super.onStart();
        drawEvents();

    }

    private void drawEvents() {
        linear.removeAllViews();
        allEds.clear();
        int cnt = 0;
        for (Integer wrk: works) {
            view = getLayoutInflater().inflate(R.layout.custom_event_date_time_change, null);
            TextView textName = (TextView) view.findViewById(R.id.textName);
            textName.setText(String.valueOf(wrk));
            view.setTag((int)wrk);
            linear.addView(view);
            allEds.add(view);
            cnt++;
        }

    }
    public void toDelete(View v){
        try {
            linear.removeView((LinearLayout)v.getParent());
            allEds.remove((LinearLayout)v.getParent());
        } catch(IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }
    public void toNewWork(View v){
        Intent event_create = new Intent(v.getContext(), Work_pump_create.class);
        startActivity(event_create);
    }
    public void toSave(View v){
        finish();
    }
}
