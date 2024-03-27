package com.example.lukoil.activity;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

public class Activity extends General {
    Context context;
    int idLayout;
    int idWorkplace;
    int idBottom;
    List<View> workplaceElements;
    String topTitle;
    int idTypeLayout;


    public int getIdTypeLayout() {
        return idTypeLayout;
    }

    public void setIdTypeLayout(int idTypeLayout) {
        this.idTypeLayout = idTypeLayout;
    }

    public Activity(int idTypeLayout, Context context, int idLayout, int idWorkplace, List<View> workplaceElements, int idBottom, String topTitle) {
        this.idTypeLayout = idTypeLayout;
        this.context = context;
        this.idLayout = idLayout;
        this.idWorkplace = idWorkplace;
        this.workplaceElements = workplaceElements;
        this.idBottom = idBottom;
        this.topTitle = topTitle;
    }

    public Context getContext() {
        return context;
    }

    public int getIdWorkplace() {
        return idWorkplace;
    }

    public int getIdBottom() {
        return idBottom;
    }

    public List<View> getWorkplaceElements() {
        return workplaceElements;
    }

    public int getIdLayout() {
        return idLayout;
    }

    public String getTopTitle() {
        return topTitle;
    }
}
