package christmas.model;

import christmas.errors.ErrorHandler;

public enum MenuPrice {
    APPETIZER_SOUP("양송이수프", 6000, MenuCategory.APPETIZER),
    APPETIZER_TAPAS("타파스", 5500, MenuCategory.APPETIZER),
    APPETIZER_CAESAR_SALAD("시저샐러드", 8000, MenuCategory.APPETIZER),
    MAIN_T_BONE_STEAK("티본스테이크", 55000, MenuCategory.MAIN),
    MAIN_BBQ_RIBS("바비큐립", 54000, MenuCategory.MAIN),
    MAIN_SEAFOOD_PASTA("해산물파스타", 35000, MenuCategory.MAIN),
    MAIN_CHRISTMAS_PASTA("크리스마스파스타", 25000, MenuCategory.MAIN),
    DESSERT_CHOCO_CAKE("초코케이크", 15000, MenuCategory.DESSERT),
    DESSERT_ICE_CREAM("아이스크림", 5000, MenuCategory.DESSERT),
    DRINK_ZERO_COLA("제로콜라", 3000, MenuCategory.DRINK),
    DRINK_RED_WINE("레드와인", 60000, MenuCategory.DRINK),
    DRINK_CHAMPAGNE("샴페인", 25000, MenuCategory.DRINK);

    private final String menuName;
    private final int price;
    private final MenuCategory category;

    MenuPrice(String menuName, int price, MenuCategory category) {
        this.menuName = menuName;
        this.price = price;
        this.category = category;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getPrice() {
        return price;
    }

    public MenuCategory getCategory() {
        return category;
    }

    public static MenuPrice getPriceByName(String name, ErrorHandler errorHandler) {
        for (MenuPrice menuPrice : MenuPrice.values()) {
            if (menuPrice.getMenuName().equals(name)) {
                return menuPrice;
            }
        }
        errorHandler.handleException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        return null;
    }
}