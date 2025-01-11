package use_case.computer_guess;

import data_access.GameDataAccessObject;
import data_access.VersusDataAccessObject;
import entities.Word;
import interface_adapter.ViewManagerModel;
import interface_adapter.computer_guess.ComputerGuessPresenter;
import interface_adapter.computer_guess.ComputerGuessViewModel;
import interface_adapter.end_of_game.EndGameViewModel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class ComputerGuessEachAnswerTest {

    @Test
    public void guessEachAnswer() {

        // mimics ComputerGuessInteractorAnswerTest for computerGuess but checks every single possible answer in our
        // answer bank.

        GameDataAccessObject answerBank = new GameDataAccessObject();
        int answerBankLength = answerBank.getAnswerBank().size();
        List<String> notAnswered = new ArrayList<>();
        ViewManagerModel vm = new ViewManagerModel();
        EndGameViewModel vm2 = new EndGameViewModel();
        ComputerGuessViewModel vmModel = new ComputerGuessViewModel();
        ComputerGuessOutputDataBoundary outputDataBoundary = new ComputerGuessPresenter(vm, vmModel, vm2);
        int wins = 0;
        int turns = 0;

        for (Word answer : answerBank.getAnswerBank()) {

            GameDataAccessObject gameDataAccessObject = new GameDataAccessObject();
            VersusDataAccessObject versusDataAccessObject = new VersusDataAccessObject(answer.getLiteral());
            ComputerGuessInteractor testGuess = new ComputerGuessInteractor(gameDataAccessObject, answerBank,
                    versusDataAccessObject, outputDataBoundary);

            // mimic the interactor of the computerGuess
            while (gameDataAccessObject.getTurn() < 7 && !gameDataAccessObject.isGameOn()) {

                gameDataAccessObject.updateTurn();

                Word guess;

                if (gameDataAccessObject.getTurn() == 1) {

                    guess = new Word("wound");
                }

                else {

                    testGuess.scoreWordbank(gameDataAccessObject.getAnswerBank());

                    guess = testGuess.getGuess();

                }


                List<Integer> guessBoard = versusDataAccessObject.verifyGuess(guess);

                for (int i = 0; i < guessBoard.size(); i++) {

                    gameDataAccessObject.updateBoard(guess.getLiteral().charAt(i), guessBoard.get(i), i);
                }

                // Update possible answers to this game.
                gameDataAccessObject.updateAnswerBank(guessBoard, guess);

                if (guess.getLiteral().equals(answer.getLiteral())) {
                    gameDataAccessObject.gameWon();
                    wins += 1;
                }

                turns += 1;
            }

            // keep track of answers that didn't get solved.
            if (!gameDataAccessObject.isGameOn()) {
                notAnswered.add(answer.getLiteral());
            }

        }

        System.out.println("Wins: " + wins + " Out of: " + answerBankLength + " possible");

        double winRate = (double) wins / (double) answerBankLength;
        double averageturns = (double) turns / (double) answerBankLength;
        System.out.println("Average Turns: " + averageturns);
        System.out.println("Win Rate: " + winRate);
        System.out.println("Not answered words: " + notAnswered);

        assertEquals(wins, answerBankLength);
    }
}
