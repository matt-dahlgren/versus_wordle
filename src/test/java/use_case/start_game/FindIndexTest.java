package use_case.start_game;

import data_access.ColourFormatAccessInterface;
import data_access.ColourFormatDataAccessObject;
import data_access.GameDataAccessObject;
import data_access.VersusDataAccessObject;
import interface_adapter.start_game.StartGamePresenter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FindIndexTest {

    @Test
    public void testFindIndex() {

        GameDataAccessObject gameDataAccessObject = new GameDataAccessObject();
        VersusDataAccessObject versusDataAccessObject = new VersusDataAccessObject("hello");
        StartGameOutputBoundary startGamePresenter = new StartGamePresenter();
        StartGameInteractor gameInteractor =
                new StartGameInteractor(gameDataAccessObject,versusDataAccessObject,startGamePresenter);
        ColourFormatAccessInterface colourFormatDataAccessObject = new ColourFormatDataAccessObject();

        List<List<Integer>> exampleData = colourFormatDataAccessObject.getNumbers();
        int result = gameInteractor.wordIndex(exampleData);

        assertTrue(0 <= result && result < 2315);
    }
}
