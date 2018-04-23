package com.group.seden.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.group.seden.R;

import static com.group.seden.controller.SendMessageActivity.password;
import static com.group.seden.controller.SendMessageActivity.usePassword;

public class DecryptOptionsDialog extends DialogFragment {
    public View.OnClickListener onButtonAccept;
    public EditText edit_text;
    private View view;
    private AlertDialog.Builder builder;
    private Dialog dialog;
    private CheckBox isEncrypted;
    private EditText passwardET;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        builder = new AlertDialog.Builder(getActivity());
        LayoutInflater li = LayoutInflater.from(builder.getContext());
        View view = li.inflate(R.layout.activity_decrypt_options_dialog, null);
        passwardET = view.findViewById(R.id.keyInput);
        isEncrypted = view.findViewById(R.id.keyCheckBox);
        Button buttonAccept = view.findViewById(R.id.acceptButton1);
        //   edit_text = view.findViewById(R.id.keyInput);
        //   buttonAccept.setOnClickListener(onButtonAccept);
        FinishEncryptHandle(buttonAccept);
        builder.setView(view);
        dialog = builder.create();
        return dialog;
    }

    public Button returnAccept(){
        Button buttonAccept = view.findViewById(R.id.acceptButton1);

        return buttonAccept;
    }

    private void FinishEncryptHandle(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                System.out.println("create alter");


                System.out.println("accept pressed");
                usePassword = isEncrypted.isChecked();

                if (!passwardET.getText().toString().equals(""))
                    password = Long.parseLong(passwardET.getText().toString());


                dialog.dismiss();

            }
        });

    } // end of finish encrpyt handle


}
