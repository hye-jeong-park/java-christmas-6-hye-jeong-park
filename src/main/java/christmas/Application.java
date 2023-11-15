package christmas;

import christmas.controller.DecemberEventPlanner;
import christmas.controller.DecemberEventPlannerImpl;
import christmas.errors.ErrorHandler;
import christmas.model.BadgeType;
import christmas.model.Discount;
import christmas.model.EventResult;
import christmas.model.Menu;
import christmas.model.MenuOrder;
import christmas.model.Order;
import christmas.utils.OrderCalculator;
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

            Order order = new Order(menuOrders);

            DecemberEventPlanner decemberEventPlanner = new DecemberEventPlannerImpl();
            EventResult eventResult = decemberEventPlanner.calculateEventResult(order, visitDate);

            outputView.printMenu(order.getMenuOrders());
            int totalOrderAmount = OrderCalculator.calculateTotalOrderAmount(order);
            outputView.printTotalOrderAmount(totalOrderAmount);

            Menu giftMenu = eventResult.getGiftMenu();
            outputView.printGiftMenu(giftMenu);

            List<Discount> discounts = eventResult.getDiscounts();
            outputView.printDiscounts(discounts);

            int totalBenefitAmount = OrderCalculator.calculateTotalDiscountAmount(discounts);
            outputView.printTotalBenefitAmount(totalBenefitAmount);

            int expectedPaymentAmount = totalOrderAmount - eventResult.getTotalDiscountAmountExcludingGift();
            outputView.printExpectedPaymentAmount(expectedPaymentAmount);

            BadgeType badge = eventResult.getBadgeType();
            outputView.printBadge(badge);

        } catch (IllegalArgumentException e) {
            errorHandler.handleException(e.getMessage());
        }
    }
}