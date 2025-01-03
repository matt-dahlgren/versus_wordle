package view;

import app.ColourConstants;

import javax.swing.*;
import java.awt.*;

/**
 * The view that manages the Keyboard View.
 */
public class KeyboardView extends JPanel {

    private final String viewName = "KeyboardView";

    public KeyboardView() {

        setBackground(ColourConstants.GREEN);
        setLayout(new GridLayout(4,1));

        // initializing the font and panels that will be added to this panel later
        Font times = new Font("Times New Roman", Font.BOLD, 24);
        JPanel textLayer = new JPanel(new FlowLayout());
        JPanel topLayer = new JPanel(new FlowLayout());
        JPanel middleLayer = new JPanel(new FlowLayout());
        JPanel bottomLayer = new JPanel(new FlowLayout());

        textLayer.setBackground(ColourConstants.GREEN);
        topLayer.setBackground(ColourConstants.GREEN);
        middleLayer.setBackground(ColourConstants.GREEN);
        bottomLayer.setBackground(ColourConstants.GREEN);

        // Add a text field for the user to type in
        JTextField textField = new JTextField(15);
        textField.setFont(new Font("Times New Roman", Font.BOLD, 16));
        textLayer.add(textField);
        // Add a guess button to make a guess
        JButton enterButton = new JButton("Enter");
        enterButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
        textLayer.add(enterButton);


        // TODO: add a way to get the board colour for the buttons!
        // Add the letter buttons to use on the keyboard
        int letterCount = 1;
        for (char letter = 'a'; letter <= 'z'; letter++) {

            LetterButton letterButton = new LetterButton(letter, times);
            letterButton.addActionListener(e -> {textField.setText(textField.getText() + letterButton.getLetter());
            });
            if (letterCount < 10) {
                topLayer.add(letterButton);
            }
            else if (letterCount < 19) {
                middleLayer.add(letterButton);
            }
            else {
                bottomLayer.add(letterButton);
            }
            letterCount++;
        }

        // add the button panels to the view.
        add(textLayer);
        add(topLayer);
        add(middleLayer);
        add(bottomLayer);
    }

    public String getViewName() {

        return viewName;
    }
}
