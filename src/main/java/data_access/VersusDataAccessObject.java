package data_access;

import entities.Word;

import java.util.ArrayList;
import java.util.List;

import static app.ColourConstants.*;

/**
 * This class holds the answer to a game and the individual DataAccessObjects of each player
 */
public class VersusDataAccessObject {

    private final Word answer;

    public VersusDataAccessObject(String answer) {
        this.answer = new Word(answer);
    }

    public List<Integer> verifyGuess(Word word) {

        List<String> wordLetters = word.getLiterals();
        List<String> answerLetters = answer.getLiterals();

        // Breaking into cases, this one is if the answer and guess are composed of only unique letters or if the answer
        // is only composed of unique letters and the guess contains none of those repeating letters.
        // the last or is added since if an answer has multiple of the same letter, but the guess has fewer occurrences
        // of that letter than the answer then there is no possibility that all letters of that typ
        if ((answer.fiveUniqueChars() && (word.fiveUniqueChars() || multipleNotInGuess(wordLetters))) ||
                letterCountCompared(word)) {
            return fiveUniqueCheck(answerLetters, wordLetters);
        }
        //
        else {
            return nonUniqueAnswerOrGuess(answerLetters, wordLetters, word);
        }

    }

    /**
     * Handles the case of the answer having only unique letters or the guess does not share the letter that appears
     * multiple times in the answer.
     * @param answerLetters a list of strings of letters, of length 5.
     * @param wordLetters a list of strings of letters, of length 5.
     * @return A list of integers elements of {-1, 1, 2}.
     */
    private List<Integer> fiveUniqueCheck(List<String> answerLetters, List<String> wordLetters) {

        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < answerLetters.size(); i++) {
            String letter = wordLetters.get(i);
            if (answerLetters.contains(letter) && answerLetters.get(i).equals(letter)) {
                result.add(BLUE);
            }
            else if (answerLetters.contains(letter) && !answerLetters.get(i).equals(letter)) {
                result.add(LIGHTBLUE);
            }
            else {
                result.add(GREY);
            }
        }

        return result;
    }

    /**
     * Verifies if a guess contains no letters that occur multiple times in the answer.
     * @param wordLetters is a list of letters.
     * @return True iff wordLetters contains no letters that occurs more than once in answer.
     */
    private boolean multipleNotInGuess(List<String> wordLetters) {

        for (String letter : wordLetters) {
            if (answer.getMultiples().contains(letter)) {
                return false;
            }
        }

        return true;
    }

    /**
     * This function determines whether in an answer the guess has no similar letter that appears more in the guess than
     * in the answer.
     * @param guess is any string.
     * @return True iff for each  letter in answer, guess has fewer or equal occurrences of that letter.
     */
    private boolean letterCountCompared(Word guess) {

        for (String letter : answer.getLiterals()) {
            if (answer.getOccurences(letter) < guess.getOccurences(letter)) {
                return false;
            }
        }

        return true;
    }

    /**
     * If a guess has more occurrences of any letter present in the asnwer, make sure that this method helps the verify
     * guess method and does not show a letter is light-blue if that letter is blue where it should be already.
     * @param answerLetters is a 5 length list containing only lower case letters.
     * @param wordLetters is a 5 length list containing only lower case letters.
     * @return a list of integers elements of {-1, 1, 2} who represent the new board after this guess.
     */
    private List<Integer> nonUniqueAnswerOrGuess(List<String> answerLetters, List<String> wordLetters, Word word) {

        ArrayList<Integer> result = new ArrayList<>();
        int counter = 0;
        for (String letter : wordLetters) {
            // Verifies if letter is in word AND if it is a multiple occurence in our guess, this if only passes if all
            // occurrences of letter in answer appears in the same indices as in guess
            if (word.getMultiples().contains(letter) && allAnswerIndicesCorrect(letter, wordLetters)
                    && answerLetters.contains(letter)) {
                // In this case either the letter is in the right place or it's an extra in the guess and should be seen
                // as a wrong guess.
                if (answer.getLetterIndexes(letter).contains(counter)) {
                    result.add(BLUE);
                }

                else {
                    result.add(GREY);
                }
            }

            // Check if the letter is present in word but not in the right index.
            else if (answerLetters.contains(letter) && !answerLetters.get(counter).equals(letter)) {
                result.add(LIGHTBLUE);
            }

            // Check if this letter is equal to the index counter in answerLetters.
            else if (answerLetters.get(counter).equals(letter)) {
                result.add(BLUE);
            }

            // This letter is not in our answer.
            else {
                result.add(GREY);
            }

            counter++;
        }

        return result;
    }

    /**
     * Check if all the indices where a letter (target) appears in answer, they also appear in the guess at those
     * indices.
     * @param target is a string of a single letter.
     * @param wordLetters is a list of strings (letters) of length five.
     * @return True iff all occurrences of target appear in the same indices of wordLetters as they do in answer.
     */
    private boolean allAnswerIndicesCorrect(String target, List<String> wordLetters) {

        List<Integer> targets = answer.getLetterIndexes(target);

        for (Integer index : targets) {
            if (!wordLetters.get(index).equals(target)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Check if the game has been won.
     * @param guess is a valid Word.
     * @return True iff guess represents the same word as answer.
     */
    public boolean verifyGameWon(Word guess) {

        return guess.getLiteral().equals(answer.getLiteral());
    }
}
