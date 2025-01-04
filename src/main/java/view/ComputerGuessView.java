package view;

import app.ColourConstants;
import entities.Word;
import interface_adapter.computer_guess.ComputerGuessState;
import interface_adapter.computer_guess.ComputerGuessViewModel;
import interface_adapter.start_game.StartGameState;
import interface_adapter.to_main_menu.MainMenuController;
import interface_adapter.to_main_menu.MainMenuState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ComputerGuessView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "ComputerGuessView";
    private JButton exitButton;
    private MainMenuController mainMenuController;

    public ComputerGuessView(Map<Integer,Map<Word, List<Integer>>> guesses) {

        setBackground(ColourConstants.GREEN);
        setLayout(new GridLayout(1,7));

        List<List<Integer>> boardCollection = new ArrayList<>();
        int size;

        if (guesses == null) {

            size = 0;
        }

        else {

            size = guesses.size();

            for (int i = 1; i <= size; i++) {

                List<Word> word = new ArrayList<>(guesses.get(i).keySet());
                boardCollection.add(guesses.get(i).get(word.getFirst()));
            }
        }

        // Add Computer's previous guesses
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
        exitButton = new JButton("Exit");
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
        exitButton = new JButton("Exit");
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

    public void setMainMenuController(MainMenuController mainMenuController) {

        this.mainMenuController = mainMenuController;

        exitButton.addActionListener(e -> {
            if (e.getSource() == exitButton) {

                mainMenuController.execute();
            }
        });
    }

    public String getViewName() {

        return viewName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final MainMenuState state = (MainMenuState) evt.getNewValue();
        JOptionPane.showMessageDialog(this, "Objective property change");
    }
}
