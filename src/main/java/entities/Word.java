package entities;

import java.util.Map;

/**
 * The class representing a word in a versus_wordgame.
 * A Word consists of a Map of 5 letters representing spelling of that word.
 */
public class Word {

    private int score;
    private String word;
    private Map<Integer, Letter> board;

    /**
     * Initializes a Word in a versus_wordgame. Score will always be initialized to zero so that the game can set scores
     * relative to the word answer bank in the use-case interactors.
     * @param word
     */
    public Word(String word) {
        this.word = word;
    }
}
