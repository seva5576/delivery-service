package delivery;

// Это наш "бланк". В нём нет кода, только требования.
public interface DeliveryMethod {

    double getCost();           // Сколько стоит доставка?

    String getEstimatedTime();  // Сколько времени займёт?

    boolean requiresCourier();  // Нужен ли курьер? (true/false)

    String getName();           // Как называется способ?
}
