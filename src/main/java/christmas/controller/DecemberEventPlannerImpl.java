package christmas.controller;

import static christmas.model.EventType.WEEKDAY;
import static christmas.model.EventType.WEEKEND;
import static christmas.utils.OrderCalculator.calculateTotalDiscountAmount;

import christmas.model.*;
import christmas.utils.OrderCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

public class DecemberEventPlannerImpl implements DecemberEventPlanner {

    private static final int MINIMUM_ORDER_AMOUNT_FOR_GIFT = 120000;

    @Override
    public EventResult calculateEventResult(Order order, int visitDate) {
        List<Discount> discounts = calculateDiscounts(order, visitDate);
        Menu giftMenu = calculateGiftMenu(order.getMenuOrders());
        BadgeType badge = calculateBadge(calculateTotalDiscountAmount(discounts));

        int totalDiscountAmountExcludingGift = calculateTotalDiscountAmountExcludingGift(discounts,
            giftMenu);

        return new EventResult(discounts, giftMenu, badge, totalDiscountAmountExcludingGift);
    }

    private List<Discount> calculateDiscounts(Order order, int visitDate) {
        List<Discount> discounts = new ArrayList<>();

        // 크리스마스 할인
        if (isChristmasDiscountPeriod(visitDate)) {
            discounts.add(calculateChristmasDiscount(visitDate));
        }

        // 주중 및 주말 할인
        if (isWeekday(visitDate)) {
            discounts.addAll(calculateWeekdayDiscount(order));
        } else {
            discounts.addAll(calculateWeekendDiscount(order));
        }

        // 특별 할인
        if (isSpecialDiscountDate(visitDate)) {
            discounts.add(calculateSpecialDiscount());
        }

        // 증정 이벤트 할인
        if (OrderCalculator.calculateTotalOrderAmount(order) >= MINIMUM_ORDER_AMOUNT_FOR_GIFT) {
            discounts.add(calculateGiftDiscount());
        }

        return discounts;
    }

    // 크리스마스 할인 기간 확인
    private boolean isChristmasDiscountPeriod(int visitDate) {
        return visitDate >= 1 && visitDate <= 25;
    }

    // 주중 여부 확인
    private boolean isWeekday(int visitDate) {
        // 일요일이 1인 가정하에 요일 계산 (Calendar.SUNDAY = 1)
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.DECEMBER, visitDate);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        return dayOfWeek >= Calendar.SUNDAY && dayOfWeek <= Calendar.THURSDAY;
    }

    //주중 할인 계산
    private List<Discount> calculateWeekdayDiscount(Order order) {
        List<Discount> discounts = new ArrayList<>();
        for (MenuOrder menuOrder : order.getMenuOrders()) {
            if (menuOrder.getMenu().getEventType() == EventType.DESSERT) {
                int discountAmount = 2023 * menuOrder.getQuantity();
                String eventTypeString = WEEKDAY.getDisplayName();
                discounts.add(new Discount(eventTypeString, discountAmount));
            }
        }
        return discounts;
    }

    // 주말 할인 계산
    private List<Discount> calculateWeekendDiscount(Order order) {
        List<Discount> discounts = new ArrayList<>();
        for (MenuOrder menuOrder : order.getMenuOrders()) {
            if (menuOrder.getMenu().getEventType() == EventType.MAIN) {
                int discountAmount = 2023 * menuOrder.getQuantity();
                String eventTypeString = WEEKEND.getDisplayName();
                discounts.add(new Discount(eventTypeString, discountAmount));
            }
        }
        return discounts;
    }


    // 특별 할인 일자 확인
    private boolean isSpecialDiscountDate(int visitDate) {
        int[] specialDiscountDates = {3, 10, 17, 24, 25, 31};
        for (int date : specialDiscountDates) {
            if (visitDate == date) {
                return true;
            }
        }
        return false;
    }

    // 크리스마스 할인 계산
    private Discount calculateChristmasDiscount(int visitDate) {
        int discountAmount = 1000 + (visitDate - 1) * 100;
        String eventTypeString = EventType.CHRISTMAS.getDisplayName();
        return new Discount(eventTypeString, discountAmount);
    }

    // 특별 할인 계산
    private Discount calculateSpecialDiscount() {
        int discountAmount = 1000;
        String eventTypeString = EventType.SPECIAL.getDisplayName();
        return new Discount(eventTypeString, discountAmount);
    }

    // 증정 이벤트 할인 계산
    private Discount calculateGiftDiscount() {
        String eventTypeString = EventType.GIFT.getDisplayName();
        return new Discount(eventTypeString, MenuPrice.DRINK_CHAMPAGNE.getPrice());
    }

    // 증정 이벤트 메뉴 계산
    private Menu calculateGiftMenu(List<MenuOrder> menuOrders) {
        if (calculateTotalOrderAmount(menuOrders) < MINIMUM_ORDER_AMOUNT_FOR_GIFT) {
            return null;
        }

        Menu giftMenu = new Menu("샴페인", MenuPrice.DRINK_CHAMPAGNE, EventType.DRINK);
        return giftMenu;
    }

    // 배지 계산
    private BadgeType calculateBadge(int totalBenefitAmount) {
        if (totalBenefitAmount >= 20000) {
            return BadgeType.SANTA;
        } else if (totalBenefitAmount >= 10000) {
            return BadgeType.TREE;
        } else if (totalBenefitAmount >= 5000) {
            return BadgeType.STAR;
        }
        return BadgeType.NONE;
    }

    // 총 주문 금액 계산
    private int calculateTotalOrderAmount(List<MenuOrder> menuOrders) {
        return menuOrders.stream()
            .mapToInt(
                menuOrder -> menuOrder.getMenu().getPrice().getPrice() * menuOrder.getQuantity())
            .sum();
    }

    private int calculateTotalDiscountAmountExcludingGift(List<Discount> discounts, Menu giftMenu) {
        int totalDiscountAmount = calculateTotalDiscountAmount(discounts);

        if (giftMenu != null) {
            totalDiscountAmount -= giftMenu.getPrice().getPrice();
        }

        return totalDiscountAmount;
    }
}
