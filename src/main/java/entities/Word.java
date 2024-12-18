package entities;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static app.ColourConstants.GREY;

/**
 * The class representing a word in a versus_wordgame.
 * A Word consists of a Map of 5 letters representing spelling of that word.
 */
public class Word {

    private int score;
    private final String literal;
    private Map<Integer, Letter> board;

    /**
     * Initializes a Word in a versus_wordgame. Score will always be initialized to zero so that the game can set scores
     * relative to the word answer bank in the use-case interactors.
     * @param word is a string of 5 letters that only contains alphabetical characters.
     * @param currentBoard is a Map that contains the game's alphabet.
     */
    public Word(String word, Map<Character, Letter> currentBoard) {
        this.literal = word;
        this.board = new HashMap<>();

        int index_counter = 0;

        // uses this game's alphabet to construct words. This way when the alphabet is updated, any letters that have
        // been updated will also update in the answer bank Words to make it easier to sort out non-answers.
        for (Character character : word.toCharArray()) {
            board.put(index_counter, currentBoard.get(character));
            index_counter++;
        }
    }

    /**
     * Returns the string that this word represents.
     * @return a string that this object represents.
     */
    public String getLiteral() {
        return literal;
    }

    /**
     * Sets the score of this word. Intended to be set after the word bank is either set or revised. This score will
     * be used to determine what the best choice of a guess is.
     * @param score is an integer corresponding to this word's guessability score.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Gets the score of this word.
     * @return an integer representing the guessability of this word in relation to other words in the answer bank. If
     * this word has bot been scored yet return 0.
     */
    public int getScore() {
        return score;
    }

    /**
     * Verify whether this word contains only unique letters. This should only be used when little to no progress is
     * made after a first guess, as this function across an entire word bank will take time, and is redundant if our
     * first guess unveils relevant enough information.
     * @return true iff this word contains five unique letters.
     */
    public boolean fiveUniqueChars() {
        Map<String, Integer> counter = new HashMap<>();

        for (char letter : literal.toCharArray()) {
            if (counter.containsKey(String.valueOf(letter))) {
                counter.put(String.valueOf(letter), counter.get(String.valueOf(letter)) + 1);
            }

            else {
                counter.put(String.valueOf(letter), 1);
            }
        }

        Collection<Integer> values = counter.values();

        // Verify that at most a letter appears once. Return false if a letter appears more than once in this Word.
        for (Integer value : values) {
            if (value > 1) {
                return false;
            }
        }

        // If for loop is terminated without returning early then this word contains five unique letters, return True.
        return true;
    }

    public boolean hasGreyLetter() {
        // This loop verifies that no letter in this word has a GREY letter, if so exit the function early and return
        // true.
        for (Letter letter : board.values()) {
            if (letter.getStatus() == GREY) {
                return true;
            }
        }
        // If for loop is terminated without an early return we know that this word contains no GREY letters.
        return false;
    }
}
