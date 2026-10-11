package delivery;

public class OrderItem {
    private String productName;
    private int quantity;
    private double price;

    public OrderItem(String productName, int quantity, double price) {
        this.productName = productName;

        // Простая проверка через if
        if (quantity <= 0) {
            System.out.println("⚠️ Ошибка: количество должно быть больше 0! Установлено 1.");
            this.quantity = 1;
        } else {
            this.quantity = quantity;
        }

        if (price < 0) {
            System.out.println("⚠️ Ошибка: цена не может быть отрицательной! Установлено 0.");
            this.price = 0;
        } else {
            this.price = price;
        }
    }

    public double getTotal() {
        return quantity * price;
    }

    @Override
    public String toString() {
        return productName + " x" + quantity + " = " + getTotal() + " руб.";
    }
}