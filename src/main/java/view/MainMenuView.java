package view;

import app.ColourConstants;
import interface_adapter.start_game.StartGameController;
import interface_adapter.start_game.StartGameState;
import interface_adapter.to_main_menu.MainMenuState;
import interface_adapter.to_main_menu.MainMenuViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainMenuView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "MainMenu";
    private StartGameController startGameController;

    public MainMenuView(MainMenuViewModel mainMenuViewModel) {

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

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(ColourConstants.GREEN);

        JButton versusComputer = new JButton("New Game");
        versusComputer.setBackground(ColourConstants.WHITE_TILE);
        versusComputer.setFont(new Font(times, Font.BOLD, 24));

        versusComputer.addActionListener(
                e -> {
                    if (e.getSource().equals(versusComputer)) {

                        startGameController.startGame();
                }
            });

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

    public void setMainMenuController(StartGameController startGameController) {

        this.startGameController = startGameController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final StartGameState state = (StartGameState) evt.getNewValue();
        JOptionPane.showMessageDialog(this, "Objective property change");
    }
}
