import java.util.Scanner;

// Суперклас: Транспортний засіб
class TransportVehicle {
    protected String name;
    protected int maxSpeed;      // км/год
    protected int capacity;      // кількість пасажирів

    public TransportVehicle(String name, int maxSpeed, int capacity) {
        this.name = name;
        this.maxSpeed = maxSpeed;
        this.capacity = capacity;
    }

    public void Show() {
        System.out.println("Транспортний засіб: " + name
                + ", макс. швидкість: " + maxSpeed + " км/год"
                + ", пасажиромісткість: " + capacity);
    }
}

// Підклас: Автомобіль
class Car extends TransportVehicle {
    private String brand;
    private double enginePower;  // к.с.

    public Car(String name, int maxSpeed, int capacity, String brand, double enginePower) {
        super(name, maxSpeed, capacity);
        this.brand = brand;
        this.enginePower = enginePower;
    }

    @Override
    public void Show() {
        System.out.println("Автомобіль: " + name
                + ", марка: " + brand
                + ", потужність двигуна: " + enginePower + " к.с."
                + ", макс. швидкість: " + maxSpeed + " км/год"
                + ", пасажиромісткість: " + capacity);
    }
}

// Підклас: Поїзд
class Train extends TransportVehicle {
    protected int wagonCount;
    protected String route;

    public Train(String name, int maxSpeed, int capacity, int wagonCount, String route) {
        super(name, maxSpeed, capacity);
        this.wagonCount = wagonCount;
        this.route = route;
    }

    @Override
    public void Show() {
        System.out.println("Поїзд: " + name
                + ", кількість вагонів: " + wagonCount
                + ", маршрут: " + route
                + ", макс. швидкість: " + maxSpeed + " км/год"
                + ", пасажиромісткість: " + capacity);
    }
}

// Підклас підкласу: Експрес (похідний від Поїзда)
class Express extends Train {
    private int stopsCount;
    private boolean boardingByTicketOnly;

    public Express(String name, int maxSpeed, int capacity, int wagonCount, String route,
                   int stopsCount, boolean boardingByTicketOnly) {
        super(name, maxSpeed, capacity, wagonCount, route);
        this.stopsCount = stopsCount;
        this.boardingByTicketOnly = boardingByTicketOnly;
    }

    @Override
    public void Show() {
        System.out.println("Експрес: " + name
                + ", кількість вагонів: " + wagonCount
                + ", маршрут: " + route
                + ", кількість зупинок: " + stopsCount
                + ", посадка тільки за квитками: " + (boardingByTicketOnly ? "так" : "ні")
                + ", макс. швидкість: " + maxSpeed + " км/год"
                + ", пасажиромісткість: " + capacity);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введіть кількість об'єктів: ");
        int n = sc.nextInt();
        sc.nextLine();

        // Масив типу суперкласу
        TransportVehicle[] arr = new TransportVehicle[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nОб'єкт №" + (i + 1));
            System.out.println("Оберіть тип: 1 - Транспортний засіб, 2 - Автомобіль, 3 - Поїзд, 4 - Експрес");
            int type = sc.nextInt();
            sc.nextLine();

            System.out.print("Назва: ");
            String name = sc.nextLine();
            System.out.print("Максимальна швидкість (км/год): ");
            int speed = sc.nextInt();
            System.out.print("Пасажиромісткість: ");
            int cap = sc.nextInt();
            sc.nextLine();

            switch (type) {
                case 2:
                    System.out.print("Марка: ");
                    String brand = sc.nextLine();
                    System.out.print("Потужність двигуна (к.с.): ");
                    double power = sc.nextDouble();
                    sc.nextLine();
                    arr[i] = new Car(name, speed, cap, brand, power);
                    break;
                case 3:
                    System.out.print("Кількість вагонів: ");
                    int wagons = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Маршрут: ");
                    String route = sc.nextLine();
                    arr[i] = new Train(name, speed, cap, wagons, route);
                    break;
                case 4:
                    System.out.print("Кількість вагонів: ");
                    int w = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Маршрут: ");
                    String r = sc.nextLine();
                    System.out.print("Кількість зупинок: ");
                    int stops = sc.nextInt();
                    System.out.print("Посадка тільки за квитками (true/false): ");
                    boolean ticket = sc.nextBoolean();
                    sc.nextLine();
                    arr[i] = new Express(name, speed, cap, w, r, stops, ticket);
                    break;
                default:
                    arr[i] = new TransportVehicle(name, speed, cap);
            }
        }

        System.out.println("\n===== Введені дані =====");
        for (TransportVehicle t : arr) {
            t.Show();   // поліморфний виклик
        }

        sc.close();
    }
}