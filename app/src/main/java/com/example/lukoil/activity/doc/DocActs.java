package com.example.lukoil.activity.doc;

import static com.example.lukoil.GeneraActList.trim;
import static com.example.lukoil.ListData.docDepartmentObjects;
import static com.example.lukoil.ListData.docDepartments;
import static com.example.lukoil.ListData.employees;
import static java.lang.Math.pow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.lukoil.activity.Activity;
import com.example.lukoil.activity.ListActs;
import com.example.lukoil.R;
import com.example.lukoil.entity.act.ActDoc;
import com.example.lukoil.entity.DepartmentObject;
import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.Employee;
import com.example.lukoil.entity.comparation.ActDocComparatot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class DocActs extends ListActs {
    LinearLayout[] linearDepart = new LinearLayout[5];
    LinearLayout viewObjects, viewNH1, viewNH2, viewNH3,viewCPPN, viewCPPD;
    int departmentNow, departmentWas;
    Button bNH1, bNH2, bNH3, bCPPN, bCPPD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Activity activity = new Activity(ID_ACTIVITY_DOC, this, R.layout.doc_acts, R.id.layoutForActs, new ArrayList<View>(), R.id.layout_menu, "Список предписаний");
        super.onStartList(activity);

        departmentNow = 31;
        departmentWas = 0;

        viewObjects = findViewById(R.id.layoutObjects);

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

        updateList();

        changeStyleButton(bToday, ContextCompat.getColor(CONTEXT, R.color.white), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1_click));
        changeStyleButton(bJob, ContextCompat.getColor(CONTEXT, R.color.white), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1_click));

        changeStyleButton(bNH1, ContextCompat.getColor(CONTEXT, R.color.white), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1_click));
        changeStyleButton(bNH2, ContextCompat.getColor(CONTEXT, R.color.white), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1_click));
        changeStyleButton(bNH3, ContextCompat.getColor(CONTEXT, R.color.white), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1_click));
        changeStyleButton(bCPPN, ContextCompat.getColor(CONTEXT, R.color.white), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1_click));
        changeStyleButton(bCPPD, ContextCompat.getColor(CONTEXT, R.color.white), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1_click));

    }
    @Override
    public void updateList() {
        for (LinearLayout id : linearDepart){ id.setVisibility(View.GONE); id.removeAllViews();}
        WORK_PLACE_ELEMENTS.clear();
//        drawActs(docActs, (double)0);
    }
    @Override
    public void toPlus(View v){
        Intent Doc_create = new Intent(v.getContext(), DocCreate.class);
        startActivity(Doc_create);
    }
    public void toView(View v){
        Intent Doc_view = new Intent(v.getContext(), DocView.class);

        Doc_view.putExtra(ActDoc.class.getSimpleName(), LIST_ACT_DOC.get((int)v.getTag()));

        startActivity(Doc_view);
    }
    public void toNH1(View v){
        System.out.println(departmentNow);
        if ((departmentNow &1)==1){
            changeStyleButton(bNH1, ContextCompat.getColor(v.getContext(), R.color.black), ContextCompat.getDrawable(v.getContext(), R.drawable.custom_button_1));
            departmentNow &=0b11110;
        }
        else{
            changeStyleButton(bNH1, ContextCompat.getColor(CONTEXT, R.color.white), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1_click));
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
            changeStyleButton(bNH2, ContextCompat.getColor(CONTEXT, R.color.white), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1_click));
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
            changeStyleButton(bNH3, ContextCompat.getColor(CONTEXT, R.color.white), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1_click));
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
            changeStyleButton(bCPPN, ContextCompat.getColor(CONTEXT, R.color.white), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1_click));
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
            changeStyleButton(bCPPD, ContextCompat.getColor(CONTEXT, R.color.white), ContextCompat.getDrawable(CONTEXT, R.drawable.custom_button_1_click));
            departmentNow |=16;
        }
        updateList();
    }
    public void drawActs(ArrayList<ActDoc> acts, double i){
        for (ActDoc act:acts) if(act.getDateTimeStop() == null) act.setDateTimeStop(new Date());
        ArrayList<Integer> idObjects = new ArrayList<Integer>();
        departmentWas = 0;
        Collections.sort(acts, new ActDocComparatot());
        Date nowDate = new Date(0,0,1);
        int cnt = 0;
        for (ActDoc act: acts) {
            int q = (int) pow(2, act.getIdDepartment())/2;
            if ((statusNow == 2 || statusNow == ((act.getIdStatus() == ACT_STATUS_JOB)?0:1) || statusNow == ((act.getIdStatus() == ACT_STATUS_READY)?1:0)) && ((timeNow == 3) || ((timeNow == 0) && ((long) ((trim(new Date())).getTime() - trim(act.getDateTimeStop()).getTime()) < 86400000)) || ((timeNow == 1) && ((long) ((trim(new Date())).getTime() - trim(act.getDateTimeStop()).getTime()) < 86400000 * 7)) || ((timeNow == 2) && ((long) ((trim(new Date())).getTime() - trim(act.getDateTimeStop()).getTime()) < 2678400000L)))) {
                if ((act.getIdDepartment() == NH1 && (departmentNow&1)==1) || (act.getIdDepartment() == NH2 && (departmentNow&2)==2) || (act.getIdDepartment() == NH3 && (departmentNow&4)==4) || (act.getIdDepartment() == CPPN && (departmentNow&8)==8) || (act.getIdDepartment() == CPPD && (departmentNow&16)==16)) {
                    if ((departmentWas & q) == 0) {
                        final View view4 = getLayoutInflater().inflate(R.layout.custom_block_type_name, null);
                        TextView textName1 = (TextView) view4.findViewById(R.id.textName);
                        String str = "";
                        for (Dir dir : docDepartments)
                            if (dir.getId() == act.getIdDepartment()) {
                                str = dir.getName();
                                break;
                            }
                        textName1.setText("" + str);
                        linearDepart[act.getIdDepartment()].setVisibility(View.VISIBLE);
                        WORK_PLACE_ELEMENTS.add(view4);
                        linearDepart[act.getIdDepartment()].addView(view4);
                        departmentWas |= q;
                    }
                    if(idObjects.contains(act.getIdDepartment()*10000+act.getIdDepartmentObject())){
                    }
                    else{
                        final View view5 = getLayoutInflater().inflate(R.layout.custom_block_object, null);
                        TextView textName2 = (TextView) view5.findViewById(R.id.textName1);
                        String str = "";
                        for (DepartmentObject obj: docDepartmentObjects) if(obj.getId() == act.getIdDepartmentObject()){str = obj.getName(); break;}
                        textName2.setText(""+str);
                        WORK_PLACE_ELEMENTS.add(view5);
                        linearDepart[act.getIdDepartment()].addView(view5);
                        idObjects.add(act.getIdDepartment()*10000+act.getIdDepartmentObject());
                    }

                    if (trim(act.getDateTimeStop()).equals(trim(nowDate))) {
                    } else {
                        final View view = getLayoutInflater().inflate(R.layout.custom_block_date, null);
                        TextView textDate = (TextView) view.findViewById(R.id.dateText);
                        nowDate = act.getDateTimeStop();
                        textDate.setText(DateToText(nowDate));
                        WORK_PLACE_ELEMENTS.add(view);
                        linearDepart[act.getIdDepartment()].addView(view);
                    }
                    nowDate = act.getDateTimeStop();
                    final View view1 = getLayoutInflater().inflate(R.layout.custom_block_name, null);
                    TextView textTime = (TextView) view1.findViewById(R.id.textTime);
                    TextView textName = (TextView) view1.findViewById(R.id.textName);
                    ImageView status = (ImageView) view1.findViewById(R.id.status);
                    view1.setTag((int) cnt);
                    if (act.getIdStatus() == ACT_STATUS_READY) status.setImageResource(R.drawable.job_green);
                    String str = "";
                    for (Employee emp: employees) if(emp.getId() == act.getIdEmployee()){str = emp.getFIO(); break;}
                    textName.setText("Выдано: " + str);
                    SimpleDateFormat formatForDate = new SimpleDateFormat("HH:mm");
                    textTime.setText(formatForDate.format(nowDate));
                    WORK_PLACE_ELEMENTS.add(view1);
                    linearDepart[act.getIdDepartment()].addView(view1);
                    cnt++;
                }
            }
        }
    }
}
