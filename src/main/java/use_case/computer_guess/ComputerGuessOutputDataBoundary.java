package use_case.computer_guess;

/**
 * The output boundary for the computer_guess usecase.
 */
public interface ComputerGuessOutputDataBoundary {

    /**
     * Prepares the view to change after this guess has been made.
     * @param outputData is a Word that has just been removed from the game's DAO and ready to be shown as guessed.
     */
    void preparePostGuessView(ComputerGuessOutputData outputData);

    /**
     * Prepares teh view to change after this guess has been made.
     * @param outputData is valid output data for this use case.
     */
    void prepareComputerWinView(ComputerGuessOutputData outputData);
}
