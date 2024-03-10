package com.example.lukoil;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lukoil.entity.act.ActDoc;
import com.example.lukoil.entity.act.ActPump;
import com.example.lukoil.entity.act.ActPipe;
import com.example.lukoil.server.Dictionary;

import java.util.ArrayList;

public class GeneralClass extends AppCompatActivity {

    public static Context OPEN_APPLICATION_NOW;
    public static int ACT_STATUS_JOB = 2, STATUS_READY = 1;
    public static int ID_ACTIVITY_HOME = 0, ID_ACTIVITY_PIPE = 1, ID_ACTIVITY_PUMP = 2, ID_ACTIVITY_DOC = 3, ID_ACTIVITY_PIPE_PLUS = 11, ID_ACTIVITY_PUMP_PLUS = 12, ID_ACTIVITY_DOC_PLUS = 13;
    public static int idDateTimeStopWork = 2, idDateTimeStopWorkDoc = 3;
    public static boolean AUTO_UPDATE_DIRS = true;
    public static int NH1, NH2, NH3, CPPD, CPPN;
    public static int PORT = 29170;
    public static int upPORT = 29171;
    public static String HOST = "192.168.0.16";
    public ArrayList<ActPump> pumpActs;
    public ArrayList<ActDoc> docActs;
    public ArrayList<ActPipe> pipeActs;

    Dictionary dictionary;

}
