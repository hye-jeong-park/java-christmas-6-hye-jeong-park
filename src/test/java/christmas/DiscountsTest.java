package christmas;

import christmas.model.Discount;
import christmas.model.EventType;
import christmas.utils.OrderCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountsTest {

    @Test
    @DisplayName("혜택 내역 테스트")
    void calculateTotalBenefitAmountTest() {
        // Given
        List<Discount> discounts = new ArrayList<>();
        discounts.add(new Discount(EventType.WEEKDAY.getDisplayName(), 5000));
        discounts.add(new Discount(EventType.WEEKEND.getDisplayName(), 12000));
        discounts.add(new Discount(EventType.SPECIAL.getDisplayName(), 25000));

        // When
        int totalBenefitAmount = OrderCalculator.calculateTotalDiscountAmount(discounts);

        // Then
        assertEquals(42000, totalBenefitAmount); // 각 이벤트의 할인 금액 합
    }
}
