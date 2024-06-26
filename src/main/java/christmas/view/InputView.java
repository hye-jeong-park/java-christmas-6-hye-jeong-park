package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.errors.ErrorHandler;
import christmas.model.EventType;
import christmas.model.Menu;
import christmas.model.MenuCategory;
import christmas.model.MenuOrder;
import christmas.model.MenuPrice;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InputView {

    public int readDate(ErrorHandler errorHandler) {
        int visitDate;
        while (true) {
            try {
                System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
                visitDate = readIntegerInput();

                if (visitDate < 1 || visitDate > 31) {
                    errorHandler.handleException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
                }

                break;

            } catch (NumberFormatException e) {
                errorHandler.handleException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            } catch (IllegalArgumentException e) {
                errorHandler.handleException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            }
        }
        return visitDate;
    }

    public String readOrder() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        return Console.readLine();
    }

    public int readIntegerInput() {
        String input = Console.readLine();
        return Integer.parseInt(input);
    }

    public List<MenuOrder> parseOrderInput(String orderInput, ErrorHandler errorHandler) {
        String[] orderTokens = orderInput.split(",");
        List<MenuOrder> menuOrders = new ArrayList<>();

        Set<String> uniqueMenuNames = new HashSet<>();
        int totalQuantity = 0;
        boolean hasDrink = false;

        while (true) {
            for (String orderToken : orderTokens) {
                String[] orderInfo = orderToken.trim().split("-");

                // 입력 포맷이 맞는지 확인
                if (orderInfo.length != 2) {
                    menuOrders.clear();
                    errorHandler.handleException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                    break;
                }

                String menuName = orderInfo[0];
                int quantity;

                try {
                    quantity = Integer.parseInt(orderInfo[1]);
                } catch (NumberFormatException e) {
                    menuOrders.clear();
                    errorHandler.handleException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                    break;
                }

                // 개수입력을 올바르게 했는지 확인
                if (quantity < 1) {
                    menuOrders.clear();
                    errorHandler.handleException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                    break;
                }

                // 총 주문 개수가 20개가 넘었는지 확인
                if (totalQuantity + quantity > 20) {
                    menuOrders.clear();
                    errorHandler.handleException("[ERROR] 주문 개수가 20개를 초과하였습니다. 다시 입력해 주세요.");
                    break;
                }

                // 주문 메뉴가 중복되지 않았는지 확인
                if (!uniqueMenuNames.add(menuName)) {
                    menuOrders.clear();
                    errorHandler.handleException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                    break;
                }

                totalQuantity += quantity;

                if (MenuCategory.DRINK == MenuPrice.getPriceByName(menuName, errorHandler)
                    .getCategory() && menuOrders.size() == 0) {
                    hasDrink = true;
                }

                // 메뉴판에 있는 메뉴인지 확인
                MenuPrice menuPrice = MenuPrice.getPriceByName(menuName, errorHandler);
                if (menuPrice == null) {
                    errorHandler.handleException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                    menuOrders.clear();
                    break;
                }

                EventType eventType = EventType.getEventTypeByCategory(menuPrice.getCategory());
                Menu menu = new Menu(menuName, menuPrice, eventType);
                MenuOrder menuOrder = new MenuOrder(menu, quantity);
                menuOrders.add(menuOrder);
            }
            if (hasDrink && menuOrders.size() == 1) {
                errorHandler.handleException("[ERROR] 음료만 주문할 수 없습니다. 다시 입력해 주세요.");
                menuOrders.clear();
                break;
            }
            break;
        }
        return menuOrders;
    }
}