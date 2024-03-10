package com.example.lukoil.activity.work;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.lukoil.activity.CreateChangeViewAct;
import com.example.lukoil.R;
import com.example.lukoil.entity.work.Work;

public class WorkCreate extends CreateChangeViewAct {
    EditText text, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_create);
        idForm = 4;

        text = findViewById(R.id.text);
        name = findViewById(R.id.name);
        context = this;
        onStartNotHome(idForm);

        topTitleActivity.setText("Добавление мероприятия");

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