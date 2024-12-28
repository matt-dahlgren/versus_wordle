package data_access;

import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ColourFormatGrabTest {

    @Test
    public void testGrabColour() {

        ColourFormatDataAccessObject colourFormatDataAccessObject = new ColourFormatDataAccessObject();
        List<List<Integer>> result  = colourFormatDataAccessObject.getNumbers();
        System.out.println(result);

        assertNotNull(result);
    }
}
