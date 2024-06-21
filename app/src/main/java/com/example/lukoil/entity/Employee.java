package com.example.lukoil.entity;

import java.io.Serializable;

public class Employee extends Dir implements Serializable {
    int idPost, idStatus;


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

    public Employee() {
    }

    public Employee(int id, int idPost, int idStatus, String name) {
        this.id = id;
        this.idPost = idPost;
        this.idStatus = idStatus;
        this.name = name;
    }
}
