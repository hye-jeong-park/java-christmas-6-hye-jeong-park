package christmas.view;

import christmas.model.*;
import java.util.List;

public class OutputView {

    public void printStartMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printMenu(List<MenuOrder> menuOrders) {
        System.out.println("\n<주문 메뉴>");
        for (MenuOrder menuOrder : menuOrders) {
            System.out.printf("%s %d개%n", menuOrder.getMenu().getName(), menuOrder.getQuantity());
        }
    }

    public void printTotalOrderAmount(int totalOrderAmount) {
        System.out.println("\n<할인 전 총주문 금액>");
        System.out.printf("%,d원%n", totalOrderAmount);
    }

    public void printGiftMenu(Menu giftMenu) {
        System.out.println("\n<증정 메뉴>");
        if (giftMenu != null) {
            System.out.printf("%s 1개%n", giftMenu.getName());
        } else {
            System.out.println("없음");
        }
    }

    public void printDiscounts(List<Discount> discounts) {
        System.out.println("\n<혜택 내역>");
        if (discounts.isEmpty()) {
            System.out.println("없음");
        } else {
            for (Discount discount : discounts) {
                System.out.printf("%s 할인: -%,d원%n", discount.getType(), discount.getAmount());
            }
        }
    }

    public void printTotalBenefitAmount(int totalBenefitAmount) {
        System.out.println("\n<총혜택 금액>");
        if (totalBenefitAmount == 0) {
            System.out.printf("0원\n");
        } else {
            System.out.printf("-%,d원%n", totalBenefitAmount);
        }
    }
}