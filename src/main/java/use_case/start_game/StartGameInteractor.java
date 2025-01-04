package use_case.start_game;

import data_access.ColourFormatDataAccessObject;
import data_access.GameDataAccessObject;
import data_access.VersusDataAccessObject;

import java.util.List;

public class StartGameInteractor implements StartGameInputBoundary {

    private GameDataAccessObject gameDataAccessObject;
    private GameDataAccessObject playerDataAccessObject;
    private VersusDataAccessObject versusDataAccessObject;
    private final StartGameOutputBoundary startGameController;

    public StartGameInteractor(GameDataAccessObject gameDataAccessObject,
                               GameDataAccessObject playerGameDataAccessObject,
                               VersusDataAccessObject versusDataAccessObject,
                               StartGameOutputBoundary startGameController) {

        this.gameDataAccessObject = gameDataAccessObject;
        this.playerDataAccessObject = playerGameDataAccessObject;
        this.versusDataAccessObject = versusDataAccessObject;
        this.startGameController = startGameController;
    }

    private String pickNewWord() {

        ColourFormatDataAccessObject colourFormatDataAccessObject = new ColourFormatDataAccessObject();

        int index = wordIndex(colourFormatDataAccessObject.getNumbers());

        return gameDataAccessObject.getAnswerBank().get(index).getLiteral();
    }

    public int wordIndex(List<List<Integer>> colourCodes) {

        int result = 0;

        for (int i = 0; i < colourCodes.size(); i++) {

            int sublistResult = 0;
            int multiplier = 1;
            for (int j = 0; j < colourCodes.get(i).size(); j++) {

                int index = colourCodes.get(i).get(j);
                sublistResult += index;

                // random prime numbers to increment the mul
                if (index % 2 == 0) {
                    multiplier += 13;
                }

                else if (index % 3 == 0) {

                    multiplier *= 7;
                }
                else {

                    multiplier += 14;
                }
            }

            result += sublistResult * multiplier;
        }

        return result % 2315;
    }

    public void prepareNewGame() {

        System.out.println("Preparing new game...");
        gameDataAccessObject.reset();
        playerDataAccessObject.reset();
        versusDataAccessObject.setAnswer(pickNewWord());
        System.out.println("New game and: " + playerDataAccessObject.getBoardLog());
        System.out.println(versusDataAccessObject.getAnswer());

        StartGameOutputData outputData = new StartGameOutputData(gameDataAccessObject.getBoardLog(),
                playerDataAccessObject.getBoardLog(), playerDataAccessObject.getLetterBoard());

        startGameController.prepareGameView(outputData);
    }
}
