//class that records information for an item on the menu
public class MenuItem {

    private final String name;
    private final double price;
    private final int quantity;
    private final double finalprice;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getFinalprice() {
        return finalprice;
    }

    public MenuItem(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.finalprice = price * quantity;
    }

}
