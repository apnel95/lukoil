package com.example.lukoil.activity.doc;

import static com.example.lukoil.ListData.actStatuses;
import static com.example.lukoil.ListData.docActs;
import static com.example.lukoil.ListData.docDepartmentObjects;
import static com.example.lukoil.ListData.docDepartments;
import static com.example.lukoil.ListData.employees;
import static com.example.lukoil.ListData.sActStatuses;
import static com.example.lukoil.ListData.sDocDepartments;
import static com.example.lukoil.ListData.sEmployees;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.lukoil.activity.Activity;
import com.example.lukoil.activity.GeneralCreateChangeViewAct;
import com.example.lukoil.R;
import com.example.lukoil.entity.act.ActDoc;
import com.example.lukoil.entity.DepartmentObject;
import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.Employee;
import com.example.lukoil.entity.act.ActPump;
import com.example.lukoil.entity.remark.Remark;
import com.example.lukoil.entity.work.WorkDoc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DocChange extends GeneralCreateChangeViewAct{
    ActDoc act;
    EditText FIO_sending;
    Spinner struct, object, employee, status;
    TextView events, remarks, works;
    ArrayAdapter<String> objectA;
    ArrayList<String> new_Sdepartment_objects = new ArrayList<>();

    protected List<View> workList, remarkList;
    protected LinearLayout workLayout, remarkLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getIntent().getExtras();
        act = (ActDoc) Objects.requireNonNull(arguments).getSerializable(ActDoc.class.getSimpleName());
        String nameActivity = arguments.getString("nameActivity");

        Activity activity = new Activity(ID_ACTIVITY_DOC, this, R.layout.doc_change, R.id.layoutPhoto, new ArrayList<View>(), R.id.layout_menu, nameActivity);
        super.onStartList(activity);

        struct = findViewById(R.id.struct);
        object = findViewById(R.id.object);
        employee = findViewById(R.id.employee);
        status = findViewById(R.id.status);
        events = findViewById(R.id.events);
        remarks = findViewById(R.id.remarks);
        works = findViewById(R.id.works);
        FIO_sending = findViewById(R.id.FIO_sending);

        FIO_sending.setText(String.valueOf(act.getFIOSending()));

        ArrayAdapter<String> structA = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sDocDepartments);
        structA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        struct.setAdapter(structA);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {setNewString();}
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        };
        struct.setOnItemSelectedListener(itemSelectedListener);

        objectA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, new_Sdepartment_objects);
        objectA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        object.setAdapter(objectA);

        ArrayAdapter<String> employeeA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sEmployees);
        employeeA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        employee.setAdapter(employeeA);

        ArrayAdapter<String> statusA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sActStatuses);
        statusA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(statusA);

        struct.setSelection(getCntToID(docDepartments, act.getIdDepartment()));
        status.setSelection(getCntToID(actStatuses, act.getIdStatus()));
        employee.setSelection(getCntToIdForEmployee(employees, act.getIdEmployee()));

        setNewString();

        int cnt=0;
        for(DepartmentObject dir: docDepartmentObjects){
            if(dir.getId() == act.getIdDepartmentObject()){
                for (String str: new_Sdepartment_objects){
                    if (str.equals(dir.getName())){ object.setSelection(cnt); break;}
                    cnt++;
                }
                break;
            }
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
    public void toPlus(View v) {
        Intent docCreate = new Intent(CONTEXT, DocChange.class);
        docCreate.putExtra(ActPump.class.getSimpleName(), new ActDoc());
        docCreate.putExtra("nameActivity", getResources().getString(R.string.createDoc));
        startActivity(docCreate);
    }
    private void setNewString() {
        int id = -1;
        for(Dir dep: docDepartments) if(dep.getName().equals(struct.getSelectedItem().toString())){ id = dep.getId();break;}
        System.out.println(id);
        new_Sdepartment_objects = new ArrayList<>();
        for (DepartmentObject dir: docDepartmentObjects) if(dir.getIdDep() ==  id) new_Sdepartment_objects.add(dir.getName());
        System.out.println(new_Sdepartment_objects);
        objectA.clear();
        objectA.addAll(new_Sdepartment_objects);
    }

    public void toChangeRemarks(View v) { drawRemarks(act.getRemarks(), R.layout.custom_remark_change); }
    public void toChangeWorks(View v) { drawDocWorks(act.getWorks(), R.layout.custom_work_change); }
    public void toEventChange(View v) { drawEvents(act.getEvents(), R.layout.custom_event_date_time_change); }

    public void toSave(View v) {
        setDataToAct();
        if (LINE_STATUS == ONLINE_STATUS) updateAct();
        if (docActs.contains(act.getId())) docActs.set(docActs.indexOf(act.getId()), act);
        else docActs.add(act);

        finish();
    }

    protected void setDataToAct() {
        for (DepartmentObject dir: docDepartmentObjects) if(dir.getName() == object.getSelectedItem()) act.setIdDepartmentObject(dir.getId());
        for (Dir dir: actStatuses) if(dir.getName().equals(status.getSelectedItem().toString())) act.setIdStatus(dir.getId());
        for (Employee dir: employees) if(dir.getFIO().equals(employee.getSelectedItem().toString().substring(0 , employee.getSelectedItem().toString().indexOf(",")))) act.setIdEmployee(dir.getId());
        act.setFIO_senging(FIO_sending.getText().toString());
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

    /*@Override
    protected void toDelete(View v){
        super.toDelete(v);
        Dir tag = new Dir((Dir) v.getTag());
        if (tag.getName().equals("event")) act.getEvents().removeIf(nextEvent -> nextEvent.getId() == tag.getId());
        else if(tag.getName().equals("docWork")) act.getWorks().removeIf(nextWork -> nextWork.getId() == tag.getId());
        else act.getRemarks().removeIf(nextWork -> nextWork.getId() == tag.getId());
    }*/

    /*private void updateAct() {
        Thread thread = new Thread(() -> {
            System.out.println("Waiting for connection");
            ObjectOutputStream outgetboard;
            Socket clientSocket, upClientSocket;
            try {
                clientSocket = new Socket(HOST, PORT);
                OutputStream outToServer = clientSocket.getOutputStream();
                outgetboard = new ObjectOutputStream(outToServer);

                upClientSocket = new Socket(HOST, UP_PORT);
                OutputStream outToUpdateServer = upClientSocket.getOutputStream();
                ObjectOutputStream outUpdate = new ObjectOutputStream(outToUpdateServer);

                System.out.println("Client connected to socket");
                Runtime.getRuntime().addShutdownHook(new Thread( () -> {
                    try {
                        HashMap.Entry<String, Object> output2 = new AbstractMap.SimpleEntry<>("QUIT", null);
                        outgetboard.writeObject(output2);
                        clientSocket.close();
                        outUpdate.writeObject(output2);
                        upClientSocket.close();
                        System.out.println("Closing the connection");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }));
            } catch (UnknownHostException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            HashMap.Entry<String, Object> output;
            output = new AbstractMap.SimpleEntry<>("UPDATE", act);
            try {
                outgetboard.writeObject(output);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            Log.d("Clown", e.toString());
        }
    }*/
}
