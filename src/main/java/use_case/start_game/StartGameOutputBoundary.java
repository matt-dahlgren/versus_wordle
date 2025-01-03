package use_case.start_game;

/**
 * The Output Boundary for the start game use case.
 */
public interface StartGameOutputBoundary {

    /**
     * Call the presenter for the start game use case with the necessary data for a wordle game.
     * @param outputData has non parameters such that they return null.
     */
    void prepareGameView(StartGameOutputData outputData);
}
