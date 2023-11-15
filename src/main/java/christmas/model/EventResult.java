package christmas.model;

import java.util.List;

public class EventResult {

    private final List<Discount> discounts;
    private final Menu giftMenu;
    private final BadgeType badgeType;
    private int totalDiscountAmountExcludingGift;


    public EventResult(List<Discount> discounts, Menu giftMenu, BadgeType badgeType, int totalDiscountAmountExcludingGift) {
        this.discounts = discounts;
        this.giftMenu = giftMenu;
        this.badgeType = badgeType;
        this.totalDiscountAmountExcludingGift = totalDiscountAmountExcludingGift;

    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public Menu getGiftMenu() {
        return giftMenu;
    }

    public BadgeType getBadgeType() {
        return badgeType;
    }

    public int getTotalDiscountAmountExcludingGift() {
        return totalDiscountAmountExcludingGift;
    }
}
