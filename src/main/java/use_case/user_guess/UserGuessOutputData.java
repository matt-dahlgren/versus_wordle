package use_case.user_guess;

import entities.Word;

import java.util.List;
import java.util.Map;

/**
 * The Output Data after a user has successfully made a guess.
 */
public class UserGuessOutputData {

    private final Map<Integer, Map<Word, List<Integer>>> userGuesses;
    private final boolean isWon;
    private final int turn;

    public UserGuessOutputData(Map<Integer, Map<Word, List<Integer>>> userGuesses, boolean isWon, int turn) {

        this.userGuesses = userGuesses;
        this.isWon = isWon;
        this.turn = turn;
    }

    // Getter functions for the user guess outputData.

    public Map<Integer, Map<Word, List<Integer>>> getUserGuesses() {
        return userGuesses;
    }

    public boolean isWon() {
        return isWon;
    }

    public int getTurn() {
        return turn;
    }
}
