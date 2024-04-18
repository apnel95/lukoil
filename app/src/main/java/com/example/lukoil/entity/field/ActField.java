package com.example.lukoil.entity.field;

import com.example.lukoil.entity.Dir;

public class ActField extends Field {
    protected int idSecondTextView, idStatusLayout, idStatusAct;
    Dir tag;
    protected String textForSecondTextView;

    public ActField(int idView, int idTextView, String textForTextView, int idSecondTextView, String textForSecondTextView, int idStatusLayout, int idStatusAct, Dir tag) {
        super(idView, idTextView, textForTextView);
        this.idSecondTextView = idSecondTextView;
        this.textForSecondTextView = textForSecondTextView;
        this.idStatusLayout = idStatusLayout;
        this.idStatusAct = idStatusAct;
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

    public int getIdStatusLayout() {
        return idStatusLayout;
    }

    public String getTextForSecondTextView() {
        return textForSecondTextView;
    }

    public int getIdStatusAct() {
        return idStatusAct;
    }
}
