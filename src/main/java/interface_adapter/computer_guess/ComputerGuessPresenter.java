package interface_adapter.computer_guess;

import interface_adapter.ViewManagerModel;
import interface_adapter.end_of_game.EndGameState;
import interface_adapter.end_of_game.EndGameViewModel;
import use_case.computer_guess.ComputerGuessOutputData;
import use_case.computer_guess.ComputerGuessOutputDataBoundary;

/**
 * The Presenter that intakes ComputerGuessOutputData and updates the ViewManger model to change the UI.
 */
public class ComputerGuessPresenter implements ComputerGuessOutputDataBoundary {

    private final ViewManagerModel viewManagerModel;
    private final ComputerGuessViewModel computerGuessViewModel;
    private final EndGameViewModel endGameViewModel;

    public ComputerGuessPresenter(ViewManagerModel viewManagerModel, ComputerGuessViewModel viewGuessModel,
                                  EndGameViewModel endGameViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.computerGuessViewModel = viewGuessModel;
        this.endGameViewModel = endGameViewModel;

    }


    // make this an if/else statement, if outputData.getIsWon is false continue game, or check the turn number
    @Override
    public void preparePostGuessView(ComputerGuessOutputData outputData) {

        // Make a state that holds what should be presented after this use case has fired (the new guess along with the
        // previously set board.
        final ComputerGuessState guessState = computerGuessViewModel.getState();
        guessState.setCurrentDisplayedBoard(outputData.getComputerGuess());
        guessState.setPlayerBoard(outputData.getPlayerGuess());

        // Set the computerGuessViewModel to the previously initialized state
        computerGuessViewModel.setState(guessState);
        computerGuessViewModel.firePropertyChanged();

        // TODO: Verify that this still builds without the commented out lines.
        // Update the View Model Manager with the newly set computerGuessViewModel
        // viewManagerModel.setState(computerGuessViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareComputerWinView(ComputerGuessOutputData outputData) {

        final EndGameState endGameState = endGameViewModel.getState();
        endGameState.setPlayerBoard(outputData.getPlayerGuess());
        endGameState.setComputerBoard(outputData.getComputerGuess());

        endGameViewModel.setState(endGameState);
        endGameViewModel.firePropertyChanged();

        viewManagerModel.setState(endGameViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
