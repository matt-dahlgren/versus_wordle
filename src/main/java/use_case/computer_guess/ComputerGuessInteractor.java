package use_case.computer_guess;

import data_access.GameDataAccessObject;
import data_access.VersusDataAccessObject;
import entities.Word;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The interactor for the computer to make a guess, this interactor will look at the available words in the
 */
public class ComputerGuessInteractor implements ComputerGuessInputDataBoundary {

    private final GameDataAccessObject gameDataAccessObject;
    private final VersusDataAccessObject versusDataAccessObject;
    private final ComputerGuessOutputDataBoundary computerGuessOutputDataBoundary;

    public ComputerGuessInteractor(GameDataAccessObject gameDataAccessObject,
                                   VersusDataAccessObject versusDataAccessObject,
                                   ComputerGuessOutputDataBoundary computerGuessOutputDataBoundary) {

        this.gameDataAccessObject = gameDataAccessObject;
        this.versusDataAccessObject = versusDataAccessObject;
        this.computerGuessOutputDataBoundary = computerGuessOutputDataBoundary;

    }

    /**
     * Score the answer bank of the gameDataAccessObject.
     */
    private void scoreAnswerBank() {

        // Build the Frequency Maps of bigrams and letters to be used for the scoring system
        Map<String, Integer> letterFrequency = charFrequency();
        Map<String, Integer> bigramFrequency = bigramFrequency();

        for (Word word : gameDataAccessObject.getAnswerBank()) {
            int score = 0;
            for (String letter : word.getUniqueLetters()) {
                score += letterFrequency.get(letter);
            }

            for (String bigram : word.getUniqueBigrams()) {

                // Later in the game bigram frequency should be wighed to be more imporant.
                if (gameDataAccessObject.getTurn() > 2) {
                    score += (int) Math.floor(bigramFrequency.get(bigram) * 1.5);
                }
                else {
                    score += bigramFrequency.get(bigram);
                }
            }
            word.setScore(score);
        }
    }

    /**
     * Collect the frequency that a Letter appears in all the word's in the gameDataAccessObject's answer bank.
     * @return a map that takes a letter to its frequency in the answerbank of the GameDataAccessObject.
     */
    private Map<String, Integer> charFrequency() {

        Map<String, Integer> result = new HashMap<>();
        for (Word word : gameDataAccessObject.getAnswerBank()) {
            for (String letter: word.getUniqueLetters()) {
                if (!result.containsKey(letter)) {
                    result.put(letter, 1);
                }
                else {
                    result.put(letter, result.get(letter) + 1);
                }
            }
        }

        return result;
    }


    /**
     * Collect the frequency that a bigram appears in all the word's in the gameDataAccessObject's answer bank.
     * @return a map that takes bigrams to their frequencies from a list of Words.
     */
    private Map<String, Integer> bigramFrequency() {

        Map<String, Integer> result = new HashMap<>();
        for (Word word : gameDataAccessObject.getAnswerBank()) {
            for (String bigram: word.getUniqueBigrams()) {
                if (!result.containsKey(bigram)) {
                    result.put(bigram, 1);
                }
                else {
                    result.put(bigram, result.get(bigram) + 1);
                }
            }
        }

        return result;
    }

    /**
     * Find the highest score word in the GameDataAccessObject's answer bank.
     * @return a Word whose score is higher than (almost) every other Word in a list of Words.
     */
    private Word getBestWord() {

        Word result = null;
        for (Word word : gameDataAccessObject.getAnswerBank()) {
            if (result == null || word.getScore() > result.getScore()) {
                result = word;
            }
        }

        return result;
    }

    /**
     * Gets the word that has the highest score while having 5 unique letters, if no such word exists then this function
     * returns null.
     * @return the Word in a list of words with the highest score that also has five unique letters, if there are no
     * such words return null.
     */
    private Word getBestUniqueLetterWord() {

        Word result = null;
        for (Word word : gameDataAccessObject.getAnswerBank()) {

            if (!(word.fiveUniqueChars())) {
                continue;
            }

            // this is only reached if this word has only unique letters, this is why the first or predicate is checking
            // if result is null.
            else if (result == null || word.getScore() > result.getScore()) {
                result = word;
            }
        }

        return result;
    }

    /**
     * Makes a guess.
     * @return a Word to guess.
     */
    private Word getGuess() {

        Word result = null;

        if (gameDataAccessObject.getAnswerBank().size() == 1) {
            return gameDataAccessObject.getAnswerBank().getFirst();
        }

        if (gameDataAccessObject.getTurn() <= 2) {
            result = getBestUniqueLetterWord();

            // very unlikely edge-case that the first turn somehow removes all unique lettered words
            if (result == null) {
                result = getBestWord();
            }
            return result;
        }

        result = getBestWord();

        return result;
    }

    @Override
    public void execute() {

        // Update the turn
        gameDataAccessObject.updateTurn();

        // Score the answer bank and make a guess
        scoreAnswerBank();
        Word result = getGuess();

        // Verify that guess with the answer of the game to return a list of statuses
        List<Integer> guessBoard = versusDataAccessObject.verifyGuess(result);

        // Update the current game board.
        for (int i = 0; i < guessBoard.size(); i++) {

            gameDataAccessObject.updateBoard(result.getLiteral().charAt(i), guessBoard.get(i), i);
        }

        // Update possible answers to this game.
        gameDataAccessObject.updateAnswerBank(guessBoard, result);
    }
}
