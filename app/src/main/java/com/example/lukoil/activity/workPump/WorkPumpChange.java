package com.example.lukoil.activity.workPump;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lukoil.GeneralClass;
import com.example.lukoil.R;

import java.util.ArrayList;

public class WorkPumpChange extends GeneralClass {
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

        workplaceElements = new ArrayList<View>();
        workplace = (LinearLayout) findViewById(R.id.layoutEvent);

        topTitleActivity.setText("Изменение списка");
    }

    @Override
    protected void onStart() {
        super.onStart();
        drawEvents();

    }

    private void drawEvents() {
        workplace.removeAllViews();
        workplaceElements.clear();
        int cnt = 0;
        for (Integer wrk: works) {
            view = getLayoutInflater().inflate(R.layout.custom_event_date_time_change, null);
            TextView textName = (TextView) view.findViewById(R.id.textName);
            textName.setText(String.valueOf(wrk));
            view.setTag((int)wrk);
            workplace.addView(view);
            workplaceElements.add(view);
            cnt++;
        }

    }
    public void toDelete(View v){
        try {
            workplace.removeView((LinearLayout)v.getParent());
            workplaceElements.remove((LinearLayout)v.getParent());
        } catch(IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }
    public void toNewWork(View v){
        Intent event_create = new Intent(v.getContext(), WorkPumpCreate.class);
        startActivity(event_create);
    }
    public void toSave(View v){
        finish();
    }
}
