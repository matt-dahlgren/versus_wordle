package interface_adapter.user_guess;

import interface_adapter.ViewManagerModel;
import use_case.user_guess.UserGuessOutputData;
import use_case.user_guess.UserGuessOutputDataBoundary;

public class UserGuessPresenter implements UserGuessOutputDataBoundary {

    private final ViewManagerModel viewManagerModel;
    private final UserGuessViewModel userGuessViewModel;

    public UserGuessPresenter(ViewManagerModel viewManagerModel, UserGuessViewModel userGuessViewModel) {

        this.viewManagerModel = viewManagerModel;
        this.userGuessViewModel = userGuessViewModel;
    }

    @Override
    public void preparePostGuessView(UserGuessOutputData outputData) {

        final UserGuessState userState = userGuessViewModel.getState();
        userState.setCurrentDisplayedBoard(outputData.getUserGuesses());

        userGuessViewModel.setState(userState);
        userGuessViewModel.firePropertyChanged();

        // TODO: check if this runs with this line of code commented
        // viewManagerModel.setState(userGuessViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
