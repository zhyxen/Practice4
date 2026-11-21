import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

/**
 * Клас для збереження та завантаження об'єкта Company та його колекції працівників з/у файл.
 */
public class FileManager {

    public static Company loadCompanyFromFile(String fileName) {
        Company company = new Company("TechSolutions Ukraine", "Суми");
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("Файл " + fileName + " не знайдено. Створено нову компанію за замовчуванням.");
            return company;
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {

            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                try {
                    String[] parts = line.split(";");
                    String recordType = parts[0].trim().toUpperCase();

                    if ("COMPANY".equals(recordType)) {
                        if (parts.length >= 3) {
                            company.setName(parts[1].trim());
                            company.setLocation(parts[2].trim());
                        }
                    } else {
                        Employee emp = parseEmployeeFromParts(parts);
                        int quantity = parseQuantityFromParts(parts);
                        if (emp != null) {
                            company.addNewEmployee(emp, quantity);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Помилка зчитування у рядку " + lineNumber + ": " + e.getMessage());
                }
            }
            System.out.println("Успішно завантажено компанію «" + company.getName() + "» (" + company.getLocation()
                    + "), позицій у штаті: " + company.getEmployees().size());
        } catch (Exception e) {
            System.out.println("Помилка під час відкриття файлу: " + e.getMessage());
        }

        return company;
    }

    public static void saveCompanyToFile(Company company, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8))) {

            writer.write(String.format("COMPANY;%s;%s", company.getName(), company.getLocation()));
            writer.newLine();

            List<Employee> employees = company.getEmployees();
            List<Integer> quantities = company.getQuantities();

            for (int i = 0; i < employees.size(); i++) {
                Employee emp = employees.get(i);
                Integer qty = quantities.get(i);
                String line = formatEmployeeToLine(emp, qty.intValue());
                if (line != null) {
                    writer.write(line);
                    writer.newLine();
                }
            }
            System.out.println("Дані компанії «" + company.getName() + "» успішно збережено у файл " + fileName + ".");
        } catch (Exception e) {
            System.out.println("Помилка під час запису у файл: " + e.getMessage());
        }
    }

    private static Employee parseEmployeeFromParts(String[] parts) {
        if (parts.length < 7) {
            throw new IllegalArgumentException("Недостатньо параметрів у рядку");
        }

        String type = parts[0].trim().toUpperCase();
        String name = parts[1].trim();
        String position = parts[2].trim();
        double salary = Double.parseDouble(parts[3].trim().replace(',', '.'));
        int experience = Integer.parseInt(parts[4].trim());
        Department department = Department.valueOf(parts[5].trim().toUpperCase());

        switch (type) {
            case "EMPLOYEE":
                return new Employee(name, position, salary, experience, department);
            case "CONTRACT":
                if (parts.length < 8) throw new IllegalArgumentException("Відсутня тривалість контракту");
                int months = Integer.parseInt(parts[6].trim());
                return new ContractEmployee(name, position, salary, experience, department, months);
            case "FULLTIME":
                if (parts.length < 8) throw new IllegalArgumentException("Відсутній розмір премії");
                double bonus = Double.parseDouble(parts[6].trim().replace(',', '.'));
                return new FullTimeEmployee(name, position, salary, experience, department, bonus);
            case "INTERN":
                if (parts.length < 8) throw new IllegalArgumentException("Відсутній ПІБ ментора");
                String mentor = parts[6].trim();
                return new InternEmployee(name, position, salary, experience, department, mentor);
            case "EXECUTIVE":
                if (parts.length < 8) throw new IllegalArgumentException("Відсутня кількість акцій");
                int stockOptions = Integer.parseInt(parts[6].trim());
                return new ExecutiveEmployee(name, position, salary, experience, department, stockOptions);
            default:
                throw new IllegalArgumentException("Невідомий тип об'єкта: " + type);
        }
    }

    private static int parseQuantityFromParts(String[] parts) {
        String type = parts[0].trim().toUpperCase();
        if ("EMPLOYEE".equals(type)) {
            return Integer.parseInt(parts[6].trim());
        } else {
            return Integer.parseInt(parts[7].trim());
        }
    }

    private static String formatEmployeeToLine(Employee emp, int quantity) {
        if (emp instanceof ContractEmployee) {
            ContractEmployee c = (ContractEmployee) emp;
            return String.format(Locale.US, "CONTRACT;%s;%s;%.2f;%d;%s;%d;%d",
                    c.getName(), c.getPosition(), c.getSalary(), c.getExperienceYears(), c.getDepartment().name(), c.getContractMonths(), quantity);
        } else if (emp instanceof FullTimeEmployee) {
            FullTimeEmployee f = (FullTimeEmployee) emp;
            return String.format(Locale.US, "FULLTIME;%s;%s;%.2f;%d;%s;%.2f;%d",
                    f.getName(), f.getPosition(), f.getSalary(), f.getExperienceYears(), f.getDepartment().name(), f.getAnnualBonus(), quantity);
        } else if (emp instanceof InternEmployee) {
            InternEmployee i = (InternEmployee) emp;
            return String.format(Locale.US, "INTERN;%s;%s;%.2f;%d;%s;%s;%d",
                    i.getName(), i.getPosition(), i.getSalary(), i.getExperienceYears(), i.getDepartment().name(), i.getMentorName(), quantity);
        } else if (emp instanceof ExecutiveEmployee) {
            ExecutiveEmployee e = (ExecutiveEmployee) emp;
            return String.format(Locale.US, "EXECUTIVE;%s;%s;%.2f;%d;%s;%d;%d",
                    e.getName(), e.getPosition(), e.getSalary(), e.getExperienceYears(), e.getDepartment().name(), e.getStockOptionsCount(), quantity);
        } else if (emp != null) {
            return String.format(Locale.US, "EMPLOYEE;%s;%s;%.2f;%d;%s;%d",
                    emp.getName(), emp.getPosition(), emp.getSalary(), emp.getExperienceYears(), emp.getDepartment().name(), quantity);
        }
        return null;
    }
}