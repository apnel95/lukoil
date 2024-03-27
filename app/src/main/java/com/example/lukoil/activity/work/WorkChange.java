package com.example.lukoil.activity.work;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.lukoil.activity.Activity;
import com.example.lukoil.R;
import com.example.lukoil.activity.GeneralCreateChangeViewAct;
import com.example.lukoil.entity.work.Work;

import java.util.ArrayList;

public class WorkChange extends GeneralCreateChangeViewAct {
    View view;
    ArrayList<Work> works;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getIntent().getExtras();
        works = (ArrayList<Work>) arguments.getSerializable(ArrayList.class.getSimpleName());

        Activity activity = new Activity(ID_ACTIVITY_EVENT, this, R.layout.work_change, R.id.layoutBlock, new ArrayList<View>(), R.id.layout_menu, "Изменение списка");
        super.onStartList(activity);

        WORK_PLACE_ELEMENTS = new ArrayList<View>();
        WORKPLACE = findViewById(R.id.layoutEvent);



        drawEvents();
    }
    private void drawEvents() {
        WORKPLACE.removeAllViews();
        WORK_PLACE_ELEMENTS.clear();
        int cnt = 0;
        if (works != null) for (Work wrk: works) {
            view = getLayoutInflater().inflate(R.layout.custom_event_date_time_change, null);
            TextView textName = view.findViewById(R.id.textName);
            textName.setText(String.valueOf(wrk.getName()));
            TextView text = view.findViewById(R.id.textDateTime);
            text.setText(String.valueOf(wrk.getText()));
            Button b = view.findViewById(R.id.toDelete);
            b.setTag(cnt);
            WORKPLACE.addView(view);
            WORK_PLACE_ELEMENTS.add(view);
            cnt++;
        }

    }
    public void toDelete(View v){
        try {
            int id = (int) v.getTag();
            works.remove(id);
            WORKPLACE.removeView((ConstraintLayout)v.getParent());
            WORK_PLACE_ELEMENTS.remove((ConstraintLayout)v.getParent());
            drawEvents();
        } catch(IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }
    public void toNewWork(View v){
        Intent event_create = new Intent(v.getContext(), WorkCreate.class);
        startActivityForResult(event_create, 1);
    }
    public void toSave(View v){
        Intent intent = new Intent();
        if (works != null ){
            intent.putExtra(ArrayList.class.getSimpleName(), works);
            setResult(RESULT_OK, intent);
            finish();
        }
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (works == null) works = new ArrayList<>();
            works.add((Work) data.getExtras().getSerializable(Work.class.getSimpleName()));
            drawEvents();
        }
    }
}