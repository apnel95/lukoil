package com.example.lukoil.entity.act;

import static com.example.lukoil.GeneralClass.idDateTimeStopWork;

import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.event.EventDateTime;

import java.util.ArrayList;
import java.util.Date;

public class ActPump extends Act {
    int id_pump, id_mark, id_reason_stop, id_status;
    String note;

    public ActPump() {
    }

    public void setId_pump(int id_pump) {
        this.id_pump = id_pump;
    }

    public int getId_pump() {
        return id_pump;
    }

    public ActPump(int id, int id_pump, int id_status, Date dateTimeStop) {
        this.id = id;
        this.id_pump = id_pump;
        this.id_status = id_status;
        this.dateTimeStop = dateTimeStop;
    }

    public ActPump(int id, int id_pump, int id_mark, int id_reason_stop, int id_status, String note, ArrayList<EventDateTime> events, ArrayList<Integer> works_ready) {
        this.id = id;
        this.id_mark = id_mark;
        this.id_reason_stop = id_reason_stop;
        this.id_status = id_status;
        this.note = note;
        this.events = events;
        this.id_pump = id_pump;
        this.works_ready = works_ready;
        for (EventDateTime wrk: events){
            if (wrk.getId_type_event() == idDateTimeStopWork){
                this.dateTimeStop = wrk.getDateTime();
                break;
            }
        }
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    ArrayList<EventDateTime> events;
    ArrayList<Integer> works_ready;

    public int getId() {
        return id;
    }
    public Date getDate_time_stop() {
        return dateTimeStop;
    }

    public void setDate_time_stop(Date date_time_stop) {
        this.dateTimeStop = date_time_stop;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_mark() {
        return id_mark;
    }

    public void setId_mark(int id_mark) {
        this.id_mark = id_mark;
    }

    public int getId_reason_stop() {
        return id_reason_stop;
    }

    public void setId_reason_stop(int id_reason_stop) {
        this.id_reason_stop = id_reason_stop;
    }

    public int getId_status() {
        return id_status;
    }

    public void setId_status(int id_status) {
        this.id_status = id_status;
    }

    public ArrayList<EventDateTime> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<EventDateTime> events) {
        this.events = events;
    }

    public ArrayList<Integer> getWorks_ready() {
        return works_ready;
    }

    public void setWorks_ready(ArrayList<Integer> works_ready) {
        this.works_ready = works_ready;
    }

    public String getName(ArrayList<Dir> names){
        if (names != null) for (Dir dr: names) if(dr.getId() == this.id_pump) return dr.getName();
        return "";
    }
}
