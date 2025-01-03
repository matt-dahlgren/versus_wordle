package use_case.start_game;

import entities.Letter;
import entities.Word;

import java.util.List;
import java.util.Map;

/**
 * Output data for building the view of starting a game.
 */
public class StartGameOutputData {

    private final Map<Word, List<Integer>> computerGuesses;
    private final Map<Word, List<Integer>> playerGuesses;
    private final Map<Character, Letter> letterMap;

    public StartGameOutputData(Map<Word, List<Integer>> computerGuesses, Map<Word, List<Integer>> playerGuesses,
                               Map<Character, Letter> letterMap) {

        this.computerGuesses = computerGuesses;
        this.playerGuesses = playerGuesses;
        this.letterMap = letterMap;
    }

    public Map<Word, List<Integer>> getComputerGuesses() {

        return computerGuesses;
    }

    public Map<Word, List<Integer>> getPlayerGuesses() {

        return playerGuesses;
    }

    public Map<Character, Letter> getLetterMap() {

        return letterMap;
    }
}
