package interface_adapter.end_of_game;

import entities.Word;

import java.util.List;
import java.util.Map;

public class EndGameState {

    private Map<Word, List<Integer>> computerBoard;
    private Map<Word, List<Integer>> playerBoard;

    public void setComputerBoard(Map<Word, List<Integer>> computerBoard) {

        this.computerBoard = computerBoard;
    }

    public void setPlayerBoard(Map<Word, List<Integer>> playerBoard) {

        this.playerBoard = playerBoard;
    }

    public Map<Word, List<Integer>> getComputerBoard() {

        return computerBoard;
    }

    public Map<Word, List<Integer>> getPlayerBoard() {

        return playerBoard;
    }
}
