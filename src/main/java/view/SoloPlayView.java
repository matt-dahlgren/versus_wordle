package view;

import app.ColourConstants;
import interface_adapter.computer_guess.ComputerGuessController;
import interface_adapter.computer_guess.ComputerGuessState;
import interface_adapter.computer_guess.ComputerGuessViewModel;
import interface_adapter.end_of_game.EndGameState;
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

public class SoloPlayView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "SoloPlayView";
    private ComputerGuessController computerGuessController;
    private UserGuessController userGuessController;
    private MainMenuController mainMenuController;
    private ComputerGuessView computerGuessView;
    private GuessView guessView;
    private KeyboardView keyboardView;
    private JPanel centrePanel;
    private final ComputerGuessViewModel computerGuessViewModel;
    private final UserGuessViewModel userGuessViewModel;

    public SoloPlayView(ComputerGuessViewModel computerGuessViewModel, UserGuessViewModel userGuessViewModel) {

        this.computerGuessViewModel = computerGuessViewModel;
        this.userGuessViewModel = userGuessViewModel;

        userGuessViewModel.addPropertyChangeListener(this);
        computerGuessViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());

        // add the guess panel
        keyboardView = new KeyboardView(userGuessViewModel.getState().getLetterBoard());
        add(keyboardView, BorderLayout.SOUTH);

        // add the centre panel with guess view in the middle
        centrePanel = new JPanel(new GridLayout(1, 3));

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(ColourConstants.GREEN);
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(ColourConstants.GREEN);

        centrePanel.add(leftPanel);
        guessView = new GuessView(userGuessViewModel.getState().getCurrentDisplayedBoard());
        centrePanel.add(guessView, BorderLayout.CENTER);
        centrePanel.add(rightPanel);

        add(centrePanel, BorderLayout.CENTER);

        // add a top panel, this houses to computer guess view
        computerGuessView = new ComputerGuessView(computerGuessViewModel.getState().getCurrentDisplayedBoard());
        add(computerGuessView, BorderLayout.PAGE_START);
    }

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

        if (evt.getNewValue() instanceof ComputerGuessState) {
            final ComputerGuessState guessState = (ComputerGuessState) evt.getNewValue();
            setFields(guessState);
        }
    }

    public void setFields(ComputerGuessState guessState) {

        // update the computer progress bar
        this.remove(computerGuessView);
        computerGuessView = new ComputerGuessView(guessState.getCurrentDisplayedBoard());
        this.add(computerGuessView, BorderLayout.PAGE_START);

        // update the keyboard view
        this.remove(keyboardView);
        keyboardView = new KeyboardView(guessState.getPlayerLetters());
        keyboardView.setComputerGuessController(computerGuessController);
        keyboardView.setUserGuessController(userGuessController);
        keyboardView.setEnterButtonUse();
        add(keyboardView, BorderLayout.SOUTH);

        // update the guess view
        this.remove(centrePanel);
        centrePanel = new JPanel(new GridLayout(1, 3));
        centrePanel.setBackground(ColourConstants.GREEN);

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(ColourConstants.GREEN);

        GuessView middlePanel = new GuessView(guessState.getPlayerBoard());

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(ColourConstants.GREEN);

        centrePanel.add(leftPanel);
        centrePanel.add(middlePanel);
        centrePanel.add(rightPanel);
        this.add(centrePanel, BorderLayout.CENTER);

        this.revalidate();
        this.repaint();
    }
}
