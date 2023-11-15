package christmas.model;

import java.util.HashMap;
import java.util.Map;

public enum EventType {
    APPETIZER("애피타이저"),
    DESSERT("디저트"),
    MAIN("메인"),
    DRINK("음료"),
    WEEKDAY("평일 할인"),
    WEEKEND("주말 할인"),
    CHRISTMAS("크리스마스 디데이 할인"),
    SPECIAL("특별 할인"),
    GIFT("증정 이벤트");

    private static final Map<MenuCategory, EventType> categoryMap = new HashMap<>();

    static {
        categoryMap.put(MenuCategory.APPETIZER, EventType.MAIN);
        categoryMap.put(MenuCategory.MAIN, EventType.MAIN);
        categoryMap.put(MenuCategory.DESSERT, EventType.DESSERT);
        categoryMap.put(MenuCategory.DRINK, EventType.DRINK);
    }

    private final String displayName;

    EventType(String displayName) {
        this.displayName = displayName;
    }

    public static EventType getEventTypeByCategory(MenuCategory category) {
        return categoryMap.get(category);
    }

    public String getDisplayName() {
        return displayName;
    }
}
