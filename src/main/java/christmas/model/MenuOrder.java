package christmas.model;

public class MenuOrder {

    private final Menu menu;
    private final int quantity;

    public MenuOrder(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public christmas.model.Menu getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }
}