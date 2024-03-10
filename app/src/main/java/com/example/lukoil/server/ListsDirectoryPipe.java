package com.example.lukoil.server;

import com.example.lukoil.entity.act.ActDoc;
import com.example.lukoil.entity.act.ActPump;
import com.example.lukoil.entity.act.ActPipe;
import com.example.lukoil.entity.DepartmentObject;
import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.Employee;
import com.example.lukoil.entity.event.EventDateTime;
import com.example.lukoil.entity.remark.Remark;
import com.example.lukoil.entity.work.Work;

import java.util.ArrayList;

public class ListsDirectoryPipe {
    ArrayList<ActPipe> pipeActs;
    ArrayList<ActPump> pumpActs;
    ArrayList<ActDoc> docActs;
    ArrayList<EventDateTime> actEvents;
    ArrayList<Work> DocWorks;
    ArrayList<Remark> docRemarks;
    ArrayList<Integer> pumpWorks;
    ArrayList<Dir> docDepartments;
    ArrayList<DepartmentObject> docDepartmentObjects;
    ArrayList<Employee> employees;
    ArrayList<Dir> actEventTypes;
    ArrayList<Dir> actEventStatuses;
    ArrayList<Dir> pumpMarks;
    ArrayList<Dir> pipeNames;
    ArrayList<Dir> pipePositions;
    ArrayList<Dir> employeePosts;
    ArrayList<Dir> pumpStopReasons;
    ArrayList<Dir> pipeCoatingTypes;
    ArrayList<Dir> pumpWorkTypes;
    ArrayList<Dir> employeeStatuses;
    ArrayList<Dir> actStatuses;
    ArrayList<Dir> pipeSubstances;
    ArrayList<Dir> pipeLeaktypes;
    ArrayList<Dir> pumpPositions;

    ArrayList<String> sDocDepartments;
    ArrayList<String> sDocDepartmentObjects;
    ArrayList<String> sEmployees;
    ArrayList<String> sActEventTypes;
    ArrayList<String> sActEventStatuses;
    ArrayList<String> sPumpMarks;
    ArrayList<String> sPipeNames;
    ArrayList<String> sPipePositions;
    ArrayList<String> sEmployeePosts;
    ArrayList<String> sPumpStopReasons;
    ArrayList<String> sPipeCoatingTypes;
    ArrayList<String> sPumpWorkTypes;
    ArrayList<String> sEmployeeStatuses;
    ArrayList<String> sActStatuses;
    ArrayList<String> sPipeSubstances;
    ArrayList<String> sPipeLeaktypes;
    ArrayList<String> sPumpPositions;
}
