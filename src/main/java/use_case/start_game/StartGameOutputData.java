package use_case.start_game;

import data_access.GameDataAccessObject;
import data_access.VersusDataAccessObject;

/**
 * Output Data of the start game use case.
 */
public class StartGameOutputData {

    private final GameDataAccessObject gameDataAccessObject;
    private final VersusDataAccessObject versusDataAccessObject;

    public StartGameOutputData(GameDataAccessObject gameDataAccessObject,
                               VersusDataAccessObject versusDataAccessObject) {

        this.gameDataAccessObject = gameDataAccessObject;
        this.versusDataAccessObject = versusDataAccessObject;
    }

    public GameDataAccessObject getGameDataAccessObject() {

        return gameDataAccessObject;
    }

    public VersusDataAccessObject getVersusDataAccessObject() {

        return versusDataAccessObject;
    }
}
