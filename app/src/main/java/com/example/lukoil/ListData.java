package com.example.lukoil;

import static com.example.lukoil.activity.General.CONTEXT_NOW;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.lukoil.entity.act.ActDoc;
import com.example.lukoil.entity.act.ActPump;
import com.example.lukoil.entity.act.ActPipe;
import com.example.lukoil.entity.DepartmentObject;
import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.Employee;
import com.example.lukoil.entity.event.EventDateTime;
import com.example.lukoil.entity.remark.Remark;
import com.example.lukoil.entity.work.WorkDoc;
import com.example.lukoil.entity.work.WorkPump;

import java.sql.Date;
import java.util.ArrayList;

public class ListData {

    public static int NEW_ID_EVENT, NEW_ID_DOC_WORK, NEW_ID_PUMP_WORK, NEW_ID_REMARK;
    public static ArrayList<ActPipe> pipeActs;
    public static ArrayList<ActPump> pumpActs;
    public static ArrayList<ActDoc> docActs;
    public static ArrayList<EventDateTime> actEvents;
    public static ArrayList<EventDateTime> actEventsPipe;
    public static ArrayList<EventDateTime> actEventsPump;
    public static ArrayList<EventDateTime> actEventsDoc;
    public static ArrayList<WorkDoc> docWorks;
    public static ArrayList<Remark> docRemarks;
    public static ArrayList<WorkPump> pumpWorks;
    public static ArrayList<Dir> docDepartments;
    public static ArrayList<DepartmentObject> docDepartmentObjects;
    public static ArrayList<Employee> employees;


    public static ArrayList<Dir> actEventTypes;
    public static ArrayList<Dir> actEventStatuses;
    public static ArrayList<Dir> pumpMarks;
    public static ArrayList<Dir> pipeNames;
    public static ArrayList<Dir> pipePositions;
    public static ArrayList<Dir> employeePosts;
    public static ArrayList<Dir> pumpStopReasons;
    public static ArrayList<Dir> pipeCoatingTypes;
    public static ArrayList<Dir> pumpWorkTypes;
    public static ArrayList<Dir> employeeStatuses;
    public static ArrayList<Dir> actStatuses;
    public static ArrayList<Dir> pipeSubstances;
    public static ArrayList<Dir> pipeLeakTypes;
    public static ArrayList<Dir> pumpPositions;



    public static ArrayList<String> sDocDepartments;
    public static ArrayList<String> sDocDepartmentObjects;
    public static ArrayList<String> sEmployees;
    public static ArrayList<String> sActEventTypes;
    public static ArrayList<String> sActEventStatuses;
    public static ArrayList<String> sPumpMarks;
    public static ArrayList<String> sPipeNames;
    public static ArrayList<String> sPipePositions;
    public static ArrayList<String> sEmployeePosts;
    public static ArrayList<String> sPumpStopReasons;
    public static ArrayList<String> sPipeCoatingTypes;
    public static ArrayList<String> sPumpWorkTypes;
    public static ArrayList<String> sEmployeeStatuses;
    public static ArrayList<String> sActStatuses;
    public static ArrayList<String> sPipeSubstances;
    public static ArrayList<String> sPipeLeakTypes;
    public static ArrayList<String> sPumpPositions;

    public ListData() {
    }

    public static void updateSArraylists(){

        sEmployees = new ArrayList<>();
        sDocDepartments = new ArrayList<>();
        sDocDepartmentObjects = new ArrayList<>();
        sActEventTypes = new ArrayList<>();
        sPumpMarks = new ArrayList<>();
        sPipeNames = new ArrayList<>();
        sPumpStopReasons = new ArrayList<>();
        sPipeCoatingTypes = new ArrayList<>();
        sActStatuses = new ArrayList<>();
        sPipeSubstances = new ArrayList<>();
        sPipeLeakTypes = new ArrayList<>();
        sPumpPositions = new ArrayList<>();

        for (Employee employee: employees) sEmployees.add(employee.getFIO());
        for (Dir dir: docDepartments) sDocDepartments.add(dir.getName());
        for (DepartmentObject departmentObject: docDepartmentObjects) sDocDepartmentObjects.add(departmentObject.getName());
        for (Dir dir: actEventTypes) sActEventTypes.add(dir.getName());
        for (Dir dir: pumpMarks) sPumpMarks.add(dir.getName());
        for (Dir dir: pipeNames) sPipeNames.add(dir.getName());
        for (Dir dir: pumpStopReasons) sPumpStopReasons.add(dir.getName());
        for (Dir dir: pipeCoatingTypes) sPipeCoatingTypes.add(dir.getName());
        for (Dir dir: actStatuses) sActStatuses.add(dir.getName());
        for (Dir dir: pipeSubstances) sPipeSubstances.add(dir.getName());
        for (Dir dir: pipeLeakTypes) sPipeLeakTypes.add(dir.getName());
        for (Dir dir: pumpPositions) sPumpPositions.add(dir.getName());;

    }

    ContentValues cv = new ContentValues();

    DBHelper dbHelper;
    SQLiteDatabase db;


