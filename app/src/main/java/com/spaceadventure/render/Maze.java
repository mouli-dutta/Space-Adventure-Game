package com.spaceadventure.render;

import android.app.Activity;

import com.spaceadventure.core.CellValueExtractor;

/**
 * {@code Maze} is used to hold default challenges for this game
 * getMaze method returns an array of 5x5 matrices of Cell
 */
public class Maze {
    private final Activity activity;

    public Maze(Activity activity) {
        this.activity = activity;
    }

    public int getTotalChallenges() {
        return getMaze().length - 1;
    }

    public Cell[][][] getMaze() {
        CellValueExtractor cX = new CellValueExtractor(activity);

        // grey color
        int grey = cX.getGreyCol(),
                // aliens color
                yellow = cX.getYellowColor(),
                purple = cX.getPurpleColor(),
                green = cX.getGreenCol(),
                cyan = cX.getCyanColor(),
                blue = cX.getBlueColor(),
                // end of alien color

                // player color
                orange = cX.getOrangeColor();

        String winningString = cX.getWinningString();
        String alienStr = cX.getAlienString();
        String playerStr = cX.getPlayerString();

        // Cell to define characteristics of each maze cell
        // void grey cell
        Cell g = new Cell(grey, "", false);

        // helper alien cells
        Cell y = new Cell(yellow, alienStr, false);
        Cell p = new Cell(purple, alienStr, false);
        Cell r = new Cell(green, alienStr, false);
        Cell c = new Cell(cyan, alienStr, false);
        Cell b = new Cell(blue, alienStr, false);

        // player cell
        Cell o = new Cell(orange, playerStr, true);

        Cell[][][] mazes = {
                //0
                {{g, g, b, g, g}, {c, g, o, g, y}, {g, g, p, g, g}, {g, g, r, g, g}, {g, g, g, g, g}},

                //1
                {{g, g, c, g, g}, {g, g, g, g, r}, {g, g, g, g, g}, {g, p, g, o, y}, {g, g, b, g, g}},
                //2
                {{g, g, g, p, g}, {g, c, g, g, y}, {g, g, g, g, g}, {g, g, g, g, g}, {g, o, g, b, g}},
                //3
                {{g, g, y, g, g}, {g, g, g, g, c}, {g, p, g, g, g}, {g, g, c, g, g}, {g, g, g, g, o}},
                //4
                {{g, g, g, p, g}, {g, y, g, g, g}, {g, g, g, g, g}, {g, g, r, g, g}, {g, g, c, g, o}},

                //5
                {{p, g, g, g, b}, {g, g, g, c, g}, {g, g, g, g, g}, {g, g, y, g, g}, {g, o, g, g, g}},
                //6
                {{g, r, g, g, g}, {g, g, g, g, c}, {g, g, g, g, g}, {g, g, g, g, g}, {b, o, y, g, g}},
                //7
                {{b, g, p, g, g}, {g, g, g, r, g}, {g, g, g, g, g}, {g, g, c, g, g}, {g, o, g, g, g}},
                //8
                {{g, g, g, g, g}, {g, y, g, g, g}, {g, g, g, g, c}, {g, g, g, g, g}, {r, g, b, g, o}},
                //9
                {{g, g, g, g, y}, {g, g, p, g, g}, {g, r, g, g, g}, {g, g, g, c, g}, {g, g, g, g, o}},

                //10
                {{g, b, g, g, g}, {g, g, g, g, c}, {g, g, g, g, g}, {g, g, g, g, g}, {g, y, g, g, o}},
                //11
                {{y, g, o, g, b}, {g, g, g, g, g}, {g, g, g, g, g}, {g, g, g, g, c}, {g, p, g, g, g}},
                //12
                {{g, r, g, g, g}, {g, g, g, b, g}, {g, g, g, g, g}, {g, c, g, o, g}, {g, g, y, g, g}},
                //13
                {{g, g, g, g, p}, {g, r, g, g, g}, {g, g, g, g, c}, {g, g, y, g, g}, {g, g, g, g, o}},
                //14
                {{g, c, g, g, r}, {g, g, g, g, g}, {g, g, g, g, b}, {y, g, g, g, g}, {g, g, g, g, o}},

                //15
                {{c, g, o, g, p}, {g, g, g, g, g}, {g, g, g, g, g}, {g, g, g, g, g}, {r, g, g, g, b}},
                //16
                {{g, g, o, g, g}, {g, p, g, g, r}, {c, g, g, g, g}, {g, g, g, g, g}, {g, b, g, g, y}},
                //17
                {{g, c, g, g, g}, {g, g, g, g, p}, {g, g, g, g, g}, {g, g, g, g, g}, {y, o, b, g, g}},
                //18
                {{r, y, g, g, g}, {g, g, g, g, c}, {g, g, g, g, g}, {p, g, g, g, g}, {g, g, g, b, o}},
                //19
                {{g, g, r, g, y}, {g, g, g, g, g}, {b, g, g, g, g}, {g, g, g, p, g}, {c, g, g, g, o}},

                //20
                {{y, g, o, g, c}, {g, g, p, g, g}, {g, g, g, g, g}, {g, g, g, g, g}, {b, g, g, g, r}},
                //21
                {{g, g, o, g, g}, {p, g, g, g, c}, {g, g, g, g, g}, {b, g, g, g, r}, {g, g, y, g, g}},
                //22
                {{y, g, g, o, b}, {g, g, g, g, g}, {g, g, g, g, g}, {p, g, g, c, g}, {g, g, g, g, g}},
                //23
                {{g, g, c, g, o}, {g, g, g, g, g}, {y, b, g, g, g}, {g, g, g, g, g}, {g, r, g, g, p}},
                //24
                {{b, g, o, g, p}, {g, g, g, g, g}, {c, g, g, g, g}, {g, g, g, g, g}, {r, g, g, g, y}},
        };

        // middle / winning cell
        for (Cell[][] maze: mazes) {
            maze[2][2] = new Cell(grey, winningString, false);
        }

        return mazes;
    }
}
