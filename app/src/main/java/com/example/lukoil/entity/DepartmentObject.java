package com.example.lukoil.entity;

import java.io.Serializable;

public class DepartmentObject extends Dir implements Serializable {
    public DepartmentObject() {
    }
    int idDep;

    public int getIdDep() {
        return idDep;
    }

    public void setIdDep(int idDep) {
        this.idDep = idDep;
    }

    public DepartmentObject(int id, int idDep, String name) {
        this.id = id;
        this.idDep = idDep;
        this.name = name;
    }
}
