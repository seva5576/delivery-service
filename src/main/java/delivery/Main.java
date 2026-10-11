package delivery;

public class Main {
    public static void main(String[] args) {
        // 1. Тестируем Клиента
        Client client = new Client("Иван", "+79991234567");
        System.out.println(client);

        // 2. Тестируем Позицию заказа (сработает наша проверка if)
        System.out.println("\n--- Тест позиции ---");
        OrderItem pizza = new OrderItem("Пицца", 2, 500.0);
        System.out.println(pizza);

        OrderItem badItem = new OrderItem("Суп", -5, 100.0); // Количество -5, сработает if
        System.out.println(badItem);

        // 3. Тестируем Курьера
        System.out.println("\n--- Тест курьера ---");
        Courier courier = new Courier("Петр", "+79001112233");
        System.out.println(courier);

        courier.assign(); // Назначаем (станет занятым)
        courier.assign(); // Пытаемся назначить еще раз (выведет предупреждение)
    }
}