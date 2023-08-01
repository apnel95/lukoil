package com.example.lukoil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.lukoil.entity.Work;
import com.example.lukoil.entity.Work;

import java.util.ArrayList;

public class Work_change extends GeneralClass {
    View view;
    ArrayList<Work> works;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_change);

        Bundle arguments = getIntent().getExtras();
        works = (ArrayList<Work>) arguments.getSerializable(ArrayList.class.getSimpleName());
        idForm = 4;

        context = this;

        allEds = new ArrayList<View>();
        linear = findViewById(R.id.layoutEvent);

        onStartNotHome(idForm);

        drawEvents();

        uppTextName.setText("Изменение списка");
    }
    private void drawEvents() {
        linear.removeAllViews();
        allEds.clear();
        int cnt = 0;
        if (works != null) for (Work wrk: works) {
            view = getLayoutInflater().inflate(R.layout.custom_event_date_time_change, null);
            TextView textName = view.findViewById(R.id.textName);
            textName.setText(String.valueOf(wrk.getName()));
            TextView text = view.findViewById(R.id.textDateTime);
            text.setText(String.valueOf(wrk.getText()));
            Button b = view.findViewById(R.id.toDelete);
            b.setTag(cnt);
            linear.addView(view);
            allEds.add(view);
            cnt++;
        }

    }
    public void toDelete(View v){
        try {
            int id = (int) v.getTag();
            works.remove(id);
            linear.removeView((ConstraintLayout)v.getParent());
            allEds.remove((ConstraintLayout)v.getParent());
            drawEvents();
        } catch(IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }
    public void toNewWork(View v){
        Intent event_create = new Intent(v.getContext(), Work_create.class);
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