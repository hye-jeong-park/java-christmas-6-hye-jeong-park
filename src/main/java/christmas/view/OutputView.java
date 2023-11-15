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
}