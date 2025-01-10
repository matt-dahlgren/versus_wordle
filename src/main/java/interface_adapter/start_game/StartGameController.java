package interface_adapter.start_game;

import use_case.start_game.StartGameInputBoundary;

/**
 * Controller that initiates the start game use case.
 */
public class StartGameController {

    private final StartGameInputBoundary startGameInteractor;

    public StartGameController(StartGameInputBoundary startGameInteractor) {

        this.startGameInteractor = startGameInteractor;
    }

    /**
     * Call the Start Game interactor to prepare the Data Access Objects needed to start a new game.
     */
    public void startGame() {

        startGameInteractor.prepareNewGame();
    }
}
