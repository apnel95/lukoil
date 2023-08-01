package com.example.lukoil.entity;

import static com.example.lukoil.GeneralClass.posts;

import java.io.Serializable;
import java.util.ArrayList;

public class Employee implements Serializable {
    int id, id_post, id_status;
    String FIO;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }

    public int getId_status() {
        return id_status;
    }

    public void setId_status(int id_status) {
        this.id_status = id_status;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public Employee() {
    }

    public Employee(int id, int id_post, int id_status, String FIO) {
        this.id = id;
        this.id_post = id_post;
        this.id_status = id_status;
        this.FIO = FIO;
    }

    @Override
    public String toString(){
        return (getFIO()+", "+posts.get(posts.indexOf(getId_post())).getName());
    }
}
