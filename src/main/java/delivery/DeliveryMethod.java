package delivery;

public interface DeliveryMethod {
    // Метод возвращает стоимость доставки
    double getCost();

    // Метод возвращает ориентировочное время доставки
    String getEstimatedTime();

    // Метод возвращает true, если для этого способа нужен курьер, и false, если нет
    boolean requiresCourier();

    // Метод возвращает название способа доставки
    String getName();
}
