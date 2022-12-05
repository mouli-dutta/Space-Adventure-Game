package com.spaceadventure.main;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.spaceadventure.R;
import com.spaceadventure.core.SpaceCell;
import com.spaceadventure.render.GameBoard;
import com.spaceadventure.render.Maze;
import com.spaceadventure.stimulator.UserGamePlay;

/**
 * {@code GameViewActivity} is the Main program Activity.
 * It initializes the Buttons
 *  - {Reset} to reset the game
 *  - {Previous} to back to the previous challenge
 *  - {Next} to go to the next challenge
 *
 * It uses
 *  - {@code GameBoard} of the render package to display the board and it's elements.
 *  - {@code UserGamePlay} of the simulator package to handle movement of the tiles
 *                             selected by the player.
 */

public class GameViewActivity extends AppCompatActivity {
    // challenge number
    private int level = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_view);

        // initialise game for the first time
        initGame();

        // display challenge number
        TextView chNum = findViewById(R.id.ch_num);
        String s = "Challenge Number #" + (level + 1);
        chNum.setText(s);

        // display move
        TextView move = findViewById(R.id.move_counter);

        // number of challenges
        int mazeLen = new Maze(this).getTotalChallenges();

        // show next challenge board on click
        Button next = findViewById(R.id.next_game);
        next.setOnClickListener(v -> {

            if (level >= mazeLen) level = -1;
            level++;

            if (level >= 0) {
                String s1 = "Challenge Number #" + (level + 1);
                chNum.setText(s1);
                initGame();
            }
            move.setText(getString(R.string.move));
        });

        // show previous challenge board on btn click
        Button prev = findViewById(R.id.previous_game);
        prev.setOnClickListener(v -> {

            if (level <= 0) level = mazeLen + 1;
            level--;

            if (level <= mazeLen) {
                String s1 = "Challenge Number #" + (level + 1);
                chNum.setText(s1);
                initGame();
            }
            move.setText(getString(R.string.move));
        });

        // reset game on btn click
        Button reset = findViewById(R.id.reset_game);
        reset.setOnClickListener(v -> {
            initGame();
            move.setText(getString(R.string.move));
        });
    }

    private void initGame() {
        GameBoard gameBoard = new GameBoard(this, this, level);
        SpaceCell[][] board = gameBoard.getBoardCell();
        new UserGamePlay(this, board).solve();
    }
}