import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас для збереження та завантаження об'єктів ієрархії Employee з/у текстовий файл.
 */
public class FileManager {

    /**
     * Зчитує список працівників з текстового файлу.
     *
     * @param fileName Шлях до файлу
     * @return Список зчитаних об'єктів
     */
    public static List<Employee> loadFromFile(String fileName) {
        List<Employee> employees = new ArrayList<>();
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("Файл " + fileName + " не знайдено. Буде створено новий при збереженні.");
            return employees;
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
                    Employee emp = parseEmployeeFromLine(line);
                    if (emp != null) {
                        employees.add(emp);
                    }
                } catch (Exception e) {
                    System.out.println("Помилка зчитування у рядку " + lineNumber + ": " + e.getMessage());
                }
            }
            System.out.println("Успішно завантажено " + employees.size() + " об'єктів з файлу " + fileName);
        } catch (Exception e) {
            System.out.println("Помилка під час відкриття файлу: " + e.getMessage());
        }

        return employees;
    }

    /**
     * Записує список працівників у текстовий файл.
     *
     * @param employees Список об'єктів для збереження
     * @param fileName  Шлях до файлу
     */
    public static void saveToFile(List<Employee> employees, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8))) {
            
            for (Employee emp : employees) {
                String line = formatEmployeeToLine(emp);
                if (line != null) {
                    writer.write(line);
                    writer.newLine();
                }
            }
            System.out.println("Дані успішно збережено у файл " + fileName + " (" + employees.size() + " записів).");
        } catch (Exception e) {
            System.out.println("Помилка під час запису у файл: " + e.getMessage());
        }
    }

    private static Employee parseEmployeeFromLine(String line) {
        String[] parts = line.split(";");
        if (parts.length < 6) {
            throw new IllegalArgumentException("Недостатньо параметрів у рядку");
        }

        String type = parts[0].trim().toUpperCase();
        String name = parts[1].trim();
        String position = parts[2].trim();
        double salary = Double.parseDouble(parts[3].trim());
        int experience = Integer.parseInt(parts[4].trim());
        Department department = Department.valueOf(parts[5].trim().toUpperCase());

        switch (type) {
            case "EMPLOYEE":
                return new Employee(name, position, salary, experience, department);
            case "CONTRACT":
                if (parts.length < 7) throw new IllegalArgumentException("Відсутня тривалість контракту");
                int months = Integer.parseInt(parts[6].trim());
                return new ContractEmployee(name, position, salary, experience, department, months);
            case "FULLTIME":
                if (parts.length < 7) throw new IllegalArgumentException("Відсутній розмір премії");
                double bonus = Double.parseDouble(parts[6].trim());
                return new FullTimeEmployee(name, position, salary, experience, department, bonus);
            case "INTERN":
                if (parts.length < 7) throw new IllegalArgumentException("Відсутній ПІБ ментора");
                String mentor = parts[6].trim();
                return new InternEmployee(name, position, salary, experience, department, mentor);
            case "EXECUTIVE":
                if (parts.length < 7) throw new IllegalArgumentException("Відсутня кількість акцій");
                int stockOptions = Integer.parseInt(parts[6].trim());
                return new ExecutiveEmployee(name, position, salary, experience, department, stockOptions);
            default:
                throw new IllegalArgumentException("Невідомий тип об'єкта: " + type);
        }
    }

    private static String formatEmployeeToLine(Employee emp) {
        if (emp instanceof ContractEmployee) {
            ContractEmployee c = (ContractEmployee) emp;
            return String.format("CONTRACT;%s;%s;%.2f;%d;%s;%d",
                    c.getName(), c.getPosition(), c.getSalary(), c.getExperienceYears(), c.getDepartment().name(), c.getContractMonths());
        } else if (emp instanceof FullTimeEmployee) {
            FullTimeEmployee f = (FullTimeEmployee) emp;
            return String.format("FULLTIME;%s;%s;%.2f;%d;%s;%.2f",
                    f.getName(), f.getPosition(), f.getSalary(), f.getExperienceYears(), f.getDepartment().name(), f.getAnnualBonus());
        } else if (emp instanceof InternEmployee) {
            InternEmployee i = (InternEmployee) emp;
            return String.format("INTERN;%s;%s;%.2f;%d;%s;%s",
                    i.getName(), i.getPosition(), i.getSalary(), i.getExperienceYears(), i.getDepartment().name(), i.getMentorName());
        } else if (emp instanceof ExecutiveEmployee) {
            ExecutiveEmployee e = (ExecutiveEmployee) emp;
            return String.format("EXECUTIVE;%s;%s;%.2f;%d;%s;%d",
                    e.getName(), e.getPosition(), e.getSalary(), e.getExperienceYears(), e.getDepartment().name(), e.getStockOptionsCount());
        } else if (emp != null) {
            return String.format("EMPLOYEE;%s;%s;%.2f;%d;%s",
                    emp.getName(), emp.getPosition(), emp.getSalary(), emp.getExperienceYears(), emp.getDepartment().name());
        }
        return null;
    }
}