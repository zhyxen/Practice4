import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Головний клас-драйвер для ПР№8 з підтримкою розширеної 5-класової ієрархії.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in, "UTF-8");
    private static final List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n===== ГОЛОВНЕ МЕНЮ (ПРАКТИЧНА №8) =====");
            System.out.println("1. Створити новий об’єкт");
            System.out.println("2. Вивести інформацію про всі об’єкти");
            System.out.println("3. Завершити роботу програми");
            System.out.print("Оберіть опцію (1-3): ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    createNewObjectMenu();
                    break;
                case "2":
                    displayEmployees();
                    break;
                case "3":
                    running = false;
                    System.out.println("Роботу програми завершено.");
                    break;
                default:
                    System.out.println("Помилка: Оберіть коректний пункт меню (1-3).");
            }
        }
    }

    private static void createNewObjectMenu() {
        System.out.println("\n--- ОБРАННЯ ТИПУ НОВОГО ОБ'ЄКТА ---");
        System.out.println("1. Базовий працівник (Employee)");
        System.out.println("2. Контрактний працівник (ContractEmployee)");
        System.out.println("3. Штатний працівник (FullTimeEmployee)");
        System.out.println("4. Стажер (InternEmployee)");
        System.out.println("5. Керівник (ExecutiveEmployee)");
        System.out.println("0. Повернутися до головного меню");
        System.out.print("Оберіть тип об'єкта (0-5): ");

        String typeChoice = scanner.nextLine().trim();
        switch (typeChoice) {
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
                createInternEmployee();
                break;
            case "5":
                createExecutiveEmployee();
                break;
            case "0":
                System.out.println("Повернення до головного меню...");
                break;
            default:
                System.out.println("Помилка: Некоректний вибір типу об'єкта.");
        }
    }

    private static void createBaseEmployee() {
        System.out.println("\n--- Створення базового працівника ---");
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
        System.out.println("\n--- Створення контрактного працівника ---");
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
        System.out.println("\n--- Створення штатного працівника ---");
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

    private static void createInternEmployee() {
        System.out.println("\n--- Створення стажера ---");
        String name = readString("Введіть ПІБ стажера: ");
        String position = readString("Введіть посаду: ");
        double salary = readDouble("Введіть стипендію/зарплату (грн): ");
        int experience = readInt("Введіть стаж роботи (років): ");
        Department dept = chooseDepartment();
        String mentor = readString("Введіть ПІБ ментора (наставника): ");

        try {
            InternEmployee emp = new InternEmployee(name, position, salary, experience, dept, mentor);
            employees.add(emp);
            System.out.println("Стажера успішно додано до колекції ArrayList!");
        } catch (InvalidEmployeeDataException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void createExecutiveEmployee() {
        System.out.println("\n--- Створення керівника ---");
        String name = readString("Введіть ПІБ керівника: ");
        String position = readString("Введіть посаду: ");
        double salary = readDouble("Введіть заробітну плату (грн): ");
        int experience = readInt("Введіть стаж роботи (років): ");
        Department dept = chooseDepartment();
        int stockOptions = readInt("Введіть кількість опціонів/акцій (шт.): ");

        try {
            ExecutiveEmployee emp = new ExecutiveEmployee(name, position, salary, experience, dept, stockOptions);
            employees.add(emp);
            System.out.println("Керівника успішно додано до колекції ArrayList!");
        } catch (InvalidEmployeeDataException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void displayEmployees() {
        System.out.println("\n================ ВМІСТ КОЛЕКЦІЇ ARRAYLIST ================");
        if (employees.isEmpty()) {
            System.out.println("Колекція порожня.");
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
            if (!input.isEmpty()) return input;
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