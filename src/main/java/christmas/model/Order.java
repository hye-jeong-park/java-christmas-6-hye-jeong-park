package christmas.model;

import java.util.List;

public class Order {

    private final List<MenuOrder> menuOrders;

    public Order(List<MenuOrder> menuOrders) {
        this.menuOrders = menuOrders;
    }

    public List<MenuOrder> getMenuOrders() {
        return menuOrders;
    }
}