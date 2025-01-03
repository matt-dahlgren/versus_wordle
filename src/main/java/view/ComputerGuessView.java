package view;

import app.ColourConstants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ComputerGuessView extends JPanel {

    private final String viewName = "ComputerGuessView";

    public ComputerGuessView(Map<String, List<Integer>> pastBoards) {

        setBackground(ColourConstants.GREEN);
        setLayout(new GridLayout(1,7));
        List<List<Integer>> boardCollection = new ArrayList<>(pastBoards.values());

        // Add Computer's previous guesses
        int size = boardCollection.size();

        for (int i = 0; i < 7; i++) {

            if (i < size) {

                add(generateTurnView(boardCollection.get(i)));
            }

            else {

                JPanel emptyPanel = new JPanel();
                emptyPanel.setBackground(ColourConstants.GREEN);
                add(emptyPanel);
            }
        }

        // add an exit button
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(exitButton);
    }

    /**
     * This initialization allows the user to just have an exit button at the top right of their screen.
     */
    public ComputerGuessView() {

        setBackground(ColourConstants.GREEN);
        setLayout(new GridLayout(1,7));

        for (int i = 0; i < 7; i++) {

            JPanel emptyPanel = new JPanel();
            emptyPanel.setBackground(ColourConstants.GREEN);
            add(emptyPanel);
        }

        // add an exit button
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(exitButton);
    }

    public JPanel generateTurnView(List<Integer> board) {

        JPanel result = new JPanel(new GridLayout(1, 5));
        result.setBackground(ColourConstants.GREEN);
        for (int i = 0; i < board.size(); i++) {

            JPanel colourPanel = new JPanel();

            if (board.get(i) == ColourConstants.GREY) {

                colourPanel.setBackground(ColourConstants.GREY_TILE);
            }

            else if (board.get(i) == ColourConstants.BLUE) {

                colourPanel.setBackground(ColourConstants.BLUE_TILE);
            }

            else {

                colourPanel.setBackground(ColourConstants.LIGHTBLUE_TILE);
            }

            result.add(colourPanel);
        }

        result.setBorder(BorderFactory.createLineBorder(ColourConstants.GREEN));
        return result;
    }

    public String getViewName() {

        return viewName;
    }
}
