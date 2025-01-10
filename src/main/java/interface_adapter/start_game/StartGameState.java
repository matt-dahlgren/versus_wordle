package interface_adapter.start_game;

import entities.Letter;
import entities.Word;

import java.util.List;
import java.util.Map;

/**
 * The information needed to prepare the view of a new game.
 */
public class StartGameState {

    private Map<Integer, Map<Word, List<Integer>>> computerGuesses;
    private Map<Integer, Map<Word, List<Integer>>> playerGuesses;
    private Map<Character, Letter> letters;

    public void setComputerGuesses(Map<Integer, Map<Word, List<Integer>>> computerGuesses) {

        this.computerGuesses = computerGuesses;
    }

    public void setPlayerGuesses(Map<Integer, Map<Word, List<Integer>>> playerGuesses) {

        this.playerGuesses = playerGuesses;
    }

    public void setLetters(Map<Character, Letter> letters) {

        this.letters = letters;
    }

    public Map<Integer, Map<Word, List<Integer>>> getComputerGuesses() {

        return computerGuesses;
    }

    public Map<Integer, Map<Word, List<Integer>>> getPlayerGuesses() {

        return playerGuesses;
    }

    public Map<Character, Letter> getLetters() {

        return letters;
    }
}
