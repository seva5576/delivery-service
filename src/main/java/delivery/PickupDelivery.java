package delivery;

public class PickupDelivery implements DeliveryMethod {

    @Override
    public double getCost() {
        return 0.0; // Бесплатно
    }

    @Override
    public String getEstimatedTime() {
        return "15-20 минут (готовность заказа)";
    }

    @Override
    public boolean requiresCourier() {
        return false; // курьер не нужен
    }

    @Override
    public String getName() {
        return "Самовывоз";
    }
}
