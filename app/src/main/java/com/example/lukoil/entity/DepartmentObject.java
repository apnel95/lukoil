package com.example.lukoil.entity;

import java.io.Serializable;

public class DepartmentObject implements Serializable {
    int id, id_dep;
    String name;

    public DepartmentObject() {
    }

    public int getId_dep() {
        return id_dep;
    }

    public void setId_dep(int id_dep) {
        this.id_dep = id_dep;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DepartmentObject(int id, int id_dep, String name) {
        this.id = id;
        this.id_dep = id_dep;
        this.name = name;
    }
    @Override
    public String toString(){
        return (getName());

    }
}
