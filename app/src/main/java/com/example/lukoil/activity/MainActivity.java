package com.example.lukoil.activity;

import static com.example.lukoil.ListData.actEventsDoc;
import static com.example.lukoil.ListData.actEventsPipe;
import static com.example.lukoil.ListData.actEventsPump;
import static com.example.lukoil.ListData.docActs;
import static com.example.lukoil.ListData.docRemarks;
import static com.example.lukoil.ListData.docWorks;
import static com.example.lukoil.ListData.employees;
import static com.example.lukoil.ListData.docDepartments;
import static com.example.lukoil.ListData.docDepartmentObjects;
import static com.example.lukoil.ListData.actEventTypes;
import static com.example.lukoil.ListData.pipeActs;
import static com.example.lukoil.ListData.pumpActs;
import static com.example.lukoil.ListData.pumpMarks;
import static com.example.lukoil.ListData.pipeNames;
import static com.example.lukoil.ListData.pumpStopReasons;
import static com.example.lukoil.ListData.pipeCoatingTypes;
import static com.example.lukoil.ListData.actStatuses;
import static com.example.lukoil.ListData.pipeSubstances;
import static com.example.lukoil.ListData.pipeLeakTypes;
import static com.example.lukoil.ListData.pumpPositions;
import static com.example.lukoil.ListData.pumpWorkTypes;
import static com.example.lukoil.ListData.pumpWorks;
import static com.example.lukoil.ListData.updateSArraylists;

