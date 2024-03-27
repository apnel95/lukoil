package com.example.lukoil;

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
import java.util.Date;

public class ListData {
    ArrayList<ActPipe> pipeActs;
    ArrayList<ActPump> pumpActs;
    ArrayList<ActDoc> docActs;
    public static ArrayList<EventDateTime> actEvents;
    public static ArrayList<Work> DocWorks;
    public static ArrayList<Remark> docRemarks;
    public static ArrayList<Integer> pumpWorks;
    public static ArrayList<Dir> docDepartments;
    public static ArrayList<DepartmentObject> docDepartmentObjects;
    public static ArrayList<Employee> employees;


    public static ArrayList<Dir> actEventTypes;
    public static ArrayList<Dir> actEventStatuses;
    public static ArrayList<Dir> pumpMarks;
    public static ArrayList<Dir> pipeNames;
    public static ArrayList<Dir> pipePositions;
    public static ArrayList<Dir> employeePosts;
    public static ArrayList<Dir> pumpStopReasons;
    public static ArrayList<Dir> pipeCoatingTypes;
    public static ArrayList<Dir> pumpWorkTypes;
    public static ArrayList<Dir> employeeStatuses;
    public static ArrayList<Dir> actStatuses;
    public static ArrayList<Dir> pipeSubstances;
    public static ArrayList<Dir> pipeLeakTypes;
    public static ArrayList<Dir> pumpPositions;



    public static ArrayList<String> sDocDepartments;
    public static ArrayList<String> sDocDepartmentObjects;
    public static ArrayList<String> sEmployees;
    public static ArrayList<String> sActEventTypes;
    public static ArrayList<String> sActEventStatuses;
    public static ArrayList<String> sPumpMarks;
    public static ArrayList<String> sPipeNames;
    public static ArrayList<String> sPipePositions;
    public static ArrayList<String> sEmployeePosts;
    public static ArrayList<String> sPumpStopReasons;
    public static ArrayList<String> sPipeCoatingTypes;
    public static ArrayList<String> sPumpWorkTypes;
    public static ArrayList<String> sEmployeeStatuses;
    public static ArrayList<String> sActStatuses;
    public static ArrayList<String> sPipeSubstances;
    public static ArrayList<String> sPipeLeakTypes;
    public static ArrayList<String> sPumpPositions;

    public static void updateSArraylists(){

        sEmployees = new ArrayList<>();
        sDocDepartments = new ArrayList<>();
        sDocDepartmentObjects = new ArrayList<>();
        sActEventTypes = new ArrayList<>();
        sPumpMarks = new ArrayList<>();
        sPipeNames = new ArrayList<>();
        sPumpStopReasons = new ArrayList<>();
        sPipeCoatingTypes = new ArrayList<>();
        sActStatuses = new ArrayList<>();
        sPipeSubstances = new ArrayList<>();
        sPipeLeakTypes = new ArrayList<>();
        sPumpPositions = new ArrayList<>();

        for (Employee employee: employees) sEmployees.add(employee.getFIO());
        for (Dir dir: docDepartments) sDocDepartments.add(dir.getName());
        for (DepartmentObject departmentObject: docDepartmentObjects) sDocDepartmentObjects.add(departmentObject.getName());
        for (Dir dir: actEventTypes) sActEventTypes.add(dir.getName());
        for (Dir dir: pumpMarks) sPumpMarks.add(dir.getName());
        for (Dir dir: pipeNames) sPipeNames.add(dir.getName());
        for (Dir dir: pumpStopReasons) sPumpStopReasons.add(dir.getName());
        for (Dir dir: pipeCoatingTypes) sPipeCoatingTypes.add(dir.getName());
        for (Dir dir: actStatuses) sActStatuses.add(dir.getName());
        for (Dir dir: pipeSubstances) sPipeSubstances.add(dir.getName());
        for (Dir dir: pipeLeakTypes) sPipeLeakTypes.add(dir.getName());
        for (Dir dir: pumpPositions) sPumpPositions.add(dir.getName());;

    }

}
