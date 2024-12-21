package interface_adapter.user_guess;

import use_case.user_guess.UserGuessInputData;
import use_case.user_guess.UserGuessInputDataBoundary;

public class UserGuessController {

    private final UserGuessInputDataBoundary userGuessInteractor;

    public UserGuessController(UserGuessInputDataBoundary userGuessInteractor) {

        this.userGuessInteractor = userGuessInteractor;
    }

    /**
     * Called from view, initiate the execution of the user_guess use-case.
     * @param guess is a 5 letter string, non-case-sensitive.
     */
    public void execute(String guess) {

        UserGuessInputData inputData = new UserGuessInputData(guess);
        userGuessInteractor.execute(inputData);
    }
}
