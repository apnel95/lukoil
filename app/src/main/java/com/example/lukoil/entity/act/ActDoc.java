package com.example.lukoil.entity.act;

import static com.example.lukoil.ListData.actEventsDoc;
import static com.example.lukoil.ListData.docDepartmentObjects;
import static com.example.lukoil.ListData.docRemarks;
import static com.example.lukoil.ListData.docWorks;
import static com.example.lukoil.activity.General.ID_DATE_TIME_STOP_WORK_DOC;

import com.example.lukoil.entity.DepartmentObject;
import com.example.lukoil.entity.event.EventDateTime;
import com.example.lukoil.entity.remark.Remark;
import com.example.lukoil.entity.work.WorkDoc;

import java.util.ArrayList;
import java.util.Date;

public class ActDoc extends Act {
    int idDepartment, idDepartmentObject, idEmployee, idStatus;
    ArrayList<Remark> remarks;

    ArrayList<WorkDoc> works;
    String FIO_senging;

    public ActDoc() {
        this.dateTimeStop = new Date();
    }

    public ActDoc(int id, int idEmployee, int idStatus, Date dateTimeStop) {
        this.id = id;
        this.idEmployee = idEmployee;
        this.idStatus = idStatus;
        this.dateTimeStop = dateTimeStop;
    }

    public ActDoc(int id, int id_department, int idDepartmentObject, int idEmployee, int idStatus, ArrayList<EventDateTime> events, ArrayList<Remark> remarks, ArrayList<WorkDoc> works, String FIO_senging) {
        this.id = id;
        this.idDepartment = id_department;
        this.idDepartmentObject = idDepartmentObject;
        this.idEmployee = idEmployee;
        this.idStatus = idStatus;
        this.events = events;
        this.remarks = remarks;
        this.works = works;
        this.FIO_senging = FIO_senging;
        this.dateTimeStop = new Date();
        for (EventDateTime wrk: events){
            if (wrk.getId_type_event() == ID_DATE_TIME_STOP_WORK_DOC){
                this.dateTimeStop = wrk.getDateTime();
                break;
            }
        }
    }
    public ActDoc(int id, int idDepartmentObject, int idEmployee, int idStatus, ArrayList<EventDateTime> events, ArrayList<Remark> remarks, ArrayList<WorkDoc> works, String FIO_senging) {
        this.id = id;
        this.idDepartmentObject = idDepartmentObject;

        this.idDepartment = 0;
        for (DepartmentObject departmentObject: docDepartmentObjects) if (departmentObject.getId() == idDepartmentObject) this.idDepartment = departmentObject.getIdDep();

        this.idEmployee = idEmployee;
        this.idStatus = idStatus;
        this.events = events;
        this.remarks = remarks;
        this.works = works;
        this.FIO_senging = FIO_senging;
        this.dateTimeStop = new Date();
        for (EventDateTime wrk: events){
            if (wrk.getId_type_event() == ID_DATE_TIME_STOP_WORK_DOC){
                this.dateTimeStop = wrk.getDateTime();
                break;
            }
        }
    }

    public ActDoc(int id, int idDepartmentObject, int idEmployee, int idStatus, String FIO_senging) {
        this.id = id;
        this.idDepartmentObject = idDepartmentObject;

        this.idDepartment = 0;
        for (DepartmentObject departmentObject: docDepartmentObjects) if (departmentObject.getId() == idDepartmentObject) this.idDepartment = departmentObject.getIdDep();

        this.idEmployee = idEmployee;
        this.idStatus = idStatus;
        this.FIO_senging = FIO_senging;

        this.events = new ArrayList<>();
        this.works = new ArrayList<>();
        this.remarks = new ArrayList<>();

        for (EventDateTime evd: actEventsDoc) if (evd.getId_act() == id) this.events.add(evd);

        for (WorkDoc dw: docWorks) if (dw.getIdDoc() == id) this.works.add(dw);

        for (Remark rd: docRemarks) if (rd.getIdDoc() == id) this.remarks.add(rd);

        this.dateTimeStop = new Date();
        for (EventDateTime wrk: events){
            if (wrk.getId_type_event() == ID_DATE_TIME_STOP_WORK_DOC){
                this.dateTimeStop = wrk.getDateTime();
                break;
            }
        }
    }


    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public int getIdDepartmentObject() {
        return idDepartmentObject;
    }

    public void setIdDepartmentObject(int idDepartmentObject) {
        this.idDepartmentObject = idDepartmentObject;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public ArrayList<EventDateTime> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<EventDateTime> events) {
        this.events = events;
    }

    public ArrayList<Remark> getRemarks() {
        return remarks;
    }

    public void setRemarks(ArrayList<Remark> remarks) {
        this.remarks = remarks;
    }

    public ArrayList<WorkDoc> getWorks() {
        return works;
    }

    public void setWorks(ArrayList<WorkDoc> works) {
        this.works = works;
    }

    public String getFIOSending() {
        return FIO_senging;
    }

    public void setFIO_senging(String FIO_senging) {
        this.FIO_senging = FIO_senging;
    }

    public void println() {
        System.out.println("ActDoc");
        System.out.println("id " +this.id);
        System.out.println("idDepartment " +this.idDepartment);
        System.out.println("idDepartmentObject " +this.idDepartmentObject);
        System.out.println("idEmployee " +this.idEmployee);
        System.out.println("idStatus " +this.idStatus);
        System.out.println("events " +this.events);
        System.out.println("remarks " +this.remarks);
        System.out.println("works " +this.works);
        System.out.println("FIO_senging " +this.FIO_senging);
        System.out.println("dateTimeStop " +this.dateTimeStop);

        for (EventDateTime event: this.events) {
            System.out.println("event ");
            System.out.println("id " +event.getId());
            System.out.println("idAct "+event.getId_act());
            System.out.println("idTypeEvent "+event.getId_type_event());
            System.out.println("idDateTime "+event.getDateTime());
        }

        for (Remark remark: this.remarks) {
            System.out.println("remark ");
            System.out.println("id " +remark.getId());
            System.out.println("Id_Doc " +remark.getIdDoc());
            System.out.println("Text " +remark.getText());
        }
        for (WorkDoc work: this.works) {
            System.out.println("work" );
            System.out.println("id " +work.getId());
            System.out.println("Id_Doc " +work.getIdDoc());
            System.out.println("Name " +work.getName());
            System.out.println("Text " +work.getText());
        }
    }
}
