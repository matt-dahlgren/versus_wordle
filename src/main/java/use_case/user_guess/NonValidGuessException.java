package use_case.user_guess;

public class NonValidGuessException extends Exception {

    /**
     * Exception thrown when the guess that the user made is not an element of the guessBank of a game, or if the guess
     * has already been guessed (and thus also no longer an element of the guessBank).
     */
    public NonValidGuessException() {}

    public NonValidGuessException(String message) {
        super(message);
    }
}
