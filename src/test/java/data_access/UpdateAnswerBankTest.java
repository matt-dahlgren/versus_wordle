package data_access;

import entities.Word;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

public class UpdateAnswerBankTest {

    @Test
    public void testUpdateAnswerBank() {

        GameDataAccessObject gameDataAccessObject = new GameDataAccessObject();
        Word pizza = new Word("pizza");
        VersusDataAccessObject versusDataAccessObject = new VersusDataAccessObject("pizza");

        int beforeSize = gameDataAccessObject.getAnswerBank().size();
        System.out.println(beforeSize);

        Word guess = new Word("guess");

        List<Integer> boardUpdate = versusDataAccessObject.verifyGuess(guess);


        for (int i = 0; i < boardUpdate.size(); i++) {

            gameDataAccessObject.updateBoard(guess.getLiteral().charAt(i), boardUpdate.get(i), i);
        }

        gameDataAccessObject.updateAnswerBank(boardUpdate,guess);

        int afterSize = gameDataAccessObject.getAnswerBank().size();
        System.out.println(afterSize);

        boolean containsPizza = false;

        for (Word word : gameDataAccessObject.getAnswerBank()) {
            if (word.getLiteral().equals(pizza.getLiteral())) {
                containsPizza = true;
                break;
            }
        }

        System.out.println("Still Contains Pizza: " + containsPizza);

        assertTrue(afterSize < beforeSize);
    }

}
