package com.spaceadventure.core;

import android.app.Activity;

import com.spaceadventure.R;

/**
 * {@code CellValueExtractor} class helps to manage the colors and emojis
 *                            that are used characterized each cell.
 */
public class CellValueExtractor {
    private final Activity activity;

    public CellValueExtractor(Activity activity) {
        this.activity = activity;
    }

    // cell colors
    public int getGreyCol() {
        return getColor(R.color.grey_cell);
    }

    public int getGreenCol() {
        return getColor(R.color.green_cell);
    }

    public int getBlueColor() {
        return getColor(R.color.blue_cell);
    }

    public int getPurpleColor() {
        return getColor(R.color.purple_cell);
    }

    public int getCyanColor() {
        return getColor(R.color.cyan_cell);
    }

    public int getOrangeColor() {
        return getColor(R.color.orange_cell);
    }

    public int getYellowColor() {
        return getColor(R.color.yellow_cell);
    }

    private int getColor(int p) {
        return activity.getResources().getColor(p);
    }

    // cell texts
    public String getWinningString() {
        return getString(R.string.earth);
    }

    public String getAlienString() {
        return getString(R.string.alien);
    }

    public String getPlayerString() {
        return getString(R.string.player);
    }

    private String getString(int id) {
        return activity.getResources().getString(id);
    }
}
