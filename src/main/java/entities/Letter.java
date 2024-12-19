package entities;

import java.util.ArrayList;
import java.util.List;

/**
 * The class that represents a letter in a Word used in a versus_wordgame.
 * The string that this letter represents should not be able to be open for setting once this object is initialized,
 * hence the non-existence of this attribute's setter function.
 */
public class Letter {

    private int status;
    private final String literal;
    private final ArrayList<Integer> lightBlueIndices;
    private final ArrayList<Integer> greyIndices;

    public Letter(String literal, int status) {
        this.literal = literal;
        this.status = status;
        this.lightBlueIndices = new ArrayList<>();
        this.greyIndices = new ArrayList<>();
    }

    /**
     * Getter for the status of a Letter.
     * @return an integer element of [-1,2], which represents the colour a Letter can be.
     */
    public int getStatus() {
        return status;
    }

    /**
     * Set the status of a letter in a versus_wordgame.
     * @param status is an integer that is an element of [-1,2], representing the colour the Letter is being set to.
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Get the string representation that this letter represents.
     * @return a string of the letter this object represents.
     */
    public String getLiteral() {
        return literal;
    }

    /**
     * Get the indices that are known to not hold this letter.
     * @return a list of integers referring to indices of a word.
     */
    public List<Integer> getLightBlueIndices() {
        return lightBlueIndices;
    }

    /**
     * Add an index that we know this letter does not occupy (while assuming this letter is in the word that it is a
     * part of.
     * @param lightBlueIndex is an integer element of [0,4] and does not yet appear in lightBlueIndices.
     */
    public void addLightBlueIndex(int lightBlueIndex) {
        this.lightBlueIndices.add(lightBlueIndex);
    }

    /**
     * Add an index that we know this letter is grey. This is different than light blue since if this letter already
     * appears elsewhere in the word other instances this letter will be grey.
     * @param index is an integer element of [0,4]
     */
    public void addGreyIndex(int index) {
        this.greyIndices.add(index);
    }

    /**
     * Get the list of grey indices.
     * @return a list of integers elements [0,4]
     */
    public List<Integer> getGreyIndices() {
        return greyIndices;
    }
}
