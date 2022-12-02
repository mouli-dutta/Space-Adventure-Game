package com.spaceadventure.stimulator;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.spaceadventure.R;
import com.spaceadventure.core.CellPos;
import com.spaceadventure.core.CellValueExtractor;
import com.spaceadventure.core.DialogManager;
import com.spaceadventure.core.Direction;
import com.spaceadventure.core.SpaceCell;

/**
 * {@code UserGamePlay} class allows user to interact with the game
 * If the cell is movable user can move the cell in up, down, left and right direction.
 *
 * It uses {@code SpaceCell}, {@code CellValueExtractor}, {@code DialogManager} and
 * {@code Direction} classes of the core package and {@code CellMovement} class of
 * the stimulator package.
 *
 */
public class UserGamePlay {
    private final Activity activity;
    private final SpaceCell[][] board;
    private final CellPos winningCell = new CellPos(2, 2);
    private Direction direction = Direction.NONE;

    private final CellValueExtractor cX;

    private int movesCounter = 0;

    public UserGamePlay(Activity activity, SpaceCell[][] board) {
        this.activity = activity;
        this.board = board;
        initDirectionBtns();

        cX = new CellValueExtractor(activity);
    }

    public void solve() {
        DialogManager dialog = new DialogManager(activity);
        int grey = cX.getGreyCol();

        CellMovement cellMovement = new CellMovement(activity, board);

        for (SpaceCell[] cells : board) {
            for (int i = 0; i < board[0].length; i++) {
                SpaceCell cell = cells[i];
                cell.getView().setOnClickListener(v -> {
                    if (cell.getColor() == grey)
                        dialog.showDialog("This cell can't move.", "Ok");

                    else {
                        switch (direction) {
                            case UP:
                                SpaceCell up = cellMovement.moveUp(cell);
                                displayResult(dialog, up);
                                ++movesCounter;
                                updateMove();
                                break;

                            case DOWN:
                                SpaceCell down = cellMovement.moveDown(cell);
                                displayResult(dialog, down);

                                ++movesCounter;
                                updateMove();
                                break;

                            case RIGHT:
                                SpaceCell right = cellMovement.moveRight(cell);
                                displayResult(dialog, right);
                                ++movesCounter;
                                updateMove();
                                break;

                            case LEFT:
                                SpaceCell left = cellMovement.moveLeft(cell);
                                displayResult(dialog, left);
                                ++movesCounter;
                                updateMove();
                                break;

                            case NONE:
                                dialog.showDialog(
                                        "Please select a direction first.\n" +
                                        "You can choose\n" +
                                        " ⬆️ - for up direction\n" +
                                        " ⬇️ - for down direction\n" +
                                        " ⬅️ - for left direction\n" +
                                        " ➡️ - for right direction",
                                        "Close");
                                break;

                            default:
                                dialog.showDialog(
                                        "What the heck did you click on? ＼（〇_ｏ）／",
                                        "close ASAP!!!");
                                break;
                        }
                    }
                });
            }
        }
    }

    private void displayResult(DialogManager dialog, SpaceCell spaceCell) {
        if (spaceCell != null) {
            if (spaceCell.isPlayer()) {
                if (spaceCell.getPos().isOverlapping(winningCell))
                    dialog.showDialog("Congrats, You've won! 🎉\n", "close");
            }
        } else {
            dialog.showDialog("Poor guy\nLost in space...Farewell 😢\n", "close");
        }
    }

    private void updateMove() {
        TextView move_counter = activity.findViewById(R.id.move_counter);
        move_counter.setVisibility(View.VISIBLE);
        String move = "Move #" + movesCounter;
        move_counter.setText(move);
    }

    // set direction btn on clicks
    private void initDirectionBtns() {
        TextView currDir = activity.findViewById(R.id.curr_dir);
        currDir.setText(activity.getResources().getString(R.string.choose_direction));

        // move cell upwards
        Button up = activity.findViewById(R.id.up_arrow_btn);
        up.setOnClickListener(v -> setDir(currDir, up, Direction.UP));

        // move cell downwards
        Button down = activity.findViewById(R.id.down_arrow_btn);
        down.setOnClickListener(v -> setDir(currDir, down, Direction.DOWN));

        // move cell to the left
        Button left = activity.findViewById(R.id.left_arrow_btn);
        left.setOnClickListener(v -> setDir(currDir, left, Direction.LEFT));

        // move cell to the right
        Button right = activity.findViewById(R.id.right_arrow_btn);
        right.setOnClickListener(v -> setDir(currDir, right, Direction.RIGHT));
    }

    // set button direction
    private void setDir(TextView currDir, Button button, Direction value) {
        String d = "The direction is " + button.getText();
        currDir.setText(d);
        direction = value;
    }
}
