package delivery;

import java.util.ArrayList;
import java.util.List;

public class Order {
    // Поля (состояние заказа) - они private, чтобы никто не мог их испортить напрямую
    private int orderNumber;
    private Client client;
    private List<OrderItem> items; // Наш "волшебный мешок" для товаров
    private DeliveryMethod deliveryMethod;
    private Courier courier;
    private String status;

    // Конструктор
    public Order(int orderNumber, Client client) {
        this.orderNumber = orderNumber;
        this.client = client;
        this.items = new ArrayList<>(); // Создаем пустой мешок
        this.status = "Новый"; // Любой новый заказ начинается со статуса "Новый"
    }

    // Геттеры (чтобы можно было прочитать данные снаружи)
    public int getOrderNumber() { return orderNumber; }
    public Client getClient() { return client; }
    public String getStatus() { return status; }
    public Courier getCourier() { return courier; }
    public List<OrderItem> getItems() { return items; }
    public DeliveryMethod getDeliveryMethod() { return deliveryMethod; }

    // --- ОПЕРАЦИИ С ЗАКАЗОМ (Инкапсуляция) ---

    // 1. Добавление товара
    public void addItem(OrderItem item) {
        if (status.equals("Новый")) {
            items.add(item); // Кладем в мешок
        } else {
            System.out.println("⚠️ Ошибка: Нельзя добавлять товары в заказ со статусом '" + status + "'.");
        }
    }

    // 2. Выбор способа доставки
    public void setDeliveryMethod(DeliveryMethod method) {
        this.deliveryMethod = method;

        // Если был курьер, а выбрали самовывоз (курьер не нужен) - освобождаем старого
        if (courier != null && !method.requiresCourier()) {
            courier.release();
            courier = null;
        }
    }

    // 3. Назначение курьера
    public void assignCourier(Courier courier) {
        if (deliveryMethod == null) {
            System.out.println("⚠️ Ошибка: Сначала выберите способ доставки!");
            return;
        }
        if (!deliveryMethod.requiresCourier()) {
            System.out.println("⚠️ Ошибка: Для самовывоза курьер не нужен!");
            return;
        }

        courier.assign(); // Помечаем курьера как занятого
        this.courier = courier;
    }

    // 4. Смена статуса (самая важная часть!)
    public void changeStatus(String newStatus) {
        boolean allowed = false;

        // Проверяем, разрешен ли переход
        if (status.equals("Новый") && (newStatus.equals("Готовится") || newStatus.equals("Отменен"))) {
            allowed = true;
        } else if (status.equals("Готовится") && (newStatus.equals("В пути") || newStatus.equals("Отменен"))) {
            allowed = true;
        } else if (status.equals("В пути") && (newStatus.equals("Доставлен") || newStatus.equals("Отменен"))) {
            allowed = true;
        }

        if (allowed) {
            this.status = newStatus;
            // Если заказ завершен (доставлен или отменен), освобождаем курьера
            if (newStatus.equals("Доставлен") || newStatus.equals("Отменен")) {
                if (courier != null) {
                    courier.release();
                }
            }
        } else {
            System.out.println("⚠️ Ошибка: Нельзя перевести заказ из '" + status + "' в '" + newStatus + "'.");
        }
    }

    // 5. Подсчет итоговой стоимости
    public double getTotalCost() {
        double itemsCost = 0;
        // Перебираем все товары в мешке и суммируем
        for (OrderItem item : items) {
            itemsCost += item.getTotal();
        }

        double deliveryCost = 0;
        if (deliveryMethod != null) {
            deliveryCost = deliveryMethod.getCost();
        }

        return itemsCost + deliveryCost;
    }

    // Красивый вывод заказа в консоль
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Заказ №").append(orderNumber).append(" [").append(status).append("] ===\n");
        sb.append("Клиент: ").append(client.getName()).append(" (").append(client.getPhone()).append(")\n");

        sb.append("Позиции:\n");
        if (items.isEmpty()) {
            sb.append("  (пусто)\n");
        } else {
            for (OrderItem item : items) {
                sb.append("  - ").append(item).append("\n");
            }
        }

        sb.append("Доставка: ").append(deliveryMethod != null ? deliveryMethod.getName() : "не выбрана").append("\n");
        sb.append("Курьер: ").append(courier != null ? courier.getName() : "не назначен").append("\n");
        sb.append("Итого к оплате: ").append(getTotalCost()).append(" руб.\n");
        sb.append("=========================================\n");

        return sb.toString();
    }
}