import static java.text.DateFormat.getTimeInstance;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.lukoil.GeneraActList;
import com.example.lukoil.ListData;
import com.example.lukoil.entity.DepartmentObject;
import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.field.Field;
import com.example.lukoil.entity.field.ActField;
import com.example.lukoil.R;
import com.example.lukoil.entity.act.ActDoc;
import com.example.lukoil.entity.act.ActPump;
import com.example.lukoil.entity.act.ActPipe;
import com.example.lukoil.entity.Employee;
import com.example.lukoil.entity.event.EventDateTime;
import com.example.lukoil.entity.remark.Remark;
import com.example.lukoil.entity.work.WorkDoc;
import com.example.lukoil.entity.work.WorkPump;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends GeneraActList {

    ListData listData = new ListData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CONTEXT_NOW = this;
        super.onCreate(savedInstanceState);
        initializationData();
        //createTestData();
        listData.loadFormSQL();
        initializationActivity(new Activity(ID_ACTIVITY_HOME, this, R.layout.home1, R.id.layoutBlock, new ArrayList<View>(), R.id.layout_menu, getResources().getString(R.string.nameGeneralForm)));
        updateDirsAndActs();
    }

    private void initializationData() {
        employees = new ArrayList<>();
        docDepartments = new ArrayList<>();
        docDepartmentObjects = new ArrayList<>();
        actEventTypes = new ArrayList<>();
        pumpMarks = new ArrayList<>();
        pipeNames = new ArrayList<>();
        pumpStopReasons = new ArrayList<>();
        pipeCoatingTypes = new ArrayList<>();
        actStatuses = new ArrayList<>();
        pipeSubstances = new ArrayList<>();
        pipeLeakTypes = new ArrayList<>();
        pumpPositions = new ArrayList<>();
        pumpWorkTypes = new ArrayList<>();
        actEventsPipe = new ArrayList<>();
        actEventsPump = new ArrayList<>();
        actEventsDoc = new ArrayList<>();
        docWorks = new ArrayList<>();
        pumpWorks = new ArrayList<>();
        docRemarks = new ArrayList<>();
        pipeActs = new ArrayList<>();
        docActs = new ArrayList<>();
        pumpActs = new ArrayList<>();
    }

    private void updateDirsAndActs() {
        drawActs();
    }

    private void drawActs() {
        drawPipeActs();
        drawPumpActs();
        drawDocActs();
    }

    private void drawPipeActs() {
        Date dateStopInLastAct = new Date(100000);
        drawNewFieldForAct(new Field(R.layout.custom_block_type_name, R.id.textName, getResources().getString(R.string.pipes)));
        for (ActPipe act : pipeActs) {
            if (CheckAndDrawFields(act, dateStopInLastAct)) dateStopInLastAct = act.getDateTimeStop();
        }
    }

    private void drawPumpActs() {
        Date dateStopInLastAct = new Date(100000);
        drawNewFieldForAct(new Field(R.layout.custom_block_type_name, R.id.textName, getResources().getString(R.string.pumps)));

        for (ActPump act : pumpActs) {
            if (CheckAndDrawFields(act, dateStopInLastAct)) dateStopInLastAct = act.getDateTimeStop();
        }
    }

    private void drawDocActs() {
        Date dateStopInLastAct = new Date(100000);

        drawNewFieldForAct(new Field(R.layout.custom_block_type_name, R.id.textName, getResources().getString(R.string.docs)));

        for (ActDoc act : docActs) {
            if (CheckAndDrawFields(act, dateStopInLastAct)) dateStopInLastAct = act.getDateTimeStop();
        }
    }

    private boolean CheckAndDrawFields(ActPipe act, Date dateStopInLastAct) {
        if ((act.getIdStatus() == ACT_STATUS_JOB)) {
            Log.d("ActPipe_CheckAndDrawFields","id: "+act.getId()+" Act dateTimeStop: "+act.getDateTimeStop()+" LastAct dateStop: "+dateStopInLastAct);
            if (isDatesNotEquivalent(act.getDateTimeStop(), dateStopInLastAct)) {
                drawNewFieldForAct(new Field(R.layout.custom_block_date, R.id.dateText, DateToText(act.getDateTimeStop())));
            }
            drawNewAct(new ActField(R.layout.custom_block_name, R.id.textName, act.getName(pipeNames), R.id.textTime, FORMAT_FOR_DATE.format(act.getDateTimeStop()), R.id.status, act.getIdStatus(), new Dir(act.getId(), "Pipe")));
            return true;
        }
        return false;
    }
    private boolean CheckAndDrawFields(ActPump act, Date dateStopInLastAct) {
        if ((act.getIdStatus() == ACT_STATUS_JOB)) {
            if (isDatesNotEquivalent(act.getDateTimeStop(), dateStopInLastAct)) {
                drawNewFieldForAct(new Field(R.layout.custom_block_date, R.id.dateText, DateToText(act.getDateTimeStop())));
            }
            drawNewAct(new ActField(R.layout.custom_block_name, R.id.textName, act.getName(pumpPositions) +", "+ act.getName(pumpMarks), R.id.textTime, FORMAT_FOR_DATE.format(act.getDateTimeStop()), R.id.status, act.getIdStatus(), new Dir(act.getId(), "Pump")));
            return true;
        }
        return false;
    }
    private boolean CheckAndDrawFields(ActDoc act, Date dateStopInLastAct) {
        if ((act.getIdStatus() == ACT_STATUS_JOB)) {
            if (isDatesNotEquivalent(act.getDateTimeStop(), dateStopInLastAct)) {
                drawNewFieldForAct(new Field(R.layout.custom_block_date, R.id.dateText, DateToText(act.getDateTimeStop())));
            }
            String str = getNameById(employees, act.getIdEmployee());

            drawNewAct(new ActField(R.layout.custom_block_name, R.id.textName, (getResources().getString(R.string.issuedTo) + str), R.id.textTime, FORMAT_FOR_DATE.format(act.getDateTimeStop()), R.id.status, act.getIdStatus(), new Dir(act.getId(), "Doc")));
            return true;
        }
        return false;
    }

    private String getNameById(ArrayList<Employee> employees, int idEmployee) {
        for (Employee emp: employees)
            if(emp.getId() == idEmployee)
                return emp.getFIO();
        System.out.println("Не найден id "+ idEmployee + " в списке employees");
        return "";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //listData.saveToSQL();
    }

    private void createTestData() {

        employees.add(new Employee(0, 1, 1, "Василий Утка"));
        employees.add(new Employee(1, 1, 1, "Петр Руки-Лопаты"));
        employees.add(new Employee(2, 1, 1, "Семен Сковородка"));
        employees.add(new Employee(3, 1, 1, "Петя Ключ-на-девять"));

        docDepartments.add(new Dir(0, "НШ1"));
        docDepartments.add(new Dir(1, "НШ2"));
        docDepartments.add(new Dir(2, "НШ3"));
        docDepartments.add(new Dir(3, "ЦППД"));
        docDepartments.add(new Dir(4, "ЦППН"));

        docDepartmentObjects.add(new DepartmentObject(1, 1, "Q1"));
        docDepartmentObjects.add(new DepartmentObject(2, 1, "D2"));
        docDepartmentObjects.add(new DepartmentObject(3, 1, "W3"));
        docDepartmentObjects.add(new DepartmentObject(4, 2, "Q111"));
        docDepartmentObjects.add(new DepartmentObject(5, 2, "D222"));
        docDepartmentObjects.add(new DepartmentObject(6, 3, "W3QQ"));
        docDepartmentObjects.add(new DepartmentObject(7, 3, "Q1QQ"));

        actEventTypes.add(new Dir(1, "Починка"));
        actEventTypes.add(new Dir(2, "Уборка"));
        actEventTypes.add(new Dir(3, "Покраска"));

        pumpMarks.add(new Dir(1, "FQ24ujrf"));
        pumpMarks.add(new Dir(2, "twetggs"));
        pumpMarks.add(new Dir(3, "agser4e"));

        pipeNames.add(new Dir(1, "Tuff_111"));
        pipeNames.add(new Dir(2, "GF_535"));
        pipeNames.add(new Dir(3, "GKLK_9"));

        pumpStopReasons.add(new Dir(1, "Поломка"));
        pumpStopReasons.add(new Dir(2, "Ремонт"));
        pumpStopReasons.add(new Dir(3, "Перерыв"));

        pipeCoatingTypes.add(new Dir(1, "Бетон"));
        pipeCoatingTypes.add(new Dir(2, "Железо"));
        pipeCoatingTypes.add(new Dir(3, "Пластик"));

        actStatuses.add(new Dir(1, "Готово"));
        actStatuses.add(new Dir(2, "В работе"));

        pipeSubstances.add(new Dir(1, "Нефть"));
        pipeSubstances.add(new Dir(2, "Вода"));
        pipeSubstances.add(new Dir(3, "Молоко"));

        pipeLeakTypes.add(new Dir(1, "Маленький"));
        pipeLeakTypes.add(new Dir(2, "Средний"));
        pipeLeakTypes.add(new Dir(3, "Большой"));

        pumpPositions.add(new Dir(1, "НПЗ 1"));
        pumpPositions.add(new Dir(2, "НПЗ 2"));
        pumpPositions.add(new Dir(3, "НПЗ 3"));

        actStatuses.add(new Dir(ACT_STATUS_READY, "Готово"));
        actStatuses.add(new Dir(ACT_STATUS_JOB, "В работе"));

        pumpWorkTypes.add(new Dir(1, "Починка главного оборудования"));
        pumpWorkTypes.add(new Dir(2, "Починка резервного оборудования"));
        pumpWorkTypes.add(new Dir(3, "Осмотр"));

        actEventsPipe = new ArrayList<>();
        actEventsPipe.add(new EventDateTime(1, 1, 2, new Date((2002-1900), 10, 23, 4, 12)));
        actEventsPipe.add(new EventDateTime(2, 1, 3, new Date((2023-1900), 5, 10)));
        actEventsPipe.add(new EventDateTime(3, 1, 0, new Date((2023-1900), 4, 1, 18, 56)));
        actEventsPipe.add(new EventDateTime(4, 1, 1, new Date()));
        actEventsPipe.add(new EventDateTime(5, 2, 2, new Date((2002-1900), 10, 23, 4, 12)));
        actEventsPipe.add(new EventDateTime(6, 2, 3, new Date((2023-1900), 5, 10)));
        actEventsPipe.add(new EventDateTime(7, 3, 2, new Date((2002-1900), 10, 23, 4, 12)));
        actEventsPipe.add(new EventDateTime(8, 3, 3, new Date((2023-1900), 5, 10)));

        actEventsPump.add(new EventDateTime(1, 1, 2, new Date((2002-1900), 10, 23, 4, 12)));
        actEventsPump.add(new EventDateTime(2, 1, 3, new Date((2023-1900), 5, 10)));
        actEventsPump.add(new EventDateTime(3, 1, 0, new Date((2023-1900), 4, 1, 18, 56)));
        actEventsPump.add(new EventDateTime(4, 1, 1, new Date()));
        actEventsPump.add(new EventDateTime(5, 2, 2, new Date((2002-1900), 10, 23, 4, 12)));
        actEventsPump.add(new EventDateTime(6, 2, 3, new Date((2023-1900), 5, 10)));
        actEventsPump.add(new EventDateTime(7, 3, 2, new Date((2002-1900), 10, 23, 4, 12)));
        actEventsPump.add(new EventDateTime(8, 3, 3, new Date((2023-1900), 5, 10)));

        actEventsDoc.add(new EventDateTime(1, 1, 2, new Date((2002-1900), 10, 23, 4, 12)));
        actEventsDoc.add(new EventDateTime(2, 1, 3, new Date((2023-1900), 5, 10)));
        actEventsDoc.add(new EventDateTime(3, 1, 0, new Date((2023-1900), 4, 1, 18, 56)));
        actEventsDoc.add(new EventDateTime(4, 1, 1, new Date()));
        actEventsDoc.add(new EventDateTime(5, 2, 2, new Date((2002-1900), 10, 23, 4, 12)));
        actEventsDoc.add(new EventDateTime(6, 2, 3, new Date((2023-1900), 5, 10)));
        actEventsDoc.add(new EventDateTime(7, 3, 2, new Date((2002-1900), 10, 23, 4, 12)));
        actEventsDoc.add(new EventDateTime(8, 3, 3, new Date((2023-1900), 5, 10)));

        docWorks.add(new WorkDoc(1, 1,  "text", "name"));
        docWorks.add(new WorkDoc(2, 2, "text2", "name3"));
        docWorks.add(new WorkDoc(2, 2, "text", "name"));

        docWorks.add(new WorkDoc(3, 3, "text", "name"));

        pumpWorks.add(new WorkPump(1, 1, 1));
        pumpWorks.add(new WorkPump(1, 2, 2));
        pumpWorks.add(new WorkPump(1, 3, 1));

        docRemarks.add(new Remark(1,1, "Text"));
        docRemarks.add(new Remark(1,1, "Text2"));
        docRemarks.add(new Remark(1,1, "Text3"));
        docRemarks.add(new Remark(1,2, "Text"));
        docRemarks.add(new Remark(1,3, "Text"));


        pipeActs.add(new ActPipe(1, 1, 10, 12, 2, 40, 1, 56, 5, 0, ACT_STATUS_JOB, 2, 24));
        pipeActs.add(new ActPipe(2, 1, 10, 12, 1, 23, 1, 56, 5, 1, ACT_STATUS_READY, 1, 10));
        pipeActs.add(new ActPipe(3, 2, 4, 10, 1, 23, 1, 56, 5, 1, ACT_STATUS_JOB, 1, 10));


        docActs.add(new ActDoc(1, 1, 1, 1, "Михалил"));
        docActs.add(new ActDoc(2, 2, 2, 2, "Свет"));
        docActs.add(new ActDoc(3, 3, 1, 2, "Щило"));

        pumpActs.add(new ActPump(1, 1,1, 1, 1, "aaa"));
        pumpActs.add(new ActPump(2, 2,2, 2, 2, "DFAfakfioajf"));
        updateSArraylists();
    }
}