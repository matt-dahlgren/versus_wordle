package interface_adapter.user_guess;

import interface_adapter.ViewManagerModel;
import interface_adapter.end_of_game.EndGameState;
import interface_adapter.end_of_game.EndGameViewModel;
import use_case.user_guess.UserGuessOutputData;
import use_case.user_guess.UserGuessOutputDataBoundary;

public class UserGuessPresenter implements UserGuessOutputDataBoundary {

    private final ViewManagerModel viewManagerModel;
    private final UserGuessViewModel userGuessViewModel;
    private final EndGameViewModel endGameViewModel;

    public UserGuessPresenter(ViewManagerModel viewManagerModel, UserGuessViewModel userGuessViewModel,
                              EndGameViewModel endGameViewModel) {

        this.viewManagerModel = viewManagerModel;
        this.userGuessViewModel = userGuessViewModel;
        this.endGameViewModel = endGameViewModel;
    }

    @Override
    public void preparePostGuessView(UserGuessOutputData outputData) {

        final UserGuessState userState = userGuessViewModel.getState();
        userState.setCurrentDisplayedBoard(outputData.getUserGuesses());
        userState.setComputerDisplayedBoard(outputData.getCpuGuesses());
        userState.setLetterBoard(outputData.getLetterBoard());

        userGuessViewModel.setState(userState);
        userGuessViewModel.firePropertyChanged();

        // TODO: check if this runs with this line of code commented
        // viewManagerModel.setState(userGuessViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareWinView(UserGuessOutputData outputData) {

        final EndGameState endGameState = endGameViewModel.getState();
        endGameState.setComputerBoard(outputData.getCpuGuesses());
        endGameState.setPlayerBoard(outputData.getUserGuesses());

        endGameViewModel.setState(endGameState);
        endGameViewModel.firePropertyChanged();

        viewManagerModel.setState(endGameViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
