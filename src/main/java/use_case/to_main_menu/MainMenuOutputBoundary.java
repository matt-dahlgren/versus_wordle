package use_case.to_main_menu;

/**
 * The Output boundary for the (exit to the) main menu use case.
 */
public interface MainMenuOutputBoundary {

    /**
     * Prepares the game to go back to its main menu.
     */
    void prepareMainMenuView();
}
