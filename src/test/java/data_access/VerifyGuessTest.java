package data_access;

import entities.Word;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class VerifyGuessTest {

    @Test
    public void verifyGuess() {

        VersusDataAccessObject versusDataAccessObject = new VersusDataAccessObject("beech");
        Word guess = new Word("check");

        List<Integer> result = versusDataAccessObject.verifyGuess(guess);
        List<Integer> expected = new ArrayList<>();
        expected.add(-1);
        expected.add(1);
        expected.add(2);
        expected.add(2);
        expected.add(-1);

        assertEquals(expected, result);
    }
}
