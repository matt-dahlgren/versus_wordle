package interface_adapter.to_main_menu;

import use_case.to_main_menu.MainMenuInputBoundary;

/**
 * The controller that the view calls with the user wants to go back to the main menu.
 */
public class MainMenuController {

    private final MainMenuInputBoundary mainMenuInteractor;

    public MainMenuController(MainMenuInputBoundary mainMenuInteractor) {

        this.mainMenuInteractor = mainMenuInteractor;
    }

    /**
     * Initiate the process of changing the user interface to show the main menu.
     */
    public void execute() {

        mainMenuInteractor.execute();
    }
}
