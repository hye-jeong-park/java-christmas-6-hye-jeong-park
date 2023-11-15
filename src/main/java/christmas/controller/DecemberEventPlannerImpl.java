package christmas.controller;

import christmas.model.*;

import java.util.ArrayList;
import java.util.List;

public class DecemberEventPlannerImpl implements DecemberEventPlanner {

    private static final int MINIMUM_ORDER_AMOUNT_FOR_GIFT = 120000;

    @Override
    public EventResult calculateEventResult(Order order, int visitDate) {
        List<Discount> discounts = calculateDiscounts(order, visitDate);
        return null;
    }

    private List<Discount> calculateDiscounts(Order order, int visitDate) {
        List<Discount> discounts = new ArrayList<>();

        // 크리스마스 할인
        if (isChristmasDiscountPeriod(visitDate)) {
            discounts.add(calculateChristmasDiscount(visitDate));
        }

        return discounts;
    }

    // 크리스마스 할인 기간 확인
    private boolean isChristmasDiscountPeriod(int visitDate) {
        return visitDate >= 1 && visitDate <= 25;
    }

    // 크리스마스 할인 계산
    private Discount calculateChristmasDiscount(int visitDate) {
        int discountAmount = 1000 + (visitDate - 1) * 100;
        String eventTypeString = EventType.CHRISTMAS.getDisplayName();
        return new Discount(eventTypeString, discountAmount);
    }
}
