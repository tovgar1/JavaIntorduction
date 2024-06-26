import java.util.*;

class Laptop {
    private int ram; // ОЗУ в ГБ
    private int hdd; // Объем ЖД в ГБ
    private String os; // Операционная система
    private String color; // Цвет

    public Laptop(int ram, int hdd, String os, String color) {
        this.ram = ram;
        this.hdd = hdd;
        this.os = os;
        this.color = color;
    }

    public int getRam() {
        return ram;
    }

    public int getHdd() {
        return hdd;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "ram=" + ram + "gb" +
                ", hdd=" + hdd +
                "gb, os='" + os + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}

public class LaptopStore {
    public static void main(String[] args) {
        Set<Laptop> laptops = new HashSet<>(Arrays.asList(
                new Laptop(8, 256, "Windows", "Black"),
                new Laptop(16, 512, "Windows", "Silver"),
                new Laptop(8, 256, "MacOS", "Gray"),
                new Laptop(16, 1024, "Linux", "Black"),
                new Laptop(32, 2048, "Windows", "White")
        ));

        filterLaptops(laptops);
    }

    public static void filterLaptops(Set<Laptop> laptops) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Object> filters = new HashMap<>();

        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");

        int criterion = scanner.nextInt();
        scanner.nextLine(); // Очистить буфер

        switch (criterion) {
            case 1:
                System.out.println("Введите минимальный объем ОЗУ (в ГБ):");
                filters.put("ram", scanner.nextInt());
                break;
            case 2:
                System.out.println("Введите минимальный объем ЖД (в ГБ):");
                filters.put("hdd", scanner.nextInt());
                break;
            case 3:
                System.out.println("Введите операционную систему:");
                filters.put("os", scanner.nextLine());
                break;
            case 4:
                System.out.println("Введите цвет:");
                filters.put("color", scanner.nextLine());
                break;
            default:
                System.out.println("Некорректный критерий.");
                return;
        }

        Set<Laptop> filteredLaptops = filterByCriteria(laptops, filters);
        System.out.println("Ноутбуки, подходящие под критерии:");
        for (Laptop laptop : filteredLaptops) {
            System.out.println(laptop);
        }
    }

    public static Set<Laptop> filterByCriteria(Set<Laptop> laptops, Map<String, Object> filters) {
        Set<Laptop> result = new HashSet<>();
        for (Laptop laptop : laptops) {
            boolean matches = true;
            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                switch (key) {
                    case "ram":
                        if (laptop.getRam() < (int) value) matches = false;
                        break;
                    case "hdd":
                        if (laptop.getHdd() < (int) value) matches = false;
                        break;
                    case "os":
                        if (!laptop.getOs().equalsIgnoreCase((String) value)) matches = false;
                        break;
                    case "color":
                        if (!laptop.getColor().equalsIgnoreCase((String) value)) matches = false;
                        break;
                }
                if (!matches) break;
            }
            if (matches) result.add(laptop);
        }
        return result;
    }
}