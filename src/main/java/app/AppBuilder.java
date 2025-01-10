package app;

import data_access.ColourFormatAccessInterface;
import data_access.ColourFormatDataAccessObject;
import data_access.GameDataAccessObject;
import data_access.VersusDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.computer_guess.ComputerGuessController;
import interface_adapter.computer_guess.ComputerGuessPresenter;
import interface_adapter.computer_guess.ComputerGuessViewModel;
import interface_adapter.end_of_game.EndGameViewModel;
import interface_adapter.start_game.StartGameController;
import interface_adapter.start_game.StartGamePresenter;
import interface_adapter.start_game.StartGameViewModel;
import interface_adapter.to_main_menu.MainMenuController;
import interface_adapter.to_main_menu.MainMenuPresenter;
import interface_adapter.to_main_menu.MainMenuViewModel;
import interface_adapter.user_guess.UserGuessController;
import interface_adapter.user_guess.UserGuessPresenter;
import interface_adapter.user_guess.UserGuessViewModel;
import use_case.computer_guess.ComputerGuessInputDataBoundary;
import use_case.computer_guess.ComputerGuessInteractor;
import use_case.computer_guess.ComputerGuessOutputDataBoundary;
import use_case.start_game.StartGameInputBoundary;
import use_case.start_game.StartGameInteractor;
import use_case.start_game.StartGameOutputBoundary;
import use_case.to_main_menu.MainMenuInputBoundary;
import use_case.to_main_menu.MainMenuInteractor;
import use_case.to_main_menu.MainMenuOutputBoundary;
import use_case.user_guess.UserGuessInputDataBoundary;
import use_case.user_guess.UserGuessInteractor;
import use_case.user_guess.UserGuessOutputDataBoundary;
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
    private EndOfGameView endOfGameView;
    private MainMenuView mainMenuView;
    private SoloPlayView soloPlayView;
    private StartPlayView startPlayView;

    // View Models
    private ComputerGuessViewModel computerGuessViewModel;
    private StartGameViewModel startGameViewModel;
    private MainMenuViewModel mainMenuViewModel;
    private UserGuessViewModel userGuessViewModel;
    private EndGameViewModel endGameViewModel;

    public AppBuilder() {

        appPanel.setLayout(appLayout);
    }

    // Building Views

    /**
     * Adds the End of game view to the application.
     * @return the end of game view builder.
     */
    public AppBuilder addEndOfGameView() {

        endGameViewModel = new EndGameViewModel();
        endOfGameView = new EndOfGameView(endGameViewModel);
        appPanel.add(endOfGameView, endGameViewModel.getViewName());
        return this;
    }

    /**
     * Adds the Main Menu view to the application.
     * @return the end of game view builder.
     */
    public AppBuilder addMainMenuView() {

        mainMenuViewModel = new MainMenuViewModel();
        mainMenuView = new MainMenuView(mainMenuViewModel);
        appPanel.add(mainMenuView, mainMenuViewModel.getViewName());
        return this;
    }

    /**
     * Adds the Play view to the application.
     * @return the play view builder.
     */
    public AppBuilder addSoloPlayView() {

        userGuessViewModel = new UserGuessViewModel();
        computerGuessViewModel = new ComputerGuessViewModel();
        soloPlayView = new SoloPlayView(computerGuessViewModel, userGuessViewModel);
        appPanel.add(soloPlayView,soloPlayView.getViewName());
        return this;
    }

    public AppBuilder addSoloPlayStartView() {

        startGameViewModel = new StartGameViewModel();
        startPlayView = new StartPlayView(startGameViewModel);
        appPanel.add(startPlayView,startPlayView.getViewName());
        return this;
    }

    // Add Use-Cases

    /**
     * Adds the computer guess use case to the application.
     * @return the computer guess use case builder.
     */
    public AppBuilder addComputerGuessUseCase() {

        final ComputerGuessOutputDataBoundary computerGuessPresenter = new ComputerGuessPresenter(viewManagerModel,
                computerGuessViewModel, endGameViewModel);
        final ComputerGuessInputDataBoundary computerGuessInteracor = new ComputerGuessInteractor(
                computerGameDataAccessObject, playerGameDataAccessObject, versusGameDataAccessObject,
                computerGuessPresenter);

        final ComputerGuessController computerGuessController = new ComputerGuessController(computerGuessInteracor);
        soloPlayView.setComputerGuessController(computerGuessController);
        soloPlayView.getKeyboardView().setComputerGuessController(computerGuessController);
        startPlayView.setComputerGuessController(computerGuessController);
        startPlayView.getKeyboardView().setComputerGuessController(computerGuessController);

        return this;
    }

    /**
     * Adds the start game use case to the application.
     * @return the start game use case builder.
     */
    public AppBuilder addStartGameUseCase() {

        final StartGameOutputBoundary startGamePresenter = new StartGamePresenter(viewManagerModel, startGameViewModel);
        final StartGameInputBoundary startGameInteracor = new StartGameInteractor(computerGameDataAccessObject,
                playerGameDataAccessObject, versusGameDataAccessObject, startGamePresenter);

        final StartGameController startGameController = new StartGameController(startGameInteracor);
        mainMenuView.setMainMenuController(startGameController);
        return this;
    }

    /**
     * Adds the to main menu use case to the application.
     * @return the main menu use case builder.
     */
    public AppBuilder addMainMenuUseCase() {

        final MainMenuOutputBoundary mainMenuPresenter = new MainMenuPresenter(mainMenuViewModel, viewManagerModel);
        final MainMenuInputBoundary mainMenuInteractor = new MainMenuInteractor(mainMenuPresenter);

        final MainMenuController mainMenuController = new MainMenuController(mainMenuInteractor);

        startPlayView.setMainMenuController(mainMenuController);
        startPlayView.getComputerGuessView().setMainMenuController(mainMenuController);
        soloPlayView.setMainMenuController(mainMenuController);
        soloPlayView.getComputerGuessView().setMainMenuController(mainMenuController);
        endOfGameView.setMainMenuController(mainMenuController);
        endOfGameView.getComputerGuessView().setMainMenuController(mainMenuController);
        return this;
    }

    public AppBuilder addUserGuessUseCase() {

        final UserGuessOutputDataBoundary userGuessPresenter = new UserGuessPresenter(viewManagerModel,
                userGuessViewModel, endGameViewModel);
        final UserGuessInputDataBoundary userGuessInteracor = new UserGuessInteractor(playerGameDataAccessObject,
                computerGameDataAccessObject, versusGameDataAccessObject, userGuessPresenter);

        final UserGuessController userGuessController = new UserGuessController(userGuessInteracor);

        soloPlayView.setUserGuessController(userGuessController);
        soloPlayView.getKeyboardView().setUserGuessController(userGuessController);
        soloPlayView.getKeyboardView().setEnterButtonUse();
        startPlayView.getKeyboardView().setUserGuessController(userGuessController);
        startPlayView.getKeyboardView().setEnterButtonUse();
        return this;
    }

    public JFrame build() {

        final JFrame application =  new JFrame("Versus Wordle Game");
        application.add(appPanel);
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        viewManagerModel.setState(mainMenuView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
