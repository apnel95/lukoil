package com.example.lukoil.entity.act;

import com.example.lukoil.entity.event.EventDateTime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Act implements Serializable {
    protected int  id, idStatus;
    protected ArrayList<EventDateTime> events;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public void setDateTimeStop(Date dateTimeStop) {
        this.dateTimeStop = dateTimeStop;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public Date getDateTimeStop() {
        return dateTimeStop;
    }

    public void setEvents(ArrayList<EventDateTime> events) {
        this.events = events;
    }

    public ArrayList<EventDateTime> getEvents() {
        return events;
    }

    protected Date dateTimeStop;
}
