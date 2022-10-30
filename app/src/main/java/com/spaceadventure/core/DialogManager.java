package com.spaceadventure.core;

import android.app.Activity;
import android.app.AlertDialog;

/**
 * {@code DialogManager} utility class helps to create dialog with given message.
 */
public class DialogManager {
    private final Activity activity;

    public DialogManager(Activity activity) {
        this.activity = activity;
    }

    public void showDialog(String msg, String btnText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(msg)
                .setPositiveButton(btnText, (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}