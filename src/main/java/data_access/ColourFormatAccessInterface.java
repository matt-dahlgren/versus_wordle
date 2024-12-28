package data_access;

import java.util.ArrayList;
import java.util.List;

public interface ColourFormatAccessInterface {

    /**
     * Pulls a request from the colormind.io API for a random colour palette.
     * @return A list of lists of integers (3 long each).
     */
    List<List<Integer>> getNumbers();
}
