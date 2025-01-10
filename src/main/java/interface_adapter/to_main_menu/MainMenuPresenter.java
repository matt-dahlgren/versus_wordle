package interface_adapter.to_main_menu;

import interface_adapter.ViewManagerModel;
import use_case.to_main_menu.MainMenuOutputBoundary;

/**
 * Presenter of the (go back to the) main menu use case, tells the view manager model what to display.
 */
public class MainMenuPresenter implements MainMenuOutputBoundary {

    private final MainMenuViewModel mainMenuViewModel;
    private final ViewManagerModel viewManagerModel;

    public MainMenuPresenter(MainMenuViewModel mainMenuViewModel, ViewManagerModel viewManagerModel) {

        this.mainMenuViewModel = mainMenuViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareMainMenuView() {

        final MainMenuState mainMenuState = mainMenuViewModel.getState();
        mainMenuViewModel.setState(mainMenuState);
        mainMenuViewModel.firePropertyChanged();

        viewManagerModel.setState(mainMenuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }
}
