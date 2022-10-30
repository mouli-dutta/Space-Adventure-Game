package com.spaceadventure.render;

import android.app.Activity;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.spaceadventure.core.SpaceCell;

/**
 * {@code GameBoard} class is used to display the GameBoard on the screen
 * uses {@code SpaceCell} in the core package to return a 2d array of the board of SpaceCell
 */
public class GameBoard {
    private final Context context;
    private final Activity activity;
    private final Cell[][] mazeCell;

    public GameBoard(Context context, Activity activity, int id) {
        this.context = context;
        this.activity = activity;
        mazeCell = new Maze(activity).getMaze()[id];
    }

    public SpaceCell[][] getBoardCell() {
        SpaceCell[][] cells = new SpaceCell[mazeCell.length][mazeCell[0].length];

        for (int i = 0; i < mazeCell.length; i++) {
            LinearLayout row = activity.findViewById(findIdByName("row_" + (i + 1)));

            for (int j = 0; j < mazeCell[0].length; j++) {
                TextView cell = row.findViewById(findIdByName("cell" + (j + 1)));
                Cell curr_cell = mazeCell[i][j];

                SpaceCell spaceCell = new SpaceCell(activity, cell, i, j,
                        curr_cell.isPlayer(), curr_cell.getColor(), curr_cell.getEmoji());

                cells[i][j] = spaceCell;

            }
        }
        return cells;
    }

    private int findIdByName(String name) {
        return context.getResources().getIdentifier(name, "id", context.getPackageName());
    }
}

