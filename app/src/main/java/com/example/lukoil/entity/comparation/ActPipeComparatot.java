package com.example.lukoil.entity.comparation;

import com.example.lukoil.entity.PipeAct;

import java.util.Comparator;

public class ActPipeComparatot implements Comparator<PipeAct> {
    @Override
    public int compare(PipeAct left, PipeAct right) {
        return right.getDateTimeStop().compareTo(left.getDateTimeStop());
    }
}
