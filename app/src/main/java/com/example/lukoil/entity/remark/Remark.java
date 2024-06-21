package com.example.lukoil.entity.remark;

import com.example.lukoil.entity.Dir;

import java.io.Serializable;

public class Remark extends Dir implements Serializable {
    int idDoc;


    public int getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(int idDoc) {
        this.idDoc = idDoc;
    }

    public Remark(int id, int idDoc, String name) {
        this.id = id;
        this.idDoc = idDoc;
        this.name = name;
    }
}
