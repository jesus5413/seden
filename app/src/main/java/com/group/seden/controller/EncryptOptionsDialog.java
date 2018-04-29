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
import com.group.seden.model.Encryption;

import static com.group.seden.controller.SendMessageActivity.password;
//import static com.group.seden.controller.SendMessageActivity.usePassword;

public class EncryptOptionsDialog extends DialogFragment {
    public View.OnClickListener onButtonAccept;
    public EditText edit_text;
    private View view;
    private AlertDialog.Builder builder;
    private Dialog dialog;
    //public CheckBox isEncrypted;
    public EditText passwardET;
    public Button buttonAccept;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        builder = new AlertDialog.Builder(getActivity());
        LayoutInflater li = LayoutInflater.from(builder.getContext());
        View view = li.inflate(R.layout.activity_decrypt_options_dialog, null);

        //find edit text and checkbox by id
        passwardET = view.findViewById(R.id.keyInput);
        //isEncrypted = view.findViewById(R.id.keyCheckBox);
        buttonAccept = view.findViewById(R.id.acceptButton1);

        FinishEncryptHandle(buttonAccept);
        builder.setView(view);
        dialog = builder.create();
        return dialog;
    }

    private void FinishEncryptHandle(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("accept pressed");
                //usePassword = isEncrypted.isChecked();

                if (!passwardET.getText().toString().isEmpty()) {
                    if (password != null) {
                        Encryption.decrypt(SendMessageActivity.message, password);
                        SendMessageActivity.messageContent.setText(SendMessageActivity.message.getMsgText());
                    }
                    password = Long.parseLong(passwardET.getText().toString());
                    SendMessageActivity.messageContent.setEnabled(false);
                    SendMessageActivity.message.setMsgText(SendMessageActivity.messageContent.getText().toString());
                    Encryption.encrypt(SendMessageActivity.message, password);
                    SendMessageActivity.messageContent.setText(SendMessageActivity.message.getMsgText());
                    //SendMessageActivity.encryptButton.setVisibility(View.GONE);
                }
                else {
                    SendMessageActivity.message.setMsgText(SendMessageActivity.messageContent.getText().toString());
                    if (password != null) {
                        Encryption.decrypt(SendMessageActivity.message, password);
                        SendMessageActivity.messageContent.setText(SendMessageActivity.message.getMsgText());
                    }
                    SendMessageActivity.messageContent.setEnabled(true);
                    password = null;

                }


                dialog.dismiss();

            }
        });

    } // end of finish encrpyt handle


}
