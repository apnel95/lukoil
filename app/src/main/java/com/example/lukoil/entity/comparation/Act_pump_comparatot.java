package com.example.lukoil.entity.comparation;

import com.example.lukoil.entity.Act_pump;
import com.example.lukoil.entity.Act_trub;

import java.util.Comparator;

public class Act_pump_comparatot implements Comparator<Act_pump> {

    @Override
    public int compare(Act_pump left, Act_pump right) {
        return right.getDate_time_stop().compareTo(left.getDate_time_stop());
    }
}
