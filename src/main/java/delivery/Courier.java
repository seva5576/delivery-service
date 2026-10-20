package delivery;

public class Courier {
    private String name;
    private String phone;
    private boolean isBusy;

    public Courier(String name, String phone) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя курьера не может быть пустым.");
        }
        this.name = name;
        this.phone = phone;
        this.isBusy = false; // По умолчанию новый курьер свободен
    }

    // Метод для назначения курьера на заказ
    public void assign() {
        if (isBusy) {
            throw new IllegalStateException("Курьер " + name + " уже занят.");
        }
        isBusy = true;
    }

    // Метод для освобождения курьера после доставки заказа
    public void release() {
        isBusy = false;
    }

    public boolean isBusy() { return isBusy; }
    public String getName() { return name; }
    public String getPhone() { return phone; }

    @Override
    public String toString() {
        String status = isBusy ? "занят" : "свободен";
        return "Курьер: " + name + " (" + phone + ") - " + status;
    }
}