package interface_adapter.start_game;

import interface_adapter.ViewManagerModel;
import use_case.start_game.StartGameOutputBoundary;
import use_case.start_game.StartGameOutputData;

public class StartGamePresenter implements StartGameOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final StartGameViewModel startGameViewModel;

    public StartGamePresenter(ViewManagerModel viewManagerModel, StartGameViewModel startGameViewModel) {

        this.viewManagerModel = viewManagerModel;
        this.startGameViewModel = startGameViewModel;
    }

    @Override
    public void prepareGameView(StartGameOutputData outputData) {

        final StartGameState startGameState = startGameViewModel.getState();
        startGameState.setLetters(outputData.getLetterMap());
        startGameState.setComputerGuesses(outputData.getComputerGuesses());
        startGameState.setPlayerGuesses(outputData.getPlayerGuesses());
        startGameViewModel.setState(startGameState);
        startGameViewModel.firePropertyChanged();

        viewManagerModel.setState(startGameViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
