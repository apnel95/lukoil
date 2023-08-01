package com.example.lukoil;

import static java.lang.Math.pow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.lukoil.entity.Act_doc;
import com.example.lukoil.entity.Act_pump;
import com.example.lukoil.entity.Department_object;
import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.Employee;
import com.example.lukoil.entity.comparation.Act_doc_comparatot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Doc_acts extends List_acts {
    LinearLayout[] linearDepart = new LinearLayout[5];
    LinearLayout viewObjects, viewNH1, viewNH2, viewNH3,viewCPPN, viewCPPD;
    int departmentNow, departmentWas;
    Button bNH1, bNH2, bNH3, bCPPN, bCPPD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doc_acts);
        idForm = 3;
        context = this;
        departmentNow = 31;
        departmentWas = 0;

        allEds = new ArrayList<View>();
        linear = (LinearLayout) findViewById(R.id.layoutForActs);
        viewObjects = (LinearLayout) findViewById(R.id.layoutObjects);

        bNH1 = (Button) viewObjects.findViewById(R.id.buttonNH1);
        bNH2 = (Button) viewObjects.findViewById(R.id.buttonNH2);
        bNH3 = (Button) viewObjects.findViewById(R.id.buttonNH3);
        bCPPN = (Button) viewObjects.findViewById(R.id.buttonCPPN);
        bCPPD = (Button) viewObjects.findViewById(R.id.buttonCPPD);

        viewNH1 = (LinearLayout) findViewById(R.id.layoutForNH1);
        viewNH2 = (LinearLayout) findViewById(R.id.layoutForNH2);
        viewNH3 = (LinearLayout) findViewById(R.id.layoutForNH3);
        viewCPPN = (LinearLayout) findViewById(R.id.layoutForCPPN);
        viewCPPD = (LinearLayout) findViewById(R.id.layoutForCPPD);

        linearDepart[0] = viewNH1;
        linearDepart[1] = viewNH2;
        linearDepart[2] = viewNH3;
        linearDepart[3] = viewCPPN;
        linearDepart[4] = viewCPPD;

        onStartThis();

        uppTextName.setText("Предписания");
        changeStyleButton(bToday, ContextCompat.getColor(context, R.color.white), ContextCompat.getDrawable(context, R.drawable.custom_button_1_click));
        changeStyleButton(bJob, ContextCompat.getColor(context, R.color.white), ContextCompat.getDrawable(context, R.drawable.custom_button_1_click));

        changeStyleButton(bNH1, ContextCompat.getColor(context, R.color.white), ContextCompat.getDrawable(context, R.drawable.custom_button_1_click));
        changeStyleButton(bNH2, ContextCompat.getColor(context, R.color.white), ContextCompat.getDrawable(context, R.drawable.custom_button_1_click));
        changeStyleButton(bNH3, ContextCompat.getColor(context, R.color.white), ContextCompat.getDrawable(context, R.drawable.custom_button_1_click));
        changeStyleButton(bCPPN, ContextCompat.getColor(context, R.color.white), ContextCompat.getDrawable(context, R.drawable.custom_button_1_click));
        changeStyleButton(bCPPD, ContextCompat.getColor(context, R.color.white), ContextCompat.getDrawable(context, R.drawable.custom_button_1_click));

    }

    protected void onStartThis() {
        super.onStart_list(idForm);
        updateList();
    }
    @Override
    public void updateList() {
        for (LinearLayout id : linearDepart){ id.setVisibility(View.GONE); id.removeAllViews();}
        allEds.clear();
        drawActs(acts_doc, (double)0);
    }
    @Override
    public void toPlus(View v){
        Intent Doc_create = new Intent(v.getContext(), Doc_create.class);
        startActivity(Doc_create);
    }
    public void toView(View v){
        Intent Doc_view = new Intent(v.getContext(), Doc_view.class);

        Doc_view.putExtra(Act_doc.class.getSimpleName(), acts_doc.get((int)v.getTag()));

        startActivity(Doc_view);
    }
    public void toNH1(View v){
        System.out.println(departmentNow);
        if ((departmentNow &1)==1){
            changeStyleButton(bNH1, ContextCompat.getColor(v.getContext(), R.color.black), ContextCompat.getDrawable(v.getContext(), R.drawable.custom_button_1));
            departmentNow &=0b11110;
        }
        else{
            changeStyleButton(bNH1, ContextCompat.getColor(context, R.color.white), ContextCompat.getDrawable(context, R.drawable.custom_button_1_click));
            departmentNow |=1;
        }
        updateList();
    }
    public void toNH2(View v){
        System.out.println(departmentNow);
        if ((departmentNow &2)==2){
            changeStyleButton(bNH2, ContextCompat.getColor(v.getContext(), R.color.black), ContextCompat.getDrawable(v.getContext(), R.drawable.custom_button_1));
            departmentNow &=0b11101;
        }
        else{
            changeStyleButton(bNH2, ContextCompat.getColor(context, R.color.white), ContextCompat.getDrawable(context, R.drawable.custom_button_1_click));
            departmentNow |=2;
        }
        updateList();
    }
    public void toNH3(View v){
        System.out.println(departmentNow);
        if ((departmentNow &4)==4){
            changeStyleButton(bNH3, ContextCompat.getColor(v.getContext(), R.color.black), ContextCompat.getDrawable(v.getContext(), R.drawable.custom_button_1));
            departmentNow &=0b11011;
        }
        else{
            changeStyleButton(bNH3, ContextCompat.getColor(context, R.color.white), ContextCompat.getDrawable(context, R.drawable.custom_button_1_click));
            departmentNow |=4;
        }
        updateList();
    }
    public void toCPPN(View v){
        System.out.println(departmentNow);
        if ((departmentNow &8)==8){
            changeStyleButton(bCPPN, ContextCompat.getColor(v.getContext(), R.color.black), ContextCompat.getDrawable(v.getContext(), R.drawable.custom_button_1));
            departmentNow &=0b10111;
        }
        else{
            changeStyleButton(bCPPN, ContextCompat.getColor(context, R.color.white), ContextCompat.getDrawable(context, R.drawable.custom_button_1_click));
            departmentNow |=8;
        }
        updateList();
    }
    public void toCPPD(View v){
        System.out.println(departmentNow);
        if ((departmentNow &16)==16){
            changeStyleButton(bCPPD, ContextCompat.getColor(v.getContext(), R.color.black), ContextCompat.getDrawable(v.getContext(), R.drawable.custom_button_1));
            departmentNow &=0b01111;
        }
        else{
            changeStyleButton(bCPPD, ContextCompat.getColor(context, R.color.white), ContextCompat.getDrawable(context, R.drawable.custom_button_1_click));
            departmentNow |=16;
        }
        updateList();
    }

    public void drawActs(ArrayList<Act_doc> acts, double i){
        for (Act_doc act:acts) if(act.getDate_time_stop() == null) act.setDate_time_stop(new Date());
        ArrayList<Integer> idObjects = new ArrayList<Integer>();
        departmentWas = 0;
        Collections.sort(acts, new Act_doc_comparatot());
        Date nowDate = new Date(0,0,1);
        int cnt = 0;
        for (Act_doc act: acts) {
            int q = (int) pow(2, act.getId_department())/2;
            if ((statusNow == 2 || statusNow == ((act.getId_status() == STATUS_JOB)?0:1) || statusNow == ((act.getId_status() == STATUS_READY)?1:0)) && ((timeNow == 3) || ((timeNow == 0) && ((long) ((trim(new Date())).getTime() - trim(act.getDate_time_stop()).getTime()) < 86400000)) || ((timeNow == 1) && ((long) ((trim(new Date())).getTime() - trim(act.getDate_time_stop()).getTime()) < 86400000 * 7)) || ((timeNow == 2) && ((long) ((trim(new Date())).getTime() - trim(act.getDate_time_stop()).getTime()) < 2678400000L)))) {
                if ((act.getId_department() == NH1 && (departmentNow&1)==1) || (act.getId_department() == NH2 && (departmentNow&2)==2) || (act.getId_department() == NH3 && (departmentNow&4)==4) || (act.getId_department() == CPPN && (departmentNow&8)==8) || (act.getId_department() == CPPD && (departmentNow&16)==16)) {
                    if ((departmentWas & q) == 0) {
                        final View view4 = getLayoutInflater().inflate(R.layout.custom_block_type_name, null);
                        TextView textName1 = (TextView) view4.findViewById(R.id.textName);
                        String str = "";
                        for (Dir dir : departments)
                            if (dir.getId() == act.getId_department()) {
                                str = dir.getName();
                                break;
                            }
                        textName1.setText("" + str);
                        linearDepart[act.getId_department()].setVisibility(View.VISIBLE);
                        allEds.add(view4);
                        linearDepart[act.getId_department()].addView(view4);
                        departmentWas |= q;
                    }
                    if(idObjects.contains(act.getId_department()*10000+act.getId_department_object())){
                    }
                    else{
                        final View view5 = getLayoutInflater().inflate(R.layout.custom_block_object, null);
                        TextView textName2 = (TextView) view5.findViewById(R.id.textName1);
                        String str = "";
                        for (Department_object obj: department_objects) if(obj.getId() == act.getId_department_object()){str = obj.getName(); break;}
                        textName2.setText(""+str);
                        allEds.add(view5);
                        linearDepart[act.getId_department()].addView(view5);
                        idObjects.add(act.getId_department()*10000+act.getId_department_object());
                    }

                    if (trim(act.getDate_time_stop()).equals(trim(nowDate))) {
                    } else {
                        final View view = getLayoutInflater().inflate(R.layout.custom_block_date, null);
                        TextView textDate = (TextView) view.findViewById(R.id.dateText);
                        nowDate = act.getDate_time_stop();
                        textDate.setText(DateToText(nowDate));
                        allEds.add(view);
                        linearDepart[act.getId_department()].addView(view);
                    }
                    nowDate = act.getDate_time_stop();
                    final View view1 = getLayoutInflater().inflate(R.layout.custom_block_name, null);
                    TextView textTime = (TextView) view1.findViewById(R.id.textTime);
                    TextView textName = (TextView) view1.findViewById(R.id.textName);
                    ImageView status = (ImageView) view1.findViewById(R.id.status);
                    view1.setTag((int) cnt);
                    if (act.getId_status() == STATUS_READY) status.setImageResource(R.drawable.job_green);
                    String str = "";
                    for (Employee emp: employees) if(emp.getId() == act.getId_employee()){str = emp.getFIO(); break;}
                    textName.setText("Выдано: " + str);
                    SimpleDateFormat formatForDate = new SimpleDateFormat("HH:mm");
                    textTime.setText(formatForDate.format(nowDate));
                    allEds.add(view1);
                    linearDepart[act.getId_department()].addView(view1);
                    cnt++;
                }
            }
        }
    }
}
