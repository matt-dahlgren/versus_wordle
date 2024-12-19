package view;

import app.ColourConstants;

import javax.swing.*;
import java.awt.*;

public class GuessView extends JPanel {

    // TODO: make a way to populate this list with correct colours. Maybe a list of Letters saved in the GDAO
    public GuessView() {

        setLayout(new GridLayout(6, 5));
        setBackground(ColourConstants.DARKPURPLE);
    }

    /**
     * Creates a letter in the view of this game when typing.
     * @param letter is any lowercase letter of the english language with no accents.
     * @param status is an element of the set {-1, 1, 2}
     * @return a JPanel that will populate our view that represents the status of that letter at the time of that turn's
     * play.
     */
    private JPanel guessBuilder(char letter, int status) {

        JPanel guess = new JPanel();
        Font times = new Font("Times New Roman", Font.BOLD, 24);

        if (status == ColourConstants.GREY) {
            guess.setBackground(ColourConstants.GREY_TILE);
            setBackground(ColourConstants.GREY_TILE);
            JLabel charLabel = new JLabel(String.valueOf(letter));
            charLabel.setBackground(ColourConstants.GREY_TILE);
            charLabel.setFont(times);
            guess.add(charLabel);
        }
        else if (status == ColourConstants.BLUE) {
            guess.setBackground(ColourConstants.BLUE_TILE);
            setBackground(ColourConstants.BLUE_TILE);
            JLabel charLabel = new JLabel(String.valueOf(letter));
            charLabel.setBackground(ColourConstants.BLUE_TILE);
            charLabel.setFont(times);
            guess.add(charLabel);
        }
        else {
            guess.setBackground(ColourConstants.LIGHTBLUE_TILE);
            setBackground(ColourConstants.LIGHTBLUE_TILE);
            JLabel charLabel = new JLabel(String.valueOf(letter));
            charLabel.setBackground(ColourConstants.LIGHTBLUE_TILE);
            charLabel.setFont(times);
            guess.add(charLabel);
        }
        return guess;
    }
}
