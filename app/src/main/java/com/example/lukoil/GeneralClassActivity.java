package com.example.lukoil;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;

import com.example.lukoil.entity.DocAct;
import com.example.lukoil.entity.PipeAct;
import com.example.lukoil.entity.PumpAct;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GeneralClassActivity extends GeneralClass {
    Drawable topHome, topTrub, topPump, topDoc, topMenu, topPlus;
    boolean statusMenu;
    LinearLayout workplace, linearLayoutMenu;
    List<View> workplaceElements;
    View viewUpp, viewBottom;
    TextView topTitleActivity;
    TextView home, trub, pump, doc, menu, plus;

    public void setActivityData(Activity activity){
        setContentView(activity.idLayout);
        workplaceElements = activity.workplaceElements;
        workplace = activity.workplace;
        topTitleActivity.setText(activity.getTopTitle());
    }

    public void initializationMainActivity(Activity activity) {
        super.onStart();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        statusMenu = false;

        linearLayoutMenu = findViewById(R.id.layout_menu);
        viewUpp = findViewById(R.id.upp);
        viewBottom = findViewById(R.id.bottom_home);
        topTitleActivity = viewUpp.findViewById(R.id.textName);

        home = viewBottom.findViewById(R.id.buttonHome);
        trub = viewBottom.findViewById(R.id.buttonTrub);
        pump = viewBottom.findViewById(R.id.buttonPump);
        doc = viewBottom.findViewById(R.id.buttonDoc);
        menu = viewBottom.findViewById(R.id.buttonMenu);

        topHome = getResources().getDrawable(R.drawable.home);
        topTrub = getResources().getDrawable(R.drawable.trub);
        topPump = getResources().getDrawable(R.drawable.pump);
        topDoc = getResources().getDrawable(R.drawable.doc);
        topMenu = getResources().getDrawable(R.drawable.menu);

        if (activity.getIdLayout() == ID_ACTIVITY_HOME) topHome = getResources().getDrawable(R.drawable.home_red);
        else if (activity.getIdLayout() == ID_ACTIVITY_PIPE) topTrub = getResources().getDrawable(R.drawable.trub_red);
        else if (activity.getIdLayout() == ID_ACTIVITY_PUMP) topPump = getResources().getDrawable(R.drawable.pump_red);
        else if (activity.getIdLayout() == ID_ACTIVITY_DOC) topDoc = getResources().getDrawable(R.drawable.doc_red);

        home.setCompoundDrawablesWithIntrinsicBounds(null, topHome, null, null);
        trub.setCompoundDrawablesWithIntrinsicBounds(null, topTrub, null, null);
        pump.setCompoundDrawablesWithIntrinsicBounds(null, topPump, null, null);
        doc.setCompoundDrawablesWithIntrinsicBounds(null, topDoc, null, null);
        menu.setCompoundDrawablesWithIntrinsicBounds(null, topMenu, null, null);

        setActivityData(activity);
    }

    public void initializationNotMainActivity(Activity activity) {
        super.onStart();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        statusMenu = false;

        linearLayoutMenu = findViewById(R.id.layout_menu);
        viewUpp = findViewById(R.id.upp);
        viewBottom = findViewById(R.id.bottom);
        topTitleActivity = viewUpp.findViewById(R.id.textName);

        home = viewBottom.findViewById(R.id.buttonHome);
        trub = viewBottom.findViewById(R.id.buttonTrub);
        pump = viewBottom.findViewById(R.id.buttonPump);
        doc = viewBottom.findViewById(R.id.buttonDoc);
        menu = viewBottom.findViewById(R.id.buttonMenu);
        plus =  viewBottom.findViewById(R.id.buttonPlus);

        topHome = getResources().getDrawable(R.drawable.home);
        topTrub = getResources().getDrawable(R.drawable.trub);
        topPump = getResources().getDrawable(R.drawable.pump);
        topDoc = getResources().getDrawable(R.drawable.doc);
        topMenu = getResources().getDrawable(R.drawable.menu);
        topPlus = getResources().getDrawable(R.drawable.b_new);

        if (activity.getIdLayout() == ID_ACTIVITY_HOME) topHome = getResources().getDrawable(R.drawable.home_red);
        else if (activity.getIdLayout() == ID_ACTIVITY_PIPE) topTrub = getResources().getDrawable(R.drawable.trub_red);
        else if (activity.getIdLayout() == ID_ACTIVITY_PUMP) topPump = getResources().getDrawable(R.drawable.pump_red);
        else if (activity.getIdLayout() == ID_ACTIVITY_DOC) topDoc = getResources().getDrawable(R.drawable.doc_red);
        else if (activity.getIdLayout() == ID_ACTIVITY_PIPE_PLUS) {
            topTrub = getResources().getDrawable(R.drawable.trub_red);
            topPlus = getResources().getDrawable(R.drawable.b_new_red);
        } else if (activity.getIdLayout() == ID_ACTIVITY_PUMP_PLUS) {
            topPump = getResources().getDrawable(R.drawable.pump_red);
            topPlus = getResources().getDrawable(R.drawable.b_new_red);
        } else if (activity.getIdLayout() == ID_ACTIVITY_DOC_PLUS) {
            topDoc = getResources().getDrawable(R.drawable.doc_red);
            topPlus = getResources().getDrawable(R.drawable.b_new_red);
        }

        home.setCompoundDrawablesWithIntrinsicBounds(null, topHome, null, null);
        trub.setCompoundDrawablesWithIntrinsicBounds(null, topTrub, null, null);
        pump.setCompoundDrawablesWithIntrinsicBounds(null, topPump, null, null);
        doc.setCompoundDrawablesWithIntrinsicBounds(null, topDoc, null, null);
        menu.setCompoundDrawablesWithIntrinsicBounds(null, topMenu, null, null);
        plus.setCompoundDrawablesWithIntrinsicBounds(null, topPlus, null, null);

        setActivityData(activity);
    }

    public void toExit(View v) {
        finish();
    }

    public void toHome(View v) {
        Intent Home = new Intent(v.getContext(), MainActivity.class);
        startActivity(Home);
    }

    public void toTrub(View v) {
        System.out.println("ButtonClick Class");
        Intent Trub = new Intent(v.getContext(), Trub_acts.class);
        startActivity(Trub);
    }

    public void toPump(View v) {
        Intent Pump = new Intent(v.getContext(), Pump_acts.class);
        startActivity(Pump);
    }

    public void toDoc(View v) {
        Intent Doc = new Intent(v.getContext(), Doc_acts.class);
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

    public void toView(View v) {
        int id = (int) v.getTag();
        if (id >= 1000 && id < 2000) {
            Intent Trub_view = new Intent(v.getContext(), Trub_view.class);
            PipeAct act1 = new PipeAct();
            for (PipeAct act: pipeActs) if (act.getId() == id-1000) act1 = act;
            Trub_view.putExtra(PipeAct.class.getSimpleName(), getActTrub(act1));
            startActivity(Trub_view);
        } else if (id >= 2000 && id < 3000) {
            Intent Pump_view = new Intent(v.getContext(), Pump_view.class);
            PumpAct act1 = new PumpAct();
            for (PumpAct act: pumpActs) if (act.getId() == id-2000) act1 = act;
            Pump_view.putExtra(PumpAct.class.getSimpleName(), getActPump(act1));
            startActivity(Pump_view);
        } else {
            Intent Doc_view = new Intent(v.getContext(), Doc_view.class);
            DocAct act1 = new DocAct();
            for (DocAct act: docActs) if (act.getId() == id-3000) act1 = act;
            Doc_view.putExtra(DocAct.class.getSimpleName(), getActDoc(act1));
            startActivity(Doc_view);
        }

    }

    public static Date trim(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
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
