package view;

import app.ColourConstants;
import entities.Word;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GuessView extends JPanel {

    private final String viewName = "GuessView";

    public GuessView(Map<Word, List<Integer>> guessBoard) {

        setLayout(new GridLayout(6, 1));
        setBackground(ColourConstants.LIGHTGREEN);

        int boardSize = guessBoard.size();
        List<Word> words = new ArrayList<>(guessBoard.keySet());
        List<List<Integer>> statuses = new ArrayList<>(guessBoard.values());

        for (int i = 0; i < 6; i++) {

            if (boardSize < i) {

                add(guessBuilder(words.get(i), statuses.get(i)));
            }

            else {

                JPanel emptyPanel = new JPanel();
                emptyPanel.setBackground(ColourConstants.LIGHTGREEN);
                add(emptyPanel);
            }
        }
    }

    private JPanel guessBuilder(Word word, List<Integer> status) {

        JPanel guess = new JPanel();
        Font times = new Font("Times New Roman", Font.BOLD, 24);

        for (int i = 0; i < 5; i++) {

            JPanel letterPanel = new JPanel();
            if (status.get(i) == ColourConstants.GREY) {

                JLabel letterLabel = new JLabel(word.getLiterals().get(i));
                letterLabel.setBackground(ColourConstants.GREY_TILE);
                letterLabel.setFont(times);
                letterPanel.add(letterLabel);

                letterPanel.setBackground(ColourConstants.GREY_TILE);
            }

            else if (status.get(i) == ColourConstants.BLUE) {

                JLabel letterLabel = new JLabel(word.getLiterals().get(i));
                letterLabel.setBackground(ColourConstants.BLUE_TILE);
                letterLabel.setFont(times);
                letterPanel.add(letterLabel);

                letterPanel.setBackground(ColourConstants.BLUE_TILE);
            }

            else {

                JLabel letterLabel = new JLabel(word.getLiterals().get(i));
                letterLabel.setBackground(ColourConstants.LIGHTBLUE_TILE);
                letterLabel.setFont(times);
                letterPanel.add(letterLabel);

                letterPanel.setBackground(ColourConstants.LIGHTBLUE_TILE);
            }
        }

        return guess;
    }
}
