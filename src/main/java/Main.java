import java.util.List;
import java.util.Scanner;

/**
 * Головний клас-драйвер для ПР№11 із використанням класу-контейнера Company (агрегація).
 */
public class Main {
    private static final String FILE_NAME = "input.txt";
    private static final Scanner scanner = new Scanner(System.in, "UTF-8");
    private static Company company;

    public static void main(String[] args) {
        System.out.println("===== ЗАВАНТАЖЕННЯ ДАНИХ КОМПАНІЇ З ФАЙЛУ =====");
        company = FileManager.loadCompanyFromFile(FILE_NAME);

        boolean running = true;
        while (running) {
            System.out.println("\n===== ГОЛОВНЕ МЕНЮ (ПРАКТИЧНА №11: " + company.getName() + ") =====");
            System.out.println("1. Пошук об’єкта в компанії");
            System.out.println("2. Додати працівника (з урахуванням кількості)");
            System.out.println("3. Вивести інформацію про компанію та всіх працівників");
            System.out.println("4. Завершити роботу програми");
            System.out.print("Оберіть опцію (1-4): ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    searchMenu();
                    break;
                case "2":
                    createNewObjectMenu();
                    break;
                case "3":
                    displayCompanyInfo();
                    break;
                case "4":
                    running = false;
                    System.out.println("\n===== ЗБЕРЕЖЕННЯ ДАНИХ КОМПАНІЇ У ФАЙЛ =====");
                    FileManager.saveCompanyToFile(company, FILE_NAME);
                    System.out.println("Роботу програми завершено.");
                    break;
                default:
                    System.out.println("Помилка: Оберіть коректний пункт меню (1-4).");
            }
        }
    }

    // ================= ПІДМЕНЮ ПОШУКУ ЧЕРЕЗ КЛАС COMPANY =================

    private static void searchMenu() {
        boolean inSearch = true;
        while (inSearch) {
            System.out.println("\n--- ПІДМЕНЮ ПОШУКУ В КОМПАНІЇ «" + company.getName() + "» ---");
            System.out.println("1. Пошук за ПІБ (частковий або повний збіг)");
            System.out.println("2. Пошук за відділом");
            System.out.println("3. Пошук за мінімальною заробітною платою");
            System.out.println("0. Повернутися до головного меню");
            System.out.print("Оберіть критерій пошуку (0-3): ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    String nameQuery = readString("Введіть ПІБ або частину імені для пошуку: ");
                    List<Employee> byName = company.searchByName(nameQuery);
                    printSearchResults(byName);
                    break;
                case "2":
                    Department targetDept = chooseDepartment();
                    List<Employee> byDept = company.searchByDepartment(targetDept);
                    printSearchResults(byDept);
                    break;
                case "3":
                    double minSalary = readDouble("Введіть мінімальну заробітну плату (грн): ");
                    List<Employee> bySalary = company.searchByMinSalary(minSalary);
                    printSearchResults(bySalary);
                    break;
                case "0":
                    inSearch = false;
                    System.out.println("Повернення до головного меню...");
                    break;
                default:
                    System.out.println("Помилка: Некоректний вибір критерію пошуку.");
            }
        }
    }

    private static void printSearchResults(List<Employee> results) {
        System.out.println("\n--- РЕЗУЛЬТАТИ ПОШУКУ ---");
        if (results.isEmpty()) {
            System.out.println("Жоден об’єкт не відповідає умовам пошуку.");
        } else {
            for (int i = 0; i < results.size(); i++) {
                System.out.println((i + 1) + ". " + results.get(i));
            }
        }
    }

    // ================= ДОДАВАННЯ ПРАЦІВНИКІВ ЧЕРЕЗ addNewEmployee =================

    private static void createNewObjectMenu() {
        System.out.println("\n--- ОБРАННЯ ТИПУ НОВОГО ПРАЦІВНИКА ---");
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
        System.out.println("\n--- Додавання базового працівника ---");
        String name = readString("Введіть ПІБ: ");
        String position = readString("Введіть посаду: ");
        double salary = readDouble("Введіть заробітну плату (грн): ");
        int experience = readInt("Введіть стаж роботи (років): ");
        Department dept = chooseDepartment();
        int quantity = readPositiveInt("Введіть кількість (штатних одиниць): ");

        try {
            Employee emp = new Employee(name, position, salary, experience, dept);
            company.addNewEmployee(emp, quantity);
            System.out.println("Дані успішно додано/оновлено у компанії!");
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
        int quantity = readPositiveInt("Введіть кількість (штатних одиниць): ");

        try {
            ContractEmployee emp = new ContractEmployee(name, position, salary, experience, dept, contractMonths);
            company.addNewEmployee(emp, quantity);
            System.out.println("Дані успішно додано/оновлено у компанії!");
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
        int quantity = readPositiveInt("Введіть кількість (штатних одиниць): ");

        try {
            FullTimeEmployee emp = new FullTimeEmployee(name, position, salary, experience, dept, bonus);
            company.addNewEmployee(emp, quantity);
            System.out.println("Дані успішно додано/оновлено у компанії!");
        } catch (InvalidEmployeeDataException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void createInternEmployee() {
        System.out.println("\n--- Додавання стажера ---");
        String name = readString("Введіть ПІБ стажера: ");
        String position = readString("Введіть посаду: ");
        double salary = readDouble("Введіть стипендію/зарплату (грн): ");
        int experience = readInt("Введіть стаж роботи (років): ");
        Department dept = chooseDepartment();
        String mentor = readString("Введіть ПІБ ментора (наставника): ");
        int quantity = readPositiveInt("Введіть кількість (штатних одиниць): ");

        try {
            InternEmployee emp = new InternEmployee(name, position, salary, experience, dept, mentor);
            company.addNewEmployee(emp, quantity);
            System.out.println("Дані успішно додано/оновлено у компанії!");
        } catch (InvalidEmployeeDataException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void createExecutiveEmployee() {
        System.out.println("\n--- Додавання керівника ---");
        String name = readString("Введіть ПІБ керівника: ");
        String position = readString("Введіть посаду: ");
        double salary = readDouble("Введіть заробітну плату (грн): ");
        int experience = readInt("Введіть стаж роботи (років): ");
        Department dept = chooseDepartment();
        int stockOptions = readInt("Введіть кількість опціонів/акцій (шт.): ");
        int quantity = readPositiveInt("Введіть кількість (штатних одиниць): ");

        try {
            ExecutiveEmployee emp = new ExecutiveEmployee(name, position, salary, experience, dept, stockOptions);
            company.addNewEmployee(emp, quantity);
            System.out.println("Дані успішно додано/оновлено у компанії!");
        } catch (InvalidEmployeeDataException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void displayCompanyInfo() {
        System.out.println("\n================ ІНФОРМАЦІЯ ПРО КОМПАНІЮ ================");
        System.out.println("Назва компанії: " + company.getName());
        System.out.println("Місто / Розташування: " + company.getLocation());
        List<Employee> employees = company.getEmployees();
        List<Integer> quantities = company.getQuantities();

        if (employees.isEmpty()) {
            System.out.println("У компанії ще немає працівників.");
        } else {
            for (int i = 0; i < employees.size(); i++) {
                System.out.println((i + 1) + ". " + employees.get(i) + " | Кількість: " + quantities.get(i) + " шт.");
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
            String input = scanner.nextLine().trim().replace(',', '.');
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

    private static int readPositiveInt(String prompt) {
        while (true) {
            int val = readInt(prompt);
            if (val > 0) return val;
            System.out.println("Помилка: Кількість має бути більшою за 0.");
        }
    }
}