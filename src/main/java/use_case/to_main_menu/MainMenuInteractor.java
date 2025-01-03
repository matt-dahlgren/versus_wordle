package use_case.to_main_menu;

/**
 * The interactor of the (exit to the) main menu use case.
 */
public class MainMenuInteractor implements MainMenuInputBoundary {

    private final MainMenuOutputBoundary mainMenuPresenter;

    public MainMenuInteractor(MainMenuOutputBoundary mainMenuPresenter) {

        this.mainMenuPresenter = mainMenuPresenter;
    }

    /**
     * Call the main menu presenter.
     */
    public void execute() {

        mainMenuPresenter.prepareMainMenuView();
    }
}
