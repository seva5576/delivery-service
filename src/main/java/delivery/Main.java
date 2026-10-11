package delivery;

public class Main {
    public static void main(String[] args) {
        // 1. Создаем клиента
        Client client = new Client("Иван", "+79991234567");
        System.out.println(client);

        // 2. Создаем позицию заказа
        OrderItem pizza = new OrderItem("Пицца Пепперони", 2, 500.0);
        System.out.println("Позиция: " + pizza);
        System.out.println("Сумма позиции: " + pizza.getTotal() + " руб.");

        // 3. Попробуем создать некорректную позицию (чтобы сработала проверка)
        try {
            OrderItem badItem = new OrderItem("Суп", -1, 100.0);
        } catch (IllegalArgumentException e) {
            System.out.println("ОШИБКА (как и ожидалось): " + e.getMessage());
        }
    }
}
