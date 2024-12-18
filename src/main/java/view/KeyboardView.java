package view;

import app.ColourConstants;

import javax.swing.*;
import java.awt.*;

/**
 * The view that manages the Keyboard View.
 */
public class KeyboardView extends JPanel {

    public KeyboardView() {

        setBackground(ColourConstants.PURPLE);
        setLayout(new GridLayout(3,1));

        // initializing the font and panels that will be added to this panel later
        Font times = new Font("Times New Roman", Font.BOLD, 24);
        JPanel topLayer = new JPanel(new FlowLayout());
        JPanel middleLayer = new JPanel(new FlowLayout());
        JPanel bottomLayer = new JPanel(new FlowLayout());

        topLayer.setBackground(ColourConstants.PURPLE);
        middleLayer.setBackground(ColourConstants.PURPLE);
        bottomLayer.setBackground(ColourConstants.PURPLE);

        // Add the letter buttons to use on the keyboard
        int letterCount = 1;
        for (char letter = 'a'; letter <= 'z'; letter++) {
            if (letterCount < 10) {
                topLayer.add(letterButton(letter, times));
            }
            else if (letterCount < 19) {
                middleLayer.add(letterButton(letter, times));
            }
            else {
                bottomLayer.add(letterButton(letter, times));
            }
            letterCount++;
        }
        // add the button panels to the view.
        add(topLayer);
        add(middleLayer);
        add(bottomLayer);
    }

    // TODO: Later add ability to change colour based on information but haven't made the viewstates yet so I can't do
    //  that yet
    private JButton letterButton(char letter, Font font) {
        JButton button = new JButton(String.valueOf(letter));
        button.setBackground(ColourConstants.WHITE_TILE);
        button.setFont(font);
        return button;
    }
}
