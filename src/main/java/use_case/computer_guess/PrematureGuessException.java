package use_case.computer_guess;

public class PrematureGuessException extends RuntimeException {

    /**
     * Exception thrown if the player's last guess was not valid.
     */
    public PrematureGuessException(String message) {
        super(message);
    }
}
