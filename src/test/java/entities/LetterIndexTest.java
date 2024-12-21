package entities;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class LetterIndexTest {

    @Test
    public void checkIndexReturnLength() {

        Word test = new Word("check");
        List<Integer> result = test.getLetterIndexes("c");
        System.out.println(result);

        assertEquals(2, result.size());
    }
}
