package com.example.lukoil.entity.act;

import static com.example.lukoil.ListData.actEventsPump;
import static com.example.lukoil.ListData.pumpWorks;
import static com.example.lukoil.activity.General.ID_DATE_TIME_STOP_WORK;

import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.event.EventDateTime;
import com.example.lukoil.entity.work.WorkPump;

import java.util.ArrayList;
import java.util.Date;

public class ActPump extends Act {
    int id_pump, id_mark, id_reason_stop;
    String note;

    ArrayList<WorkPump> works_ready;

    public ActPump() {
    }

    public void setId_pump(int id_pump) {
        this.id_pump = id_pump;
    }

    public int getIdPump() {
        return id_pump;
    }

    public ActPump(int id, int id_pump, int id_status, Date dateTimeStop) {
        this.id = id;
        this.id_pump = id_pump;
        this.idStatus = id_status;
        this.dateTimeStop = dateTimeStop;
    }

    public ActPump(int id, int id_pump, int id_mark, int id_reason_stop, int id_status, String note, ArrayList<EventDateTime> events, ArrayList<WorkPump> works_ready) {
        this.id = id;
        this.id_mark = id_mark;
        this.id_reason_stop = id_reason_stop;
        this.idStatus = id_status;
        this.note = note;
        this.events = events;
        this.id_pump = id_pump;
        this.works_ready = works_ready;
        for (EventDateTime wrk: events){
            if (wrk.getId_type_event() == ID_DATE_TIME_STOP_WORK){
                this.dateTimeStop = wrk.getDateTime();
                break;
            }
        }
    }

    public ActPump(int id, int id_pump, int id_mark, int id_reason_stop, int id_status, String note) {
        this.id = id;
        this.id_mark = id_mark;
        this.id_reason_stop = id_reason_stop;
        this.idStatus = id_status;
        this.note = note;
        this.id_pump = id_pump;

        this.events = new ArrayList<>();
        this.works_ready = new ArrayList<>();

        for (EventDateTime evd: actEventsPump) if (evd.getId_act() == id) this.events.add(evd);

        for (WorkPump pw: pumpWorks) if (pw.getIdAct() == id) this.works_ready.add(pw);

        this.dateTimeStop = new Date();
        for (EventDateTime wrk: events){
            if (wrk.getId_type_event() == ID_DATE_TIME_STOP_WORK){
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

    public int getIdMark() {
        return id_mark;
    }

    public void setId_mark(int id_mark) {
        this.id_mark = id_mark;
    }

    public int getIdReasonStop() {
        return id_reason_stop;
    }

    public void setId_reason_stop(int id_reason_stop) {
        this.id_reason_stop = id_reason_stop;
    }

    public ArrayList<EventDateTime> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<EventDateTime> events) {
        this.events = events;
    }

    public ArrayList<WorkPump> getWorksReady() {
        return works_ready;
    }

    public void setWorks_ready(ArrayList<WorkPump> works_ready) {
        this.works_ready = works_ready;
    }

    public String getName(ArrayList<Dir> names){
        if (names != null) for (Dir dr: names) if(dr.getId() == this.id_pump) return dr.getName();
        return "";
    }
}
