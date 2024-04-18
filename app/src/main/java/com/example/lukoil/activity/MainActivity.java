package com.example.lukoil.activity;

import static com.example.lukoil.ListData.employees;
import static com.example.lukoil.ListData.docDepartments;
import static com.example.lukoil.ListData.docDepartmentObjects;
import static com.example.lukoil.ListData.actEventTypes;
import static com.example.lukoil.ListData.pumpMarks;
import static com.example.lukoil.ListData.pipeNames;
import static com.example.lukoil.ListData.pumpStopReasons;
import static com.example.lukoil.ListData.pipeCoatingTypes;
import static com.example.lukoil.ListData.actStatuses;
import static com.example.lukoil.ListData.pipeSubstances;
import static com.example.lukoil.ListData.pipeLeakTypes;
import static com.example.lukoil.ListData.pumpPositions;
import static com.example.lukoil.ListData.updateSArraylists;

import static java.text.DateFormat.getTimeInstance;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lukoil.GeneraActList;
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
import com.example.lukoil.entity.work.Work;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends GeneraActList {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createTestData();
        initializationActivity(new Activity(ID_ACTIVITY_HOME, this, R.layout.home1, R.id.layoutBlock, new ArrayList<View>(), R.id.layout_menu, getResources().getString(R.string.nameGeneralForm)));
        updateDirsAndActs();
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
        for (ActPipe act : LIST_ACT_PIPE) {
            if (CheckAndDrawFields(act, dateStopInLastAct)) dateStopInLastAct = act.getDateTimeStop();
        }
    }

    private void drawPumpActs() {
        Date dateStopInLastAct = new Date(100000);
        drawNewFieldForAct(new Field(R.layout.custom_block_type_name, R.id.textName, getResources().getString(R.string.pumps)));

        for (ActPump act : LIST_ACT_PUMP) {
            if (CheckAndDrawFields(act, dateStopInLastAct)) dateStopInLastAct = act.getDateTimeStop();
        }
    }

    private void drawDocActs() {
        Date dateStopInLastAct = new Date(100000);

        drawNewFieldForAct(new Field(R.layout.custom_block_type_name, R.id.textName, getResources().getString(R.string.docs)));

        for (ActDoc act : LIST_ACT_DOC) {
            if (CheckAndDrawFields(act, dateStopInLastAct)) dateStopInLastAct = act.getDateTimeStop();
        }
    }

    private boolean CheckAndDrawFields(ActPipe act, Date dateStopInLastAct) {
        if ((act.getIdStatus() == ACT_STATUS_JOB)) {
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

    private void createTestData() {

        LIST_ACT_PIPE.clear();
        LIST_ACT_DOC.clear();
        LIST_ACT_PUMP.clear();

        ArrayList<EventDateTime> events = new ArrayList<EventDateTime>();
        events.add(new EventDateTime(1, 1, 2, new Date((2002-1900), 10, 23, 4, 12)));
        events.add(new EventDateTime(2, 0, 3, new Date((2023-1900), 5, 10)));
        events.add(new EventDateTime(3, 0, 0, new Date((2023-1900), 4, 1, 18, 56)));
        events.add(new EventDateTime(4, 0, 1, new Date()));
        LIST_ACT_PIPE.add(new ActPipe(1, 1, 10, 12, 2, 40, 1, 56, 5, 0, ACT_STATUS_JOB, 2, 24, events ));
        ArrayList<EventDateTime> events2 = new ArrayList<EventDateTime>();
        events2.add(new EventDateTime(1, 2, 3, new Date((2002-1900), 10, 23)));
        events2.add(new EventDateTime(2, 2, 1, new Date((2023-1900), 5, 10)));
        events2.add(new EventDateTime(3, 2, 0, new Date((2023-1900), 4, 24, 23, 49)));
        events2.add(new EventDateTime(4, 2, 2, new Date()));
        LIST_ACT_PIPE.add(new ActPipe(2, 1, 10, 12, 1, 23, 1, 56, 5, 1, ACT_STATUS_READY, 1, 10, events2));
        ArrayList<EventDateTime> events3 = new ArrayList<EventDateTime>();
        events3.add(new EventDateTime(1, 1, 2, new Date((2002-1900), 10, 23)));
        events3.add(new EventDateTime(2, 1, 1, new Date((2023-1900), 5, 5)));
        events3.add(new EventDateTime(3, 1, 3, new Date()));
        events3.add(new EventDateTime(4, 1, 0, new Date()));
        LIST_ACT_PIPE.add(new ActPipe(3, 2, 4, 10, 1, 23, 1, 56, 5, 1, ACT_STATUS_JOB, 1, 10, events3));
        ArrayList<EventDateTime> works3 = new ArrayList<EventDateTime>();
        works3.add(new EventDateTime(1, 3, 3, new Date((2002-1900), 10, 23)));
        works3.add(new EventDateTime(4, 3, 2, new Date()));

        ArrayList<Integer> list_reade = new ArrayList<Integer>();
        list_reade.add(1);
        list_reade.add(2);

        ArrayList<Remark> remarks1 = new ArrayList<Remark>();
        ArrayList<Remark> remarks2 = new ArrayList<Remark>();
        remarks1.add(new Remark(1, 1, "Ошибка В1346"));
        remarks1.add(new Remark(2, 2, "Ошибка В166"));
        remarks1.add(new Remark(3, 2, "Ошибка A1"));
        remarks2.add(new Remark(4, 2, "Ошибка В161"));

        ArrayList<Work> work12 = new ArrayList<Work>();
        work12.add(new Work(1, 1, "Работа Вх46", "Первый"));
        work12.add(new Work(2, 2, "Работа Вх6", "Первый3"));

        LIST_ACT_DOC.add(new ActDoc(1, 0, 1, 1, 1, events3, new ArrayList<Remark>(), new ArrayList<Work>(), "Михалил"));
        LIST_ACT_DOC.add(new ActDoc(2, 1, 3, 2, 2, events2, remarks1, work12, "Свет"));
        LIST_ACT_DOC.add(new ActDoc(3, 0, 7, 1, 2, works3, remarks2, new ArrayList<Work>(), "Щило"));

        LIST_ACT_PUMP.add(new ActPump(1, 1,1, 1, 1, "aaa", events, list_reade));
        LIST_ACT_PUMP.add(new ActPump(2, 2,2, 2, 2, "DFAfakfioajf", events3, list_reade));

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

        pipeCoatingTypes.add(new Dir(1, "Маленький"));
        pipeCoatingTypes.add(new Dir(2, "Средний"));
        pipeCoatingTypes.add(new Dir(3, "Большой"));

        actStatuses.add(new Dir(1, "Готово"));
        actStatuses.add(new Dir(2, "В работе"));

        pipeSubstances.add(new Dir(1, "Нефть"));
        pipeSubstances.add(new Dir(2, "Вода"));
        pipeSubstances.add(new Dir(3, "Молоко"));

        pipeLeakTypes.add(new Dir(1, "Бетон"));
        pipeLeakTypes.add(new Dir(2, "Железо"));
        pipeLeakTypes.add(new Dir(3, "Пластик"));

        pumpPositions.add(new Dir(1, "НПЗ 1"));
        pumpPositions.add(new Dir(2, "НПЗ 2"));
        pumpPositions.add(new Dir(3, "НПЗ 3"));

        updateSArraylists();
    }
}