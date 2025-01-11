package use_case.computer_guess;

import data_access.GameDataAccessObject;
import data_access.VersusDataAccessObject;
import entities.Word;
import interface_adapter.ViewManagerModel;
import interface_adapter.computer_guess.ComputerGuessPresenter;
import interface_adapter.computer_guess.ComputerGuessViewModel;
import interface_adapter.end_of_game.EndGameViewModel;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

public class ComputerGuessInteractorAnswerTest {

    @Test
    public void computerGuess() {

        ViewManagerModel vm = new ViewManagerModel();
        ComputerGuessViewModel vmModel = new ComputerGuessViewModel();
        EndGameViewModel vmModel2 = new EndGameViewModel();
        GameDataAccessObject gameDataAccessObject = new GameDataAccessObject();
        GameDataAccessObject playerData = new GameDataAccessObject();
        Word pizza = new Word("round");
        VersusDataAccessObject versusDataAccessObject = new VersusDataAccessObject("wooer");
        ComputerGuessOutputDataBoundary outputDataBoundary = new ComputerGuessPresenter(vm, vmModel, vmModel2);
        ComputerGuessInteractor testGuess = new ComputerGuessInteractor(gameDataAccessObject, playerData,
                versusDataAccessObject, outputDataBoundary);

        // mimic the interactor of the computerGuess
        while (gameDataAccessObject.getTurn() < 7 && !gameDataAccessObject.isGameOn()) {

            gameDataAccessObject.updateTurn();

            testGuess.scoreWordbank(gameDataAccessObject.getGuessBank());
            testGuess.scoreWordbank(gameDataAccessObject.getAnswerBank());

            Word guess;

            if (gameDataAccessObject.getTurn() == 1) {

                guess = new Word("round");
            }

            else {

                guess = testGuess.getGuess();

            }

            System.out.println(guess.getLiteral());

            List<Integer> guessBoard = versusDataAccessObject.verifyGuess(guess);

            System.out.println(guessBoard);

            for (int i = 0; i < guessBoard.size(); i++) {

                gameDataAccessObject.updateBoard(guess.getLiteral().charAt(i), guessBoard.get(i), i);
            }

            // Update possible answers to this game.
            gameDataAccessObject.updateAnswerBank(guessBoard, guess);

            boolean containsPizza = false;

            for (Word word : gameDataAccessObject.getAnswerBank()) {
                if (word.getLiteral().equals(pizza.getLiteral())) {
                    containsPizza = true;
                    break;
                }
            }

            int answerBankLength = gameDataAccessObject.getAnswerBank().size();

            System.out.println(gameDataAccessObject.getAnswerLiterals());
            System.out.println("Is 'Beech' still in answerbank? " + containsPizza);
            System.out.println("Bank Size is: " + answerBankLength);


            if (guess.getLiteral().equals(pizza.getLiteral())) {
                gameDataAccessObject.gameWon();
            }

        }

        assertTrue(gameDataAccessObject.isGameOn());
    }
}
