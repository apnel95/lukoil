package com.example.lukoil.entity;

import static com.example.lukoil.GeneralClass.idDateTimeStopWorkDoc;

import com.example.lukoil.Act;

import java.util.ArrayList;
import java.util.Date;

public class DocAct extends Act {
    int id_department, id_department_object, id_employee, id_status;
    ArrayList<Event_date_time> events;
    ArrayList<Remark> remarks;

    ArrayList<Work> works;
    String FIO_senging;

    public DocAct() {
    }

    public DocAct(int id, int id_employee, int id_status, Date date_time_stop) {
        this.id = id;
        this.id_employee = id_employee;
        this.id_status = id_status;
        this.dateTimeStop = date_time_stop;
    }

    public DocAct(int id, int id_department, int id_department_object, int id_employee, int id_status, ArrayList<Event_date_time> events, ArrayList<Remark> remarks, ArrayList<Work> works, String FIO_senging) {
        this.id = id;
        this.id_department = id_department;
        this.id_department_object = id_department_object;
        this.id_employee = id_employee;
        this.id_status = id_status;
        this.events = events;
        this.remarks = remarks;
        this.works = works;
        this.FIO_senging = FIO_senging;
        for (Event_date_time wrk: events){
            if (wrk.getId_type_event() ==  idDateTimeStopWorkDoc){
                this.dateTimeStop = wrk.getDateTime();
                break;
            }
        }
    }

    public Date getDate_time_stop() {
        return dateTimeStop;
    }

    public void setDate_time_stop(Date date_time_stop) {
        this.dateTimeStop = date_time_stop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_department() {
        return id_department;
    }

    public void setId_department(int id_department) {
        this.id_department = id_department;
    }

    public int getId_department_object() {
        return id_department_object;
    }

    public void setId_department_object(int id_department_object) {
        this.id_department_object = id_department_object;
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    public int getId_status() {
        return id_status;
    }

    public void setId_status(int id_status) {
        this.id_status = id_status;
    }

    public ArrayList<Event_date_time> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event_date_time> events) {
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

    public String getFIO_senging() {
        return FIO_senging;
    }

    public void setFIO_senging(String FIO_senging) {
        this.FIO_senging = FIO_senging;
    }

}
