package com.example.lukoil.entity.comparation;

import com.example.lukoil.entity.act.ActPump;

import java.util.Comparator;

public class ActPumpComparatot implements Comparator<ActPump> {

    @Override
    public int compare(ActPump left, ActPump right) {
        return right.getDateTimeStop().compareTo(left.getDateTimeStop());
    }
}
