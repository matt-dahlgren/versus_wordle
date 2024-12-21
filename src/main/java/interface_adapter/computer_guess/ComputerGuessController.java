package interface_adapter.computer_guess;

import use_case.computer_guess.ComputerGuessInputDataBoundary;

/**
 * Controller for the Computer Guess Use-Case
 */
public class ComputerGuessController {

    private final ComputerGuessInputDataBoundary computerGuessInputDataBoundary;

    public ComputerGuessController(ComputerGuessInputDataBoundary computerGuessInputDataBoundary) {
        this.computerGuessInputDataBoundary = computerGuessInputDataBoundary;
    }

    /**
     * Execute the computer guess use-case (Accessed only through view).
     */
    public void execute() {

        computerGuessInputDataBoundary.execute();
    }
}
