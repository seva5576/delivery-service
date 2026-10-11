package delivery;

public class Main {
    public static void main(String[] args) {

        // Создаём три разных способа доставки
        DeliveryMethod standard = new StandardDelivery();
        DeliveryMethod express = new ExpressDelivery();
        DeliveryMethod pickup = new PickupDelivery();

        // Складываем их в один массив (потому что все они подходят под "бланк" DeliveryMethod)
        DeliveryMethod[] methods = {standard, express, pickup};

        System.out.println("--- Наши способы доставки ---\n");

        // Перебираем все способы
        for (DeliveryMethod method : methods) {
            System.out.println("Способ: " + method.getName());
            System.out.println("Цена: " + method.getCost() + " руб.");
            System.out.println("Срок: " + method.getEstimatedTime());

            if (method.requiresCourier()) {
                System.out.println("Курьер: НУЖЕН");
            } else {
                System.out.println("Курьер: НЕ НУЖЕН (самовывоз)");
            }
            System.out.println("-------------------------");
        }
    }
}