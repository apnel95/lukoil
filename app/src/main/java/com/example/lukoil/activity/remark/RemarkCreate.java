package com.example.lukoil.activity.remark;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.lukoil.activity.CreateChangeViewAct;
import com.example.lukoil.R;
import com.example.lukoil.entity.remark.Remark;

public class RemarkCreate extends CreateChangeViewAct {
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
