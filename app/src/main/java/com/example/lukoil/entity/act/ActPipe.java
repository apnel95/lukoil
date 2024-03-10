package com.example.lukoil.entity.act;

import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.event.EventDateTime;

import java.util.ArrayList;
import java.util.Date;

public class ActPipe extends Act {
    int id_trub, diameter, wall, id_type_coating, piketash, id_leak_type, leak_parameter, leak_position, id_substance, id_status, id_who;
    double spill_area;
    ArrayList<EventDateTime> works;

    public ActPipe(int id, int id_trub, int diameter, int wall, int id_type_coating, int piketash, int id_leak_type, int leak_parameter, int leak_position, int id_substance, int id_status, int id_who, double spill_area, ArrayList<EventDateTime> works) {
        this.id = id;
        this.id_trub = id_trub;
        this.diameter = diameter;
        this.wall = wall;
        this.id_type_coating = id_type_coating;
        this.piketash = piketash;
        this.id_leak_type = id_leak_type;
        this.leak_parameter = leak_parameter;
        this.leak_position = leak_position;
        this.id_substance = id_substance;
        this.id_status = id_status;
        this.id_who = id_who;
        this.spill_area = spill_area;
        this.works = works;
        dateTimeStop = new Date(0,0,1);
        for (EventDateTime wrk: works){
            if (wrk.getId_type_event() == 0){
                this.dateTimeStop = wrk.getDateTime();
                break;
            }
        }
    }

    public ActPipe(int id, int id_trub, int id_status, Date dateTimeStop) {
        this.id = id;
        this.id_trub = id_trub;
        this.id_status = id_status;
        this.dateTimeStop = dateTimeStop;
    }

    public ActPipe(int id, int id_trub, int id_status) {
        this.id = id;
        this.id_trub = id_trub;
        this.id_status = id_status;
    }

    public int getId_who() {
        return id_who;
    }

    public void setId_who(int id_who) {
        this.id_who = id_who;
    }

    public ActPipe() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_trub(int id_trub) {
        this.id_trub = id_trub;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public void setWall(int wall) {
        this.wall = wall;
    }

    public void setId_type_coating(int id_type_coating) {
        this.id_type_coating = id_type_coating;
    }

    public void setPiketash(int piketash) {
        this.piketash = piketash;
    }

    public void setId_leak_type(int id_leak_type) {
        this.id_leak_type = id_leak_type;
    }

    public void setLeak_parameter(int leak_parameter) {
        this.leak_parameter = leak_parameter;
    }

    public void setLeak_position(int leak_position) {
        this.leak_position = leak_position;
    }

    public void setId_substance(int id_substance) {
        this.id_substance = id_substance;
    }

    public void setId_status(int id_status) {
        this.id_status = id_status;
    }

    public void setSpill_area(double spill_area) {
        this.spill_area = spill_area;
    }

    public void setWorks(ArrayList<EventDateTime> works) {
        this.works = works;
    }

    public int getId() {
        return id;
    }

    public int getId_trub() {
        return id_trub;
    }

    public int getDiameter() {
        return diameter;
    }

    public int getWall() {
        return wall;
    }

    public int getId_type_coating() {
        return id_type_coating;
    }

    public int getPiketash() {
        return piketash;
    }

    public int getId_leak_type() {
        return id_leak_type;
    }

    public int getLeak_parameter() {
        return leak_parameter;
    }

    public int getLeak_position() {
        return leak_position;
    }

    public int getId_substance() {
        return id_substance;
    }

    public int getId_status() {
        return id_status;
    }

    public double getSpill_area() {
        return spill_area;
    }

    public ArrayList<EventDateTime> getWorks() {
        return works;
    }

    public Date getDateTimeStop() {
        return dateTimeStop;
    }

    public void setDateTimeStop(Date date_time_stop) {
        this.dateTimeStop = date_time_stop;
    }

    public ActPipe(int id, ArrayList<EventDateTime> works) {
        this.id = id;
        this.works = works;
        dateTimeStop = new Date(0,0,1);
        for (EventDateTime wrk: works){
            if (wrk.getId_type_event() == 0){
                this.dateTimeStop = wrk.getDateTime();
                break;
            }
        }
    }
    public String getName(ArrayList<Dir> trubs){
        if (trubs != null) for (Dir dr: trubs) if(dr.getId() == this.id_trub) return dr.getName();
        return "";
    }
}


