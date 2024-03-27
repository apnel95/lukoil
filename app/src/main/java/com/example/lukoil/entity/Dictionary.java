package com.example.lukoil.entity;

import com.example.lukoil.entity.work.Work;
import com.example.lukoil.entity.DepartmentObject;

import java.util.HashMap;

public class Dictionary {
    protected HashMap[] dictionaries;

    final int ID_DEPARTMENT_OBJECTS_IN_DICTIONARIES = 0;
    final int ID_WORKS_IN_DICTIONARIES = 1;
    final int ID_DIRS_IN_DICTIONARIES = 2;
    public Dictionary() {
        dictionaries = new HashMap[]{new HashMap<Integer, DepartmentObject>(), new HashMap<Integer, Work>(), new HashMap<String, HashMap<Integer, String>>()};
    }

    public HashMap[] getDictionaries() {
        return dictionaries;
    }

    public DepartmentObject getDepartmentObjectByKey(int key) {
        return  (DepartmentObject) dictionaries[ID_DEPARTMENT_OBJECTS_IN_DICTIONARIES].get(key);
    }

    public Work getWorkByKey(int key) {
        return  (Work) dictionaries[ID_WORKS_IN_DICTIONARIES].get(key);
    }

    public String getDirValueByDirNameAndKey(String name, int key) {
        return ((HashMap<Integer, String>) dictionaries[ID_DIRS_IN_DICTIONARIES].get(name)).get(key);
    }
    public HashMap getDirByDirName(String name) {
        return (HashMap<Integer, String>) dictionaries[ID_DIRS_IN_DICTIONARIES].get(name);
    }


    public void setDictionaries(HashMap[] dictionaries) {
        this.dictionaries = dictionaries;
    }

    public void setDictionaries(int key, Work work) {
        dictionaries[ID_WORKS_IN_DICTIONARIES].put(key, work);
    }

    public void setDictionaries(int key, DepartmentObject departmentObject) {
        dictionaries[ID_DEPARTMENT_OBJECTS_IN_DICTIONARIES].put(key, departmentObject);
    }

    public void setDictionaries(String key, HashMap<Integer, String> value) {
        dictionaries[ID_DIRS_IN_DICTIONARIES].put(key, value);
    }
}
