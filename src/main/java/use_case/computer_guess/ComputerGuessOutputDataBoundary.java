package use_case.computer_guess;

/**
 * The output boundary for the computer_guess usecase.
 */
public class ComputerGuessOutputDataBoundary {

    /**
     * Prepares the view to change after this guess has been made.
     * @param outputData is a Word that has just been removed from the game's DAO and ready to be shown as guessed.
     */
    void preparePostGuessView(ComputerGuessOutputData outputData) {}
}
