package com.example.lukoil.entity.comparation;

import com.example.lukoil.entity.PumpAct;

import java.util.Comparator;

public class ActPumpComparatot implements Comparator<PumpAct> {

    @Override
    public int compare(PumpAct left, PumpAct right) {
        return right.getDate_time_stop().compareTo(left.getDate_time_stop());
    }
}
