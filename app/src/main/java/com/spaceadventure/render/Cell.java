package com.spaceadventure.render;

/**
 * {@code Cell} class is used to hold the properties color, emoji and if the cell is player.
 */
public class Cell {
    private int color;
    private String emoji;
    private boolean isPlayer;

    public Cell(int color, String emoji, boolean isPlayer) {
        this.color = color;
        this.emoji = emoji;
        this.isPlayer = isPlayer;
    }

    public String getEmoji() {
        return emoji;
    }

    public int getColor() {
        return color;
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setPlayer(boolean player) {
        isPlayer = player;
    }
}
