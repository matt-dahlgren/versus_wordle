package view;

import app.ColourConstants;

import javax.swing.*;
import java.awt.*;

public class EndOfGameView extends JPanel {

    private final String viewName = "EndOfGameView";

    public EndOfGameView() {

        setLayout(new BorderLayout());
        setBackground(ColourConstants.GREEN);

        add(new ComputerGuessView(), BorderLayout.NORTH);

        // add a bottom layer to the screen
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(ColourConstants.GREEN);
        add(bottomPanel, BorderLayout.SOUTH);

        // add the centre panel
        JPanel centrePanel = new JPanel(new GridLayout(2,3));
        centrePanel.setBackground(ColourConstants.GREEN);

        JPanel leftEmptyPanel = new JPanel();
        leftEmptyPanel.setBackground(ColourConstants.GREEN);

        JPanel rightEmptyPanel = new JPanel();
        rightEmptyPanel.setBackground(ColourConstants.GREEN);

        JPanel guessPanels = new JPanel(new GridLayout(1,2));
        guessPanels.setBackground(ColourConstants.GREEN);

        JPanel computerGuess = new JPanel(new BorderLayout());
        computerGuess.setBackground(ColourConstants.GREEN);
        JLabel computerGuessLabel = new JLabel("Computer's Guesses");
        computerGuessLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        computerGuessLabel.setBackground(ColourConstants.GREEN);

        computerGuess.add(computerGuessLabel, BorderLayout.NORTH);
        computerGuess.add(new GuessView(), BorderLayout.CENTER);
        computerGuess.setBorder(BorderFactory.createLineBorder(ColourConstants.GREEN));

        JPanel userGuess = new JPanel(new BorderLayout());
        userGuess.setBackground(ColourConstants.GREEN);
        JLabel userGuessLabel = new JLabel("Your Guesses");
        userGuessLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        userGuessLabel.setBackground(ColourConstants.GREEN);

        userGuess.add(userGuessLabel, BorderLayout.NORTH);
        userGuess.add(new GuessView(), BorderLayout.CENTER);
        userGuess.setBorder(BorderFactory.createLineBorder(ColourConstants.GREEN));

        guessPanels.add(userGuess);
        guessPanels.add(computerGuess);

        centrePanel.add(leftEmptyPanel);
        centrePanel.add(guessPanels);
        centrePanel.add(rightEmptyPanel);

        for (int i = 0; i < 3; i++) {

            JPanel emptyPanel = new JPanel();
            emptyPanel.setBackground(ColourConstants.GREEN);
            centrePanel.add(emptyPanel);
        }

        add(centrePanel, BorderLayout.CENTER);
    }
}
