package view;

import app.ColourConstants;

import javax.swing.*;
import java.awt.*;

public class SoloPlayView extends JPanel {

    public SoloPlayView() {

        setLayout(new BorderLayout());
        setBackground(ColourConstants.PURPLE);

        // add the keyboard of this game
        JPanel keyBoard = new JPanel();
        keyBoard.setBackground(ColourConstants.PURPLE);
        keyBoard.add(new KeyboardView());
        add(keyBoard, BorderLayout.SOUTH);

        // set up the middle of the screen
        JPanel middlePanel = new JPanel();
        middlePanel.setBackground(ColourConstants.PURPLE);
        middlePanel.setLayout(new GridLayout(1, 3));

        JPanel leftEmptyPanel = new JPanel();
        leftEmptyPanel.setBackground(ColourConstants.PURPLE);
        middlePanel.add(leftEmptyPanel);

        middlePanel.add(new GuessView());

        JPanel rightEmptyPanel = new JPanel();
        rightEmptyPanel.setBackground(ColourConstants.PURPLE);
        middlePanel.add(rightEmptyPanel);

        add(middlePanel, BorderLayout.CENTER);

        // add the Title card to this view
        JPanel titlePanel = new JPanel(new FlowLayout());
        titlePanel.setBackground(ColourConstants.PURPLE);

        JLabel titleLabel = new JLabel("Solo Game");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 36));
        titlePanel.setBackground(ColourConstants.PURPLE);
        titlePanel.add(titleLabel);

        add(titlePanel, BorderLayout.NORTH);
    }
}