    public void saveData(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                saveToSQL();
            }
        };
    }

    public void saveToSQL() {
        dbHelper = new DBHelper(CONTEXT_NOW);
        db = dbHelper.getWritableDatabase();
        savePipeActs();
        savePumpActs();
        saveDocs();
        saveDocDepartment();
        saveDocDepartmentObjects();
        saveActEventTypes();
        savePumpMarks();
        savePipeNames();
        savePumpStopReasons();
        savePipeCoatingTypes();
        saveActStatuses();
        savePipeSubstances();
        savePipeLeakTypes();
        savePumpPositions();
        saveEmloyees();
        saveEvents();
        saveWorks();
        saveDocRemarks();
        savePumpWorkTypes();
        dbHelper.close();
    }

    private void saveWorks() {
        cv.clear();
        String nameTable = "Prescription_work";
        removeDataToTable(nameTable);
        for (WorkDoc work: docWorks){
            cv.put("id_prescription_work",work.getId());
            cv.put("id_prescription",work.getIdDoc());
            cv.put("name_work",work.getName());
            cv.put("description_work",work.getText());
            db.insert(nameTable, null, cv);
            Log.d(nameTable+" save", "saved: "+work.getId()+" "+work.getIdDoc()+" "+work.getName()+" "+work.getText());
        }
        Log.d("AllSaved", nameTable + "was saved");

        cv.clear();
        nameTable = "Work_pump_completed";
        removeDataToTable(nameTable);
        for (WorkPump eDT: pumpWorks){
            cv.put("id_completed_work_pump",eDT.getId());
            cv.put("id_act_pump",eDT.getIdAct());
            cv.put("id_type_work",eDT.getIdTypeWork());
            db.insert(nameTable, null, cv);
            Log.d(nameTable+" save", "saved: "+eDT.getId()+" "+eDT.getIdAct()+" "+eDT.getIdTypeWork());
        }
        Log.d("AllSaved", nameTable + "was saved");

    }

    private void saveDocRemarks() {
        cv.clear();
        String nameTable = "Prescription_remark";
        removeDataToTable(nameTable);
        for (Remark remark: docRemarks){
            cv.put("id_prescription_remark",remark.getId());
            cv.put("id_prescription",remark.getIdDoc());
            cv.put("text",remark.getText());
            db.insert(nameTable, null, cv);
            Log.d(nameTable+" save", "saved: "+remark.getId()+" "+remark.getIdDoc()+" "+remark.getText());
        }
        Log.d("AllSaved", nameTable + "was saved");
    }

    private void savePumpWorkTypes() {
        cv.clear();
        String nameTable = "Type_work_pump";
        removeDataToTable(nameTable);
        for (Dir dir: pumpWorkTypes){
            cv.put("id_type_work_pump",dir.getId());
            cv.put("name_",dir.getName());

            db.insert(nameTable, null, cv);
            Log.d(nameTable+" save", "saved: "+dir.getId()+" "+dir.getName());
        }
        Log.d("AllSaved", nameTable + "was saved");
    }

    private void saveEvents() {
        cv.clear();
        String nameTable = "Event_date_time_pipeline";
        removeDataToTable(nameTable);
        for (EventDateTime eDT: actEventsPipe){
            cv.put("id_list_date_time_pipeline",eDT.getId());
            cv.put("id_act_pipeline",eDT.getId_act());
            cv.put("id_event_date_time_for",eDT.getId_type_event());
            cv.put("date_time", ((eDT.getDateTime()).getTime()/1000));
            db.insert(nameTable, null, cv);
            Log.d(nameTable+" save", "saved: "+eDT.getId()+" "+eDT.getId_act()+" "+eDT.getId_type_event()+" "+eDT.getDateTime());
        }
        Log.d("AllSaved", nameTable + "was saved");

        cv.clear();
        nameTable = "Event_date_time_pump";
        removeDataToTable(nameTable);
        for (EventDateTime eDT: actEventsPump){
            cv.put("id_list_date_time_pump",eDT.getId());
            cv.put("id_act_pump",eDT.getId_act());
            cv.put("id_event_date_time_for",eDT.getId_type_event());
            cv.put("date_time", ((eDT.getDateTime()).getTime()/1000));
            db.insert(nameTable, null, cv);
            Log.d(nameTable+" save", "saved: "+eDT.getId()+" "+eDT.getId_act()+" "+eDT.getId_type_event()+" "+eDT.getDateTime());
        }
        Log.d("AllSaved", nameTable + "was saved");

        cv.clear();
        nameTable = "Event_date_time_prescription";
        removeDataToTable(nameTable);
        for (EventDateTime eDT: actEventsDoc){
            cv.put("id_list_date_time_prescription",eDT.getId());
            cv.put("id_prescription",eDT.getId_act());
            cv.put("id_event_date_time_for",eDT.getId_type_event());
            cv.put("date_time", ((eDT.getDateTime()).getTime()/1000));
            db.insert(nameTable, null, cv);
            Log.d(nameTable+" save", "saved: "+eDT.getId()+" "+eDT.getId_act()+" "+eDT.getId_type_event()+" "+eDT.getDateTime());
        }
        Log.d("AllSaved", nameTable + "was saved");
    }

    private void saveDocs() {
        cv.clear();
        String nameTable = "Prescription";
        removeDataToTable(nameTable);
        for (ActDoc actDoc: docActs){
            cv.put("id_prescription",actDoc.getId());
            cv.put("id_department_object",actDoc.getIdDepartmentObject());
            cv.put("id_employee",actDoc.getIdEmployee());
            cv.put("fio_sending",actDoc.getFIOSending());
            cv.put("id_status",actDoc.getIdStatus());
            db.insert(nameTable, null, cv);
            Log.d(nameTable+" save", "saved: "+actDoc.getId()+" "+actDoc.getIdDepartmentObject()+" "+actDoc.getIdEmployee()+" "
                    +actDoc.getFIOSending()+" "+actDoc.getIdStatus());
        }
        Log.d("AllSaved", nameTable + "was saved");
    }

    private void savePumpActs() {
        cv.clear();
        String nameTable = "Act_pump";
        removeDataToTable(nameTable);
        for (ActPump actPump: pumpActs){
            cv.put("id_act_pump",actPump.getId());
            cv.put("id_mark",actPump.getIdMark());
            cv.put("id_reason_stop_pump",actPump.getIdReasonStop());
            cv.put("note",actPump.getNote());
            cv.put("id_status",actPump.getIdStatus());
            cv.put("id_pump",actPump.getIdPump());
            db.insert(nameTable, null, cv);
            Log.d(nameTable+" save", "saved: "+actPump.getId()+" "+actPump.getIdMark()+" "+actPump.getIdReasonStop()+" "
                    +actPump.getNote()+" "+actPump.getIdStatus()+" "+actPump.getIdPump());
        }
        Log.d("AllSaved", nameTable + "was saved");
    }

    private void savePipeActs() {
        cv.clear();
        String nameTable = "Act_pipeline";
        removeDataToTable(nameTable);
        for (ActPipe actPipe: pipeActs){
            cv.put("id_act_pipeline",actPipe.getId());
            cv.put("id_pipeline",actPipe.getIdPipe());
            cv.put("diameter_pipeline",actPipe.getDiameter());
            cv.put("wall_thickness",actPipe.getWall());
            cv.put("id_type_coating",actPipe.getIdTypeCoating());
            cv.put("piketash",actPipe.getPiketash());
            cv.put("id_leak_type",actPipe.getIdLeakType());
            cv.put("leak_parameter",actPipe.getLeak_parameter());
            cv.put("leak_position",actPipe.getLeak_position());
            cv.put("id_substance",actPipe.getIdSubstance());
            cv.put("spill_area",actPipe.getSpill_area());
            cv.put("id_status",actPipe.getIdStatus());
            cv.put("id_employee",actPipe.getIdWho());
            db.insert(nameTable, null, cv);
            Log.d(nameTable+" save", "saved: "+actPipe.getId()+" "+actPipe.getIdPipe()+" "+actPipe.getDiameter()+" "+actPipe.getWall()+ " "
                    +actPipe.getIdTypeCoating()+" "+actPipe.getPiketash()+" "+actPipe.getIdLeakType()+" "+actPipe.getLeak_parameter() +" "
                    +actPipe.getLeak_position()+ " "+actPipe.getIdSubstance()+" "+actPipe.getSpill_area()+" "+actPipe.getIdStatus()+" "+actPipe.getIdWho());
        }
        Log.d("AllSaved", nameTable + "was saved");
    }

    private void savePumpPositions() {
        cv.clear();
        String nameTable = "Position";
        removeDataToTable(nameTable);
        for (Dir dir: pumpPositions){
            cv.put("id_position",dir.getId());
            cv.put("name_",dir.getName());
            db.insert(nameTable, null, cv);
            Log.d(nameTable+" save", "saved: "+dir.getId()+" "+dir.getName());
        }
        Log.d("AllSaved", nameTable + "was saved");
    }

    private void savePipeLeakTypes() {
        cv.clear();
        String nameTable = "Type_leak";
        removeDataToTable(nameTable);
        for (Dir dir: pipeLeakTypes){
            cv.put("id_type_leak",dir.getId());
            cv.put("name_",dir.getName());
            db.insert(nameTable, null, cv);
            Log.d(nameTable+" save", "saved: "+dir.getId()+" "+dir.getName());
        }
        Log.d("AllSaved", nameTable + "was saved");

    }

    private void savePipeSubstances() {
        cv.clear();
        String nameTable = "Substance";
        removeDataToTable(nameTable);
        for (Dir dir: pipeSubstances){
            cv.put("id_substance",dir.getId());
            cv.put("name_",dir.getName());
            db.insert(nameTable, null, cv);
            Log.d(nameTable+" save", "saved: "+dir.getId()+" "+dir.getName());
        }
        Log.d("AllSaved", nameTable + "was saved");

    }

    private void saveActStatuses() {
        cv.clear();
        String nameTable = "Status_act";
        removeDataToTable(nameTable);
        for (Dir dir: actStatuses){
            cv.put("id_status_act",dir.getId());
            cv.put("name_",dir.getName());
            db.insert(nameTable, null, cv);
            Log.d(nameTable+" save", "saved: "+dir.getId()+" "+dir.getName());
        }
        Log.d("AllSaved", nameTable + "was saved");
    }

    private void savePipeCoatingTypes() {
        cv.clear();
        String nameTable = "Type_coating";
        removeDataToTable(nameTable);
        for (Dir dir: pipeCoatingTypes){
            cv.put("id_type_coating",dir.getId());
            cv.put("name_",dir.getName());
            db.insert(nameTable, null, cv);
            Log.d(nameTable+" save", "saved: "+dir.getId()+" "+dir.getName());
        }
        Log.d("AllSaved", nameTable + "was saved");
    }

    private void savePumpStopReasons() {
        cv.clear();
        String nameTable = "Reason_stop_pump";
        removeDataToTable(nameTable);
        for (Dir dir: pumpStopReasons){
            cv.put("id_reason_stop_pump",dir.getId());
            cv.put("name_",dir.getName());
            db.insert(nameTable, null, cv);
            Log.d(nameTable+" save", "saved: "+dir.getId()+" "+dir.getName());
        }
        Log.d("AllSaved", nameTable + "was saved");
    }

    private void savePipeNames() {
        cv.clear();
        String nameTable = "Pipeline";
        removeDataToTable(nameTable);
        for (Dir dir: pipeNames){
            cv.put("id_pipeline",dir.getId());
            cv.put("name_",dir.getName());
            db.insert(nameTable, null, cv);
            Log.d(nameTable+" save", "saved: "+dir.getId()+" "+dir.getName());
        }
        Log.d("AllSaved", nameTable + "was saved");
    }

    private void savePumpMarks() {
        cv.clear();
        String nameTable = "Mark";
        removeDataToTable(nameTable);
        for (Dir dir: pumpMarks){
            cv.put("id_mark",dir.getId());
            cv.put("name_",dir.getName());
            db.insert(nameTable, null, cv);
            Log.d(nameTable+" save", "saved: "+dir.getId()+" "+dir.getName());
        }
        Log.d("AllSaved", nameTable + "was saved");
    }

    private void saveActEventTypes() {
        cv.clear();
        String nameTable = "Event_date_time_for";
        removeDataToTable(nameTable);
        for (Dir dir: actEventTypes){
            cv.put("id_event_date_time_for",dir.getId());
            cv.put("name_",dir.getName());
            db.insert(nameTable, null, cv);
            Log.d(nameTable+" save", "saved: "+dir.getId()+" "+dir.getName());
        }
        Log.d("AllSaved", nameTable + "was saved");

    }


    private void saveDocDepartmentObjects() {
        cv.clear();
        String nameTable = "Department_object";
        removeDataToTable(nameTable);
        for (DepartmentObject departmentObject: docDepartmentObjects){
            cv.put("id_department_object",departmentObject.getId());
            cv.put("id_department",departmentObject.getIdDep());
            cv.put("name_",departmentObject.getName());
            db.insert(nameTable, null, cv);
            Log.d(nameTable+" save", "saved: "+departmentObject.getId()+" "+departmentObject.getIdDep()+" "+departmentObject.getName());
        }
        Log.d("AllSaved", nameTable + "was saved");
    }

    private void saveDocDepartment() {
        cv.clear();
        String nameTable = "Department";
        removeDataToTable(nameTable);
        for (Dir dir: docDepartments){
            cv.put("id_department",dir.getId());
            cv.put("name_",dir.getName());
            db.insert(nameTable, null, cv);
            Log.d(nameTable+" save", "saved: "+dir.getId()+" "+dir.getName());
        }
        Log.d("AllSaved", nameTable + "was saved");
    }

    private void saveEmloyees() {
        cv.clear();
        String nameTable = "Employee";
        removeDataToTable(nameTable);
        for (Employee employee: employees){
            cv.put("id_employee",employee.getId());
            cv.put("fio",employee.getFIO());
            cv.put("id_post",employee.getIdPost());
            cv.put("id_status",employee.getIdStatus());
            db.insert(nameTable, null, cv);
            Log.d(nameTable+" save", "saved: "+employee.getId()+" "+employee.getFIO()+" "+employee.getIdPost()+" "+employee.getIdStatus());
        }
        Log.d("AllSaved", nameTable + "was saved");
    }

    public void loadFormSQL() {
        dbHelper = new DBHelper(CONTEXT_NOW);
        db = dbHelper.getWritableDatabase();
        loadEmloyees();
        loadDocDepartment();
        loadDocDepartmentObjects();
        loadActEventTypes();
        loadPumpMarks();
        loadPipeNames();
        loadPumpStopReasons();
        loadPipeCoatingTypes();
        loadActStatuses();
        loadPipeSubstances();
        loadPipeLeakTypes();
        loadPumpPositions();
        loadEmloyees();
        loadEvents();
        loadWorks();
        loadRemarks();
        loadEventStatuses();
        loadPumpWorkTypes();
        loadPipeActs();
        loadPumpActs();
        loadDocs();
        dbHelper.close();
        updateSArraylists();
    }

    private void loadEvents() {
        int maxIdEvent = 0;

        String nameTable = "Event_date_time_pipeline";
        Cursor c = db.query(nameTable, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int id_list_date_time_pipeline = c.getColumnIndex("id_list_date_time_pipeline");
            int id_act_pipeline = c.getColumnIndex("id_act_pipeline");
            int id_event_date_time_for = c.getColumnIndex("id_event_date_time_for");
            int date_time = c.getColumnIndex("date_time");
            actEventsPipe.clear();
            do  {
                actEventsPipe.add( new EventDateTime(c.getInt(id_list_date_time_pipeline), c.getInt(id_act_pipeline), c.getInt(id_event_date_time_for), new Date(c.getInt(date_time)*1000)));
                Log.d(nameTable+"load", "loaded: "+c.getInt(id_list_date_time_pipeline)+" "+c.getString(id_act_pipeline)+" "+c.getString(id_event_date_time_for)+" "+new Date(c.getInt(date_time)*1000));
                if (c.getInt(id_list_date_time_pipeline) > maxIdEvent) maxIdEvent = c.getInt(id_list_date_time_pipeline);
            }while (c.moveToNext());
        } else
            Log.d(nameTable+"load", "0 rows");
        c.close();

        nameTable = "Event_date_time_pump";
        c = db.query(nameTable, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int id_list_date_time_pipeline = c.getColumnIndex("id_list_date_time_pump");
            int id_act_pipeline = c.getColumnIndex("id_act_pump");
            int id_event_date_time_for = c.getColumnIndex("id_event_date_time_for");
            int date_time = c.getColumnIndex("date_time");

            actEventsPump.clear();
            do  {
                actEventsPump.add( new EventDateTime(c.getInt(id_list_date_time_pipeline), c.getInt(id_act_pipeline), c.getInt(id_event_date_time_for), new Date(c.getInt(date_time)* 1000L)));
                if (c.getInt(id_list_date_time_pipeline) > maxIdEvent) maxIdEvent = c.getInt(id_list_date_time_pipeline);
                Log.d(nameTable+"load", "loaded: "+c.getInt(id_list_date_time_pipeline)+" "+c.getString(id_act_pipeline)+" "+c.getString(id_event_date_time_for)+" "+new Date(c.getInt(date_time)* 1000L));
            }while (c.moveToNext());
        } else
            Log.d(nameTable+"load", "0 rows");
        c.close();

        nameTable = "Event_date_time_prescription";
        c = db.query(nameTable, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int id_list_date_time_pipeline = c.getColumnIndex("id_list_date_time_prescription");
            int id_act_pipeline = c.getColumnIndex("id_prescription");
            int id_event_date_time_for = c.getColumnIndex("id_event_date_time_for");
            int date_time = c.getColumnIndex("date_time");

            actEventsDoc.clear();
            do  {
                actEventsDoc.add( new EventDateTime(c.getInt(id_list_date_time_pipeline), c.getInt(id_act_pipeline), c.getInt(id_event_date_time_for), new Date(c.getInt(date_time)* 1000L)));
                if (c.getInt(id_list_date_time_pipeline) > maxIdEvent) maxIdEvent = c.getInt(id_list_date_time_pipeline);
                Log.d(nameTable+"load", "loaded: "+c.getInt(id_list_date_time_pipeline)+" "+c.getString(id_act_pipeline)+" "+c.getString(id_event_date_time_for)+" "+new Date(c.getInt(date_time)* 1000L));
            }while (c.moveToNext());
        } else
            Log.d(nameTable+"load", "0 rows");
        c.close();
        NEW_ID_EVENT = maxIdEvent;
    }

    private void loadWorks() {
        int maxIdWorkDoc = 0;
        int maxIdWorkPump = 0;

        String nameTable = "Work_pump_completed";
        Cursor c = db.query(nameTable, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int id_prescription_remark = c.getColumnIndex("id_completed_work_pump");
            int name_ = c.getColumnIndex("id_act_pump");
            int text = c.getColumnIndex("id_type_work");

            pumpWorks.clear();
            do  {
                pumpWorks.add(new WorkPump(c.getInt(id_prescription_remark), c.getInt(name_), c.getInt(text)));
                if (c.getInt(id_prescription_remark) > maxIdWorkPump) maxIdWorkPump = c.getInt(id_prescription_remark);
                Log.d(nameTable+"load", "loaded: "+c.getInt(id_prescription_remark)+" "+c.getString(name_));
            }while (c.moveToNext());
        } else
            Log.d(nameTable+"load", "0 rows");
        c.close();

        nameTable = "Prescription_work";
        c = db.query(nameTable, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int id_prescription_remark = c.getColumnIndex("id_prescription_work");
            int name_ = c.getColumnIndex("id_prescription");
            int name = c.getColumnIndex("name_work");
            int text = c.getColumnIndex("description_work");
            docWorks.clear();
            do  {
                docWorks.add( new WorkDoc(c.getInt(id_prescription_remark), c.getInt(name_), c.getString(text), c.getString(name)));
                if (c.getInt(id_prescription_remark) > maxIdWorkDoc) maxIdWorkPump = c.getInt(id_prescription_remark);
                Log.d(nameTable+"load", "loaded: "+c.getInt(id_prescription_remark)+" "+c.getString(name_));
            }while (c.moveToNext());
        } else
            Log.d(nameTable+"load", "0 rows");
        c.close();

        NEW_ID_DOC_WORK = maxIdWorkDoc;
        NEW_ID_PUMP_WORK = maxIdWorkPump;
    }

    private void loadRemarks() {
        int maxIdRemark = 0;
        String nameTable = "Prescription_remark";
        Cursor c = db.query(nameTable, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int id_prescription_remark = c.getColumnIndex("id_prescription_remark");
            int name_ = c.getColumnIndex("id_prescription");
            int text = c.getColumnIndex("text");

            docRemarks.clear();
            do  {
                docRemarks.add( new Remark(c.getInt(id_prescription_remark), c.getInt(name_), c.getString(text)));
                if (c.getInt(id_prescription_remark) > maxIdRemark) maxIdRemark = c.getInt(id_prescription_remark);
                Log.d(nameTable+"load", "loaded: "+c.getInt(id_prescription_remark)+" "+c.getString(name_));
            }while (c.moveToNext());
        } else
            Log.d(nameTable+"load", "0 rows");
        c.close();

        NEW_ID_REMARK = maxIdRemark;
    }

    private void loadEventStatuses() {
        String nameTable = "Event_status";
        Cursor c = db.query(nameTable, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int id_department = c.getColumnIndex("id_event_status");
            int name_ = c.getColumnIndex("name_");
            actEventStatuses.clear();
            do  {
                actEventStatuses.add( new Dir(c.getInt(id_department), c.getString(name_)));
                Log.d(nameTable+"load", "loaded: "+c.getInt(id_department)+" "+c.getString(name_));
            }while (c.moveToNext());
        } else
            Log.d(nameTable+"load", "0 rows");
        c.close();
    }

    private void loadPumpWorkTypes() {
        String nameTable = "Type_work_pump";
        Cursor c = db.query(nameTable, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int id_department = c.getColumnIndex("id_type_work_pump");
            int name_ = c.getColumnIndex("name_");
            pumpWorkTypes.clear();
            do  {
                pumpWorkTypes.add( new Dir(c.getInt(id_department), c.getString(name_)));
                Log.d(nameTable+"load", "loaded: "+c.getInt(id_department)+" "+c.getString(name_));
            }while (c.moveToNext());
        } else
            Log.d(nameTable+"load", "0 rows");
        c.close();
    }

    private void loadPumpPositions() {
        String nameTable = "Position";
        Cursor c = db.query(nameTable, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int id_department = c.getColumnIndex("id_position");
            int name_ = c.getColumnIndex("name_");
            pumpPositions.clear();
            do  {
                pumpPositions.add( new Dir(c.getInt(id_department), c.getString(name_)));
                Log.d(nameTable+"load", "loaded: "+c.getInt(id_department)+" "+c.getString(name_));
            }while (c.moveToNext());
        } else
            Log.d(nameTable+"load", "0 rows");
        c.close();
    }

    private void loadPipeLeakTypes() {
        String nameTable = "Type_leak";
        Cursor c = db.query(nameTable, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int id_department = c.getColumnIndex("id_type_leak");
            int name_ = c.getColumnIndex("name_");
            pipeLeakTypes.clear();
            do  {
                pipeLeakTypes.add( new Dir(c.getInt(id_department), c.getString(name_)));
                Log.d(nameTable+"load", "loaded: "+c.getInt(id_department)+" "+c.getString(name_));
            }while (c.moveToNext());
        } else
            Log.d(nameTable+"load", "0 rows");
        c.close();
    }

    private void loadPipeSubstances() {
        String nameTable = "Substance";
        Cursor c = db.query(nameTable, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int id_department = c.getColumnIndex("id_substance");
            int name_ = c.getColumnIndex("name_");
            pipeSubstances.clear();
            do  {
                pipeSubstances.add( new Dir(c.getInt(id_department), c.getString(name_)));
                Log.d(nameTable+"load", "loaded: "+c.getInt(id_department)+" "+c.getString(name_));
            }while (c.moveToNext());
        } else
            Log.d(nameTable+"load", "0 rows");
        c.close();
    }

    private void loadActStatuses() {
        String nameTable = "Status_act";
        Cursor c = db.query(nameTable, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int id_department = c.getColumnIndex("id_status_act");
            int name_ = c.getColumnIndex("name_");
            actStatuses.clear();
            actStatuses.add( new Dir(c.getInt(id_department), c.getString(name_)));
            c.moveToNext();
            actStatuses.add( new Dir(c.getInt(id_department), c.getString(name_)));
//            do  {
//                actStatuses.add( new Dir(c.getInt(id_department), c.getString(name_)));
//                Log.d(nameTable+"load", "loaded: "+c.getInt(id_department)+" "+c.getString(name_));
//            }while (c.moveToNext());
        } else
            Log.d(nameTable+"load", "0 rows");
        c.close();
    }

    private void loadPipeCoatingTypes() {
        String nameTable = "Type_coating";
        Cursor c = db.query(nameTable, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int id_department = c.getColumnIndex("id_type_coating");
            int name_ = c.getColumnIndex("name_");
            pipeCoatingTypes.clear();
            do  {
                pipeCoatingTypes.add( new Dir(c.getInt(id_department), c.getString(name_)));
                Log.d(nameTable+"load", "loaded: "+c.getInt(id_department)+" "+c.getString(name_));
            }while (c.moveToNext());
        } else
            Log.d(nameTable+"load", "0 rows");
        c.close();
    }

    private void loadPumpStopReasons() {
        String nameTable = "Reason_stop_pump";
        Cursor c = db.query(nameTable, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int id_department = c.getColumnIndex("id_reason_stop_pump");
            int name_ = c.getColumnIndex("name_");
            pumpStopReasons.clear();
            do  {
                pumpStopReasons.add( new Dir(c.getInt(id_department), c.getString(name_)));
                Log.d(nameTable+"load", "loaded: "+c.getInt(id_department)+" "+c.getString(name_));
            }while (c.moveToNext());
        } else
            Log.d(nameTable+"load", "0 rows");
        c.close();
    }

    private void loadPipeNames() {
        String nameTable = "Pipeline";
        Cursor c = db.query(nameTable, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int id_department = c.getColumnIndex("id_pipeline");
            int name_ = c.getColumnIndex("name_");
            pipeNames.clear();
            do  {
                pipeNames.add( new Dir(c.getInt(id_department), c.getString(name_)));
                Log.d(nameTable+"load", "loaded: "+c.getInt(id_department)+" "+c.getString(name_));
            }while (c.moveToNext());
        } else
            Log.d(nameTable+"load", "0 rows");
        c.close();
    }

    private void loadPumpMarks() {
        String nameTable = "Mark";
        Cursor c = db.query(nameTable, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int id_department = c.getColumnIndex("id_mark");
            int name_ = c.getColumnIndex("name_");
            pumpMarks.clear();
            do  {
                pumpMarks.add( new Dir(c.getInt(id_department), c.getString(name_)));
                Log.d(nameTable+"load", "loaded: "+c.getInt(id_department)+" "+c.getString(name_));
            }while (c.moveToNext());
        } else
            Log.d(nameTable+"load", "0 rows");
        c.close();
    }

    private void loadActEventTypes() {
        String nameTable = "Event_date_time_for";
        Cursor c = db.query(nameTable, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int id_event_date_time_for = c.getColumnIndex("id_event_date_time_for");
            int name_ = c.getColumnIndex("name_");
            actEventTypes.clear();
            do  {
                actEventTypes.add( new Dir(c.getInt(id_event_date_time_for), c.getString(name_)));
                Log.d(nameTable+"load", "loaded: "+c.getInt(id_event_date_time_for)+" "+c.getString(name_));
            }while (c.moveToNext());
        } else
            Log.d(nameTable+"load", "0 rows");
        c.close();
    }

    private void loadDocDepartmentObjects() {
        String nameTable = "Department_object";
        Cursor c = db.query(nameTable, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int id_department_object = c.getColumnIndex("id_department_object");
            int id_department = c.getColumnIndex("id_department");
            int name_ = c.getColumnIndex("name_");
            docDepartmentObjects.clear();
            do  {
                docDepartmentObjects.add( new DepartmentObject(c.getInt(id_department_object), c.getInt(id_department), c.getString(name_)));
                Log.d(nameTable+"load", "loaded: "+c.getInt(id_department_object)+" "+c.getInt(id_department)+" "+c.getString(name_));
            }while (c.moveToNext());
        } else
            Log.d(nameTable+"load", "0 rows");
        c.close();
    }

    private void loadDocDepartment() {
        String nameTable = "Department";
        Cursor c = db.query(nameTable, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int id_department = c.getColumnIndex("id_department");
            int name_ = c.getColumnIndex("name_");
            docDepartments.clear();
            do  {
                docDepartments.add( new Dir(c.getInt(id_department), c.getString(name_)));
                Log.d(nameTable+"load", "loaded: "+c.getInt(id_department)+" "+c.getString(name_));
            }while (c.moveToNext());
        } else
            Log.d(nameTable+"load", "0 rows");
        c.close();
    }

    private void loadDocs() {
        String nameTable = "Prescription";
        Cursor c = db.query(nameTable, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int id_prescription = c.getColumnIndex("id_prescription");
            int id_department_object = c.getColumnIndex("id_department_object");
            int id_employee = c.getColumnIndex("id_employee");
            int fio_sending = c.getColumnIndex("fio_sending");
            int id_status = c.getColumnIndex("id_status");
            docActs.clear();
            do  {
                docActs.add( new ActDoc(c.getInt(id_prescription), c.getInt(id_department_object), c.getInt(id_employee),
                        c.getInt(id_status), c.getString(fio_sending)));
                Log.d(nameTable+"load", "loaded: "+docActs.get(docActs.size()-1));
            }while (c.moveToNext());
        } else
            Log.d(nameTable+"load", "0 rows");
        c.close();
    }

    private void loadPumpActs() {
        String nameTable = "Act_pump";
        Cursor c = db.query(nameTable, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int id_act_pump = c.getColumnIndex("id_act_pump");
            int id_mark = c.getColumnIndex("id_mark");
            int id_reason_stop_pump = c.getColumnIndex("id_reason_stop_pump");
            int note = c.getColumnIndex("note");
            int id_status = c.getColumnIndex("id_status");
            int id_pump = c.getColumnIndex("id_pump");
            pumpActs.clear();
            do  {
                pumpActs.add( new ActPump(c.getInt(id_act_pump), c.getInt(id_pump), c.getInt(id_mark), c.getInt(id_reason_stop_pump), c.getInt(id_status),
                        c.getString(note)));
                Log.d(nameTable+"load", "loaded: "+pumpActs.get(pumpActs.size()-1));
            }while (c.moveToNext());
        } else
            Log.d(nameTable+"load", "0 rows");
        c.close();
    }

    private void loadPipeActs() {
        String nameTable = "Act_pipeline";
        Cursor c = db.query(nameTable, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int id = c.getColumnIndex("id_act_pipeline");
            int id_pipeline = c.getColumnIndex("id_pipeline");
            int diameter_pipeline = c.getColumnIndex("diameter_pipeline");
            int wall_thickness = c.getColumnIndex("wall_thickness");
            int id_type_coating = c.getColumnIndex("id_type_coating");
            int piketash = c.getColumnIndex("piketash");
            int id_leak_type = c.getColumnIndex("id_leak_type");
            int leak_parameter = c.getColumnIndex("leak_parameter");
            int leak_position = c.getColumnIndex("leak_position");
            int id_substance = c.getColumnIndex("id_substance");
            int spill_area = c.getColumnIndex("spill_area");
            int id_status = c.getColumnIndex("id_status");
            int id_employee = c.getColumnIndex("id_employee");
            pipeActs.clear();
            do  {
                pipeActs.add( new ActPipe(c.getInt(id), c.getInt(id_pipeline), c.getInt(diameter_pipeline), c.getInt(wall_thickness),
                        c.getInt(id_type_coating), c.getInt(piketash), c.getInt(id_leak_type), c.getInt(leak_parameter), c.getInt(leak_position),
                        c.getInt(id_substance), c.getInt(id_status), c.getInt(id_employee), c.getDouble(spill_area)));
                Log.d(nameTable+"load", "loaded: "+pipeActs.get(pipeActs.size()-1));
            }while (c.moveToNext());
        } else
            Log.d(nameTable+"load", "0 rows");
        c.close();
    }

    private void loadEmloyees() {
        String nameTable = "Employee";
        Cursor c = db.query(nameTable, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int id = c.getColumnIndex("id_employee");
            int FIO = c.getColumnIndex("fio");
            int idPost = c.getColumnIndex("id_post");
            int idStatus = c.getColumnIndex("id_status");
            employees.clear();
            do  {
                employees.add( new Employee(c.getInt(id), c.getInt(idPost), c.getInt(idStatus),c.getString(FIO)));
                Log.d(nameTable+"load", "loaded: "+c.getInt(id)+" "+c.getInt(idPost)+" "+c.getInt(idStatus)+" "+c.getString(FIO));
            }while (c.moveToNext());
        } else
            Log.d(nameTable+"load", "0 rows");
        c.close();
    }

    private void removeDataToTable(String nameTable) {
        db.delete(nameTable, null, null);
    }


    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d("1", "--- onCreate database ---");
            db.execSQL("create table act_pipeline ( id_act_pipeline integer, id_pipeline integer, diameter_pipeline integer, wall_thickness integer, id_type_coating integer, piketash integer, id_leak_type integer, leak_parameter real, leak_position integer, id_substance integer, spill_area real, id_status integer, id_employee integer);");
            db.execSQL("create table act_pump ( id_act_pump integer, id_mark integer, id_reason_stop_pump integer, note text, id_status integer, id_pump integer);");
            db.execSQL("create table department ( id_department integer, name_ text);");
            db.execSQL("create table department_object ( id_department_object integer, id_department integer, name_ text);");
            db.execSQL("create table employee ( id_employee integer, fio text, id_post integer, id_status integer) ;");
            db.execSQL("create table employee_login ( id_employee_login integer, login text, password_ text, id_employee integer, id_role integer);");
            db.execSQL("create table event_date_time_for ( id_event_date_time_for integer, name_ text);");
            db.execSQL("create table event_date_time_pipeline ( id_list_date_time_pipeline integer, id_act_pipeline integer, id_event_date_time_for integer, date_time integer)");
            db.execSQL("create table event_date_time_prescription ( id_list_date_time_prescription integer, id_prescription integer, id_event_date_time_for integer, date_time integer);");
            db.execSQL("create table event_date_time_pump ( id_list_date_time_pump integer, id_act_pump integer, id_event_date_time_for integer, date_time integer);");
            db.execSQL("create table event_status ( id_event_status integer, name_ text);");
            db.execSQL("create table mark ( id_mark integer, name_ text);");
            db.execSQL("create table pipeline ( id_pipeline integer, name_ text, length_ integer, year_start_pipeline integer);");
            db.execSQL("create table position ( id_position integer, name_ text) ;");
            db.execSQL("create table post ( id_post integer, name_ text);");
            db.execSQL("create table prescription ( id_prescription integer, id_department_object integer, id_employee integer, fio_sending text, id_status integer);");
            db.execSQL("create table prescription_remark ( id_prescription_remark, id_prescription integer, text text);");
            db.execSQL("create table prescription_work ( id_prescription_work integer, id_prescription integer, name_work text, description_work text)");
            db.execSQL("create table pump ( id_pump integer, id_position integer, working_time_after_repair integer, working_time_after_major_repair integer);");
            db.execSQL("create table reason_stop_pump ( id_reason_stop_pump integer, name_ text);");
            db.execSQL("create table role ( id_role integer, name_ text);");
            db.execSQL("create table status_act ( id_status_act integer, name_ text);");
            db.execSQL("create table status_employee ( id_status_employee integer, name_ text);");
            db.execSQL("create table substance ( id_substance integer, name_ text);");
            db.execSQL("create table type_coating ( id_type_coating integer, name_ text);");
            db.execSQL("create table type_leak ( id_type_leak integer, name_ text);");
            db.execSQL("create table type_work_pump ( id_type_work_pump, name_ text);");
            db.execSQL("create table work_pump_completed ( id_completed_work_pump integer, id_act_pump integer, id_type_work integer);");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}
