import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, "UTF-8");
        ArrayList<Employee> employees = new ArrayList<>();

        System.out.print("Введіть кількість працівників: ");
        int count = 0;
        if (scanner.hasNextInt()) {
            count = scanner.nextInt();
            scanner.nextLine();
        }

        for (int step = 1; step <= count; step++) {
            System.out.println("\n--- Введення даних для працівника №" + step + " ---");
            
            System.out.print("ПІБ: ");
            String name = scanner.nextLine();

            System.out.print("Посада: ");
            String position = scanner.nextLine();

            System.out.print("Зарплата (грн): ");
            double salary = 0;
            if (scanner.hasNextDouble()) {
                salary = scanner.nextDouble();
                scanner.nextLine();
            }

            employees.add(new Employee(name, position, salary));
        }

        System.out.println("\n================ СПИСОК ПРАЦІВНИКІВ ================");
        for (Employee emp : employees) {
            System.out.println(emp);
        }

        scanner.close();
    }
}
