package christmas;

import christmas.errors.ErrorHandler;
import christmas.model.MenuOrder;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ErrorHandler errorHandler = new ErrorHandler(inputView);

        outputView.printStartMessage();
        int visitDate = inputView.readDate(errorHandler);

        try {
            String orderInput;
            List<MenuOrder> menuOrders;

            while (true) {
                orderInput = inputView.readOrder();
                menuOrders = inputView.parseOrderInput(orderInput, errorHandler);
                if (!menuOrders.isEmpty()) {
                    break;
                }
            }
        } catch (IllegalArgumentException e) {
            errorHandler.handleException(e.getMessage());
        }
    }
}
