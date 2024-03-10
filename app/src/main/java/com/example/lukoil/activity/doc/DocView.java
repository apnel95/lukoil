package com.example.lukoil.activity.doc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lukoil.activity.CreateChangeViewAct;
import com.example.lukoil.R;
import com.example.lukoil.entity.act.ActDoc;
import com.example.lukoil.entity.DepartmentObject;
import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.Employee;
import com.example.lukoil.entity.event.EventDateTime;
import com.example.lukoil.entity.remark.Remark;
import com.example.lukoil.entity.work.Work;

public class DocView extends CreateChangeViewAct {

    ActDoc act;
    EditText struct, object, employee, FIO_sending, status;
    TextView events, remarks, works;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doc_view);

        Bundle arguments = getIntent().getExtras();
        act = (ActDoc) arguments.getSerializable(ActDoc.class.getSimpleName());
        System.out.println(act.getId_department());
        idForm = 3;
        context = this;

        onStartNotHome(idForm);

        struct = findViewById(R.id.struct);
        object = findViewById(R.id.object);
        employee = findViewById(R.id.employee);
        FIO_sending = findViewById(R.id.FIO_sending);
        status = findViewById(R.id.status);

        struct.setText(String.valueOf(act.getId_department()));
        object.setText(String.valueOf(act.getId_department_object()));
        employee.setText(String.valueOf(act.getId_employee()));
        FIO_sending.setText(String.valueOf(act.getFIO_senging()));
        status.setText(String.valueOf(act.getId_status()));

        events = findViewById(R.id.events);
        remarks = findViewById(R.id.remarks);
        works = findViewById(R.id.works);

        topTitleActivity.setText("Просмотр предписания");

        String textForEvents = "";
        if (act.getEvents() != null) for (EventDateTime wrk : act.getEvents()) textForEvents += wrk.getName(event_types) + ": " + DateToText(wrk.getDateTime()) + "\n";
        events.setText(textForEvents);

        String textForWorks = "";
        if (act.getWorks() != null) for (Work wrks : act.getWorks()) textForWorks += wrks.getName() + ": " + (wrks.getText()) + "\n";
        works.setText(textForWorks);

        String textForRemark = "";
        if (act.getRemarks() != null) for (Remark rmk : act.getRemarks()) textForRemark +=(rmk.getText()) + "\n";
        remarks.setText(textForRemark);

        int cnt=0;
        for(Dir dir: departments){
            if(dir.getId() == act.getId_department()){
                struct.setText(dir.getName());
                break;
            }
            cnt++;
        }

        cnt=0;
        for(DepartmentObject dir: DepartmentObjects){
            if(dir.getId() == act.getId_department_object()){
                object.setText(dir.getName());
                break;
            }
            cnt++;
        }

        cnt=0;
        for(Employee dir: employees){
            if(dir.getId() == act.getId_employee()){
                employee.setText(dir.getFIO());
                break;
            }
            cnt++;
        }
        cnt=0;
        for(Dir dir: statuses_act){
            if(dir.getId() == act.getId_status()){
                status.setText(dir.getName());
                break;
            }
            cnt++;
        }


    }
    public void toChange(View v){
        Intent Doc_change = new Intent(v.getContext(), DocChange.class);
        Doc_change.putExtra(ActDoc.class.getSimpleName(), act);
        startActivity(Doc_change);
    }
    @Override
    public void toPlus(View v){
        Intent Doc_create = new Intent(v.getContext(), DocCreate.class);
        startActivity(Doc_create);
    }

}