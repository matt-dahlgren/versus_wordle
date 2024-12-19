package use_case.computer_guess;

import entities.Word;

/**
 * This class represents the output data of the computer_guess use case.
 */
public class ComputerGuessOutputData {

    private final Word computerGuess;

    public ComputerGuessOutputData(Word computerGuess) {
        this.computerGuess = computerGuess;
    }

    /**
     * Get the computer's guess for this turn.
     * @return a Word corresponding to this Computer's guess.
     */
    public Word getComputerGuess() {
        return computerGuess;
    }
}
