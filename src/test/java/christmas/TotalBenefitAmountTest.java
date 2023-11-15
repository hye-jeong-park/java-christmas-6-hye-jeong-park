package christmas;

import christmas.model.Discount;
import christmas.utils.OrderCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TotalBenefitAmountTest {

    @Test
    @DisplayName("총혜택 금액 계산 테스트-1")
    void totalBenefitAmountCalculationTest() {
        // Given
        List<Discount> discounts = new ArrayList<>();

        discounts.add(new Discount("주중 할인", 5000));
        discounts.add(new Discount("주말 할인", 12000));
        discounts.add(new Discount("특별 할인", 25000));

        // When
        int totalBenefitAmount = OrderCalculator.calculateTotalDiscountAmount(discounts);

        // Then
        assertEquals(42000, totalBenefitAmount);
    }

    @Test
    @DisplayName("총혜택 금액 계산 테스트-2")
    void noDiscountCalculationTest() {
        // Given
        List<Discount> discounts = new ArrayList<>();

        discounts.add(new Discount("NONE", 0));

        // When
        int totalBenefitAmount = OrderCalculator.calculateTotalDiscountAmount(discounts);

        // Then
        assertEquals(0, totalBenefitAmount);
    }
}

