package com.example.lukoil.entity;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Dir implements Serializable {
    protected int id;
    protected String name;

    public Dir(Dir dir) {
        this.id = dir.getId();
        this.name = dir.getName();
    }

    public Dir(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Dir() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString(){
        return getName();
    }
}
