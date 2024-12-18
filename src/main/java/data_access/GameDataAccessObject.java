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

    public GameDataAccessObject() {

        letterBoard = new HashMap<>();

        for (char letter = 'a'; letter <= 'z'; letter++) {
            Letter curr = new Letter(String.valueOf(letter), WHITE);
            letterBoard.put(letter, curr);
        }

        answerBank = new ArrayList<>();

        // build the answer bank with words from valid_answers.txt found in src/main/resources
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

        guessBank = new ArrayList<>();

        // build the answer bank with words from valid_guesses.txt found in src/main/resources
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
