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
import java.util.List;
import java.util.Map;

public class SoloPlayView extends JPanel {

    private final String viewName = "SoloPlayView";
    private ComputerGuessController computerGuessController;
    private UserGuessController userGuessController;
    private MainMenuController mainMenuController;

    public SoloPlayView(ComputerGuessViewModel computerGuessViewModel, UserGuessViewModel userGuessViewModel) {

        setLayout(new BorderLayout());

        // add the guess panel
        final UserGuessState userGuessState = userGuessViewModel.getState();
        add(new KeyboardView(userGuessState.getLetterBoard()), BorderLayout.SOUTH);

        // add the centre panel with guess view in the middle
        JPanel centrePanel = new JPanel(new GridLayout(1, 3));

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(ColourConstants.GREEN);
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(ColourConstants.GREEN);

        centrePanel.add(leftPanel);
        centrePanel.add(new GuessView(userGuessState.getCurrentDisplayedBoard()), BorderLayout.CENTER);
        centrePanel.add(rightPanel);

        add(centrePanel, BorderLayout.CENTER);

        // add a top panel, this houses to computer guess view
        final ComputerGuessState computerGuessState = computerGuessViewModel.getState();
        add(new ComputerGuessView(computerGuessState.getCurrentDisplayedBoard()), BorderLayout.PAGE_START);
    }

    public void setComputerGuessController(ComputerGuessController computerGuessController) {

        this.computerGuessController = computerGuessController;
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
    public SoloPlayView(StartGameViewModel startGameViewModel) {

        setLayout(new BorderLayout());

        // add the guess panel
        final StartGameState startGameState = startGameViewModel.getState();
        add(new KeyboardView(startGameState.getLetters()), BorderLayout.SOUTH);

        // add the centre panel with guess view in the middle
        JPanel centrePanel = new JPanel(new GridLayout(1, 3));

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(ColourConstants.GREEN);
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(ColourConstants.GREEN);

        centrePanel.add(leftPanel);
        centrePanel.add(new GuessView(startGameState.getPlayerGuesses()), BorderLayout.CENTER);
        centrePanel.add(rightPanel);

        add(centrePanel, BorderLayout.CENTER);

        // add a top panel, this houses to computer guess view
        add(new ComputerGuessView(startGameState.getComputerGuesses()), BorderLayout.PAGE_START);
    }
}
