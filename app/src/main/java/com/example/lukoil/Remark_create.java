package com.example.lukoil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.lukoil.entity.Remark;

public class Remark_create extends Create_change_view_act {
    EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remark_create);
        idForm = 4;

        text = findViewById(R.id.text);
        context = this;
        onStartNotHome(idForm);

        topTitleActivity.setText("Добавление события");

    }
    public void toSave(View v){
        Intent intent = new Intent();
        if (text.getText().toString() != null ){
            intent.putExtra(Remark.class.getSimpleName(), new Remark(0,0, text.getText().toString()));
            setResult(RESULT_OK, intent);
            finish();
        }
        setResult(RESULT_CANCELED, intent);
        finish();
    }
}
