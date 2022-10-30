package com.spaceadventure.core;

import org.jetbrains.annotations.NotNull;

/**
 * {@code CellPos} gives the X and Y coordinates of the cell on the board.
 *
 * Utility methods:
 *  - getX() returns the X coordinate of the cell on the board.
 *  - getY() returns the Y coordinate of the cell on the board.
 *  - isOverlapping(CellPos other) checks if positions of two different cells are same or not
 *                                 i.e. if they are overlapping or not.
 *  - isOnBoard(CellPos pos) checks if the current cell is on the board or not.
 */

public class CellPos {
    private final int row;
    private final int col;

    public CellPos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    // check if position equals another position on grid
    public boolean isOverlapping(CellPos other) {
        return ((other.row == this.row) && (other.col == this.col));
    }

    // check if specified position is on the game board (5x5)
    public boolean isOnBoard(CellPos pos) {
        return (pos.getRow() >= 0 && pos.getRow() < 5 && pos.getCol() >= 0 && pos.getCol() < 5);
    }

    @NotNull
    @Override
    public String toString() {
        return "CellPos{" +
                "x=" + row +
                ", y=" + col +
                '}';
    }
}
