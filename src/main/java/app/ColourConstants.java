package app;

import java.awt.Color;

/**
 * This class contains the constants to the colours (Colors because JavaSwing is American**) being used in the View of
 * this program.
 */
public class ColourConstants {

    //GREY - refers to a guessed letter that is not present in a game's answer.
    public static final int GREY = -1;
    public static final Color GREY_TILE = new Color(119,136,153);

    //WHITE - refers to an unguessed letter.
    public static final int WHITE = 0;
    public static final Color WHITE_TILE = new Color(255, 255, 255);

    //LIGHTBLUE - refers to a guessed letter in the wrong index.
    public static final int LIGHTBLUE = 1;
    public static final Color LIGHTBLUE_TILE = new Color(135, 206, 250);

    //BLUE - refers to a guessed letter in the right index.
    public static final int BLUE = 2;
    public static final Color BLUE_TILE = new Color(100, 149, 237);

    //BACKGROUND - a light purple
    public static final Color PURPLE = new Color(221, 160, 221);
    public static Color DARKPURPLE = new Color(221, 140, 221);
}
