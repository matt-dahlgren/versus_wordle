package use_case.user_guess;

import data_access.GameDataAccessObject;
import data_access.VersusDataAccessObject;
import entities.Word;

import java.util.List;

/**
 * The Interactor for the user Guess use case.
 */
public class UserGuessInteractor implements UserGuessInputDataBoundary{

    private final GameDataAccessObject gameDataAccessObject;
    private final VersusDataAccessObject versusDataAccessObject;
    private final UserGuessOutputDataBoundary userGuessPresenter;

    public UserGuessInteractor(GameDataAccessObject gameDataAccessObject,
                               VersusDataAccessObject versusDataAccessObject,
                               UserGuessOutputDataBoundary userGuessPresenter) {

        this.gameDataAccessObject = gameDataAccessObject;
        this.versusDataAccessObject = versusDataAccessObject;
        this.userGuessPresenter = userGuessPresenter;
    }

    @Override
    public void execute(UserGuessInputData inputData) {

        Word guess = new Word(inputData.getGuess());

        // verify that the word that the user input was a valid guess, if yes then continue to preparing outputData to
        // send to the presenter, otherwise stop the flow of this function and
        try {

            if (guess.getLiterals().size() != 5) {

                throw new NonValidGuessException("User's guess is not 5 letters long");
            }

            boolean result = false;

            for (Word possibleGuess : gameDataAccessObject.getGuessBank()) {

                if (possibleGuess.getLiteral().equals(guess.getLiteral())) {
                    result = true;
                    break;
                }
            }

            if (!result) {

                throw new NonValidGuessException("User's guess is not in the Guess Bank!");
            }

            else {

                // update turn, remove the guessed word from the answer bank, and add it to the guessed word bank.
                gameDataAccessObject.updateTurn();
                gameDataAccessObject.updateGuessBank(guess);

                // get the newest layer of the user's board.
                List<Integer> newBoardLayer = versusDataAccessObject.verifyGuess(guess);

                // add this turn's results to the memory of this game to later update the user's view.
                gameDataAccessObject.updateAnswerBank(newBoardLayer, guess);

                if (versusDataAccessObject.verifyGameWon(guess)) {

                    gameDataAccessObject.gameWon();
                }

                UserGuessOutputData outputData = new UserGuessOutputData(gameDataAccessObject.getBoardLog(),
                        gameDataAccessObject.isGameOn(), gameDataAccessObject.getTurn());

                if (gameDataAccessObject.isGameOn()) {

                    userGuessPresenter.prepareWinView(outputData);
                }

                else if (gameDataAccessObject.getTurn() < 6) {

                    userGuessPresenter.preparePostGuessView(outputData);
                }

                else {

                    userGuessPresenter.prepareLoseView(outputData);
                }
            }
        }

        catch (NonValidGuessException e) {

            // the user's guess is not a valid guess, so we reset the view of the player without altering anything,
            // including turn number, board or any banks.
            UserGuessOutputData outputData = new UserGuessOutputData(gameDataAccessObject.getBoardLog(),
                    gameDataAccessObject.isGameOn(), gameDataAccessObject.getTurn());
            userGuessPresenter.preparePostGuessView(outputData);
        }
    }
}
