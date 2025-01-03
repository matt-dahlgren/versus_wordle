package view;

import app.ColourConstants;

import javax.swing.*;
import java.awt.*;

public class MainMenuView extends JPanel {

    private final String viewName = "MainMenu";

    public MainMenuView() {

        setLayout(new GridLayout(5,1));
        setBackground(ColourConstants.GREEN);
        String times = "Times New Roman";

        // Add an empty panel that populates the top fifth of the view.
        JPanel topPanel = new JPanel();
        topPanel.setBackground(ColourConstants.GREEN);
        add(topPanel);

        // Add the Title Panel
        JPanel titlePanel = new JPanel(new FlowLayout());
        titlePanel.setBackground(ColourConstants.GREEN);

        // Title Card
        JLabel titleLabel = new JLabel("Versus Wordle");
        titleLabel.setBackground(ColourConstants.GREEN);
        titleLabel.setFont(new Font(times, Font.BOLD, 48));

        titlePanel.add(titleLabel);
        add(titlePanel);

        // Add button panel to access the two types of playable wordgames.
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(ColourConstants.GREEN);

        JButton singlePlayer = new JButton("Solo Game");
        singlePlayer.setBackground(ColourConstants.WHITE_TILE);
        singlePlayer.setFont(new Font(times, Font.BOLD, 24));

        JButton versusComputer = new JButton("New Game");
        versusComputer.setBackground(ColourConstants.WHITE_TILE);
        versusComputer.setFont(new Font(times, Font.BOLD, 24));

        // Set both buttons to be the same size, decided to only verify the size of versusComputer button since its
        // text is noticeably larger than the text in singlePlayer
        Dimension buttonSize = versusComputer.getPreferredSize();
        singlePlayer.setPreferredSize(buttonSize);

        // buttonPanel.add(singlePlayer);
        buttonPanel.add(versusComputer);
        add(buttonPanel);

        // Add another filler panel to occupy the 4th row in this layout
        JPanel fourthPanel = new JPanel();
        fourthPanel.setBackground(ColourConstants.GREEN);
        add(fourthPanel);

        // Add a panel with some disclosing information
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(ColourConstants.GREEN);

        String textBlock = """
                A game heavily inspired by the game Wordle by the NYT, in a 'Solo Game' you, the player,
                                will be able to play a game entirely adjacent to Wordle. In the 'Versus Computer' game
                                mode your goal is to guess the five letter word before my program does! I built this to
                                learn how to make an algorithm to solve puzzles!""";
        String singleLineTextBlock = textBlock.replace("/n", " ");

        JLabel singleLineLabel = new JLabel("<html><font color = 'black'>" + singleLineTextBlock
                + "</font></html>");
        singleLineLabel.setFont(new Font(times, Font.BOLD, 20));
        singleLineLabel.setBackground(ColourConstants.GREEN);

        bottomPanel.add(singleLineLabel, BorderLayout.SOUTH);
        add(bottomPanel);
    }

    public String getViewName() {

        return viewName;
    }
}
