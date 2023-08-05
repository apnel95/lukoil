package com.example.lukoil;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.lukoil.entity.DocAct;
import com.example.lukoil.entity.PumpAct;
import com.example.lukoil.entity.PipeAct;
import com.example.lukoil.entity.DepartmentObject;
import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.Employee;
import com.example.lukoil.entity.Event_date_time;
import com.example.lukoil.entity.Remark;
import com.example.lukoil.entity.Work;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
    public ArrayList<PumpAct> pumpActs;
    public ArrayList<DocAct> docActs;
    public ArrayList<PipeAct> pipeActs;

    Dictionary dictionary;

}
