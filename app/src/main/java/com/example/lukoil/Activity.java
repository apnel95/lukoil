package com.example.lukoil;

import android.content.Context;
import android.text.Layout;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

public class Activity {
    Context context;

    int idLayout;
    LinearLayout workplace, bottom;
    List<View> workplaceElements;
    String topTitle;

    public Context getContext() {
        return context;
    }

    public LinearLayout getWorkplace() {
        return workplace;
    }

    public LinearLayout getBottom() {
        return bottom;
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

    public Activity(int id, Context context, int idLayout, LinearLayout workplace, List<View> workplaceElements, LinearLayout bottom, String topTitle) {
        this.context = context;
        this.idLayout = idLayout;
        this.workplace = workplace;
        this.workplaceElements = workplaceElements;
        this.bottom = bottom;
        this.topTitle = topTitle;
    }
}
