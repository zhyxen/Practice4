import java.util.Objects;

/**
 * Клас, що описує працівника підприємства.
 * Містить атрибути: ПІБ, посаду, зарплату та стаж роботи з перевіркою коректності.
 */
public class Employee {
    private String name;
    private String position;
    private double salary;
    private int experienceYears;

    /**
     * Конструктор класу Employee.
     *
     * @param name            ПІБ працівника
     * @param position        Посада працівника
     * @param salary          Заробітна плата (грн)
     * @param experienceYears Стаж роботи (років)
     */
    public Employee(String name, String position, double salary, int experienceYears) {
        setName(name);
        setPosition(position);
        setSalary(salary);
        setExperienceYears(experienceYears);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidEmployeeDataException("ПІБ працівника не може бути порожнім.");
        }
        this.name = name.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        if (position == null || position.trim().isEmpty()) {
            throw new InvalidEmployeeDataException("Посада не може бути порожньою.");
        }
        this.position = position.trim();
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary <= 0) {
            throw new InvalidEmployeeDataException("Заробітна плата повинна бути більшою за 0.");
        }
        this.salary = salary;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        if (experienceYears < 0) {
            throw new InvalidEmployeeDataException("Стаж роботи не може бути від'ємним.");
        }
        this.experienceYears = experienceYears;
    }

    @Override
    public String toString() {
        return String.format("Працівник: %s | Посада: %s | Зарплата: %.2f грн | Стаж: %d років",
                name, position, salary, experienceYears);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Double.compare(employee.salary, salary) == 0 &&
                experienceYears == employee.experienceYears &&
                Objects.equals(name, employee.name) &&
                Objects.equals(position, employee.position);
    }
}
