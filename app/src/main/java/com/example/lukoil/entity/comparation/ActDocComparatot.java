package com.example.lukoil.entity.comparation;

import com.example.lukoil.entity.DocAct;

import java.util.Comparator;

public class ActDocComparatot implements Comparator<DocAct> {
    @Override
    public int compare(DocAct left, DocAct right) {
        return right.getDate_time_stop().compareTo(left.getDate_time_stop());
    }
}
