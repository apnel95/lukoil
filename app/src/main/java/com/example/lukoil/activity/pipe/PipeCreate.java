package com.example.lukoil.activity.pipe;

import static com.example.lukoil.ListData.actStatuses;
import static com.example.lukoil.ListData.employees;
import static com.example.lukoil.ListData.pipeCoatingTypes;
import static com.example.lukoil.ListData.pipeLeakTypes;
import static com.example.lukoil.ListData.pipeNames;
import static com.example.lukoil.ListData.pipeSubstances;
import static com.example.lukoil.ListData.sActStatuses;
import static com.example.lukoil.ListData.sEmployees;
import static com.example.lukoil.ListData.sPipeCoatingTypes;
import static com.example.lukoil.ListData.sPipeLeakTypes;
import static com.example.lukoil.ListData.sPipeNames;
import static com.example.lukoil.ListData.sPipeSubstances;

import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.lukoil.activity.Activity;
import com.example.lukoil.activity.General;
import com.example.lukoil.activity.GeneralCreateChangeViewAct;
import com.example.lukoil.R;
import com.example.lukoil.entity.act.ActPipe;
import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.Employee;
import com.example.lukoil.entity.event.EventDateTime;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;

public class PipeCreate extends GeneralCreateChangeViewAct {
    ActPipe act;
    EditText diameter, wall, piketach, leak_parameter, leak_location, area;
    Spinner name, coating, leak_type, subst, who, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Activity activity = new Activity(General.ID_ACTIVITY_PIPE_PLUS, this, R.layout.trub_create, R.id.layoutForActs, new ArrayList<View>(), R.id.layout_menu, "Создание акта");
        super.onStartList(activity);

        name = findViewById(R.id.name);
        diameter = findViewById(R.id.diameter);
        wall = findViewById(R.id.wall);
        coating = findViewById(R.id.coating);
        piketach = findViewById(R.id.piketach);
        leak_type = findViewById(R.id.leak_type);
        leak_parameter = findViewById(R.id.leak_parameter);
        leak_location = findViewById(R.id.leak_location);
        who = findViewById(R.id.employee);
        area = findViewById(R.id.area);
        subst = findViewById(R.id.subst);
        status = findViewById(R.id.status);

        EditText dateTime = findViewById(R.id.dateTime);

        ArrayAdapter<String> nameA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sPipeNames);
        nameA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        name.setAdapter(nameA);

        ArrayAdapter<String> coatingA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sPipeCoatingTypes);
        coatingA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coating.setAdapter(coatingA);

        ArrayAdapter<String> leak_typeA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sPipeLeakTypes);
        leak_typeA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leak_type.setAdapter(leak_typeA);

        ArrayAdapter<String> substA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sPipeSubstances);
        substA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subst.setAdapter(substA);

        ArrayAdapter<String> whoA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sEmployees);
        whoA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        who.setAdapter(whoA);

        ArrayAdapter<String> statusA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sActStatuses);
        statusA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(statusA);

        dateTime.setText(DateUtils.formatDateTime(this, dateAndTime.getTimeInMillis(), DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_TIME));

    }
    @Override
    public void toPlus(View v) {
        finish();
    }
    @Override
    public void toSave(View v){
        getIds();
        act.setDiameter(Integer.parseInt(diameter.getText().toString()));
        act.setWall(Integer.parseInt(wall.getText().toString()));
        act.setPiketash(Integer.parseInt(piketach.getText().toString()));
        act.setLeak_parameter(Integer.parseInt(leak_parameter.getText().toString()));
        act.setLeak_position(Integer.parseInt(leak_location.getText().toString()));
        act.setSpill_area(Double.parseDouble(area.getText().toString()));
        ArrayList<EventDateTime> events = new ArrayList<>();
        events.add( new EventDateTime(0,0, ID_DATE_TIME_STOP_WORK, dateAndTime.getTime()));
        act.setEvents(events);
        getNewActTrub();
        finish();
    }

    private void getIds() {
        for (Dir dir: pipeNames) if(dir.getName() == name.getSelectedItem()) act.setIdPipe(dir.getId());
        for (Dir dir: pipeCoatingTypes) if(dir.getName().equals(coating.getSelectedItem().toString())) act.setId_type_coating(dir.getId());
        for (Dir dir: pipeLeakTypes) if(dir.getName().equals(leak_type.getSelectedItem().toString())) act.setId_leak_type(dir.getId());
        for (Dir dir: pipeSubstances) if(dir.getName().equals(subst.getSelectedItem().toString())) act.setId_substance(dir.getId());
        for (Dir dir: actStatuses) if(dir.getName().equals(status.getSelectedItem().toString())) act.setIdStatus(dir.getId());
        for (Employee dir: employees) if(dir.getFIO().equals(who.getSelectedItem().toString().substring(0 , who.getSelectedItem().toString().indexOf(",")))) act.setId_who(dir.getId());
    }

    private void getNewActTrub() {
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
