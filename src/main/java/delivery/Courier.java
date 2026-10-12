package delivery;

public class Courier {
    private String name;
    private String phone;
    private boolean isBusy; // true = занят, false = свободен

    public Courier(String name, String phone) {
        this.name = name;
        this.phone = phone;
        this.isBusy = false; // по умолчанию курьер свободен
    }

    // Метод, чтобы назначить курьера на заказ
    public void assign() {
        if (isBusy) {
            System.out.println("⚠️ Ошибка: Курьер " + name + " уже занят!");
        } else {
            isBusy = true;
            System.out.println("✅ Курьер " + name + " назначен на заказ.");
        }
    }

    // Метод, чтобы освободить курьера после доставки
    public void release() {
        isBusy = false;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        String status = isBusy ? "занят" : "свободен";
        return "Курьер: " + name + " (" + phone + ") - " + status;
    }
}