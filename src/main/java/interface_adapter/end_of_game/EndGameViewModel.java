package interface_adapter.end_of_game;

import interface_adapter.ViewModel;

public class EndGameViewModel extends ViewModel<EndGameState> {

    public EndGameViewModel() {
        super("EndOfGameView");
        setState(new EndGameState());
    }
}
