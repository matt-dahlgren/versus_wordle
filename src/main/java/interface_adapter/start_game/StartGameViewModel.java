package interface_adapter.start_game;

import interface_adapter.ViewModel;
import interface_adapter.to_main_menu.MainMenuState;

public class StartGameViewModel extends ViewModel<StartGameState> {

    public StartGameViewModel() {
        super("StartPlayView");
        setState(new StartGameState());
    }
}
