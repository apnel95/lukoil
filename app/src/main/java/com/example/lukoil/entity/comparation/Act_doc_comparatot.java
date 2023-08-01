package com.example.lukoil.entity.comparation;

import com.example.lukoil.entity.Act_doc;
import com.example.lukoil.entity.Act_trub;

import java.util.Comparator;

public class Act_doc_comparatot implements Comparator<Act_doc> {
    @Override
    public int compare(Act_doc left, Act_doc right) {
        return right.getDate_time_stop().compareTo(left.getDate_time_stop());
    }
}
