package com.example.lukoil.entity.event;

import com.example.lukoil.entity.Dir;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class EventDateTime implements Serializable {
    int id, id_act, id_type_event;
    Date date_time;

    public int getId() {
        return id;
    }

    public EventDateTime() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_act() {
        return id_act;
    }

    public void setId_act(int id_act) {
        this.id_act = id_act;
    }

    public int getId_type_event() {
        return id_type_event;
    }

    public void setId_type_event(int id_type_event) {
        this.id_type_event = id_type_event;
    }

    public Date getDateTime() {
        return date_time;
    }

    public void setDate_time(Date date_time) {
        this.date_time = date_time;
    }

    public EventDateTime(int id, int id_act, int id_type_event, Date date_time) {
        this.id = id;
        this.id_act = id_act;
        this.id_type_event = id_type_event;
        this.date_time = date_time;
    }
    public String getName(ArrayList<Dir> events){
        if (events != null) for (Dir dr: events) if(dr.getId() == this.id_type_event) return dr.getName();
        return "";
    }
}
