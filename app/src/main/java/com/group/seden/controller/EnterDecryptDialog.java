package com.group.seden.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.group.seden.R;


//Temporarily not in use
public class EnterDecryptDialog extends Fragment {

    /*
    public interface DecryptSuccessDialogListener {
        public void onDialogYesClick(DialogFragment dialog);
        public void onDialogNoClick(DialogFragment dialog);
    }
    // Use this instance of the interface to deliver action events
    DecryptSuccessDialog.DecryptSuccessDialogListener mListener;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (DecryptSuccessDialog.DecryptSuccessDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Did the message decrypt sucessfully?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the positive button event back to the host activity
                        mListener.onDialogYesClick(DecryptSuccessDialog.this);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the negative button event back to the host activity
                        mListener.onDialogNoClick(DecryptSuccessDialog.this);
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }*/
}
