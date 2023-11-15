package christmas.model;

public class Discount {

    private String type;
    private int amount;

    public Discount(String type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }
}