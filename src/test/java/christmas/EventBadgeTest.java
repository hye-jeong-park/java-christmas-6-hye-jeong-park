package christmas;

import christmas.model.BadgeType;
import christmas.model.Discount;
import christmas.model.EventResult;
import christmas.model.MenuOrder;
import christmas.model.Order;
import christmas.view.InputView;
import christmas.controller.DecemberEventPlanner;
import christmas.controller.DecemberEventPlannerImpl;
import christmas.errors.ErrorHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventBadgeTest {

    @Test
    @DisplayName("총혜택 금액에 따른 이벤트 배지 테스트-1")
    void applyBadgeNoBenefitTest() {
        // Given
        InputView inputView = new InputView();
        ErrorHandler errorHandler = new ErrorHandler(inputView);
        List<MenuOrder> menuOrders = new ArrayList<>();
        Order order = new Order(menuOrders);

        // When
        DecemberEventPlanner decemberEventPlanner = new DecemberEventPlannerImpl();
        EventResult eventResult = decemberEventPlanner.calculateEventResult(order, 15);
        BadgeType badge = eventResult.getBadgeType();

        // Then
        assertEquals(BadgeType.NONE, badge);
    }

    @Test
    @DisplayName("총혜택 금액에 따른 이벤트 배지 테스트-2")
    void applyBadgeStarTest() {
        // Given
        InputView inputView = new InputView();
        ErrorHandler errorHandler = new ErrorHandler(inputView);

        List<Discount> discounts = new ArrayList<>();
        discounts.add(new Discount("크리스마스 이벤트", 5000));

        List<MenuOrder> menuOrders = new ArrayList<>();
        Order order = new Order(menuOrders);

        // When
        DecemberEventPlanner decemberEventPlanner = new DecemberEventPlannerImpl();
        EventResult eventResult = decemberEventPlanner.calculateEventResult(order, 15);
        BadgeType badge = eventResult.getBadgeType();

        // Then
        assertEquals(BadgeType.NONE, badge);
    }
}
