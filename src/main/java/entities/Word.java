package entities;

import java.util.*;

import static app.ColourConstants.GREY;
import static app.ColourConstants.WHITE;

/**
 * The class representing a word in a versus_wordgame.
 * A Word consists of a Map of 5 letters representing spelling of that word.
 */
public class Word {

    private int score;
    private final String literal;
    private final Map<Integer, Letter> board;

    /**
     * Initializes a Word in a versus_wordgame. Score will always be initialized to zero so that the game can set scores
     * relative to the word answer bank in the use-case interactors.
     * @param word is a string of 5 letters that only contains alphabetical characters.
     * @param currentBoard is a Map that contains the game's alphabet.
     */
    public Word(String word, Map<Character, Letter> currentBoard) {
        this.literal = word;
        this.board = new HashMap<>();

        int indexCounter = 0;

        // uses this game's alphabet to construct words. This way when the alphabet is updated, any letters that have
        // been updated will also update in the answer bank Words to make it easier to sort out non-answers.
        for (Character character : word.toCharArray()) {
            board.put(indexCounter, currentBoard.get(character));
            indexCounter++;
        }
    }

    /**
     * Initializes a Word ONLY TO BE USED not in a game setting, this initialization should only be used if
     * @param word is a five-letter string.
     */
    public Word(String word) {
        this.literal = word;
        this.board = new HashMap<>();

        int indexCounter = 0;
        for (Character character : word.toCharArray()) {
            board.put(indexCounter, new Letter(String.valueOf(character), WHITE));
            indexCounter++;
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

    /**
     * Makes a list of each unique letter of this word, this will be used to help score letters for building the
     * guessability score of a word.
     * @return a list that contains each unique letter of this word.
     */
    public List<String> getUniqueLetters() {
        List<String> letters = new ArrayList<>();
        for (Letter letter : board.values()) {
            if (!(letters.contains(letter.getLiteral()))) {
                letters.add(letter.getLiteral());
            }
        }
        return letters;
    }


    /**
     * Makes a list of each unique bigram in this Word, this will be used to help score letters for building the
     * guessability score of a word.
     * @return a list of bigrams.
     */
    public List<String> getUniqueBigrams() {
        List<String> bigrams = new ArrayList<>();
        for (int i = 0; i <= 3; i++) {
            if (!(bigrams.contains(literal.substring(i, i + 1)))){
                bigrams.add(literal.substring(i, i + 1));
            }
        }
        return bigrams;
    }

    /**
     * Get the Board so that this word can be compared with the answer.
     * @return the board of this word.
     */
    public Map<Integer, Letter> getBoard() {
        return board;
    }

    /**
     * Get the list of letters in strings that this word represents.
     * @return a list of Strings of legnth one each representing a Letter in order it appears in this word.
     */
    public List<String> getLiterals() {
        List<String> literals = new ArrayList<>();
        for (Letter letter : board.values()) {
            literals.add(letter.getLiteral());
        }
        return literals;
    }

    /**
     * Get the letters that appear multiple times in this Word.
     * @return a list of single length strings that appear multiple times in this word literal.
     */
    public List<String> getMultiples() {

        Map<String, Integer> counter = new HashMap<>();
        List<String> multiples = new ArrayList<>();

        for (String letter : getLiterals()) {
            if (counter.containsKey(letter)) {
                counter.put(letter, counter.get(letter) + 1);
            }
            else {
                counter.put(letter, 1);
            }
        }

        for (Map.Entry<String, Integer> entry : counter.entrySet()) {
            if (entry.getValue() > 1) {
                multiples.add(entry.getKey());
            }
        }

        return multiples;
    }

    /**
     * Get the occurences of a string in this word.
     * @param target is any string.
     * @return the number of times target appears in this word.
     */
    public int getOccurences(String target) {

        int counter = 0;
        for (String letter: getLiterals()) {
            if (letter.equals(target)) {
                counter++;
            }
        }

        return counter;
    }

    /**
     * Get the indexes this letter appears in this word.
     * @param target is a single length string that is a lower case letter.
     * @return a list of indexes target appears in the literal of word.
     */
    public List<Integer> getLetterIndexes(String target) {

        List<Integer> indexes = new ArrayList<>();
        int counter = 0;
        for (String letter: getLiterals()) {
            if (letter.equals(target)) {
                indexes.add(counter);
                counter += 1;
            }
        }

        return indexes;
    }
}
