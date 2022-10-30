package com.spaceadventure.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.spaceadventure.R;
import com.spaceadventure.core.DialogManager;
import com.spaceadventure.core.SoundManager;

import org.jetbrains.annotations.NotNull;

/**
 * MainPage of the Game has three buttons
 *  - StartGame goes to the GameViewActivity
 *  - Play/Pause music (music stops when user exits the app)
 *  - How to play guide
 */
public class MainActivity extends AppCompatActivity {

    private SoundManager soundManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        soundManager = new SoundManager(this);

        Button startGame = findViewById(R.id.start_game);
        goToMainGame(startGame);

        Button music = findViewById(R.id.play_pause_music);
        playPauseMusic(music);

        Button howToPlay = findViewById(R.id.how_to_play);
        guide(howToPlay);
    }

    private void goToMainGame(@NotNull Button startGame) {
        startGame.setOnClickListener(v -> startActivity(new Intent(this, GameViewActivity.class)));
    }

    private void playPauseMusic(@NotNull Button music) {
        music.setOnClickListener(v -> {
            String s = music.getText().toString();
            if (s.equals(getString(R.string.play_music))) {
                soundManager.playMusic();
                music.setText(getString(R.string.pause_music));

            } else if (s.equals(getString(R.string.pause_music))) {
                soundManager.pauseMusic();
                music.setText(getString(R.string.play_music));
            }
            soundManager.restartMusic();
        });
    }

    private void guide(@NotNull Button howToPlay) {
        DialogManager dialog = new DialogManager(this);
        howToPlay.setOnClickListener(v -> dialog.showDialog(
                "Welcome to Space Adventure 🤗\n\n" +
                        "Your objective in this game is to move " +
                        "the orange tile with the Astronaut emoji " +
                        "to the middle tile where the Earth emoji is.\n" +
                        "You can only move in Up, Down, Left and Right directions.\n" +
                        "First select:\n" +
                        " ⬆️ - for up direction\n" +
                        " ⬇️ - for down direction\n" +
                        " ⬅️ - for left direction\n" +
                        " ➡️ - for right direction\n" +
                        "and then tap on the tile you want to move " +
                        "(only Astronaut and Alien tiles can move).\n" +
                        "But the Astronaut and the Alien tiles can only move by " +
                        "moving straight into another Astronaut or Alien tile.\n" +
                        "You can not move into the walls of the board " +
                        "otherwise your tile will be lost in the space forever...\n\n" +
                        "Have Fun!\n        ~ Mouli Dutta"
                ,
                "close"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // release SoundManager
        soundManager.stopMusic();
    }
}