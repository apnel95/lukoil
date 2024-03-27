package com.example.lukoil.entity.act;

import static com.example.lukoil.activity.General.ID_DATE_TIME_STOP_WORK_DOC;

import com.example.lukoil.entity.event.EventDateTime;
import com.example.lukoil.entity.remark.Remark;
import com.example.lukoil.entity.work.Work;

import java.util.ArrayList;
import java.util.Date;

public class ActDoc extends Act {
    int idDepartment, idDepartmentObject, idEmployee, idStatus;
    ArrayList<EventDateTime> events;
    ArrayList<Remark> remarks;

    ArrayList<Work> works;
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

    public ActDoc(int id, int id_department, int idDepartmentObject, int idEmployee, int idStatus, ArrayList<EventDateTime> events, ArrayList<Remark> remarks, ArrayList<Work> works, String FIO_senging) {
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

    public ArrayList<Work> getWorks() {
        return works;
    }

    public void setWorks(ArrayList<Work> works) {
        this.works = works;
    }

    public String getFIOSending() {
        return FIO_senging;
    }

    public void setFIO_senging(String FIO_senging) {
        this.FIO_senging = FIO_senging;
    }

}
