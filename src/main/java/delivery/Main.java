package delivery;

public class Main {
    public static void main(String[] args) {
        // 1. Создаем клиента и заказ
        Client client = new Client("Иван", "+79991234567");
        Order order = new Order(1, client);

        // 2. Добавляем товары
        order.addItem(new OrderItem("Пицца", 2, 500.0));
        order.addItem(new OrderItem("Кола", 3, 100.0));

        // 3. Выбираем доставку (попробуем Экспресс)
        order.setDeliveryMethod(new ExpressDelivery());

        // 4. Назначаем курьера
        Courier courier = new Courier("Петр", "+79001112233");
        order.assignCourier(courier);

        // 5. Пытаемся изменить статус (сработает проверка)
        System.out.println("\n--- Тест смены статуса ---");
        order.changeStatus("Доставлен"); // Ошибка! Нельзя из "Новый" сразу в "Доставлен"

        order.changeStatus("Готовится"); // Успех!
        order.changeStatus("В пути");    // Успех!
        order.changeStatus("Доставлен"); // Успех! (Курьер Петр автоматически освободится)

        // 6. Печатаем итоговый заказ
        System.out.println("\n" + order);
        System.out.println("Курьер Петр после доставки: " + courier);
    }
}