package com.example.lukoil.entity.act;

import java.io.Serializable;
import java.util.Date;

public class Act implements Serializable {
    protected int  id, idStatus;

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

    protected Date dateTimeStop;
}
