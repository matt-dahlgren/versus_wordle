package interface_adapter.computer_guess;

import entities.Word;

import java.util.List;
import java.util.Map;

/**
 * The State to change the view of the ComputerGuess View in the UI.
 */
public class ComputerGuessState {

    private Map<Integer, Map<Word, List<Integer>>> currentDisplayedBoard;

    public void setCurrentDisplayedBoard(Map<Integer, Map<Word, List<Integer>>> currentDisplayedBoard) {
        this.currentDisplayedBoard = currentDisplayedBoard;
    }

    public Map<Integer, Map<Word, List<Integer>>> getCurrentDisplayedBoard() {
        return currentDisplayedBoard;
    }
}
