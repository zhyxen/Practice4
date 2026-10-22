import java.util.Objects;

/**
 * Клас, що описує працівника підприємства.
 * Містить атрибути: ПІБ, посаду, зарплату та стаж роботи.
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
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.experienceYears = experienceYears;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
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
