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
        return false; // ⚠️ КУРЬЕР НЕ НУЖЕН! Это главное отличие.
    }

    @Override
    public String getName() {
        return "Самовывоз";
    }
}
