package com.example.lukoil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.lukoil.entity.Event_date_time;
import com.example.lukoil.entity.Remark;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Remark_change extends GeneralClass {
    View view;
    ArrayList<Remark> remarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remark_change);

        Bundle arguments = getIntent().getExtras();
        remarks = (ArrayList<Remark>) arguments.getSerializable(ArrayList.class.getSimpleName());
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
        if (remarks != null) for (Remark wrk: remarks) {
            view = getLayoutInflater().inflate(R.layout.custom_remark_change, null);
            TextView textName = view.findViewById(R.id.text);
            textName.setText(String.valueOf(wrk.getText()));
            Button b = view.findViewById(R.id.b);
            b.setTag(cnt);
            linear.addView(view);
            allEds.add(view);
            cnt++;
        }

    }
    public void toDelete(View v){
        try {
            int id = (int) v.getTag();
            remarks.remove(id);
            linear.removeView((ConstraintLayout)v.getParent());
            allEds.remove((ConstraintLayout)v.getParent());
            drawEvents();
        } catch(IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }
    public void toNewRemark(View v){
        Intent event_create = new Intent(v.getContext(), Remark_create.class);
        startActivityForResult(event_create, 1);
    }
    public void toSave(View v){
        Intent intent = new Intent();
        if (remarks != null ){
            intent.putExtra(ArrayList.class.getSimpleName(), remarks);
            setResult(RESULT_OK, intent);
            finish();
        }
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (remarks == null) remarks = new ArrayList<>();
            remarks.add((Remark) data.getExtras().getSerializable(Remark.class.getSimpleName()));
            drawEvents();
        }
    }
}
