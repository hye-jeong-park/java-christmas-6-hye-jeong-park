package christmas.errors;

import christmas.view.InputView;

public class ErrorHandler {
    private final InputView inputView;

    public ErrorHandler(InputView inputView) {
        this.inputView = inputView;
    }

    public void handleException(String errorMessage) {
        System.out.println(errorMessage);
    }
}

