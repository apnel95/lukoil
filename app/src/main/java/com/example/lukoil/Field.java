package com.example.lukoil;

import android.view.View;
import android.widget.TextView;

public class Field {
    protected int idView, idTextView;
    protected String textForTextView;

    public int getIdView() {
        return idView;
    }

    public int getIdTextView() {
        return idTextView;
    }

    public String getTextForTextView() {
        return textForTextView;
    }

    public Field(int idView, int idTextView, String textForTextView) {
        this.idView = idView;
        this.idTextView = idTextView;
        this.textForTextView = textForTextView;
    }
}
