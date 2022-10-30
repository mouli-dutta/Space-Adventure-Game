package com.spaceadventure.core;

import android.content.Context;
import android.media.MediaPlayer;

import com.spaceadventure.R;

/**
 * {@code SoundManager} class uses MediaPlayer class to play/pause/restart and stop music
 */
public class SoundManager {
    private final Context context;
    private MediaPlayer mediaPlayer;

    public SoundManager(Context context) {
        this.context = context;
    }

    // media player
    public void playMusic() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, R.raw.game_sound);
        }
        mediaPlayer.start();
    }

    public void pauseMusic() {
        if (mediaPlayer != null) mediaPlayer.pause();
    }

    public void restartMusic() {
        mediaPlayer.setOnCompletionListener(MediaPlayer::start);
    }

    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
