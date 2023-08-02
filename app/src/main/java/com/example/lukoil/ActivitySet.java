package com.example.lukoil;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;

public class ActivitySet extends GeneralClass {


    public void setActivityData(Activity activity){
        setContentView(activity.idLayout);
        workplaceElements = activity.workplaceElements;
        workplace = activity.workplace;
        topTitleActivity.setText(activity.getTopTitle());
    }

    public void drawActivity(int i) {
        super.onStart();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        statusMenu = false;

        linearLayoutMenu = findViewById(R.id.layout_menu);
        viewUpp = findViewById(R.id.upp);
        viewBottom = findViewById(R.id.bottom_home);
        topTitleActivity = viewUpp.findViewById(R.id.textName);

        home = (TextView) viewBottom.findViewById(R.id.buttonHome);
        trub = (TextView) viewBottom.findViewById(R.id.buttonTrub);
        pump = (TextView) viewBottom.findViewById(R.id.buttonPump);
        doc = (TextView) viewBottom.findViewById(R.id.buttonDoc);
        menu = (TextView) viewBottom.findViewById(R.id.buttonMenu);

        topHome = getResources().getDrawable(R.drawable.home);
        topTrub = getResources().getDrawable(R.drawable.trub);
        topPump = getResources().getDrawable(R.drawable.pump);
        topDoc = getResources().getDrawable(R.drawable.doc);
        topMenu = getResources().getDrawable(R.drawable.menu);

        if (i == 0) topHome = getResources().getDrawable(R.drawable.home_red);
        else if (i == 1) topTrub = getResources().getDrawable(R.drawable.trub_red);
        else if (i == 2) topPump = getResources().getDrawable(R.drawable.pump_red);
        else if (i == 3) topDoc = getResources().getDrawable(R.drawable.doc_red);

        home.setCompoundDrawablesWithIntrinsicBounds(null, topHome, null, null);
        trub.setCompoundDrawablesWithIntrinsicBounds(null, topTrub, null, null);
        pump.setCompoundDrawablesWithIntrinsicBounds(null, topPump, null, null);
        doc.setCompoundDrawablesWithIntrinsicBounds(null, topDoc, null, null);
        menu.setCompoundDrawablesWithIntrinsicBounds(null, topMenu, null, null);
    }
}
