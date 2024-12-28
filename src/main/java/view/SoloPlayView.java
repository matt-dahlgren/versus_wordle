package view;

import app.ColourConstants;

import javax.swing.*;
import java.awt.*;

public class SoloPlayView extends JPanel {

    public SoloPlayView() {

        setLayout(new BorderLayout());
        setBackground(ColourConstants.GREEN);

        // add the keyboard of this game
        JPanel keyBoard = new JPanel();
        keyBoard.setBackground(ColourConstants.GREEN);
        keyBoard.add(new KeyboardView());
        add(keyBoard, BorderLayout.SOUTH);

        // set up the middle of the screen
        JPanel middlePanel = new JPanel();
        middlePanel.setBackground(ColourConstants.GREEN);
        middlePanel.setLayout(new GridLayout(1, 3));

        JPanel leftEmptyPanel = new JPanel();
        leftEmptyPanel.setBackground(ColourConstants.GREEN);
        middlePanel.add(leftEmptyPanel);

        middlePanel.add(new GuessView());

        JPanel rightEmptyPanel = new JPanel();
        rightEmptyPanel.setBackground(ColourConstants.GREEN);
        middlePanel.add(rightEmptyPanel);

        add(middlePanel, BorderLayout.CENTER);

        // add the Title card to this view
        JPanel titlePanel = new JPanel(new FlowLayout());
        titlePanel.setBackground(ColourConstants.GREEN);

        JLabel titleLabel = new JLabel("Solo Game");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 36));
        titlePanel.setBackground(ColourConstants.GREEN);
        titlePanel.add(titleLabel);

        add(titlePanel, BorderLayout.NORTH);
    }
}
