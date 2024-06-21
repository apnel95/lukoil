package com.example.lukoil.activity.doc;

import static com.example.lukoil.ListData.actEventTypes;
import static com.example.lukoil.ListData.actStatuses;
import static com.example.lukoil.ListData.docDepartmentObjects;
import static com.example.lukoil.ListData.docDepartments;
import static com.example.lukoil.ListData.employees;
import static com.example.lukoil.ListData.pipeCoatingTypes;
import static com.example.lukoil.ListData.pipeLeakTypes;
import static com.example.lukoil.ListData.pipeNames;
import static com.example.lukoil.ListData.pipeSubstances;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lukoil.activity.Activity;
import com.example.lukoil.activity.GeneralCreateChangeViewAct;
import com.example.lukoil.R;
import com.example.lukoil.activity.pump.PumpChange;
import com.example.lukoil.entity.act.ActDoc;
import com.example.lukoil.entity.DepartmentObject;
import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.Employee;
import com.example.lukoil.entity.act.ActPump;
import com.example.lukoil.entity.event.EventDateTime;
import com.example.lukoil.entity.remark.Remark;
import com.example.lukoil.entity.work.WorkDoc;

import java.util.ArrayList;
import java.util.List;

public class DocView extends GeneralCreateChangeViewAct {

    ActDoc act;
    EditText struct, object, employee, FIO_sending, status;
    protected List<View> workList, remarkList;
    protected LinearLayout workLayout, remarkLayout;

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

        struct.setText(getNameToId(docDepartments, act.getIdDepartment()));
        status.setText(getNameToId(actStatuses, act.getIdStatus()));
        employee.setText(getNameToIdForEmployee(employees, act.getIdEmployee()));

        int cnt=0;
        for(DepartmentObject dir: docDepartmentObjects){
            if(dir.getId() == act.getIdDepartmentObject()){
                object.setText(dir.getName());
                break;
            }
            cnt++;
        }
        eventDateTimeList = new ArrayList<>();
        eventDateTimeLayout = findViewById(R.id.layoutEvents);
        drawEvents(act.getEvents(), R.layout.custom_event_date_time_view);

        workList = new ArrayList<>();
        workLayout = findViewById(R.id.layoutWorks);
        drawDocWorks(act.getWorks(), R.layout.custom_work_doc_view);

        remarkList = new ArrayList<>();
        remarkLayout = findViewById(R.id.layoutRemarks);
        drawRemarks(act.getRemarks(), R.layout.custom_remark_view);
    }
    @Override
    public void toChange(View v){
        Intent docChange = new Intent(v.getContext(), DocChange.class);
        docChange.putExtra(ActPump.class.getSimpleName(), act);
        docChange.putExtra("nameActivity", getResources().getString(R.string.changeDoc));
        startActivity(docChange);
    }

    @Override
    public void toPlus(View v) {
        Intent docCreate = new Intent(CONTEXT, DocChange.class);
        docCreate.putExtra(ActPump.class.getSimpleName(), new ActDoc());
        docCreate.putExtra("nameActivity", getResources().getString(R.string.createDoc));
        startActivity(docCreate);
    }
    private void drawDocWorks(ArrayList<WorkDoc> works, int idLayout) {
        workLayout.removeAllViews();
        workList.clear();
        int cnt = 0;
        if (works != null) for (WorkDoc wrk : works) {
            View view = getLayoutInflater().inflate(idLayout, null);
            TextView textName = view.findViewById(R.id.textName);
            TextView textDesc = view.findViewById(R.id.textDescription);
            textName.setText(String.valueOf(wrk.getName()));
            textDesc.setText(String.valueOf(wrk.getText()));
            view.setTag(new Dir(wrk.getId(), "docWork"));
            view.setId(cnt);
            workLayout.addView(view);
            workList.add(view);
            cnt++;
        }
    }

    private void drawRemarks(ArrayList<Remark> remarks, int idLayout) {
        workLayout.removeAllViews();
        workList.clear();
        int cnt = 0;
        if (remarks != null) for (Remark rmk : remarks) {
            View view = getLayoutInflater().inflate(idLayout, null);
            TextView textName = view.findViewById(R.id.textName);
            textName.setText(String.valueOf(rmk.getText()));
            view.setTag(new Dir(rmk.getId(), "remark"));
            view.setId(cnt);
            workLayout.addView(view);
            workList.add(view);
            cnt++;
        }
    }

}