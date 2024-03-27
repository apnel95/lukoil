package com.example.lukoil.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.lukoil.R;
import com.example.lukoil.activity.doc.DocActs;
import com.example.lukoil.activity.pipe.PipeActs;
import com.example.lukoil.activity.pump.PumpActs;
import com.example.lukoil.entity.act.ActDoc;
import com.example.lukoil.entity.act.ActPump;
import com.example.lukoil.entity.act.ActPipe;
import com.example.lukoil.entity.Dictionary;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class General extends AppCompatActivity {

    public static Context CONTEXT_NOW;
    final public static int ACT_STATUS_JOB = 2, ACT_STATUS_READY = 1;
    final public static int ID_ACTIVITY_HOME = 0, ID_ACTIVITY_PIPE = 1, ID_ACTIVITY_PUMP = 2, ID_ACTIVITY_DOC = 3, ID_ACTIVITY_PIPE_PLUS = 11, ID_ACTIVITY_PUMP_PLUS = 12, ID_ACTIVITY_DOC_PLUS = 13, ID_ACTIVITY_EVENT = 4;
    final  public static int ID_DATE_TIME_STOP_WORK = 2, ID_DATE_TIME_STOP_WORK_DOC = 3;
    public static boolean AUTO_UPDATE_DIRS = true;
    public static int NH1, NH2, NH3, CPPD, CPPN;
    final public static int PORT = 29170;
    final public static int UP_PORT = 29171;
    final public static String HOST = "192.168.0.16";
    public ArrayList<ActPump> LIST_ACT_PUMP = new ArrayList<>();
    public ArrayList<ActDoc> LIST_ACT_DOC = new ArrayList<>();
    public ArrayList<ActPipe> LIST_ACT_PIPE = new ArrayList<>();

    public Context CONTEXT;
    public Dictionary DICTIONARY;


    Drawable topHome, topPipe, topPump, topDoc, topMenu, topPlus;
    boolean statusMenu;
    public LinearLayout WORKPLACE;
    LinearLayout linearLayoutMenu;
    public List<View> WORK_PLACE_ELEMENTS;
    View viewUpp, viewBottom;
    public TextView topTitleActivity;
    TextView home, pipe, pump, doc, menu, plus;

    public void setActivityData(Activity activity){
        WORK_PLACE_ELEMENTS = activity.workplaceElements;
        WORKPLACE = findViewById(activity.getIdWorkplace());

        topTitleActivity.setText(activity.getTopTitle());
    }

    public void initializationActivity(Activity activity) {
        //setTheme(R.style.Theme_Lukoil1);

        setContentView(activity.idLayout);

        super.onStart();

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        statusMenu = false;

        CONTEXT = activity.getContext();

        linearLayoutMenu = findViewById(R.id.layout_menu);
        viewUpp = findViewById(R.id.upp);

        if (activity.getIdTypeLayout() == ID_ACTIVITY_HOME) viewBottom = findViewById(R.id.bottom_home);
        else viewBottom = findViewById(R.id.bottom);

        topTitleActivity = viewUpp.findViewById(R.id.textName);

        home = viewBottom.findViewById(R.id.buttonHome1);
        pipe = viewBottom.findViewById(R.id.buttonPipe);
        pump = viewBottom.findViewById(R.id.buttonPump);
        doc = viewBottom.findViewById(R.id.buttonDoc);
        menu = viewBottom.findViewById(R.id.buttonMenu);
        if (activity.getIdLayout() != ID_ACTIVITY_HOME) plus =  viewBottom.findViewById(R.id.buttonPlus);

        topHome = getResources().getDrawable(R.drawable.home);
        topPipe = getResources().getDrawable(R.drawable.pipe);
        topPump = getResources().getDrawable(R.drawable.pump);
        topDoc = getResources().getDrawable(R.drawable.doc);
        topMenu = getResources().getDrawable(R.drawable.menu);
        if (activity.getIdTypeLayout() != ID_ACTIVITY_HOME) topPlus = getResources().getDrawable(R.drawable.plus);

        if (activity.getIdTypeLayout() == ID_ACTIVITY_HOME) topHome = getResources().getDrawable(R.drawable.home_red);
        else if (activity.getIdTypeLayout() == ID_ACTIVITY_PIPE) topPipe = getResources().getDrawable(R.drawable.pipe_red);
        else if (activity.getIdTypeLayout() == ID_ACTIVITY_PUMP) topPump = getResources().getDrawable(R.drawable.pump_red);
        else if (activity.getIdTypeLayout() == ID_ACTIVITY_DOC) topDoc = getResources().getDrawable(R.drawable.doc_red);
        else if (activity.getIdTypeLayout() == ID_ACTIVITY_PIPE_PLUS) {
            topPipe = getResources().getDrawable(R.drawable.pipe_red);
            topPlus = getResources().getDrawable(R.drawable.plus_red);
        } else if (activity.getIdTypeLayout() == ID_ACTIVITY_PUMP_PLUS) {
            topPump = getResources().getDrawable(R.drawable.pump_red);
            topPlus = getResources().getDrawable(R.drawable.plus_red);
        } else if (activity.getIdTypeLayout() == ID_ACTIVITY_DOC_PLUS) {
            topDoc = getResources().getDrawable(R.drawable.doc_red);
            topPlus = getResources().getDrawable(R.drawable.plus_red);
        }

        home.setCompoundDrawablesWithIntrinsicBounds(null, topHome, null, null);
        pipe.setCompoundDrawablesWithIntrinsicBounds(null, topPipe, null, null);
        pump.setCompoundDrawablesWithIntrinsicBounds(null, topPump, null, null);
        doc.setCompoundDrawablesWithIntrinsicBounds(null, topDoc, null, null);
        menu.setCompoundDrawablesWithIntrinsicBounds(null, topMenu, null, null);
        if (activity.getIdTypeLayout() != ID_ACTIVITY_HOME) plus.setCompoundDrawablesWithIntrinsicBounds(null, topPlus, null, null);

        setActivityData(activity);
    }
    public void toExit(View v) {
        finish();
    }

    public void toHome(View v) {
        Intent Home = new Intent(v.getContext(), MainActivity.class);
        startActivity(Home);
    }

    public void toPipe(View v) {
        Intent Trub = new Intent(v.getContext(), PipeActs.class);
        startActivity(Trub);
    }

    public void toPump(View v) {
        Intent Pump = new Intent(v.getContext(), PumpActs.class);
        startActivity(Pump);
    }

    public void toDoc(View v) {
        Intent Doc = new Intent(v.getContext(), DocActs.class);
        startActivity(Doc);
    }

    public void toPlus(View v) {
    }

    public void toMenu(View v) {
        if (!statusMenu) {
            menu.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.menu_red), null, null);
            linearLayoutMenu.setVisibility(View.VISIBLE);
            statusMenu = true;
        } else {
            menu.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.menu), null, null);
            linearLayoutMenu.setVisibility(View.GONE);
            statusMenu = false;
        }
    }
    public String DateToText(Date date) {
        String newDate = "";
        switch (date.getMonth()) {
            case (0):
                newDate = "Январь";
                break;
            case (1):
                newDate = "Февраль";
                break;
            case (2):
                newDate = "Март";
                break;
            case (3):
                newDate = "Апрель";
                break;
            case (4):
                newDate = "Май";
                break;
            case (5):
                newDate = "Июнь";
                break;
            case (6):
                newDate = "Июль";
                break;
            case (7):
                newDate = "Август";
                break;
            case (8):
                newDate = "Сентябрь";
                break;
            case (9):
                newDate = "Октябрь";
                break;
            case (10):
                newDate = "Ноябрь";
                break;
            case (11):
                newDate = "Декабрь";
                break;
        }
        SimpleDateFormat formatForDate = new SimpleDateFormat("dd");
        newDate += ", " + formatForDate.format(date);
        return newDate;
    }
}
