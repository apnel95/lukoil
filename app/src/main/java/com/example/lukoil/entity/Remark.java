package com.example.lukoil.entity;

import java.io.Serializable;

public class Remark implements Serializable {
    int id, id_Doc;
    String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_Doc() {
        return id_Doc;
    }

    public void setId_Doc(int id_Doc) {
        this.id_Doc = id_Doc;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Remark(int id, int id_Doc, String text) {
        this.id = id;
        this.id_Doc = id_Doc;
        this.text = text;
    }
}
