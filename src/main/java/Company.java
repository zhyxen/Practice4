import java.util.ArrayList;
import java.util.List;

/**
 * Клас-контейнер Company (агрегація), що зберігає колекцію працівників та їх кількість.
 */
public class Company {
    private String name;
    private String location;
    private List<Employee> employees;
    private List<Integer> quantities;

    public Company(String name, String location) {
        this.name = name;
        this.location = location;
        this.employees = new ArrayList<>();
        this.quantities = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Integer> getQuantities() {
        return quantities;
    }

    /**
     * Додає працівника з урахуванням кількості.
     * Якщо такий об'єкт вже існує в колекції, збільшує його кількість.
     */
    public void addNewEmployee(Employee emp, int quantity) {
        if (emp == null || quantity <= 0) {
            return;
        }
        for (int i = 0; i < employees.size(); i++) {
            Employee existing = employees.get(i);
            if (isSameEmployee(existing, emp)) {
                Integer currentQty = quantities.get(i);
                quantities.set(i, Integer.valueOf(currentQty.intValue() + quantity));
                return;
            }
        }
        employees.add(emp);
        quantities.add(Integer.valueOf(quantity));
    }

    private boolean isSameEmployee(Employee a, Employee b) {
        return a.getClass().equals(b.getClass())
                && a.getName().equalsIgnoreCase(b.getName())
                && a.getPosition().equalsIgnoreCase(b.getPosition())
                && Double.compare(a.getSalary(), b.getSalary()) == 0
                && a.getDepartment() == b.getDepartment();
    }

    // ================= МЕТОДИ ПОШУКУ (ПЕРЕНЕСЕНІ З ПР №10) =================

    public List<Employee> searchByName(String query) {
        List<Employee> results = new ArrayList<>();
        String lowerQuery = query.toLowerCase();
        for (int i = 0; i < employees.size(); i++) {
            Employee emp = employees.get(i);
            if (emp.getName().toLowerCase().contains(lowerQuery)) {
                results.add(emp);
            }
        }
        return results;
    }

    public List<Employee> searchByDepartment(Department targetDept) {
        List<Employee> results = new ArrayList<>();
        for (int i = 0; i < employees.size(); i++) {
            Employee emp = employees.get(i);
            if (emp.getDepartment() == targetDept) {
                results.add(emp);
            }
        }
        return results;
    }

    public List<Employee> searchByMinSalary(double minSalary) {
        List<Employee> results = new ArrayList<>();
        for (int i = 0; i < employees.size(); i++) {
            Employee emp = employees.get(i);
            if (emp.getSalary() >= minSalary) {
                results.add(emp);
            }
        }
        return results;
    }
}