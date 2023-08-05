package com.example.lukoil;

import android.util.Log;
import android.widget.Toast;

import com.example.lukoil.entity.Work;
import com.example.lukoil.entity.departmentObject;
import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.Employee;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class DirsOnServer extends ServerConnection{


    public Map<String, Map<Integer, String>> dirs;
    public Map<Integer, departmentObject> departmentObjects;
    public Map<Integer, Work> works;

    public Map<String, Map<Object, Object>> directory;

    public ArrayList<Dir> getDirs(){
        System.out.println("Обновление списков");
        Thread thread = new Thread(() -> {
            tryConnection();
            trySendRequest("GET_ALL_DIRS");
            try {
                departments = (ArrayList<Dir>) in.readObject();
                for (Dir dir: departments){
                    if(dir.getName().equals("НШ№1")) NH1 = dir.getId();
                    else if(dir.getName().equals("НШ№2")) NH2 = dir.getId();
                    else if(dir.getName().equals("НШ№3")) NH3 = dir.getId();
                    else if(dir.getName().equals("ЦППН")) CPPN = dir.getId();
                    else if(dir.getName().equals("ЦППД")) CPPD = dir.getId();
                }
                System.out.println(departments.size()+": 1");
                department_objects = (ArrayList<departmentObject>) in.readObject();
                System.out.println(department_objects.size()+": 2");
                employees = (ArrayList<Employee>) in.readObject();
                System.out.println(employees.size()+": 3");
                event_types = (ArrayList<Dir>) in.readObject();
                System.out.println(event_types.size()+": 4");
                event_statuses = (ArrayList<Dir>) in.readObject();
                for (Dir dir: event_statuses){
                    if(dir.getName().equals("Выдача предписания")) idDateTimeStopWorkDoc = dir.getId();
                    else if(dir.getName().equals("Обнаружение неисправности")) idDateTimeStopWork = dir.getId();
                }
                System.out.println("New idDateTimeStopWork - " + idDateTimeStopWork);
                System.out.println(event_statuses.size()+": 5");
                marks = (ArrayList<Dir>) in.readObject();
                System.out.println(marks.size()+": 6");
                pipes = (ArrayList<Dir>) in.readObject();
                System.out.println(pipes.size()+": 7");
//              pumps = (ArrayList<Dir>) in.readObject();
//              System.out.println(pumps.size()+": 8");
                positions = (ArrayList<Dir>) in.readObject();
                System.out.println(positions.size()+": 9");
                posts = (ArrayList<Dir>) in.readObject();
                System.out.println(posts.size()+": 10");
                reasons_stop_pump = (ArrayList<Dir>) in.readObject();
                System.out.println(reasons_stop_pump.size()+": 11");
                statuses_employee = (ArrayList<Dir>) in.readObject();
                System.out.println(statuses_employee.size()+": 12");
                statuses_act = (ArrayList<Dir>) in.readObject();
                for (Dir dir: statuses_act){
                    if(dir.getName().equals("В работе")) ACT_STATUS_JOB = dir.getId();
                    else if(dir.getName().equals("Готово")) STATUS_READY = dir.getId();
                }
                System.out.println("New STATUS_READY - " + STATUS_READY);
                System.out.println(statuses_act.size()+": 13");
                substances = (ArrayList<Dir>) in.readObject();
                System.out.println(substances.size()+": 14");
                types_leak = (ArrayList<Dir>) in.readObject();
                System.out.println(types_leak.size()+": 15");
                types_coating = (ArrayList<Dir>) in.readObject();
                System.out.println(types_coating.size()+": 16");
                types_work_pump = (ArrayList<Dir>) in.readObject();
                System.out.println(types_work_pump.size()+": 17");


            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            Log.d("Clown", e.toString());
        }
        Toast toast = Toast.makeText(getApplicationContext(), "Обновление списков успешно завершено", Toast.LENGTH_LONG);
        toast.show();
        updateArrayString();
    }
    private void updateArrayString() {
        Sdepartments = new ArrayList<>();
        Sdepartment_objects = new ArrayList<>();
        Semployees = new ArrayList<>();
        Sevent_types = new ArrayList<>();
        Sevent_statuses = new ArrayList<>();
        Smarks = new ArrayList<>();
        Strubs = new ArrayList<>();
        Spositions = new ArrayList<>();
        Sreasons_stop_pump = new ArrayList<>();
        Sposts = new ArrayList<>();
        Stypes_coating = new ArrayList<>();
        Stypes_work_pump = new ArrayList<>();
        Sstatuses_employee = new ArrayList<>();
        Sstatuses_act = new ArrayList<>();
        Ssubstances = new ArrayList<>();
        Stypes_leak = new ArrayList<>();

        for (Dir dir: departments) Sdepartments.add(dir.getName());
        for (departmentObject dir: department_objects) Sdepartment_objects.add(dir.getName());
        for (Dir dir: event_types) Sevent_types.add(dir.getName());
        for (Dir dir: event_statuses) Sevent_statuses.add(dir.getName());
        for (Dir dir: marks) Smarks.add(dir.getName());
        for (Dir dir: pipes) Strubs.add(dir.getName());
        for (Dir dir: posts) Sposts.add(dir.getName());
        for (Employee emp: employees) for (Dir dir: posts) if(dir.getId() == emp.getIdPost()) Semployees.add(emp.getFIO()+", "+dir.getName());
        for (Dir dir: reasons_stop_pump) Sreasons_stop_pump.add(dir.getName());
        for (Dir dir: types_coating) Stypes_coating.add(dir.getName());
        for (Dir dir: types_work_pump) Stypes_work_pump.add(dir.getName());
        for (Dir dir: statuses_employee) Sstatuses_employee.add(dir.getName());
        for (Dir dir: statuses_act) Sstatuses_act.add(dir.getName());
        for (Dir dir: substances) Ssubstances.add(dir.getName());
        for (Dir dir: types_leak) Stypes_leak.add(dir.getName());
        for (Dir dir: positions) Spositions.add(dir.getName());
    }
}
