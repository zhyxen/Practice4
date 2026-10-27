import java.util.Scanner;

/**
 * Головний клас-драйвер із підтримкою агрегації, enum та статичних лічильників.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in, "UTF-8");
    private static final Company company = new Company("Tech Solutions");

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n===== МЕНЮ УПРАВЛІННЯ КОМПАНІЄЮ: " + company.getName() + " =====");
            System.out.println("1. Створити нового працівника");
            System.out.println("2. Створити копію працівника (конструктор копіювання)");
            System.out.println("3. Вивести список усіх працівників компанії");
            System.out.println("4. Показати кількість створених об'єктів (статичний лічильник)");
            System.out.println("5. Завершити роботу");
            System.out.print("Оберіть пункт меню (1-5): ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    createEmployee();
                    break;
                case "2":
                    copyEmployee();
                    break;
                case "3":
                    displayEmployees();
                    break;
                case "4":
                    System.out.println("\n[СТАТИКА] Всього створено об'єктів Employee: " + Employee.getCount());
                    break;
                case "5":
                    running = false;
                    System.out.println("Роботу програми завершено.");
                    break;
                default:
                    System.out.println("Помилка: Оберіть коректний пункт меню (1-5).");
            }
        }
    }

    private static void createEmployee() {
        System.out.println("\n--- Створення нового працівника ---");
        String name = readString("Введіть ПІБ: ");
        String position = readString("Введіть посаду: ");
        double salary = readDouble("Введіть заробітну плату (грн): ");
        int experience = readInt("Введіть стаж роботи (років): ");
        Department dept = chooseDepartment();

        try {
            Employee emp = new Employee(name, position, salary, experience, dept);
            company.addEmployee(emp);
            System.out.println("Працівника успішно додано до компанії!");
        } catch (InvalidEmployeeDataException e) {
            System.out.println("Помилка створення об'єкта: " + e.getMessage());
        }
    }

    private static void copyEmployee() {
        if (company.getEmployeeCount() == 0) {
            System.out.println("\nСписок порожній. Спочатку додайте хоча б одного працівника.");
            return;
        }

        displayEmployees();
        int index = readInt("Введіть номер працівника для копіювання: ") - 1;

        if (index >= 0 && index < company.getEmployeeCount()) {
            Employee original = company.getEmployees().get(index);
            Employee copy = new Employee(original);
            copy.setName(original.getName() + " (Копія)");
            company.addEmployee(copy);
            System.out.println("Успішно створено копію об'єкта за допомогою конструктора копіювання!");
        } else {
            System.out.println("Некоректний номер у списку.");
        }
    }

    private static void displayEmployees() {
        System.out.println("\n================ СПИСОК ПРАЦІВНИКІВ КОМПАНІЇ ================");
        if (company.getEmployeeCount() == 0) {
            System.out.println("Список порожній.");
        } else {
            for (int i = 0; i < company.getEmployees().size(); i++) {
                System.out.println((i + 1) + ". " + company.getEmployees().get(i));
            }
        }
    }

    private static Department chooseDepartment() {
        System.out.println("Оберіть відділ:");
        Department[] departments = Department.values();
        for (int i = 0; i < departments.length; i++) {
            System.out.println((i + 1) + ". " + departments[i].getDisplayName());
        }
        while (true) {
            int choice = readInt("Пункт відділу (1-" + departments.length + "): ");
            if (choice >= 1 && choice <= departments.length) {
                return departments[choice - 1];
            }
            System.out.println("Помилка: Некоректний вибір відділу.");
        }
    }

    private static String readString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Помилка: Введення не може бути порожнім.");
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Помилка: Введіть коректне число.");
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