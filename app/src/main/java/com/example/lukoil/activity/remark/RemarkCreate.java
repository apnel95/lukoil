package com.example.lukoil.activity.remark;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.lukoil.activity.Activity;
import com.example.lukoil.activity.GeneralCreateChangeViewAct;
import com.example.lukoil.R;
import com.example.lukoil.entity.remark.Remark;

import java.util.ArrayList;

public class RemarkCreate extends GeneralCreateChangeViewAct {
    EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Activity activity = new Activity(ID_ACTIVITY_EVENT,this, R.layout.remark_create, R.id.layoutBlock, new ArrayList<View>(), R.id.layout_menu, "Добавление списка");
        super.onStartList(activity);

        text = findViewById(R.id.text);

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
