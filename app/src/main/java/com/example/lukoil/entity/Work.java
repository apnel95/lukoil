package com.example.lukoil.entity;

import java.io.Serializable;

public class Work implements Serializable {
    int id, id_Doc;
    String text, name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Work(int id, int id_Doc, String text, String name) {
        this.id = id;
        this.id_Doc = id_Doc;
        this.text = text;
        this.name = name;
    }
}
