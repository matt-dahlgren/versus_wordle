package view;

import app.ColourConstants;

import javax.swing.*;
import java.awt.*;

public class LetterButton extends JButton {

    private final String letter;

    public LetterButton(char letter, Font font, Color buttonColour) {

        super(String.valueOf(letter));
        this.letter = String.valueOf(letter);
        setBackground(buttonColour);
        setFont(font);
    }

    /**
     * Get the letter this button represents to add to the text field.
     * @return a single length string.
     */
    public String getLetter() {

        return letter;
    }
}
