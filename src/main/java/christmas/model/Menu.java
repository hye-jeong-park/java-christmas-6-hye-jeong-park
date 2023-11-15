package christmas.model;

import java.util.Objects;

public class Menu {
    private final String name;
    private final MenuPrice price;
    private final EventType eventType;

    public Menu(String name, MenuPrice price, EventType eventType) {
        this.name = name;
        this.price = price;
        this.eventType = eventType;
    }

    public String getName() {
        return name;
    }

    public MenuPrice getPrice() {
        return price;
    }

    public EventType getEventType() {
        return eventType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Menu menu = (Menu) obj;
        return Objects.equals(name, menu.name) && eventType == menu.eventType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, eventType);
    }
}
