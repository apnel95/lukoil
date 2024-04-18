package com.example.lukoil.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.lukoil.R;
import com.example.lukoil.Removable;

import java.util.ArrayList;
import java.util.Objects;

public class CustomDialog extends DialogFragment {
    String sItem;
    String typeDialog;

    private Removable removable;

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        removable = (Removable) context;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        assert getArguments() != null;
        ArrayList<String> list = getArguments().getStringArrayList("list");
        int viewId = getArguments().getInt("viewId");
        String typeDialog = getArguments().getString("typeDialog");
        final String[] array;
        String[] array1;
        array1 = new String[0];
        if (list != null) {
            array1 = (list).toArray(new String[0]);
        }
        array = array1;
        sItem = null;

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        if (typeDialog.equals("setTypeEvent")) {
            builder.setTitle(getResources().getString(R.string.selectTypeEvent))
                    .setSingleChoiceItems(array, -1,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int item) {
                                    sItem = array[item];
                                }
                            })
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            if (sItem != null) removable.changeNameEventDateList(viewId, sItem);

                        }
                    })
                    .setNegativeButton("Отмена", null);

            return builder.create();
        }
        else {
            return builder
                    .setTitle("Вы точно хотите удалить событие?")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            removable.removeEventDateView(viewId);
                        }
                    })
                    .setNegativeButton("Отмена", null)
                    .create();

        }
    }


}