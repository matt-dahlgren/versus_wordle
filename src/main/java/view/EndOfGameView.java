package view;

import app.ColourConstants;
import interface_adapter.ViewManagerModel;
import interface_adapter.end_of_game.EndGameState;
import interface_adapter.end_of_game.EndGameViewModel;
import interface_adapter.to_main_menu.MainMenuController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EndOfGameView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "EndOfGameView";
    private final ComputerGuessView computerGuessView;
    private MainMenuController mainMenuController;
    private final EndGameViewModel endGameViewModel;
    private ViewManagerModel viewManagerModel;

    private JPanel centrePanel;

    public EndOfGameView(EndGameViewModel endGameViewModel) {

        this.endGameViewModel = endGameViewModel;
        this.endGameViewModel.addPropertyChangeListener(this);
        setLayout(new BorderLayout());
        setBackground(ColourConstants.GREEN);

        this.computerGuessView = new ComputerGuessView();
        add(computerGuessView, BorderLayout.NORTH);

        // add a bottom layer to the screen
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(ColourConstants.GREEN);
        add(bottomPanel, BorderLayout.SOUTH);

        // add the centre panel
        centrePanel = new JPanel(new GridLayout(2,3));
        centrePanel.setBackground(ColourConstants.GREEN);

        JPanel leftEmptyPanel = new JPanel();
        leftEmptyPanel.setBackground(ColourConstants.GREEN);

        JPanel rightEmptyPanel = new JPanel();
        rightEmptyPanel.setBackground(ColourConstants.GREEN);

        JPanel guessPanels = new JPanel(new GridLayout(1,2));
        guessPanels.setBackground(ColourConstants.GREEN);

        final EndGameState endState = endGameViewModel.getState();
        JPanel computerGuess = new JPanel(new BorderLayout());
        computerGuess.setBackground(ColourConstants.GREEN);
        JLabel computerGuessLabel = new JLabel("Computer's Guesses");
        computerGuessLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        computerGuessLabel.setBackground(ColourConstants.GREEN);

        computerGuess.add(computerGuessLabel, BorderLayout.NORTH);
        System.out.println(endState.getComputerBoard());
        computerGuess.add(new GuessView(endState.getComputerBoard()), BorderLayout.CENTER);
        computerGuess.setBorder(BorderFactory.createLineBorder(ColourConstants.GREEN));

        JPanel userGuess = new JPanel(new BorderLayout());
        userGuess.setBackground(ColourConstants.GREEN);
        JLabel userGuessLabel = new JLabel("Your Guesses");
        userGuessLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        userGuessLabel.setBackground(ColourConstants.GREEN);

        userGuess.add(userGuessLabel, BorderLayout.NORTH);
        userGuess.add(new GuessView(endState.getPlayerBoard()), BorderLayout.CENTER);
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

    public void setMainMenuController(MainMenuController mainMenuController) {

        this.mainMenuController = mainMenuController;
    }

    public ComputerGuessView getComputerGuessView() {

        return computerGuessView;
    }

    public String getViewName() {

        return viewName;
    }

    public void setViewManagerModel(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if (evt.getNewValue() instanceof EndGameState) {
            final EndGameState endState = (EndGameState) evt.getNewValue();
            setFields(endState);
        }
    }

    public void setFields(EndGameState endState) {

        this.remove(centrePanel);
        centrePanel = new JPanel(new GridLayout(2,3));
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
        System.out.println(endState.getComputerBoard());
        computerGuess.add(new GuessView(endState.getComputerBoard()), BorderLayout.CENTER);
        computerGuess.setBorder(BorderFactory.createLineBorder(ColourConstants.GREEN));

        JPanel userGuess = new JPanel(new BorderLayout());
        userGuess.setBackground(ColourConstants.GREEN);
        JLabel userGuessLabel = new JLabel("Your Guesses");
        userGuessLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        userGuessLabel.setBackground(ColourConstants.GREEN);

        userGuess.add(userGuessLabel, BorderLayout.NORTH);
        System.out.println(endState.getPlayerBoard());
        userGuess.add(new GuessView(endState.getPlayerBoard()), BorderLayout.CENTER);
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

        this.add(centrePanel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }
}
