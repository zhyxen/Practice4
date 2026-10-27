import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Головний клас-драйвер із підтримкою успадкування, поліморфізму та колекції ArrayList.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in, "UTF-8");
    private static final List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n===== МЕНЮ УПРАВЛІННЯ ПРАЦІВНИКАМИ (ПОЛІМОРФІЗМ & ARRAYLIST) =====");
            System.out.println("1. Додати базового працівника (Employee)");
            System.out.println("2. Додати контрактного працівника (ContractEmployee)");
            System.out.println("3. Додати штатного працівника (FullTimeEmployee)");
            System.out.println("4. Вивести весь список працівників (демонстрація поліморфізму)");
            System.out.println("5. Завершити роботу");
            System.out.print("Оберіть пункт меню (1-5): ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    createBaseEmployee();
                    break;
                case "2":
                    createContractEmployee();
                    break;
                case "3":
                    createFullTimeEmployee();
                    break;
                case "4":
                    displayEmployees();
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

    private static void createBaseEmployee() {
        System.out.println("\n--- Додавання базового працівника ---");
        String name = readString("Введіть ПІБ: ");
        String position = readString("Введіть посаду: ");
        double salary = readDouble("Введіть заробітну плату (грн): ");
        int experience = readInt("Введіть стаж роботи (років): ");
        Department dept = chooseDepartment();

        try {
            Employee emp = new Employee(name, position, salary, experience, dept);
            employees.add(emp);
            System.out.println("Базового працівника успішно додано до колекції ArrayList!");
        } catch (InvalidEmployeeDataException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void createContractEmployee() {
        System.out.println("\n--- Додавання контрактного працівника ---");
        String name = readString("Введіть ПІБ: ");
        String position = readString("Введіть посаду: ");
        double salary = readDouble("Введіть заробітну плату (грн): ");
        int experience = readInt("Введіть стаж роботи (років): ");
        Department dept = chooseDepartment();
        int contractMonths = readInt("Введіть тривалість контракту (місяців): ");

        try {
            ContractEmployee emp = new ContractEmployee(name, position, salary, experience, dept, contractMonths);
            employees.add(emp);
            System.out.println("Контрактного працівника успішно додано до колекції ArrayList!");
        } catch (InvalidEmployeeDataException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void createFullTimeEmployee() {
        System.out.println("\n--- Додавання штатного працівника ---");
        String name = readString("Введіть ПІБ: ");
        String position = readString("Введіть посаду: ");
        double salary = readDouble("Введіть заробітну плату (грн): ");
        int experience = readInt("Введіть стаж роботи (років): ");
        Department dept = chooseDepartment();
        double bonus = readDouble("Введіть розмір річної премії (грн): ");

        try {
            FullTimeEmployee emp = new FullTimeEmployee(name, position, salary, experience, dept, bonus);
            employees.add(emp);
            System.out.println("Штатного працівника успішно додано до колекції ArrayList!");
        } catch (InvalidEmployeeDataException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void displayEmployees() {
        System.out.println("\n================ СПИСОК ПРАЦІВНИКІВ (ПОЛІМОРФНЕ ВИВЕДЕННЯ) ================");
        if (employees.isEmpty()) {
            System.out.println("Список порожній.");
        } else {
            for (int i = 0; i < employees.size(); i++) {
                System.out.println((i + 1) + ". " + employees.get(i));
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