package com.example.lukoil.activity.doc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.lukoil.activity.CreateChangeViewAct;
import com.example.lukoil.R;
import com.example.lukoil.activity.remark.RemarkChange;
import com.example.lukoil.activity.work.WorkChange;
import com.example.lukoil.entity.act.ActDoc;
import com.example.lukoil.entity.DepartmentObject;
import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.Employee;
import com.example.lukoil.entity.event.EventDateTime;
import com.example.lukoil.entity.remark.Remark;
import com.example.lukoil.entity.work.Work;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;

public class DocCreate extends CreateChangeViewAct {
    ActDoc act = new ActDoc();
    EditText FIO_sending;
    Spinner struct, object, employee, status;
    TextView remarks, works;
    ArrayAdapter<String> objectA;
    ArrayList<String> new_Sdepartment_objects = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doc_create);
        idForm = 13;

        workplaceElements = new ArrayList<View>();
        workplace = findViewById(R.id.layoutPhoto);

        context = this;

        onStartNotHome(idForm);

        topTitleActivity.setText("Создание предписания");

        struct = findViewById(R.id.struct);
        object = findViewById(R.id.object);
        employee = findViewById(R.id.employee);
        status = findViewById(R.id.status);
        remarks = findViewById(R.id.remarks);
        works = findViewById(R.id.works);
        FIO_sending = findViewById(R.id.FIO_sending);

        ArrayAdapter<String> structA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Sdepartments);
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

        ArrayAdapter<String> employeeA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Semployees);
        employeeA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        employee.setAdapter(employeeA);

        ArrayAdapter<String> statusA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Sstatuses_act);
        statusA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(statusA);
    }

    private void setNewString() {
        int id = -1;
        for(Dir dep: departments) if(dep.getName().equals(struct.getSelectedItem().toString())){ id = dep.getId();break;}
        System.out.println(id);
        new_Sdepartment_objects = new ArrayList<>();
        for (DepartmentObject dir: DepartmentObjects) if(dir.getId_dep() ==  id) new_Sdepartment_objects.add(dir.getName());
        System.out.println(new_Sdepartment_objects);
        objectA.clear();
        objectA.addAll(new_Sdepartment_objects);
    }

    @Override
    public void toPlus(View v) {finish();}
    public void toChangeWorks(View v) {
        Intent events_change = new Intent(v.getContext(), WorkChange.class);
        events_change.putExtra(ArrayList.class.getSimpleName(), act.getWorks());
        startActivityForResult(events_change, 2);
    }
    public void toChangeRemarks(View v) {
        Intent events_change = new Intent(v.getContext(), RemarkChange.class);
        events_change.putExtra(ArrayList.class.getSimpleName(), act.getRemarks());
        startActivityForResult(events_change, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if(requestCode == 1){
                act.setRemarks((ArrayList<Remark>) data.getExtras().getSerializable(ArrayList.class.getSimpleName()));
                String textForRemark = "";
                if (act.getRemarks() != null) for (Remark rmk : act.getRemarks()) textForRemark +=(rmk.getText()) + "\n";
                remarks.setText(textForRemark);
            }
            if(requestCode == 2){
                act.setWorks((ArrayList<Work>) data.getExtras().getSerializable(ArrayList.class.getSimpleName()));
                String textForWorks = "";
                if (act.getWorks() != null) for (Work wrks : act.getWorks()) textForWorks += wrks.getName() + ": " + (wrks.getText()) + "\n";
                works.setText(textForWorks);
            }
        }
    }
    @Override
    public void toSave(View v) {
        getIds();
        act.setFIO_senging(FIO_sending.getText().toString());
        ArrayList<EventDateTime> events1 = new ArrayList<>();
        events1.add( new EventDateTime(0,0, idDateTimeStopWork, dateAndTime.getTime()));
        act.setEvents(events1);
        getNewActDoc();
        finish();
    }

    private void getIds() {
        for (DepartmentObject dir: DepartmentObjects) if(dir.getName() == object.getSelectedItem()) act.setId_department_object(dir.getId());
        for (Dir dir: statuses_act) if(dir.getName().equals(status.getSelectedItem().toString())) act.setId_status(dir.getId());
        for (Employee dir: employees) if(dir.getFIO().equals(employee.getSelectedItem().toString().substring(0 , employee.getSelectedItem().toString().indexOf(",")))) act.setId_employee(dir.getId());
    }
    private void getNewActDoc() {
        Thread thread = new Thread(() -> {
            System.out.println("Waiting for connection");
            ObjectOutputStream outgetboard;
            Socket clientSocket, upClientSocket;
            try {
                clientSocket = new Socket(HOST, PORT);
                OutputStream outToServer = clientSocket.getOutputStream();
                outgetboard = new ObjectOutputStream(outToServer);

                upClientSocket = new Socket(HOST, upPORT);
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
            output = new AbstractMap.SimpleEntry<>("INSERT", act);
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
    }
}
