package com.spaceadventure.stimulator;

import android.app.Activity;


import com.spaceadventure.core.SpaceCell;
import com.spaceadventure.core.CellPos;
import com.spaceadventure.core.CellValueExtractor;

import org.jetbrains.annotations.NotNull;

/**
 * {@code CellMovement} class is used to move a cell in the given direction on the board.
 * Uses classes {@code SpaceCell}, {@code CellPos} and {@code CellValueExtractor} in the core package.
 * <p>
 * It's public methods are
 * - moveUp
 * - moveDown
 * - moveLeft and
 * - moveRight
 * <p>
 * and Helper, private methods are
 * - setMiddleCell
 * - updateCell
 */
public class CellMovement {
    private final SpaceCell[][] board;
    private final CellValueExtractor cX;
    private final CellPos middleCell = new CellPos(2, 2);

    public CellMovement(Activity activity, SpaceCell[][] board) {
        this.board = board;
        cX = new CellValueExtractor(activity);
    }

    /**
     * Tries to move player or alien cell in the upward direction
     *
     * @param cell current cell on the board
     * @return cell in the new position after moving upwards on the board
     */
    public SpaceCell moveUp(@NotNull SpaceCell cell) {
        int grey = cX.getGreyCol();

        CellPos initialPos = cell.getPos();
        int row = initialPos.getRow();
        int col = initialPos.getCol();

        boolean isPlayer = cell.isPlayer();
        String emoji = cell.getEmoji();
        int color = cell.getColor();

        // check row bound
        if (row - 1 >= 0) {
            // we need to move up from down along row
            for (int k = row - 1; k >= 0; k--) {

                // if player or alien cell is found
                // we stop the current cell before the found cell
                if (board[k][col].getColor() != grey) {
                    // update the current cell color
                    if (row != k + 1) {
                        updateCell(board[row][col], initialPos, initialPos, false, grey, "");
                    }

                    updateCell(board[k + 1][col], new CellPos(k + 1, col), initialPos, isPlayer, color, emoji);

                    // return new position of the cell
                    return board[k + 1][col];
                }
            }

            // could not find any non grey cell
            updateCell(board[row][col], initialPos, initialPos, false, grey, "");

        } else {
            updateCell(board[0][col], new CellPos(0, col), initialPos, false, grey, "");
        }

        updateMidCell();
        return null;
    }

    /**
     * Tries to move player or alien cell in the downwards direction
     *
     * @param cell current cell on the board
     * @return cell in the new position after moving downwards on the board
     */
    public SpaceCell moveDown(@NotNull SpaceCell cell) {
        int grey = cX.getGreyCol();

        CellPos initialPos = cell.getPos();
        int row = initialPos.getRow();
        int col = initialPos.getCol();

        boolean isPlayer = cell.isPlayer();
        String emoji = cell.getEmoji();
        int color = cell.getColor();

        if (row + 1 < 5) {
            for (int k = row + 1; k < 5; k++) {
                // non grey cell is found
                if (board[k][col].getColor() != grey) {

                    // turn the selected cell grey
                    if (row != k - 1) {
                        updateCell(board[row][col], initialPos, initialPos, false, grey, "");
                    }

                    // update destination cell
                    updateCell(board[k - 1][col], new CellPos(k - 1, col), initialPos, isPlayer, color, emoji);

                    return board[k - 1][col];
                }
            }

            // couldn't find any non grey cell, update the current cell as grey
            updateCell(board[row][col], new CellPos(row, col), initialPos, false, grey, "");

        } else {
            // index (row + 1 ) is greater than 4
            // set color of cell[4][j] to grey
            updateCell(board[4][col], new CellPos(4, col), initialPos, false, grey, "");
        }

        updateMidCell();
        return null;
    }

    /**
     * Tries to move player or alien cell in the left direction
     *
     * @param cell current cell on the board
     * @return cell in the new position after moving to the left on the board
     */
    public SpaceCell moveLeft(@NotNull SpaceCell cell) {
        int grey = cX.getGreyCol();

        CellPos initialPos = cell.getPos();
        int row = initialPos.getRow();
        int col = initialPos.getCol();

        boolean isPlayer = cell.isPlayer();
        String emoji = cell.getEmoji();
        int color = cell.getColor();

        // col bound
        if (col - 1 >= 0) {
            for (int k = col - 1; k >= 0; k--) {
                // non grey cell is found
                if (board[row][k].getColor() != grey) {
                    // turing selected cell grey
                    if (col != (k + 1)) {
                        updateCell(board[row][col], initialPos, initialPos, false, grey, "");
                    }

                    updateCell(board[row][k + 1], new CellPos(row, k + 1), initialPos, isPlayer, color, emoji);
                    return board[row][k + 1];
                }
            }
            // couldn't find any non grey cell
            updateCell(board[row][col], initialPos, initialPos, false, grey, "");

        } else {
            updateCell(board[row][0], new CellPos(row, 0), initialPos, false, grey, "");
        }

        updateMidCell();
        return null;
    }

    /**
     * Tries to move player or alien cell in the right direction
     *
     * @param cell current cell on the board
     * @return cell in the new position after moving to the right direction on the board
     */
    public SpaceCell moveRight(@NotNull SpaceCell cell) {
        int grey = cX.getGreyCol();

        CellPos initialPos = cell.getPos();
        int row = initialPos.getRow();
        int col = initialPos.getCol();

        boolean isPlayer = cell.isPlayer();
        String emoji = cell.getEmoji();
        int color = cell.getColor();

        if (col + 1 < 5) {
            for (int k = col + 1; k < 5; k++) {
                // non grey cell is found
                if (board[row][k].getColor() != grey) {
                    // turning selected cell grey
                    if (col != (k - 1)) {
                        updateCell(board[row][col], initialPos, initialPos, false, grey, "");
                    }

                    updateCell(board[row][k - 1], new CellPos(row, k - 1), initialPos, isPlayer, color, emoji);
                    return board[row][k - 1];
                }
            }

            // couldn't find any non grey cell
            updateCell(board[row][col], initialPos, initialPos, false, grey, "");

        } else {
            updateCell(board[row][4], new CellPos(row, 4), initialPos, false, grey, "");
        }

        updateMidCell();
        return null;
    }

    /**
     * Update given cell with new values
     *
     * @param cell     given cell
     * @param pos      new position on the board
     * @param isPlayer new isPlayer value
     * @param color    new color
     * @param emoji    new emoji
     */
    private void updateCell(@NotNull SpaceCell cell, CellPos pos, CellPos prevPos, boolean isPlayer, int color, String emoji) {
        cell.setPos(pos);
        cell.setPrevPos(prevPos);
        cell.setPlayer(isPlayer);
        cell.setColor(color);
        cell.setEmoji(emoji);
        cell.setViewBackground(cell.getView(), cell);
        cell.getView().setText(emoji);

        updateMidCell();
    }

    private void updateMidCell() {
        SpaceCell midCell = board[middleCell.getRow()][middleCell.getCol()];
        if (midCell.getColor() == cX.getGreyCol())
            midCell.getView().setText(cX.getWinningString());
    }
}


