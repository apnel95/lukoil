package com.example.lukoil.entity.comparation;

import com.example.lukoil.entity.act.ActDoc;

import java.util.Comparator;

public class ActDocComparatot implements Comparator<ActDoc> {
    @Override
    public int compare(ActDoc left, ActDoc right) {
        return right.getDate_time_stop().compareTo(left.getDate_time_stop());
    }
}
