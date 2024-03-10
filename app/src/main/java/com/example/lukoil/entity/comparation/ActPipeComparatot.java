package com.example.lukoil.entity.comparation;

import com.example.lukoil.entity.act.ActPipe;

import java.util.Comparator;

public class ActPipeComparatot implements Comparator<ActPipe> {
    @Override
    public int compare(ActPipe left, ActPipe right) {
        return right.getDateTimeStop().compareTo(left.getDateTimeStop());
    }
}
