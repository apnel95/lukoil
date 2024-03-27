package com.example.lukoil.activity.work;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.lukoil.activity.Activity;
import com.example.lukoil.activity.General;
import com.example.lukoil.activity.GeneralCreateChangeViewAct;
import com.example.lukoil.R;
import com.example.lukoil.entity.work.Work;

import java.util.ArrayList;

public class WorkCreate extends GeneralCreateChangeViewAct {
    EditText text, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Activity activity = new Activity(General.ID_ACTIVITY_EVENT, this, R.layout.work_create, R.id.layoutBlock, new ArrayList<View>(), R.id.layout_menu, "Добавление мероприятия");
        super.onStartList(activity);


        text = findViewById(R.id.text);
        name = findViewById(R.id.name);
        CONTEXT = this;
    }
    public void toSave(View v){
        Intent intent = new Intent();
        if (text.getText().toString() != null &&  name.getText().toString() != null){
            intent.putExtra(Work.class.getSimpleName(), new Work(0,0, name.getText().toString(), text.getText().toString()));
            setResult(RESULT_OK, intent);
            finish();
        }
        setResult(RESULT_CANCELED, intent);
        finish();
    }
}