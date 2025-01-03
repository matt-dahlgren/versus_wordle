package use_case.start_game;

import data_access.ColourFormatAccessInterface;
import data_access.ColourFormatDataAccessObject;
import data_access.GameDataAccessObject;
import data_access.VersusDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.start_game.StartGamePresenter;
import interface_adapter.start_game.StartGameViewModel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FindIndexTest {

    @Test
    public void testFindIndex() {

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        StartGameViewModel startGameViewModel = new StartGameViewModel();
        GameDataAccessObject gameDataAccessObject = new GameDataAccessObject();
        GameDataAccessObject playerDataAccessObject = new GameDataAccessObject();
        VersusDataAccessObject versusDataAccessObject = new VersusDataAccessObject("hello");
        StartGameOutputBoundary startGamePresenter = new StartGamePresenter(viewManagerModel, startGameViewModel);
        StartGameInteractor gameInteractor =
                new StartGameInteractor(gameDataAccessObject, playerDataAccessObject, versusDataAccessObject,
                        startGamePresenter);
        ColourFormatAccessInterface colourFormatDataAccessObject = new ColourFormatDataAccessObject();

        List<List<Integer>> exampleData = colourFormatDataAccessObject.getNumbers();
        int result = gameInteractor.wordIndex(exampleData);

        assertTrue(0 <= result && result < 2315);
    }
}
