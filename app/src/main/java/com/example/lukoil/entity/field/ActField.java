package com.example.lukoil.entity.field;

import com.example.lukoil.entity.field.Field;

public class ActField extends Field {

    protected int idSecondTextView, idStatus, tag;
    protected String textForSecondTextView;

    public ActField(int idView, int idTextView, String textForTextView, int idSecondTextView, String textForSecondTextView, int idStatus, int tag) {
        super(idView, idTextView, textForTextView);
        this.idSecondTextView = idSecondTextView;
        this.textForSecondTextView = textForSecondTextView;
        this.idStatus = idStatus;
        this.tag = tag;
    }

    public int getIdSecondTextView() {
        return idSecondTextView;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public int getTag() {
        return tag;
    }

    public String getTextForSecondTextView() {
        return textForSecondTextView;
    }
}
