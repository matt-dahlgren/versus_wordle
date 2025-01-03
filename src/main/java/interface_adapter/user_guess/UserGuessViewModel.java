package interface_adapter.user_guess;

import interface_adapter.ViewModel;
import interface_adapter.computer_guess.ComputerGuessState;

public class UserGuessViewModel extends ViewModel<UserGuessState> {

    public UserGuessViewModel() {
        super("GuessView");
        setState(new UserGuessState());
    }
}
