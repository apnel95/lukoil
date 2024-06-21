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
import com.example.lukoil.interfaces.RemovableList;

public class CustomDialogForDelete extends DialogFragment {
    private RemovableList removableList;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        removableList = (RemovableList) context;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d("dialog", "create dialog");

        int idEvent = (int) getArguments().get("idEvent");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            return builder
                    .setTitle(R.string.youSureDeleteThisEvent)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            removableList.deleteEvent(idEvent);
                        }
                    })
                    .setNegativeButton(R.string.cancel, null)
                    .create();
    }
}
