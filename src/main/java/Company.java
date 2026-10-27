import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що агрегує список об'єктів Employee.
 */
public class Company {
    private String name;
    private final List<Employee> employees;

    /**
     * Конструктор компанії.
     *
     * @param name Назва компанії
     */
    public Company(String name) {
        setName(name);
        this.employees = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidEmployeeDataException("Назва компанії не може бути порожньою.");
        }
        this.name = name.trim();
    }

    public void addEmployee(Employee employee) {
        if (employee == null) {
            throw new InvalidEmployeeDataException("Неможливо додати порожнього працівника.");
        }
        this.employees.add(employee);
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }

    public int getEmployeeCount() {
        return employees.size();
    }
}