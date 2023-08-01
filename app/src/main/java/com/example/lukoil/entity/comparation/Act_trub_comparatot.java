package com.example.lukoil.entity.comparation;

import com.example.lukoil.entity.Act_trub;

import java.util.Comparator;

public class Act_trub_comparatot implements Comparator<Act_trub> {
    @Override
    public int compare(Act_trub left, Act_trub right) {
        return right.getDate_time_stop().compareTo(left.getDate_time_stop());
    }
}
