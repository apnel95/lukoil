package com.example.lukoil;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.lukoil.entity.Act_trub;
import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.Employee;
import com.example.lukoil.entity.Event_date_time;
import com.example.lukoil.entity.Remark;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;

public class Trub_change extends Create_change_view_act {

    Act_trub act;
    EditText diameter, wall, piketach,  leak_location,   area, leak_parameter;
    Spinner name, coating, leak_type, subst, who, status;
    TextView events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trub_change);
        idForm = 1;

        allEds = new ArrayList<View>();
        linear = findViewById(R.id.layoutPhoto);

        Bundle arguments = getIntent().getExtras();
        act = (Act_trub) arguments.getSerializable(Act_trub.class.getSimpleName());
        context = this;

        onStartNotHome(idForm);

        uppTextName.setText("Изменение акта");

        name = findViewById(R.id.spinnerName);
        diameter = findViewById(R.id.diameter);
        wall = findViewById(R.id.wall);
        coating = findViewById(R.id.spinnerCoating);
        piketach = findViewById(R.id.piketach);
        events = findViewById(R.id.events);
        leak_type = findViewById(R.id.spinner_leak_type);
        leak_parameter = findViewById(R.id.leak_parameter);
        leak_location = findViewById(R.id.leak_location);
        who = findViewById(R.id.spinner_who);
        area = findViewById(R.id.area);
        subst = findViewById(R.id.spinner_subst);
        status = findViewById(R.id.spinner_status);

        diameter.setText(String.valueOf(act.getDiameter()));
        wall.setText(String.valueOf(act.getWall()));
        piketach.setText(String.valueOf(act.getPiketash()));
        leak_location.setText(String.valueOf(act.getLeak_position()));
        leak_parameter.setText(String.valueOf(act.getLeak_parameter()));
        area.setText(String.valueOf(act.getSpill_area()));

        String textForEvents = "";
        if (act.getWorks() != null) for (Event_date_time wrk : act.getWorks()) textForEvents += wrk.getName(event_types) + ": " + DateToText(wrk.getDate_time()) + "\n";
        events.setText(textForEvents);

        ArrayAdapter<String> nameA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Strubs);
        nameA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        name.setAdapter(nameA);

        ArrayAdapter<String> coatingA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Stypes_coating);
        coatingA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coating.setAdapter(coatingA);

        ArrayAdapter<String> leak_typeA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Stypes_leak);
        leak_typeA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leak_type.setAdapter(leak_typeA);

        ArrayAdapter<String> substA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Ssubstances);
        substA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subst.setAdapter(substA);

        ArrayAdapter<String> whoA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Semployees);
        whoA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        who.setAdapter(whoA);

        ArrayAdapter<String> statusA = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Sstatuses_act);
        statusA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(statusA);

        int cnt=0;
        for(Dir dir: trubs){
            if(dir.getId() == act.getId_trub()){
                name.setSelection(cnt);
                break;
            }
            cnt++;
        }

        cnt=0;
        for(Dir dir: types_coating){
            if(dir.getId() == act.getId_type_coating()){
                coating.setSelection(cnt);
                break;
            }
            cnt++;
        }

        cnt=0;
        for(Dir dir: types_leak){
            if(dir.getId() == act.getId_leak_type()){
                leak_type.setSelection(cnt);
                break;
            }
            cnt++;
        }
        cnt=0;
        for(Dir dir: substances){
            if(dir.getId() == act.getId_substance()){
                subst.setSelection(cnt);
                break;
            }
            cnt++;
        }
        cnt=0;
        for(Employee dir: employees){
            if(dir.getId() == act.getId_who()){
                who.setSelection(cnt);
                break;
            }
            cnt++;
        }
        cnt=0;
        for(Dir dir: statuses_act){
            if(dir.getId() == act.getId_status()){
                status.setSelection(cnt);
                break;
            }
            cnt++;
        }


    }

    public void toPlus(View v) {
        Intent Plus = new Intent(v.getContext(), Trub_create.class);
        startActivity(Plus);
    }

    public void toEventChange(View v) {
        Intent events_change = new Intent(v.getContext(), Event_date_time_change.class);
        events_change.putExtra(ArrayList.class.getSimpleName(), act.getWorks());
        startActivity(events_change);
    }
    @Override
    public void toSave(View v) {
        getIds();
        act.setDiameter(Integer.parseInt(diameter.getText().toString()));
        act.setWall(Integer.parseInt(wall.getText().toString()));
        act.setPiketash(Integer.parseInt(piketach.getText().toString()));
        act.setLeak_parameter(Integer.parseInt(leak_parameter.getText().toString()));
        act.setLeak_position(Integer.parseInt(leak_location.getText().toString()));
        act.setSpill_area(Double.parseDouble(area.getText().toString()));
        updateAct();
        finish();
    }
    private void getIds() {
        for (Dir dir: trubs) if(dir.getName() == name.getSelectedItem()) act.setId_trub(dir.getId());
        for (Dir dir: types_coating) if(dir.getName().equals(coating.getSelectedItem().toString())) act.setId_type_coating(dir.getId());
        for (Dir dir: types_leak) if(dir.getName().equals(leak_type.getSelectedItem().toString())) act.setId_leak_type(dir.getId());
        for (Dir dir: substances) if(dir.getName().equals(subst.getSelectedItem().toString())) act.setId_substance(dir.getId());
        for (Dir dir: statuses_act) if(dir.getName().equals(status.getSelectedItem().toString())) act.setId_status(dir.getId());
        for (Employee dir: employees) if(dir.getFIO().equals(who.getSelectedItem().toString().substring(0 , who.getSelectedItem().toString().indexOf(",")))) act.setId_who(dir.getId());
    }

    private void updateAct() {
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
    }
}

