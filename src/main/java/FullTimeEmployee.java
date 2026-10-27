/**
 * Похідний клас, що описує штатного працівника (успадковано від Employee).
 */
public class FullTimeEmployee extends Employee {
    private double annualBonus;

    /**
     * Конструктор штатного працівника.
     *
     * @param name            ПІБ працівника
     * @param position        Посада
     * @param salary          Заробітна плата (грн)
     * @param experienceYears Стаж роботи (років)
     * @param department      Відділ (enum)
     * @param annualBonus     Річна премія (грн)
     */
    public FullTimeEmployee(String name, String position, double salary, int experienceYears, Department department, double annualBonus) {
        super(name, position, salary, experienceYears, department);
        setAnnualBonus(annualBonus);
    }

    public double getAnnualBonus() {
        return annualBonus;
    }

    public void setAnnualBonus(double annualBonus) {
        if (annualBonus < 0) {
            throw new InvalidEmployeeDataException("Річна премія не може бути від'ємною.");
        }
        this.annualBonus = annualBonus;
    }

    @Override
    public String toString() {
        return String.format("[ШТАТНИЙ] %s | Річна премія: %.2f грн", super.toString(), annualBonus);
    }
}