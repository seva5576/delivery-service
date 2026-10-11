package delivery;

public class StandardDelivery implements DeliveryMethod {

    @Override
    public double getCost() {
        return 150.0;
    }

    @Override
    public String getEstimatedTime() {
        return "60-90 минут";
    }

    @Override
    public boolean requiresCourier() {
        return true; // Курьер нужен
    }

    @Override
    public String getName() {
        return "Стандартная доставка";
    }
}
