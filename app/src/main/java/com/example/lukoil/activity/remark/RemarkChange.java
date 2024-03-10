package com.example.lukoil.activity.remark;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.lukoil.GeneralClass;
import com.example.lukoil.R;
import com.example.lukoil.entity.remark.Remark;

import java.util.ArrayList;

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

        workplaceElements = new ArrayList<View>();
        workplace = findViewById(R.id.layoutEvent);

        onStartNotHome(idForm);

        drawEvents();

        topTitleActivity.setText("Изменение списка");
    }
    private void drawEvents() {
        workplace.removeAllViews();
        workplaceElements.clear();
        int cnt = 0;
        if (remarks != null) for (Remark wrk: remarks) {
            view = getLayoutInflater().inflate(R.layout.custom_remark_change, null);
            TextView textName = view.findViewById(R.id.text);
            textName.setText(String.valueOf(wrk.getText()));
            Button b = view.findViewById(R.id.b);
            b.setTag(cnt);
            workplace.addView(view);
            workplaceElements.add(view);
            cnt++;
        }

    }
    public void toDelete(View v){
        try {
            int id = (int) v.getTag();
            remarks.remove(id);
            workplace.removeView((ConstraintLayout)v.getParent());
            workplaceElements.remove((ConstraintLayout)v.getParent());
            drawEvents();
        } catch(IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }
    public void toNewRemark(View v){
        Intent event_create = new Intent(v.getContext(), RemarkCreate.class);
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
