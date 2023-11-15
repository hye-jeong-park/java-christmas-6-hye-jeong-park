package christmas.utils;

import christmas.model.*;

import java.util.List;

public class OrderCalculator {

    public static int calculateTotalOrderAmount(Order order) {
        int totalOrderAmount = 0;
        for (MenuOrder menuOrder : order.getMenuOrders()) {
            totalOrderAmount += menuOrder.getMenu().getPrice().getPrice() * menuOrder.getQuantity();
        }
        return totalOrderAmount;
    }

    public static int calculateTotalDiscountAmount(List<Discount> discounts) {
        int totalDiscountAmount = 0;
        for (Discount discount : discounts) {
            totalDiscountAmount += discount.getAmount();
        }
        return totalDiscountAmount;
    }
}