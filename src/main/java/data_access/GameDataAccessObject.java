package data_access;

import entities.Letter;
import entities.Word;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

import static app.ColourConstants.*;

/**
 * This is the Data Object that holds the information about the current board and previous plays a player or computer
 * has played in a versus_wordgame.
 */
public class GameDataAccessObject {

    private final ArrayList<Word> answerBank;
    private final ArrayList<Word> guessBank;
    private final Map<Character, Letter> letterBoard;
    private final ArrayList<Word> guessedWords;
    private int turn;
    private final Map<Integer, Map<Word,List<Integer>>> boardLog;
    private boolean gameOn;

    public GameDataAccessObject() {

        // Games are initialized as not won.
        this.gameOn = false;

        // Initializes the board log.
        this.boardLog = new HashMap<>();

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
                Word newWord = new Word(line, letterBoard);
                guessBank.add(newWord);
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

    public List<Word> getGuessedWords() {
        return guessedWords;
    }

    /**
     * Update the current alphabet with known changes from an answer.
     * @param letter is a valid letter from the alphabet of an ongoing versus_wordgame.
     * @param status is an integer in the set [-1, 2]
     */
    public void updateBoard(char letter, int status, int index) {

        // if the letter's status is BLUE or if the status is equal to the status we are setting it to leave function.
        if (letterBoard.get(letter).getStatus() == BLUE || letterBoard.get(letter).getStatus() == status) {
            return;
        }

        if (status == LIGHTBLUE) {
            letterBoard.get(letter).addLightBlueIndex(index);
        }

        if (status == GREY) {
            letterBoard.get(letter).addGreyIndex(index);
        }
        letterBoard.get(letter).setStatus(status);
    }

    /**
     * Getter for the answerBank attribute of the Word class.
     * @return answerBank.
     */
    public List<Word> getAnswerBank() {
        return answerBank;
    }

    /**
     * Update an answer bank to remove any Words that have a letter that has been guessed and is known to not be in the
     * answer to the game.
     */
    public void updateAnswerBank(List<Integer> updatedBoard, Word guess) {

        // remove all answers remaining that have grey letters.
        answerBank.removeIf(Word::hasGreyLetter);

        List<Word> copyOfBank = new ArrayList<>(answerBank);
        for (Word word : copyOfBank) {
            for (int i = 0; i < 5; i++) {

                // If this letter is Grey or LightBlue at this index or if this index is found to be Blue through a
                // guess where the letter at the index of this Blue letter of this word is not equal to the letter of
                // the guess at this index remove this word from the answer bank.
                if (word.getBoard().get(i).getGreyIndices().contains(i) ||
                        word.getBoard().get(i).getLightBlueIndices().contains(i) ||
                        updatedBoard.get(i) == BLUE && !guess.getLiterals().get(i).equals(word.getLiterals().get(i))) {
                    answerBank.remove(word);
                }
            }
        }

        answerBank.remove(guess);
    }

    /**
     * This is to be used after player guesses this word to remove it from the guess bank.
     * @param guess is a word that has been proven to already be in the guess bank.
     */
    public void updateGuessBank(Word guess) {

        guessBank.remove(guess);
        guessedWords.add(guess);
    }

    /**
     * Check the guessBank to check if this word is a valid guess.
     * @param target is any string, as edgecases will always return false as they will not occur in guessBank.
     * @return True iff target is an element of guessBank.
     */
    public boolean validGuess(String target) {

        for (Word word : guessBank) {
            if (Objects.equals(word.getLiteral(), target)) {
                return true;
            }
        }
        return false;
    }

    /**
     * After a turn is played update the board log, this is used to update the view and keep track of previous board
     * states throughout the game.
     * @param guess is a Word and has not been guessed yet.
     * @param boardColours is a List of numbers element of {-1, 1, 2}.
     */
    public void updateBoardLog(Word guess, List<Integer> boardColours) {

        Map<Word, List<Integer>> result = new HashMap<>();
        result.put(guess, boardColours);
        boardLog.put(turn, result);
    }

    /**
     * The getter for the boardLog instance attribute.
     * @return A Map that holds turn numbers to their respective guess and board format.
     */
    public Map<Integer,Map<Word, List<Integer>>> getBoardLog() {

        return boardLog;
    }

    /**
     * Change the status of this game to Won.
     */
    public void gameWon() {

        gameOn = true;
    }

    /**
     * Get game status.
     * @return True iff this game has been won.
     */
    public boolean isGameOn() {
        return gameOn;
    }

    /**
     * For testing purposes to find out what is left of the answer bank.
     * @return a list of strings representing the words left in the answer bank.
     */
    public List<String> getAnswerLiterals() {

        List<String> result = new ArrayList<>();

        for (Word word : answerBank) {
            result.add(word.getLiteral());
        }

        return result;
    }

    public Map<Character, Letter> getLetterBoard() {

        return letterBoard;
    }

    public List<Word> getGuessBank() {
        return guessBank;
    }
}
