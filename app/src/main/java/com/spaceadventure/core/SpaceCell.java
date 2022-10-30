package com.spaceadventure.core;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.spaceadventure.R;

import org.jetbrains.annotations.NotNull;

/**
 * {@code SpaceCell} represents a single cell in the GameBoard.
 * This class holds details about the cells on the board such as
 * the cell's current / previous position, color, emoji, if its a player cell
 *
 * To get the position of the cell on the board it uses {@code CellPos} in the core package.
 *
 *  It uses {textview} to change the cell's text and background color dynamically
 */
public class SpaceCell {
    private final Activity activity;
    private CellPos pos;
    private CellPos prevPos;
    private int color;
    private String emoji;
    private boolean isPlayer;
    private final TextView view;

    public SpaceCell(Activity activity, TextView view, int x, int y, boolean isPlayer, int color, String emoji) {
        this.activity = activity;
        this.view = view;
        this.pos = new CellPos(x, y);
        this.prevPos = new CellPos(x, y);
        this.isPlayer = isPlayer;
        this.color = color;
        this.emoji = emoji;
        setViewBackground(view, this);
    }

    public CellPos getPos() {
        return pos;
    }

    public CellPos getPrevPos() {
        return prevPos;
    }

    public void setPos(CellPos pos) {
        if (pos.isOnBoard(pos)) {
            this.pos = pos;
        } else throw new IllegalArgumentException("Position must be between 0 to 4");
    }

    public void setPrevPos(CellPos prevPos) {
        if (prevPos.isOnBoard(prevPos)) this.prevPos = prevPos;
        else throw new IllegalArgumentException("Position must be between 0 to 4.");
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        if (color != -1)
            this.color = color;
    }

    public TextView getView() {
        return view;
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public void setPlayer(boolean player) {
        isPlayer = player;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public void setViewBackground(TextView view, SpaceCell otherCell) {
        // set background
        Drawable drawable = ContextCompat.getDrawable(activity, R.drawable.text_view_cell_background);
        if (drawable != null)
            drawable.setColorFilter(new PorterDuffColorFilter(otherCell.getColor(), PorterDuff.Mode.SRC_IN));
        view.setBackground(drawable);

        // set text
        String emoji = otherCell.getEmoji();
        if (emoji != null) {
            view.setText(emoji);
            view.setGravity(Gravity.CENTER);
            view.setTextSize(23f);
        } else view.setText("");
    }


    @NotNull
    @Override
    public String toString() {
        return "SpaceCell{" +
                "pos {row = " + pos.getRow() + ", col = " + pos.getCol() + "},\n" +
                "\ncolor='" + color +
                "'\nemoji='" + emoji +
                "'\nisPlayer=" + isPlayer +
                '}';
    }
}
