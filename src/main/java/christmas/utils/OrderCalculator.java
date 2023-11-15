package christmas.utils;

import christmas.model.*;

public class OrderCalculator {

    public static int calculateTotalOrderAmount(Order order) {
        int totalOrderAmount = 0;
        for (MenuOrder menuOrder : order.getMenuOrders()) {
            totalOrderAmount += menuOrder.getMenu().getPrice().getPrice() * menuOrder.getQuantity();
        }
        return totalOrderAmount;
    }
}