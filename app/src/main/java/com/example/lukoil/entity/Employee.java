package com.example.lukoil.entity;

import java.io.Serializable;

public class Employee implements Serializable {
    int id, idPost, idStatus;
    String FIO;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public Employee() {
    }

    public Employee(int id, int idPost, int idStatus, String FIO) {
        this.id = id;
        this.idPost = idPost;
        this.idStatus = idStatus;
        this.FIO = FIO;
    }

    /*
    @Override
    public String toString(){
        return (getFIO()+", "+posts.get(posts.indexOf(getIdPost())).getName());
    }

     */
}
