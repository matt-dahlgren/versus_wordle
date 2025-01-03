package app;

import data_access.ColourFormatAccessInterface;
import data_access.ColourFormatDataAccessObject;
import data_access.GameDataAccessObject;
import data_access.VersusDataAccessObject;
import interface_adapter.ViewManagerModel;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

/**
 * Builds all use cases, views, data access objects and interfaces needed to run this game.
 */
public class AppBuilder {

    // UI elements that the rest of the app is built upon
    private final JPanel appPanel = new JPanel();
    private final CardLayout appLayout = new CardLayout();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(appPanel, appLayout, viewManagerModel);

    // Data access objects
    private GameDataAccessObject computerGameDataAccessObject = new GameDataAccessObject();
    private GameDataAccessObject playerGameDataAccessObject = new GameDataAccessObject();
    private VersusDataAccessObject versusGameDataAccessObject = new VersusDataAccessObject("hello");
    private final ColourFormatAccessInterface colourCodeDataAccessObject = new ColourFormatDataAccessObject();

    // Views
    private GuessView guessView;
    private KeyboardView keyboardView;
    private MainMenuView mainMenuView;
    private SoloPlayView soloPlayView;

    public AppBuilder() throws FileNotFoundException {}

    public JFrame build() {

        final JFrame application =  new JFrame("Versus Wordle Game");
        application.add(appPanel);
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        viewManagerModel.setState(mainMenuView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
