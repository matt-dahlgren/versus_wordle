package interface_adapter.user_guess;

import entities.Letter;
import entities.Word;

import java.util.List;
import java.util.Map;

/**
 * The State to update the user's view after making a guess.
 */
public class UserGuessState {

    private Map<Integer, Map<Word, List<Integer>>> currentDisplayedBoard;
    private Map<Integer, Map<Word, List<Integer>>> computerDisplayedBoard;
    private Map<Character, Letter> letterBoard;

    public void setCurrentDisplayedBoard(Map<Integer, Map<Word, List<Integer>>> currentDisplayedBoard) {
        this.currentDisplayedBoard = currentDisplayedBoard;
    }

    public Map<Integer,Map<Word, List<Integer>>> getCurrentDisplayedBoard() {
        return currentDisplayedBoard;
    }

    public void setLetterBoard(Map<Character, Letter> letterBoard) {

        this.letterBoard = letterBoard;
    }

    public Map<Character, Letter> getLetterBoard() {

        return letterBoard;
    }

    public void setComputerDisplayedBoard(Map<Integer,Map<Word, List<Integer>>> computerDisplayedBoard) {

        this.computerDisplayedBoard = computerDisplayedBoard;
    }

    public Map<Integer,Map<Word, List<Integer>>> getComputerDisplayedBoard() {

        return computerDisplayedBoard;
    }
}
