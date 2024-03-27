package com.example.lukoil.entity.field;

import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.field.Field;

import java.util.HashMap;
import java.util.Map;

public class ActField extends Field {
    protected int idSecondTextView, idStatus;
    Dir tag;
    protected String textForSecondTextView;

    public ActField(int idView, int idTextView, String textForTextView, int idSecondTextView, String textForSecondTextView, int idStatus, Dir tag) {
        super(idView, idTextView, textForTextView);
        this.idSecondTextView = idSecondTextView;
        this.textForSecondTextView = textForSecondTextView;
        this.idStatus = idStatus;
        this.tag = tag;
    }

    public Dir getTag() {
        return tag;
    }

    public void setTag(Dir tag) {
        this.tag = tag;
    }

    public int getIdSecondTextView() {
        return idSecondTextView;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public String getTextForSecondTextView() {
        return textForSecondTextView;
    }
}
