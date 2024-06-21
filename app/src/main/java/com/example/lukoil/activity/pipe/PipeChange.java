package com.example.lukoil.activity.pipe;

import static com.example.lukoil.ListData.actStatuses;
import static com.example.lukoil.ListData.employees;
import static com.example.lukoil.ListData.pipeActs;
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

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.lukoil.activity.Activity;
import com.example.lukoil.activity.CustomDialogForDelete;
import com.example.lukoil.activity.CustomDialogForList;
import com.example.lukoil.activity.GeneralCreateChangeViewAct;
import com.example.lukoil.R;
import com.example.lukoil.interfaces.RemovableList;
import com.example.lukoil.entity.act.ActPipe;
import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.Employee;

import java.util.ArrayList;
import java.util.Objects;

public class PipeChange extends GeneralCreateChangeViewAct implements RemovableList {

    ActPipe act;
    EditText diameter, wall, piketach,  leak_location,   area, leak_parameter;
    Spinner name, coating, leak_type, subst, who, status;
    TextView events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getIntent().getExtras();
        String nameActivity = Objects.requireNonNull(arguments).getString("nameActivity");
        int cntAct = (Objects.requireNonNull(arguments).getInt("idAct"));
        act = getActPipeForCnt(cntAct);

        Activity activity = new Activity(ID_ACTIVITY_PIPE, this, R.layout.pipe_change, R.id.layoutForActs, new ArrayList<View>(), R.id.layout_menu, nameActivity);
        super.onStartList(activity);


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

        //updateEvents();

        ArrayAdapter<String> nameA = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sPipeNames);
        nameA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        name.setAdapter(nameA);

        ArrayAdapter<String> coatingA = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sPipeCoatingTypes);
        coatingA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coating.setAdapter(coatingA);

        ArrayAdapter<String> leak_typeA = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sPipeLeakTypes);
        leak_typeA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leak_type.setAdapter(leak_typeA);

        ArrayAdapter<String> substA = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sPipeSubstances);
        substA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subst.setAdapter(substA);

        ArrayAdapter<String> whoA = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sEmployees);
        whoA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        who.setAdapter(whoA);

        ArrayAdapter<String> statusA = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sActStatuses);
        statusA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(statusA);

        name.setSelection(getCntToID(pipeNames, act.getIdPipe()));
        coating.setSelection(getCntToID(pipeCoatingTypes, act.getIdTypeCoating()));
        leak_type.setSelection(getCntToID(pipeLeakTypes, act.getIdLeakType()));
        subst.setSelection(getCntToID(pipeSubstances, act.getIdSubstance()));
        status.setSelection(getCntToID(actStatuses, act.getIdStatus()));
        who.setSelection(getCntToIdForEmployee(employees, act.getIdWho()));

        eventDateTimeList = new ArrayList<View>();
        eventDateTimeLayout = findViewById(R.id.layoutEvents);
        drawEvents(act.getEvents(), R.layout.custom_event_date_time_view);
    }
    @Override
    public void toPlus(View v) {
        Intent pipeChange = new Intent(CONTEXT, PipeChange.class);
        pipeChange.putExtra("idAct", -1 );
        pipeChange.putExtra("nameActivity", getResources().getString(R.string.createAct));
        startActivity(pipeChange);
    }

    public void toEventChange(View v) {
        drawEvents(act.getEvents(), R.layout.custom_event_date_time_change);
        drawButtonsToControlEvent();
    }

    protected void drawButtonsToControlEvent() {
        View view = getLayoutInflater().inflate(R.layout.custom_button_controls, null);
        TextView save = view.findViewById(R.id.save);
        TextView plus = view.findViewById(R.id.plus);
        eventDateTimeLayout.addView(view);
        eventDateTimeList.add(view);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawEvents(act.getEvents(), R.layout.custom_event_date_time_view);
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEvent();
            }
        });
    }


    public void toEventView(View v) {
        drawEvents(act.getEvents(), R.layout.custom_event_date_time_view);
    }

    @Override
    protected void toSaveAct(View v) {
        Log.d("toSaveAct", "start");
        setDataToAct();
        updateAct();
        boolean newAct = true;
        for (ActPipe a: pipeActs){
            if (a.getId() == act.getId()){
                pipeActs.set(pipeActs.indexOf(a), act);
                newAct = false;
                break;
            }
        }
        if (newAct) pipeActs.add(act);




        finish();
    }
    protected void setDataToAct() {
        for (Dir dir: pipeNames) if(dir.getName() == name.getSelectedItem()) act.setIdPipe(dir.getId());
        for (Dir dir: pipeCoatingTypes) if(dir.getName().equals(coating.getSelectedItem().toString())) act.setId_type_coating(dir.getId());
        for (Dir dir: pipeLeakTypes) if(dir.getName().equals(leak_type.getSelectedItem().toString())) act.setId_leak_type(dir.getId());
        for (Dir dir: pipeSubstances) if(dir.getName().equals(subst.getSelectedItem().toString())) act.setId_substance(dir.getId());
        for (Dir dir: actStatuses) if(dir.getName().equals(status.getSelectedItem().toString())) act.setIdStatus(dir.getId());
        for (Employee emp: employees) if (emp.getFIO().equals(who.getSelectedItem().toString())) act.setId_who(emp.getId());
        //for (Employee dir: employees) if(dir.getFIO().equals(who.getSelectedItem().toString().substring(0 , who.getSelectedItem().toString().indexOf(",")))) act.setId_who(dir.getId());
        act.setDiameter(Integer.parseInt(diameter.getText().toString()));
        act.setWall(Integer.parseInt(wall.getText().toString()));
        act.setPiketash(Integer.parseInt(piketach.getText().toString()));
        act.setLeak_parameter(Integer.parseInt(leak_parameter.getText().toString()));
        act.setLeak_position(Integer.parseInt(leak_location.getText().toString()));
        act.setSpill_area(Double.parseDouble(area.getText().toString()));
    }

    public void toDelete(View v){
        Log.d("toDelete", "start");

        CustomDialogForDelete dialog = new CustomDialogForDelete();
        Bundle args = new Bundle();

        View vP = (View) v.getParent();
        args.putInt("idEvent", ((Dir) vP.getTag()).getId());
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "custom");
    }

    @Override
    public void deleteEvent(int idEvent) {
        act.getEvents().removeIf(nextEvent -> nextEvent.getId() == idEvent);
        drawEvents(act.getEvents(), R.layout.custom_event_date_time_change);
        drawButtonsToControlEvent();
    }
    public void addEvent() {
        act.addEvent();
        drawEvents(act.getEvents(), R.layout.custom_event_date_time_change);
        drawButtonsToControlEvent();
    }


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

