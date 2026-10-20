package delivery;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderNumber;
    private Client client;
    private String address;
    private List<OrderItem> items;
    private DeliveryMethod deliveryMethod;
    private Courier courier;
    private String status;

    // Создаёт новый заказ с валидацией входных данных
    public Order(int orderNumber, Client client, String address) {
        if (client == null) {
            throw new IllegalArgumentException("У заказа должен быть клиент.");
        }
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Адрес доставки не может быть пустым.");
        }
        this.orderNumber = orderNumber;
        this.client = client;
        this.address = address;
        this.items = new ArrayList<>();
        this.status = "Новый";
    }

    public int getOrderNumber() { return orderNumber; }
    public Client getClient() { return client; }
    public String getAddress() { return address; }
    public String getStatus() { return status; }
    public Courier getCourier() { return courier; }
    public List<OrderItem> getItems() { return new ArrayList<>(items); }
    public DeliveryMethod getDeliveryMethod() { return deliveryMethod; }

    // 1. Добавление товара
    public void addItem(OrderItem item) {
        if (status.equals("Доставлен") || status.equals("Отменен")) {
            throw new IllegalStateException("Нельзя добавлять товары в статусе" + status + " в заказ.");
        }
        items.add(item);
    }

    // 2. Выбор способа доставки
    public void setDeliveryMethod(DeliveryMethod method) {
        if (status.equals("В пути") || status.equals("Доставлен") || status.equals("Отменен")) {
            throw new IllegalStateException("Нельзя менять способ доставки для заказа в статусе '" + status + "'.");
        }
        this.deliveryMethod = method;

        if (courier != null && !method.requiresCourier()) { // если назначен курьер и самовывоз
            courier.release();
            courier = null; // убираем курьера из заказа
        }
    }

    // 3. Назначение курьера
    public void assignCourier(Courier courier) {
        if (status.equals("Доставлен") || status.equals("Отменен")) {
            throw new IllegalStateException("Нельзя назначить курьера на '" + status + "' заказ.");
        }
        if (deliveryMethod == null) {
            throw new IllegalStateException("Сначала выберите способ доставки.");
        }
        if (!deliveryMethod.requiresCourier()) {
            throw new IllegalStateException("Для самовывоза курьер не нужен.");
        }
        if (courier.isBusy()) {
            throw new IllegalStateException("Курьер " + courier.getName() + " уже занят другим заказом.");
        }

        courier.assign();
        this.courier = courier;
    }

    // 4. Смена статуса заказа
    public void changeStatus(String newStatus) {
        // Проверка: для статусов "В пути" и "Доставлен" нужен курьер
        if ((newStatus.equals("В пути") || newStatus.equals("Доставлен"))) {
            if (deliveryMethod != null && deliveryMethod.requiresCourier() && courier == null) {
                throw new IllegalStateException("Нельзя перевести заказ в статус '" + newStatus + "' без назначенного курьера.");
            }
        }

        boolean allowed = false;
        if (status.equals("Новый") && (newStatus.equals("Готовится") || newStatus.equals("Отменен"))) {
            allowed = true;
        } else if (status.equals("Готовится") && (newStatus.equals("В пути") || newStatus.equals("Отменен"))) {
            allowed = true;
        } else if (status.equals("В пути") && (newStatus.equals("Доставлен") || newStatus.equals("Отменен"))) {
            allowed = true;
        }

        if (!allowed) {
            throw new IllegalStateException("Нельзя перевести заказ из '" + status + "' в '" + newStatus + "'.");
        }

        this.status = newStatus;
        // Если заказ завершён — освобождаем курьера
        if (newStatus.equals("Доставлен") || newStatus.equals("Отменен")) {
            if (courier != null) {
                courier.release();
            }
        }
    }

    // 5. Подсчет стоимости
    public double getTotalCost() {
        // Суммируем стоимость всех позиций
        double itemsCost = 0;
        for (OrderItem item : items) {
            itemsCost += item.getTotal();
        }
        // Добавляем стоимость доставки (если выбрана)
        double deliveryCost = deliveryMethod != null ? deliveryMethod.getCost() : 0;
        return itemsCost + deliveryCost;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Заказ №").append(orderNumber).append(" [").append(status).append("] ===\n");
        sb.append("Клиент: ").append(client.getName()).append(" (").append(client.getPhone()).append(")\n");
        sb.append("Адрес: ").append(address).append("\n");
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
        return sb.toString();
    }
}
