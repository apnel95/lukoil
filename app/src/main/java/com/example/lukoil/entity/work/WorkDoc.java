package com.example.lukoil.entity.work;

import com.example.lukoil.entity.Dir;

import java.io.Serializable;

public class WorkDoc extends Dir implements Serializable {
    int idDoc;
    String text;
    public int getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(int idDoc) {
        this.idDoc = idDoc;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public WorkDoc(int id, int idDoc, String text, String name) {
        this.id = id;
        this.idDoc = idDoc;
        this.text = text;
        this.name = name;
    }
}
