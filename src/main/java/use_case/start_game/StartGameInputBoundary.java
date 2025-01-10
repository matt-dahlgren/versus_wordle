package use_case.start_game;

/**
 * The Input Boundary for the start game use case.
 */
public interface StartGameInputBoundary {

    /**
     * Start a new game, initialize a new GameDataAccessObject and VersusDataAccessObject (so also choose a new word)
     */
    void prepareNewGame();
}
