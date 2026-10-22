import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Головний клас-драйвер із інтерактивним консольним меню.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in, "UTF-8");
    private static final List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n===== МЕНЮ УПРАВЛІННЯ ПРАЦІВНИКАМИ =====");
            System.out.println("1. Створити нового працівника");
            System.out.println("2. Вивести інформацію про всіх працівників");
            System.out.println("3. Завершити роботу");
            System.out.print("Оберіть пункт меню (1-3): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    createEmployee();
                    break;
                case "2":
                    displayEmployees();
                    break;
                case "3":
                    running = false;
                    System.out.println("Роботу програми завершено.");
                    break;
                default:
                    System.out.println("Помилка: Оберіть коректний пункт меню (1, 2 або 3).");
            }
        }
    }

    private static void createEmployee() {
        System.out.println("\n--- Створення нового працівника ---");

        String name = readString("Введіть ПІБ: ");
        String position = readString("Введіть посаду: ");
        double salary = readDouble("Введіть заробітну плату (грн): ");
        int experience = readInt("Введіть стаж роботи (років): ");

        try {
            Employee emp = new Employee(name, position, salary, experience);
            employees.add(emp);
            System.out.println("Працівника успішно додано!");
        } catch (InvalidEmployeeDataException e) {
            System.out.println("Помилка створення об'єкта: " + e.getMessage());
        }
    }

    private static void displayEmployees() {
        System.out.println("\n================ СПИСОК ПРАЦІВНИКІВ ================");
        if (employees.isEmpty()) {
            System.out.println("Список порожній. Спочатку додайте працівників через пункт 1.");
        } else {
            for (int i = 0; i < employees.size(); i++) {
                System.out.println((i + 1) + ". " + employees.get(i));
            }
        }
    }

    private static String readString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Помилка: Введення не може бути порожнім. Спробуйте ще раз.");
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Помилка: Введіть коректне число (наприклад, 25000 або 25000.50).");
            }
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Помилка: Введіть ціле число.");
            }
        }
    }
}