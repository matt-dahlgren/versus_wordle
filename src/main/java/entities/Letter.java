package entities;

/**
 * The class that represents a letter in a Word used in a versus_wordgame.
 * The string that this letter represents should not be able to be open for setting once this object is initialized,
 * hence the non-existence of this attribute's setter function.
 */
public class Letter {

    private int status;
    private final String literal;

    public Letter(String literal, int status) {
        this.literal = literal;
        this.status = status;
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
}
