package com.example.lukoil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.lukoil.entity.Remark;
import com.example.lukoil.entity.Work;

public class Work_create extends Create_change_view_act {
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

        uppTextName.setText("Добавление мероприятия");

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