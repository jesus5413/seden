package com.group.seden.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.group.seden.R;

public class DecryptOptionsDialog extends DialogFragment {
    public View.OnClickListener onButtonAccept;
    public EditText edit_text;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater li = LayoutInflater.from(builder.getContext());
        View view = li.inflate(R.layout.activity_decrypt_options_dialog, null);
        CheckBox timeout = view.findViewById(R.id.timeoutCheckBox);
        // Button buttonAccept = view.findViewById(R.id.acceptButton1);
        //   edit_text = view.findViewById(R.id.keyInput);
        //   buttonAccept.setOnClickListener(onButtonAccept);
        builder.setView(view);

        return builder.create();
    }

}
