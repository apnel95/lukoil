package com.example.lukoil;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.lukoil.activity.General;
import com.example.lukoil.activity.doc.DocChange;
import com.example.lukoil.activity.doc.DocCreate;
import com.example.lukoil.activity.doc.DocView;
import com.example.lukoil.activity.pipe.PipeChange;
import com.example.lukoil.activity.pipe.PipeCreate;
import com.example.lukoil.activity.pipe.PipeView;
import com.example.lukoil.activity.pump.PumpChange;
import com.example.lukoil.activity.pump.PumpCreate;
import com.example.lukoil.activity.pump.PumpView;
import com.example.lukoil.entity.Dir;
import com.example.lukoil.entity.act.ActDoc;
import com.example.lukoil.entity.act.ActPipe;
import com.example.lukoil.entity.act.ActPump;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GeneraActList extends General {
    public void toViewAct(View v) {
        Dir tag = (Dir) v.getTag();
        startNewActivity(v.getContext(),  tag.getName()+"View", tag.getId());
    }
    public void startNewActivity(Context context, String classSimpleName, int idAct){
        if (classSimpleName.contains("Pipe")){
            if (classSimpleName.contains("View")) startActivityPipeView(context, idAct);
            else if (classSimpleName.contains("Create")) startActivityPipeCreate(context, idAct);
            else startActivityPipeChange(context, idAct);
        }
        else if (classSimpleName.contains("Pump")){
            if (classSimpleName.contains("View")) startActivityPumpView(context, idAct);
            else if (classSimpleName.contains("Create")) startActivityPumpCreate(context, idAct);
            else startActivityPumpChange(context, idAct);
        }
        else{
            if (classSimpleName.contains("View")) startActivityDocView(context, idAct);
            else if (classSimpleName.contains("Create")) startActivityDocCreate(context, idAct);
            else startActivityDocChange(context, idAct);
        }
    }

    private void startActivityDocChange(Context context,  int idAct) {
        Intent newIntent = new Intent(context, DocChange.class);
        ActDoc act1 = new ActDoc();
        for (ActDoc act: LIST_ACT_DOC) if (act.getId() == idAct){ act1 = act; break;}
        newIntent.putExtra(ActDoc.class.getSimpleName(), act1);
        startActivity(newIntent);
    }

    private void startActivityDocCreate(Context context,  int idAct) {
        Intent newIntent = new Intent(context, DocCreate.class);
        ActDoc act1 = new ActDoc();
        for (ActDoc act: LIST_ACT_DOC) if (act.getId() == idAct){ act1 = act; break;}
        newIntent.putExtra(ActDoc.class.getSimpleName(), act1);
        startActivity(newIntent);
    }

    private void startActivityDocView(Context context,  int idAct) {
        Intent newIntent = new Intent(context, DocView.class);
        ActDoc act1 = new ActDoc();
        for (ActDoc act: LIST_ACT_DOC) if (act.getId() == idAct){ act1 = act; break;}
        newIntent.putExtra(ActDoc.class.getSimpleName(), act1);
        startActivity(newIntent);
    }

    private void startActivityPumpChange(Context context,  int idAct) {
        Intent newIntent = new Intent(context, PumpChange.class);
        ActPump act1 = new ActPump();
        for (ActPump act: LIST_ACT_PUMP) if (act.getId() == idAct){ act1 = act; break;}
        newIntent.putExtra(ActPump.class.getSimpleName(), act1);
        startActivity(newIntent);
    }

    private void startActivityPumpCreate(Context context,  int idAct) {
        Intent newIntent = new Intent(context, PumpCreate.class);
        ActPump act1 = new ActPump();
        for (ActPump act: LIST_ACT_PUMP) if (act.getId() == idAct){ act1 = act; break;}
        newIntent.putExtra(ActPump.class.getSimpleName(), act1);
        startActivity(newIntent);
    }

    private void startActivityPumpView(Context context,  int idAct) {
        Intent newIntent = new Intent(context, PumpView.class);
        ActPump act1 = new ActPump();
        for (ActPump act: LIST_ACT_PUMP) if (act.getId() == idAct){ act1 = act; break;}
        newIntent.putExtra(ActPump.class.getSimpleName(), act1);
        startActivity(newIntent);
    }

    private void startActivityPipeChange(Context context,  int idAct) {
        Intent newIntent = new Intent(context, PipeChange.class);
        ActPipe act1 = new ActPipe();
        for (ActPipe act: LIST_ACT_PIPE) if (act.getId() == idAct){ act1 = act; break;}
        newIntent.putExtra(ActPipe.class.getSimpleName(), act1);
        startActivity(newIntent);
    }

    private void startActivityPipeCreate(Context context,  int idAct) {
        Intent newIntent = new Intent(context, PipeCreate.class);
        ActPipe act1 = new ActPipe();
        for (ActPipe act: LIST_ACT_PIPE) if (act.getId() == idAct){ act1 = act; break;}
        newIntent.putExtra(ActPipe.class.getSimpleName(), act1);
        startActivity(newIntent);
    }

    private void startActivityPipeView(Context context,  int idAct) {
        Intent newIntent = new Intent(context, PipeView.class);
        ActPipe act1 = new ActPipe();
        for (ActPipe act: LIST_ACT_PIPE) if (act.getId() == idAct){ act1 = act; break;}
        newIntent.putExtra(ActPipe.class.getSimpleName(), act1);
        startActivity(newIntent);
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

}
