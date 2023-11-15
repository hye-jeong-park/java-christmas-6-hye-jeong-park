package christmas;

import christmas.controller.DecemberEventPlanner;
import christmas.controller.DecemberEventPlannerImpl;
import christmas.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GiftEventTest {

    @Test
    @DisplayName("증정 이벤트 테스트")
    void giftEventCalculationTest() {
        // Given
        DecemberEventPlanner decemberEventPlanner = new DecemberEventPlannerImpl();
        List<MenuOrder> menuOrders = new ArrayList<>();
        menuOrders.add(new MenuOrder(new Menu("크리스마스파스타", MenuPrice.MAIN_CHRISTMAS_PASTA, EventType.MAIN), 2));
        Order order = new Order(menuOrders);

        // When
        EventResult eventResult = decemberEventPlanner.calculateEventResult(order, 15);

        // Then
        assertNull(eventResult.getGiftMenu());
    }
}
