package com.example.lukoil.activity.doc;

import static com.example.lukoil.ListData.actEventTypes;
import static com.example.lukoil.ListData.actStatuses;
import static com.example.lukoil.ListData.docDepartmentObjects;
import static com.example.lukoil.ListData.docDepartments;
import static com.example.lukoil.ListData.employees;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lukoil.activity.Activity;
import com.example.lukoil.activity.GeneralCreateChangeViewAct;
import com.example.lukoil.R;
import com.example.lukoil.entity.act.ActDoc;
import com.example.lukoil.entity.DepartmentObject;
import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.Employee;
import com.example.lukoil.entity.event.EventDateTime;
import com.example.lukoil.entity.remark.Remark;
import com.example.lukoil.entity.work.WorkDoc;

import java.util.ArrayList;

public class DocView extends GeneralCreateChangeViewAct {

    ActDoc act;
    EditText struct, object, employee, FIO_sending, status;
    TextView events, remarks, works;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getIntent().getExtras();
        act = (ActDoc) arguments.getSerializable(ActDoc.class.getSimpleName());

        Activity activity = new Activity(ID_ACTIVITY_DOC, this, R.layout.doc_view, R.id.layoutBlock, new ArrayList<View>(), R.id.layout_menu, "Просмотр предписания");
        super.onStartList(activity);

        struct = findViewById(R.id.struct);
        object = findViewById(R.id.object);
        employee = findViewById(R.id.employee);
        FIO_sending = findViewById(R.id.FIO_sending);
        status = findViewById(R.id.status);

        struct.setText(String.valueOf(act.getIdDepartment()));
        object.setText(String.valueOf(act.getIdDepartmentObject()));
        employee.setText(String.valueOf(act.getIdEmployee()));
        FIO_sending.setText(String.valueOf(act.getFIOSending()));
        status.setText(String.valueOf(act.getIdStatus()));

        events = findViewById(R.id.events);
        remarks = findViewById(R.id.remarks);
        works = findViewById(R.id.works);

        String textForEvents = "";
        if (act.getEvents() != null) for (EventDateTime wrk : act.getEvents()) textForEvents += wrk.getNameTypeEvent(actEventTypes) + ": " + DateToText(wrk.getDateTime()) + "\n";
        events.setText(textForEvents);

        String textForWorks = "";
        if (act.getWorks() != null) for (WorkDoc wrks : act.getWorks()) textForWorks += wrks.getName() + ": " + (wrks.getText()) + "\n";
        works.setText(textForWorks);

        String textForRemark = "";
        if (act.getRemarks() != null) for (Remark rmk : act.getRemarks()) textForRemark +=(rmk.getText()) + "\n";
        remarks.setText(textForRemark);

        int cnt=0;
        for(Dir dir: docDepartments){
            if(dir.getId() == act.getIdDepartment()){
                struct.setText(dir.getName());
                break;
            }
            cnt++;
        }

        cnt=0;
        for(DepartmentObject dir: docDepartmentObjects){
            if(dir.getId() == act.getIdDepartmentObject()){
                object.setText(dir.getName());
                break;
            }
            cnt++;
        }

        cnt=0;
        for(Employee dir: employees){
            if(dir.getId() == act.getIdEmployee()){
                employee.setText(dir.getFIO());
                break;
            }
            cnt++;
        }
        cnt=0;
        for(Dir dir: actStatuses){
            if(dir.getId() == act.getIdStatus()){
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