package com.example.lukoil;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;

import java.util.List;

public class ActivitySet extends GeneralClass {
    Drawable topHome, topTrub, topPump, topDoc, topMenu;
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
}
