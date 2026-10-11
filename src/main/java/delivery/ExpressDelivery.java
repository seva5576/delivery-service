package delivery;

public class ExpressDelivery implements DeliveryMethod {

    @Override
    public double getCost() {
        return 350.0; // Дороже
    }

    @Override
    public String getEstimatedTime() {
        return "20-30 минут"; // Быстрее
    }

    @Override
    public boolean requiresCourier() {
        return true; // Курьер тоже нужен
    }

    @Override
    public String getName() {
        return "Экспресс-доставка";
    }
}
