package com.example.lukoil.entity.work;

import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.act.ActDoc;

import java.util.ArrayList;

public class WorkPump {
    int id, idAct, idTypeWork;

    public WorkPump(int id, int idAct, int idTypeWork) {
        this.id = id;
        this.idAct = idAct;
        this.idTypeWork = idTypeWork;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAct() {
        return idAct;
    }

    public void setIdAct(int idAct) {
        this.idAct = idAct;
    }

    public int getIdTypeWork() {
        return idTypeWork;
    }

    public void setIdTypeWork(int idTypeWork) {
        this.idTypeWork = idTypeWork;
    }

    public String getNameWorkById(ArrayList<Dir> works) {
        for (Dir dir: works){
            if (dir.getId() == this.getIdTypeWork()) return dir.getName();
        }
        return "";
    }
}
