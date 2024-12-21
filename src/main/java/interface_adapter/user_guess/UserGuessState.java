package interface_adapter.user_guess;

import entities.Word;

import java.util.List;
import java.util.Map;

/**
 * The State to update the user's view after making a guess.
 */
public class UserGuessState {

    private Map<Integer, Map<Word, List<Integer>>> currentDisplayedBoard;

    public void setCurrentDisplayedBoard(Map<Integer, Map<Word, List<Integer>>> currentDisplayedBoard) {
        this.currentDisplayedBoard = currentDisplayedBoard;
    }

    public Map<Integer, Map<Word, List<Integer>>> getCurrentDisplayedBoard() {
        return currentDisplayedBoard;
    }
}
