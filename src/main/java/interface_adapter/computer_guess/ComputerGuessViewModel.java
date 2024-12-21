package interface_adapter.computer_guess;

import interface_adapter.ViewModel;

public class ComputerGuessViewModel extends ViewModel<ComputerGuessState> {

    public ComputerGuessViewModel() {
        super("computer_guess");
        setState(new ComputerGuessState());
    }
}
