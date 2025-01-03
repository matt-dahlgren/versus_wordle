package use_case.user_guess;

/**
 * The output boundary for the user_guess use case.
 */
public interface UserGuessOutputDataBoundary {

    /**
     * Prepares the user's view after they have made a guess, this will either prepare for them to make another move, or
     * it will send them to a win/loss screen.
     * @param outputData is valid outputData for this usecase. Which should only ever be initialized in the User guess
     *                   interactor.
     */
    void preparePostGuessView(UserGuessOutputData outputData);

    /**
     * Prepares the user's view after they have made a guess and they guessed correctly.
     * @param outputData is valid outputData where none of its attributes are null.
     */
    void prepareWinView(UserGuessOutputData outputData);

}
