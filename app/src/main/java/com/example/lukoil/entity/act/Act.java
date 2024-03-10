package com.example.lukoil.entity.act;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Act implements Serializable {
    protected int  id, status;

    public int getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public Date getDateTimeStop() {
        return dateTimeStop;
    }

    protected Date dateTimeStop;
}
