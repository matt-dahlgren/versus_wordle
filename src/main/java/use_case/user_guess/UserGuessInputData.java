package use_case.user_guess;

/**
 * The class representing the InputData from the user when guessing a word in a game of versus_wordgame.
 */
public class UserGuessInputData {

    private final String guess;

    /**
     * Initialize InputData from the user after guessing.
     * @param guess is a string of letters.
     */
    public UserGuessInputData(String guess) {
        this.guess = guess.toLowerCase();
    }

    public String getGuess() {
        return guess;
    }
}
