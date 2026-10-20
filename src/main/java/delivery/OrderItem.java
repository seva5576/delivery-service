package delivery;

public class OrderItem {
    private String productName;
    private int quantity;
    private double price;

    public OrderItem(String productName, int quantity, double price) {
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Название товара не может быть пустым.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Количество должно быть больше 0.");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Цена должна быть больше 0.");
        }
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    // Метод для подсчёта общей стоимости позиции
    public double getTotal() {
        return quantity * price;
    }

    @Override
    public String toString() {
        return productName + " x" + quantity + " = " + getTotal() + " руб.";
    }
}