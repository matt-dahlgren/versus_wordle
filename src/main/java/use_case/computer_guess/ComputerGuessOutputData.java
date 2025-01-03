package use_case.computer_guess;

import entities.Word;

import java.util.List;
import java.util.Map;

/**
 * This class represents the output data of the computer_guess use case.
 */
public class ComputerGuessOutputData {

    private final Map<Word, List<Integer>> computerGuesses;
    private final Map<Word, List<Integer>> playerGuesses;
    private final boolean isWon;
    private final int turn;

    public ComputerGuessOutputData(Map<Word, List<Integer>> computerGuesses,
                                   Map<Word, List<Integer>> playerGuesses, boolean isWon, int turn) {

        this.computerGuesses = computerGuesses;
        this.playerGuesses = playerGuesses;
        this.isWon = isWon;
        this.turn = turn;
    }

    /**
     * Get the computer's guess for this turn.
     * @return a Word corresponding to this Computer's guess.
     */
    public Map<Word, List<Integer>> getComputerGuess() {

        return computerGuesses;
    }

    public Map<Word, List<Integer>> getPlayerGuess() {

        return playerGuesses;
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
