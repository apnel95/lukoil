package com.example.lukoil.activity.doc;

import static com.example.lukoil.GeneraActList.trim;
import static com.example.lukoil.ListData.docActs;
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
    int departmentNow;

    ArrayList <Integer> idDepartment;
    Button bNH1, bNH2, bNH3, bCPPN, bCPPD;
    public static int NH1 = 0, NH2 = 1, NH3 = 2, CPPD = 3, CPPN = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Activity activity = new Activity(ID_ACTIVITY_DOC, this, R.layout.doc_acts, R.id.layoutForActs, new ArrayList<View>(), R.id.layout_menu, "Список предписаний");
        super.onStartList(activity);

        departmentNow = 31;

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
        drawDocs(docActs);
    }
    @Override
    public void toPlus(View v){
        Intent Doc_create = new Intent(v.getContext(), DocCreate.class);
        Doc_create.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(Doc_create);
    }
    public void toView(View v){
        Intent Doc_view = new Intent(v.getContext(), DocView.class);

        Doc_view.putExtra(ActDoc.class.getSimpleName(), docActs.get((int)v.getTag()));

        startActivity(Doc_view);
    }
    public void toNH1(View v){

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

    public void drawDocs(ArrayList<ActDoc> acts){
        idDepartment = new ArrayList<>();
        acts = CheckAndSortActs(acts);
        ArrayList<Integer> idObjects = new ArrayList<Integer>();
        Date nowDate = new Date(0,0,1);
        int cnt = 0;

        for (ActDoc act: acts) {
            if (needDraw(act)){
                drawDepartmentName(act);
                    if (!idObjects.contains(act.getIdDepartment() * 10000 + act.getIdDepartmentObject())) {
                        drawObjectName(act);
                        idObjects.add(act.getIdDepartment()*10000+act.getIdDepartmentObject());
                        nowDate = new Date(0,0,1);
                    }
                if (!trim(act.getDateTimeStop()).equals(trim(nowDate))) {
                    nowDate = drawDate(act);
                }
                else nowDate = act.getDateTimeStop();
                drawAct(act, nowDate, cnt);
                cnt++;
            }
        }
    }

    private void drawAct(ActDoc act, Date nowDate, int cnt) {
        final View view1 = getLayoutInflater().inflate(R.layout.custom_block_name, null);
        TextView textTime = view1.findViewById(R.id.textTime);
        TextView textName = view1.findViewById(R.id.textName);
        ImageView status = view1.findViewById(R.id.status);
        view1.setTag(new Dir(act.getId(), "Doc"));
        if (act.getIdStatus() == ACT_STATUS_READY) status.setImageResource(R.drawable.job_green);
        String str = "";
        for (Employee emp: employees) if(emp.getId() == act.getIdEmployee()){str = emp.getFIO(); break;}
        textName.setText(String.format("%s %s", getResources().getString(R.string.issuedTo), str));
        textTime.setText(FORMAT_FOR_DATE.format(nowDate));
        WORK_PLACE_ELEMENTS.add(view1);
        linearDepart[act.getIdDepartment()].addView(view1);
    }

    private Date drawDate(ActDoc act) {
        final View view = getLayoutInflater().inflate(R.layout.custom_block_date, null);
        TextView textDate = (TextView) view.findViewById(R.id.dateText);
        Date nowDate = act.getDateTimeStop();
        textDate.setText(DateToText(nowDate));
        WORK_PLACE_ELEMENTS.add(view);
        linearDepart[act.getIdDepartment()].addView(view);
        return nowDate;
    }

    private void drawObjectName(ActDoc act) {
        final View view5 = getLayoutInflater().inflate(R.layout.custom_block_object, null);
        TextView textName2 = (TextView) view5.findViewById(R.id.textName1);
        String str = "";
        for (DepartmentObject obj: docDepartmentObjects) if(obj.getId() == act.getIdDepartmentObject()){str = obj.getName(); break;}
        textName2.setText(String.format("%s", str));
        WORK_PLACE_ELEMENTS.add(view5);
        linearDepart[act.getIdDepartment()].addView(view5);
    }

    private void drawDepartmentName(ActDoc act) {
        final View view4 = getLayoutInflater().inflate(R.layout.custom_block_type_name, null);
        TextView textName1 = view4.findViewById(R.id.textName);
        String str = "";
        for (Dir dir : docDepartments)
            if (dir.getId() == act.getIdDepartment()) {
                if (!idDepartment.contains(dir.getId())) {
                    str = dir.getName();
                    idDepartment.add(dir.getId());
                    break;
                }
                else return;
            }
        textName1.setText(String.format("%s", str));
        linearDepart[act.getIdDepartment()].setVisibility(View.VISIBLE);
        WORK_PLACE_ELEMENTS.add(view4);
        linearDepart[act.getIdDepartment()].addView(view4);
    }


    private boolean needDraw(ActDoc act) {
        if ((statusNow == ALL_TYPE_STATUS ||
                statusNow == ((act.getIdStatus() == ACT_STATUS_JOB) ? IN_WORK : READY) ||
                statusNow == ((act.getIdStatus() == ACT_STATUS_READY) ? READY : IN_WORK))
                && ((timeNow == ALL_DAYS) ||
                ((timeNow == TODAY) && ((long) ((trim(new Date())).getTime() - trim(act.getDateTimeStop()).getTime()) < 86400000)) ||
                ((timeNow == WEEK) && ((long) ((trim(new Date())).getTime() - trim(act.getDateTimeStop()).getTime()) < 86400000 * 7)) ||
                ((timeNow == MONTH) && ((long) ((trim(new Date())).getTime() - trim(act.getDateTimeStop()).getTime()) < 2678400000L)))) {
            return (act.getIdDepartment() == NH1 && (departmentNow & 1) == 1) || (act.getIdDepartment() == NH2 && (departmentNow & 2) == 2) || (act.getIdDepartment() == NH3 && (departmentNow & 4) == 4) || (act.getIdDepartment() == CPPN && (departmentNow & 8) == 8) || (act.getIdDepartment() == CPPD && (departmentNow & 16) == 16);
        }
        return false;
    }

    private ArrayList<ActDoc> CheckAndSortActs(ArrayList<ActDoc> acts) {
        for (ActDoc act:acts) if(act.getDateTimeStop() == null) act.setDateTimeStop(new Date());
        Collections.sort(acts, new ActDocComparatot());
        return acts;
    }

}
