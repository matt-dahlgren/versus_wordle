package interface_adapter.computer_guess;

import entities.Word;

import java.util.List;
import java.util.Map;

/**
 * The State to change the view of the ComputerGuess View in the UI.
 */
public class ComputerGuessState {

    private Map<Word, List<Integer>> currentDisplayedBoard;
    private Map<Word, List<Integer>> playerBoard;

    public void setCurrentDisplayedBoard(Map<Word, List<Integer>> currentDisplayedBoard) {
        this.currentDisplayedBoard = currentDisplayedBoard;
    }

    public Map<Word, List<Integer>> getCurrentDisplayedBoard() {
        return currentDisplayedBoard;
    }

    public void setPlayerBoard(Map<Word, List<Integer>> playerBoard) {

        this.playerBoard = playerBoard;
    }

    public Map<Word, List<Integer>> getPlayerBoard() {

        return playerBoard;
    }
}
