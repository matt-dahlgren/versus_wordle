package use_case.computer_guess;

import entities.Word;

import java.util.List;
import java.util.Map;

/**
 * This class represents the output data of the computer_guess use case.
 */
public class ComputerGuessOutputData {

    private final Map<Integer, Map<Word, List<Integer>>> computerGuesses;
    private boolean isWon;
    private final int turn;

    public ComputerGuessOutputData(Map<Integer, Map<Word, List<Integer>>> computerGuesses, boolean isWon, int turn) {

        this.computerGuesses = computerGuesses;
        this.isWon = isWon;
        this.turn = turn;
    }

    /**
     * Get the computer's guess for this turn.
     * @return a Word corresponding to this Computer's guess.
     */
    public Map<Integer, Map<Word, List<Integer>>> getComputerGuess() {

        return computerGuesses;
    }

    /**
     * Get whether the game is won on this turn.
     * @return True iff the game has been won on this turn.
     */
    public boolean isWon() {
        return isWon;
    }

    /**
     * Get the turn number.
     * @return a non-negative integer representing the turn number.
     */
    public int getTurn() {
        return turn;
    }
}
