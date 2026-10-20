package delivery;

public class Client {
    private String name;
    private String phone;

    public Client(String name, String phone) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя клиента не может быть пустым.");
        }
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("Телефон не может быть пустым.");
        }
        this.name = name;
        this.phone = phone;
    }

    public String getName() { return name; }
    public String getPhone() { return phone; }

    @Override
    public String toString() {
        return "Клиент: " + name + " (" + phone + ")";
    }
}