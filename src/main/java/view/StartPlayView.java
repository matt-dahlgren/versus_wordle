package view;

import app.ColourConstants;
import interface_adapter.computer_guess.ComputerGuessController;
import interface_adapter.computer_guess.ComputerGuessState;
import interface_adapter.computer_guess.ComputerGuessViewModel;
import interface_adapter.start_game.StartGameState;
import interface_adapter.start_game.StartGameViewModel;
import interface_adapter.to_main_menu.MainMenuController;
import interface_adapter.user_guess.UserGuessController;
import interface_adapter.user_guess.UserGuessState;
import interface_adapter.user_guess.UserGuessViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class StartPlayView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "StartPlayView";
    private ComputerGuessController computerGuessController;
    private UserGuessController userGuessController;
    private MainMenuController mainMenuController;
    private final ComputerGuessView computerGuessView;
    private final GuessView guessView;
    private KeyboardView keyboardView;

    public void setComputerGuessController(ComputerGuessController computerGuessController) {

        this.computerGuessController = computerGuessController;
        this.computerGuessView.setMainMenuController(mainMenuController);
    }

    public void setUserGuessController(UserGuessController userGuessController) {

        this.userGuessController = userGuessController;
    }

    public void setMainMenuController(MainMenuController mainMenuController) {

        this.mainMenuController = mainMenuController;
    }

    /**
     * Prepare the view when the game has just started.
     * @param startGameViewModel has no null parameters.
     */
    public StartPlayView(StartGameViewModel startGameViewModel) {

        setLayout(new BorderLayout());

        // add the guess panel
        final StartGameState startGameState = startGameViewModel.getState();
        keyboardView = new KeyboardView(startGameState.getLetters());
        add(keyboardView, BorderLayout.SOUTH);

        // add the centre panel with guess view in the middle
        JPanel centrePanel = new JPanel(new GridLayout(1, 3));

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(ColourConstants.GREEN);
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(ColourConstants.GREEN);

        centrePanel.add(leftPanel);
        guessView = new GuessView(startGameState.getPlayerGuesses());
        centrePanel.add(guessView, BorderLayout.CENTER);
        centrePanel.add(rightPanel);

        add(centrePanel, BorderLayout.CENTER);

        // add a top panel, this houses to computer guess view
        computerGuessView = new ComputerGuessView(startGameState.getComputerGuesses());
        add(computerGuessView, BorderLayout.PAGE_START);
    }

    public String getViewName() {

        return viewName;
    }

    public ComputerGuessView getComputerGuessView() {

        return computerGuessView;
    }

    public GuessView getGuessView() {

        return guessView;
    }

    public KeyboardView getKeyboardView() {

        return keyboardView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if (evt.getNewValue() instanceof StartGameState) {
            final StartGameState startGameState = (StartGameState) evt.getNewValue();
            setFields(startGameState);
        }
    }

    private void setFields(StartGameState startGameState) {

        this.remove(keyboardView);
        keyboardView = new KeyboardView(startGameState.getLetters());
        keyboardView.getTextField().setText("");
        keyboardView.setComputerGuessController(computerGuessController);
        keyboardView.setUserGuessController(userGuessController);
        keyboardView.setEnterButtonUse();
        this.add(keyboardView, BorderLayout.SOUTH);
        this.revalidate();
        this.repaint();
    }
}
