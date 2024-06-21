package com.example.lukoil.entity.act;

import static com.example.lukoil.ListData.NEW_ID_EVENT;

import android.util.Log;
import android.view.View;

import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.event.EventDateTime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.LongFunction;

import javax.crypto.AEADBadTagException;

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


    public void addEvent() {
        this.events.add(new EventDateTime(this.newIdEvent(),  this.id, 1, new Date()));
        Log.d("NEW_ID_EVENT", NEW_ID_EVENT+"");
    }

    private int newIdEvent() {
        NEW_ID_EVENT++;
        return NEW_ID_EVENT;
    }
}
