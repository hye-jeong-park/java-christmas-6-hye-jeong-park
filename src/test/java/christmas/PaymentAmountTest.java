package christmas;

import christmas.controller.DecemberEventPlanner;
import christmas.controller.DecemberEventPlannerImpl;
import christmas.errors.ErrorHandler;
import christmas.model.EventResult;
import christmas.model.EventType;
import christmas.model.Menu;
import christmas.model.MenuOrder;
import christmas.model.MenuPrice;
import christmas.model.Order;
import christmas.utils.OrderCalculator;
import christmas.view.InputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentAmountTest {

    @Test
    @DisplayName("할인 후 예상 결제 금액 계산 테스트-1")
    void calculateExpectedPaymentAmountTest() {
        // Given
        InputView inputView = new InputView();
        ErrorHandler errorHandler = new ErrorHandler(inputView);

        // 12만원의 주문에 대한 테스트
        List<MenuOrder> menuOrders = new ArrayList<>();
        menuOrders.add(new MenuOrder(new Menu("크리스마스파스타", MenuPrice.MAIN_CHRISTMAS_PASTA, EventType.MAIN), 1));
        menuOrders.add(new MenuOrder(new Menu("샴페인", MenuPrice.DRINK_CHAMPAGNE, EventType.DRINK), 1));
        Order order = new Order(menuOrders);

        // When
        DecemberEventPlanner decemberEventPlanner = new DecemberEventPlannerImpl();
        EventResult eventResult = decemberEventPlanner.calculateEventResult(order, 15);

        int totalOrderAmount = OrderCalculator.calculateTotalOrderAmount(order);
        int expectedPaymentAmount = totalOrderAmount - eventResult.getTotalDiscountAmountExcludingGift();

        // Then
        assertEquals(expectedPaymentAmount, 45577);
    }

    @Test
    @DisplayName("할인 후 예상 결제 금액 계산 테스트-2")
    void calculateExpectedPaymentAmountNoDiscountTest() {
        // Given
        InputView inputView = new InputView();
        ErrorHandler errorHandler = new ErrorHandler(inputView);

        List<MenuOrder> menuOrders = new ArrayList<>();
        menuOrders.add(new MenuOrder(new Menu("타파스", MenuPrice.APPETIZER_TAPAS, EventType.APPETIZER), 1));
        Order order = new Order(menuOrders);

        // When
        DecemberEventPlanner decemberEventPlanner = new DecemberEventPlannerImpl();
        EventResult eventResult = decemberEventPlanner.calculateEventResult(order, 15);

        int totalOrderAmount = OrderCalculator.calculateTotalOrderAmount(order);
        int expectedPaymentAmount = totalOrderAmount - eventResult.getTotalDiscountAmountExcludingGift();

        // Then
        assertEquals(expectedPaymentAmount, 3100);
    }
}
