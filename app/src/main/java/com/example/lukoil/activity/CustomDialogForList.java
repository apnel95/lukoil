package com.example.lukoil.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.lukoil.R;
import com.example.lukoil.interfaces.ChangeNameList;

import java.util.ArrayList;
import java.util.Objects;

public class CustomDialogForList extends DialogFragment {
    String sItem;
    private ChangeNameList changeNameList;

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        changeNameList = (ChangeNameList) context;
    }
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d("dialog", "create dialog");

        assert getArguments() != null;
        ArrayList<String> list = getArguments().getStringArrayList("list");
        int viewId = getArguments().getInt("viewId");

        String[] array = (Objects.requireNonNull(list)).toArray(new String[0]);

        sItem = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(getResources().getString(R.string.selectTypeEvent))
                    .setSingleChoiceItems(array, -1,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int item) {
                                    Log.d("dialog", "setTypeEvent setSingleChoiceItems onClick");
                                    sItem = array[item];
                                }
                            })
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            Log.d("dialog", "setTypeEvent setPositiveButton onClick");
                            if (sItem != null) changeNameList.changeNameEventDateList(viewId, sItem);

                        }
                    })
                    .setNegativeButton(R.string.Cancel, null);
            return builder.create();
    }
}