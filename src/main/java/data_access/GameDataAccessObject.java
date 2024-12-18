package data_access;

import entities.Letter;
import entities.Word;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

import static app.ColourConstants.WHITE;

/**
 * This is the Data Object that holds the information about the current board and previous plays a player or computer
 * has played in a versus_wordgame.
 */
public class GameDataAccessObject {

    private final ArrayList<Word> answerBank;
    private final ArrayList<String> guessBank;
    private final Map<Character, Letter> letterBoard;
    private final ArrayList<String> guessedWords;
    private int turn;

    public GameDataAccessObject() {

        // Initializes the turn counter.
        turn = 0;

        // Initializes an empty list corresponding to the guessed words.
        guessedWords = new ArrayList<>();

        // Initializes the alphabet for this player in this game.
        letterBoard = new HashMap<>();

        for (char letter = 'a'; letter <= 'z'; letter++) {
            Letter curr = new Letter(String.valueOf(letter), WHITE);
            letterBoard.put(letter, curr);
        }

        // build the answer bank with words from valid_answers.txt found in src/main/resources
        answerBank = new ArrayList<>();

        try {
            URL answerPath = getClass().getClassLoader().getResource("valid_answers.txt");
            if (answerPath == null) {
                throw new FileNotFoundException();
            }

            File answerFile = new File(answerPath.getFile());
            Scanner answerReader = new Scanner(answerFile);

            while (answerReader.hasNextLine()) {
                // Reads lines of this file until the end and adds new words to our answer bank.
                String line = answerReader.nextLine();
                Word addedWord = new Word(line, letterBoard);
                answerBank.add(addedWord);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found: 'valid_answers.txt'");
        }

        // build the answer bank with words from valid_guesses.txt found in src/main/resources
        guessBank = new ArrayList<>();

        try {
            URL answerPath = getClass().getClassLoader().getResource("valid_guesses.txt");
            if (answerPath == null) {
                throw new FileNotFoundException();
            }

            File answerFile = new File(answerPath.getFile());
            Scanner answerReader = new Scanner(answerFile);

            while (answerReader.hasNextLine()) {
                // Reads lines of this file until the end and adds new words to our answer bank.
                String line = answerReader.nextLine();
                guessBank.add(line);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found: 'valid_answers.txt'");
        }

    }

    /**
     * Increment turn by 1 (indicating a turn has been played).
     */
    public void updateTurn() {
        turn += 1;
    }

    /**
     * Getter for the turn attribute of the GDAO.
     * @return an integer corresponding to how many turns a game has played out to.
     */
    public int getTurn() {
        return turn;
    }

    public List<String> getGuessedWords() {
        return guessedWords;
    }

    /**
     * Update the current alphabet with known changes from an answer.
     * @param letter is a valid letter from the alphabet of an ongoing versus_wordgame.
     * @param status is an integer in the set [-1, 2]
     */
    public void updateBoard(char letter, int status) {
        letterBoard.get(letter).setStatus(status);
    }

    /**
     * Update an answer bank to remove any Words that have a letter that has been guessed and is known to not be in the
     * answer to the game.
     */
    public void updateAnswerBank() {
        answerBank.removeIf(Word::hasGreyLetter);
    }

    /**
     * This is to be used after player guesses this word to remove it from the guess bank.
     * @param guess is a string word that has been proven to already be in the guess bank.
     */
    public void updateGuessBank(String guess) {
        guessBank.remove(guess);
        guessedWords.add(guess);
    }

    /**
     * Check the guessBank to check if this word is a valid guess.
     * @param target is any string, as edgecases will always return false as they will not occur in guessBank.
     * @return True iff target is an element of guessBank.
     */
    public boolean validGuess(String target) {

        for (String word : guessBank) {
            if (Objects.equals(word, target)) {
                return true;
            }
        }
        return false;
    }
}
