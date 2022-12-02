package com.spaceadventure.util;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;

public class StatusBar {
    private final Activity activity;

    public StatusBar(Activity activity) {
        this.activity = activity;
    }

    public void changeColor(int color) {
        Window window = activity.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(activity, color));
    }
}
