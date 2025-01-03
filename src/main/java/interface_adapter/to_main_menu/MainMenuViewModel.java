package interface_adapter.to_main_menu;

import interface_adapter.ViewModel;

public class MainMenuViewModel extends ViewModel<MainMenuState> {

    public MainMenuViewModel() {
        super("main_menu");
        setState(new MainMenuState());
    }
}
