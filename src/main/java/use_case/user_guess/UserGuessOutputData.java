package use_case.user_guess;

import entities.Letter;
import entities.Word;

import java.util.List;
import java.util.Map;

/**
 * The Output Data after a user has successfully made a guess.
 */
public class UserGuessOutputData {

    private final Map<Integer, Map<Word, List<Integer>>> userGuesses;
    private final Map<Integer, Map<Word, List<Integer>>> cpuGuesses;
    private final boolean isWon;
    private final int turn;
    private final Map<Character, Letter> letterBoard;

    public UserGuessOutputData(Map<Integer, Map<Word, List<Integer>>> userGuesses,
                               Map<Integer, Map<Word, List<Integer>>> cpuGuesses, boolean isWon, int turn,
                               Map<Character, Letter> letterBoard) {

        this.userGuesses = userGuesses;
        this.isWon = isWon;
        this.turn = turn;
        this.letterBoard = letterBoard;
        this.cpuGuesses = cpuGuesses;
    }

    // Getter functions for the user guess outputData.

    public Map<Integer,Map<Word, List<Integer>>> getUserGuesses() {
        return userGuesses;
    }

    public boolean isWon() {
        return isWon;
    }

    public int getTurn() {
        return turn;
    }

    public Map<Integer, Map<Word, List<Integer>>> getCpuGuesses() {
        return cpuGuesses;
    }

    public Map<Character, Letter> getLetterBoard() {

        return letterBoard;
    }
}
