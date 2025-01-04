package interface_adapter.computer_guess;

import entities.Letter;
import entities.Word;

import java.util.List;
import java.util.Map;

/**
 * The State to change the view of the ComputerGuess View in the UI.
 */
public class ComputerGuessState {

    private Map<Integer, Map<Word, List<Integer>>> currentDisplayedBoard;
    private Map<Integer, Map<Word, List<Integer>>> playerBoard;
    private Map<Character, Letter> playerLetters;

    public void setCurrentDisplayedBoard(Map<Integer, Map<Word, List<Integer>>> currentDisplayedBoard) {
        this.currentDisplayedBoard = currentDisplayedBoard;
    }

    public Map<Integer, Map<Word, List<Integer>>> getCurrentDisplayedBoard() {
        return currentDisplayedBoard;
    }

    public void setPlayerBoard(Map<Integer, Map<Word, List<Integer>>> playerBoard) {

        this.playerBoard = playerBoard;
    }

    public Map<Integer, Map<Word, List<Integer>>> getPlayerBoard() {

        return playerBoard;
    }

    public void setPlayerLetters(Map<Character, Letter> playerLetters) {

        this.playerLetters = playerLetters;
    }

    public Map<Character, Letter> getPlayerLetters() {

        return playerLetters;
    }
}
